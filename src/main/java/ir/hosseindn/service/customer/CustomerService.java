package ir.hosseindn.service.customer;

import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Customer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Wallet;
import ir.hosseindn.model.enums.OrderStatus;
import ir.hosseindn.model.enums.Role;
import ir.hosseindn.repository.customer.CustomerRepository;
import ir.hosseindn.service.confirmationservice.ConfirmationTokenService;
import ir.hosseindn.service.user.UserService;
import ir.hosseindn.utility.CustomValidations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;

    @Autowired
    EntityManager entityManager;

    public Customer register(Customer customer) {
        if (CustomValidations.isNotValidIranianNationalCode(customer.getNationalCode()))
            throw new NotValidInformation("National Code is Not valid");
        if (userService.isExistEmail(customer.getEmail()))
            throw new DuplicateInformationException("A USER with this Email exist.");
        customer.setRegisteredDate(LocalDateTime.now());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(Role.ROLE_CUSTOMER);
        customer.setEnabled(false);
        customer.setLocked(false);
        customer.setWallet(new Wallet(0L));
        Customer savedCustomer = customerRepository.save(customer);
        confirmationTokenService.generateConfirmationToken(customer);
        return savedCustomer;
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByEmail(username).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new NotValidInformation(String.format("Customer by id %s not found", id))
        );
    }


    public Customer changePassword(String email, String newPassword, String confirmPassword) {
        if (!Objects.equals(newPassword, confirmPassword))
            throw new NotValidInformation("new password must be match by confirm");
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Customer with email :" + email + " Not found.")
        );
        customerRepository.updatePassword(email, newPassword);
        return customer;
    }

    public Customer login(String email, String Password) {
        return customerRepository.findByEmailAndPassword(email, Password).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }

    public Long getWalletAmount(String customerEmail) {
        return findByUsername(customerEmail).getWallet().getAmount();
    }

    public List<Customer> findByCriteria(UserCriteriaItems userCriteriaItems) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> customerQuery = builder.createQuery(Customer.class);
        Root<Customer> root = customerQuery.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();
        if (userCriteriaItems.id() != null)
            predicates.add(builder.equal(root.get("id"), userCriteriaItems.id()));
        if (userCriteriaItems.email() != null)
            predicates.add(builder.equal(root.get("email"), userCriteriaItems.email()));
        if (userCriteriaItems.firstName() != null)
            predicates.add(builder.equal(root.get("firstName"), userCriteriaItems.firstName()));
        if (userCriteriaItems.lastName() != null)
            predicates.add(builder.equal(root.get("lastName"), userCriteriaItems.lastName()));
        if (userCriteriaItems.nationalCode() != null)
            predicates.add(builder.equal(root.get("nationalCode"), userCriteriaItems.nationalCode()));
        if (userCriteriaItems.password() != null)
            predicates.add(builder.equal(root.get("password"), userCriteriaItems.password()));
        if (userCriteriaItems.registeredDateStart() != null)
            predicates.add(builder.greaterThanOrEqualTo(root.get("registeredDate"), userCriteriaItems.registeredDateStart()));
        if (userCriteriaItems.registeredDateEnd() != null)
            predicates.add(builder.lessThanOrEqualTo(root.get("registeredDate"), userCriteriaItems.registeredDateEnd()));
        Join<Customer, Wallet> customerWalletJoin = root.join("wallet", JoinType.INNER);
        if (userCriteriaItems.walletId() != null)
            predicates.add(builder.equal(customerWalletJoin.get("id"), userCriteriaItems.walletId()));

        customerQuery.where(builder.and(predicates.toArray(new Predicate[]{})));
        List<Customer> list1 = entityManager.createQuery(customerQuery).getResultList();
        List<Customer> list2 = new ArrayList<>();
        if (userCriteriaItems.countRequests() != null) {
            CriteriaQuery<Object[]> orderQuery = builder.createQuery(Object[].class);
            Root<Order> rootOrder = orderQuery.from(Order.class);
            orderQuery.multiselect(
                    rootOrder.get("customer"),
                    builder.count(rootOrder)
            );
            orderQuery.groupBy(rootOrder.get("customer"));
            orderQuery.having(builder.gt(builder.count(rootOrder), userCriteriaItems.countRequests()));
            List<Object[]> results = entityManager.createQuery(orderQuery).getResultList();
            for (Object[] result : results) {
                Customer customer = (Customer) result[0];
                list2.add(customer);
            }
            list1.retainAll(list2);
            return list1;
        }
        if (userCriteriaItems.countDoneOrders() != null) {
            CriteriaQuery<Object[]> orderQuery = builder.createQuery(Object[].class);
            Root<Order> rootOrder = orderQuery.from(Order.class);
            Predicate orderStatusPredicate = builder.equal(rootOrder.get("orderStatus"), OrderStatus.DONE);
            orderQuery.where(orderStatusPredicate);
            orderQuery.multiselect(
                    rootOrder.get("customer"),
                    builder.count(rootOrder)
            );
            orderQuery.groupBy(rootOrder.get("customer"));
            orderQuery.having(builder.gt(builder.count(rootOrder), userCriteriaItems.countDoneOrders()));
            List<Object[]> results = entityManager.createQuery(orderQuery).getResultList();
            for (Object[] result : results) {
                Customer customer = (Customer) result[0];
                list2.add(customer);
            }
            list1.retainAll(list2);
            return list1;
        }

        return list1;
    }

    public List<Order> ordersHistory(String email, String orderStatus) {
        Customer customer = findByUsername(email);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> orderCriteriaQuery = builder.createQuery(Order.class);
        Root<Order> root = orderCriteriaQuery.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("customer"), customer));
        predicates.add(builder.equal(root.get("orderStatus"), orderStatus));
        orderCriteriaQuery.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[]{}))));
        return entityManager.createQuery(orderCriteriaQuery).getResultList();
    }

}
