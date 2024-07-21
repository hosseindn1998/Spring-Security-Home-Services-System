package ir.hosseindn.service.technician;

import ir.hosseindn.dto.technician.TechnicianChangePasswordRequest;
import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.*;
import ir.hosseindn.model.enums.OrderStatus;
import ir.hosseindn.model.enums.Role;
import ir.hosseindn.model.enums.TechnicianStatus;
import ir.hosseindn.repository.technician.TechnicianRepository;
import ir.hosseindn.service.confirmationservice.ConfirmationTokenService;
import ir.hosseindn.service.user.UserService;
import ir.hosseindn.utility.CustomValidations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TechnicianService {
    private final TechnicianRepository technicianRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final UserService userService;
    @Autowired
    EntityManager entityManager;

    public Technician register(Technician technician, MultipartFile file) throws IOException {
        if (CustomValidations.isNotValidIranianNationalCode(technician.getNationalCode()))
            throw new NotValidInformation("National Code is Not valid");
        if (userService.isExistEmail(technician.getEmail()))
            throw new DuplicateInformationException("A USER with this Email exist.");
        if (file.isEmpty())
            throw new NotValidInformation("File is empty.");
        if (!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".jpg"))
            throw new NotValidInformation("file must be jpg");
        if (file.getSize() > (300 * 1024))
            throw new NotValidInformation("File must be les than 300 KB");
        technician.setAvatar(file.getBytes());
        technician.setRegisteredDate(LocalDateTime.now());
        technician.setTechnicianStatus(TechnicianStatus.NEW_TECHNICIAN);
        technician.setTotalScores(0);
        technician.setCountScores(0);
        technician.setRate(0.0);
        technician.setEnabled(Boolean.FALSE);
        technician.setLocked(false);
        technician.setPassword(passwordEncoder.encode(technician.getPassword()));
        technician.setRole(Role.ROLE_TECHNICIAN);
        technician.setWallet(new Wallet(0L));
        Technician savedTechnician = technicianRepository.save(technician);
        confirmationTokenService.generateConfirmationToken(technician);
        return savedTechnician;
    }

    public Technician update(Technician technician) {
        return technicianRepository.save(technician);
    }

    public Technician checkConfirmationAndChangePassword(TechnicianChangePasswordRequest request) {
        if (!request.password().equals(request.confirmPassword()))
            throw new NotValidInformation("new password must be match by confirm");
        return changePassword(request.email(), request.password());
    }

    public Technician changePassword(String email, String newPassword) {
        Technician technician = findByEmail(email);
        technicianRepository.updatePassword(email, passwordEncoder.encode(newPassword));
        return technician;
    }

    public void updateScores(Long id, Long minusScore) {
        Technician technician = findById(id);
        if (technician.getTotalScores() + minusScore < 0) {
            technician.setLocked(true);
            technician.setTotalScores(0);
            technicianRepository.save(technician);
        } else {
            technicianRepository.updateScores(technician.getId(),
                    technician.getTotalScores() + minusScore);
        }
    }

    public Technician login(String email, String Password) {
        return technicianRepository.findByEmailAndPassword(email, Password).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }

    public Technician findByEmail(String email) {
        return technicianRepository.findByEmail(email).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }

    public void changeStatusToWaitForVerify(String email) {
        technicianRepository.updateTechnicianStatus(TechnicianStatus.WAIT_FOR_VERIFY, email);
    }

    public Technician changeStatusToVerify(Technician technician) {
        Technician founded = technicianRepository.findByEmail(technician.getEmail()).orElseThrow(
                () -> new NotFoundException("Technician with email :" + technician.getEmail() + " Not found."));
        technicianRepository.updateTechnicianStatus(TechnicianStatus.VERIFIED, technician.getEmail());
        return founded;
    }

    public Technician findById(Long id) {
        return technicianRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Technician with id %s Not Found", id))
        );
    }

    public List<Technician> findByCriteria(UserCriteriaItems userCriteriaItems) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Technician> technicianQuery = builder.createQuery(Technician.class);
        Root<Technician> root = technicianQuery.from(Technician.class);
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
        if (userCriteriaItems.technicianStatus() != null)
            predicates.add(builder.equal(root.get("technicianStatus"), userCriteriaItems.technicianStatus()));
        if (userCriteriaItems.rate() != null)
            predicates.add(builder.equal(root.get("rate"), userCriteriaItems.rate()));
        if (userCriteriaItems.totalScores() != null)
            predicates.add(builder.equal(root.get("totalScores"), userCriteriaItems.totalScores()));
        if (userCriteriaItems.countScores() != null)
            predicates.add(builder.equal(root.get("countScores"), userCriteriaItems.countScores()));
        if (userCriteriaItems.locked() != null)
            predicates.add(builder.equal(root.get("locked"), userCriteriaItems.locked()));

        if (userCriteriaItems.walletId() != null) {
            Join<Technician, Wallet> technicianWalletJoin = root.join("wallet", JoinType.INNER);
            predicates.add(builder.equal(technicianWalletJoin.get("id"), userCriteriaItems.walletId()));
        }

        if (userCriteriaItems.subServiceName() != null) {
            Join<Technician, TechnicianSubService> technicianTechnicianSubServiceJoin = root.join("technicianSubServices", JoinType.INNER);
            Join<TechnicianSubService, SubService> technicianSubServiceSubServiceJoin = technicianTechnicianSubServiceJoin.join("subService", JoinType.LEFT);
            predicates.add(builder.equal(technicianSubServiceSubServiceJoin.get("name"), userCriteriaItems.subServiceName()));
        }


        technicianQuery.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[]{}))));

        List<Technician> list1 = entityManager.createQuery(technicianQuery).getResultList();
        List<Technician> list2 = new ArrayList<>();
        if (userCriteriaItems.countRequests() != null) {
            CriteriaQuery<Object[]> orderQuery = builder.createQuery(Object[].class);
            Root<Offer> rootOrder = orderQuery.from(Offer.class);
            orderQuery.multiselect(
                    rootOrder.get("technician"),
                    builder.count(rootOrder)
            );
            orderQuery.groupBy(rootOrder.get("technician"));
            orderQuery.having(builder.gt(builder.count(rootOrder), userCriteriaItems.countRequests()));
            List<Object[]> results = entityManager.createQuery(orderQuery).getResultList();
            for (Object[] result : results) {
                Technician technician = (Technician) result[0];
                list2.add(technician);
            }
            list1.retainAll(list2);
            return list1;
        }
        if (userCriteriaItems.countDoneOrders() != null) {
            CriteriaQuery<Object[]> orderQuery = builder.createQuery(Object[].class);
            Root<Order> rootOrder = orderQuery.from(Order.class);
            Predicate orderStatusPredicate = builder.equal(rootOrder.get("orderStatus"), OrderStatus.DONE);
            orderQuery.multiselect(
                    rootOrder.get("technician"),
                    builder.count(rootOrder)
            );
            orderQuery.where(orderStatusPredicate);
            orderQuery.groupBy(rootOrder.get("technician"));
            orderQuery.having(builder.gt(builder.count(rootOrder), userCriteriaItems.countDoneOrders()));
            List<Object[]> results = entityManager.createQuery(orderQuery).getResultList();
            for (Object[] result : results) {
                Technician technician = (Technician) result[0];
                list2.add(technician);
            }
            list1.retainAll(list2);
            return list1;
        }

        return list1;
    }

    public String fetchAvatarFile(Long technicianId, String fileAddressForSave) {
        if (!CustomValidations.isValidPathFile(fileAddressForSave))
            throw new NotValidInformation("File address Not valid");
        Technician technician = findById(technicianId);
        Path path = Paths.get(fileAddressForSave);
        try (FileOutputStream fos = new FileOutputStream(path.toString())) {
            fos.write(technician.getAvatar());
            return "fetch successfully";
        } catch (IOException e) {
            log.error(e.getMessage());
            return "failed";
        }
    }


    public Double getTechnicianRate(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Double> technicianQuery = builder.createQuery(Double.class);
        Root<Technician> root = technicianQuery.from(Technician.class);
        technicianQuery.select(root.get("rate")).where(builder.equal(root.get("email"), email));
        return entityManager.createQuery(technicianQuery).getSingleResult();
    }

    public List<Order> ordersHistory(String email, String orderStatus) {
        Technician technician = findByEmail(email);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> orderCriteriaQuery = builder.createQuery(Order.class);
        Root<Order> root = orderCriteriaQuery.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("technician"), technician));
        predicates.add(builder.equal(root.get("orderStatus"), orderStatus));
        orderCriteriaQuery.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[]{}))));
        return entityManager.createQuery(orderCriteriaQuery).getResultList();
    }

    public Long getWalletAmount(String customerEmail) {
        return findByEmail(customerEmail).getWallet().getAmount();
    }

}