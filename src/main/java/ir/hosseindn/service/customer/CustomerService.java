package ir.hosseindn.service.customer;

import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Customer;
import ir.hosseindn.model.Roles;
import ir.hosseindn.repository.customer.CustomerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    EntityManager entityManager;

    public Customer register(Customer customer) {
        if (customerRepository.findByEmailOrNationalCode(customer.getEmail(), customer.getNationalCode()).isPresent())
            throw new DuplicateInformationException("A Customer with this Email/National Code exist.");
        customer.setRegisteredDate(LocalDateTime.now());
        customer.setRole(Roles.CUSTOMER);
        return customerRepository.save(customer);
    }

    public Customer changePassword(String email, String newPassword) {
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

    public List<Customer> findByCriteria(UserCriteriaItems userCriteriaItems) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> customerQuery = builder.createQuery(Customer.class);
        Root<Customer> root = customerQuery.from(Customer.class);
        List<Predicate> predicates = new ArrayList<>();
        if (userCriteriaItems.role()!=null)
            predicates.add(builder.equal(root.get("role"), userCriteriaItems.role()));
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
        if (userCriteriaItems.registeredDate() != null)
            predicates.add(builder.equal(root.get("registeredDate"), userCriteriaItems.registeredDate()));

        customerQuery.where(builder.and(predicates.toArray(new Predicate[]{})));

        return entityManager.createQuery(customerQuery).getResultList();
    }
}
