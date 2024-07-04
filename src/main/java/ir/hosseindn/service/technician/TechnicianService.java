package ir.hosseindn.service.technician;

import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Roles;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianStatus;
import ir.hosseindn.repository.technician.TechnicianRepository;
import ir.hosseindn.utility.CustomValidations;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianService {
    private final TechnicianRepository technicianRepository;
    @Autowired
    EntityManager entityManager;

    public Technician register(Technician technician, String fileAddressAndName) throws IOException {
        if (CustomValidations.isNotValidIranianNationalCode(technician.getNationalCode()))
            throw new NotValidInformation("National Code is Not valid");
        if (technicianRepository.findByEmailOrNationalCode(technician.getEmail(), technician.getNationalCode()).isPresent()) {
            throw new DuplicateInformationException("A Technician with this Email/National Code exist.");
        }
        if (!CustomValidations.isValidPathFile(fileAddressAndName))
            throw new NotValidInformation("file address not valid ");
        Path path = Paths.get(fileAddressAndName);
        if (Files.size(path) > (300 * 1024))
            throw new NotValidInformation("File must be les than 300 KB");
        technician.setAvatar(Files.readAllBytes(path));
        technician.setRegisteredDate(LocalDateTime.now());
        technician.setTechnicianStatus(TechnicianStatus.NEW_TECHNICIAN);
        technician.setTotalScores(0);
        technician.setCountScores(0);
        technician.setIsActive(Boolean.TRUE);
        technician.setRate(0.0);
        technician.setRole(Roles.TECHNICIAN);
        return technicianRepository.save(technician);
    }

    public Technician update(Technician technician) {
        return technicianRepository.save(technician);
    }

    public Technician changePassword(String email, String newPassword) {
        Technician technician = technicianRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Technician with email :" + email + " Not found.")
        );
        technicianRepository.updatePassword(email, newPassword);
        return technician;
    }

    public void updateScores(Long id, Long minusScore) {
        Technician technician = findById(id);
        if (technician.getTotalScores() + minusScore < 0) {
            technician.setIsActive(Boolean.FALSE);
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
        if (userCriteriaItems.technicianStatus() != null)
            predicates.add(builder.equal(root.get("technicianStatus"), userCriteriaItems.technicianStatus()));
        if (userCriteriaItems.rate() != null)
            predicates.add(builder.equal(root.get("rate"), userCriteriaItems.rate()));
        if (userCriteriaItems.totalScores() != null)
            predicates.add(builder.equal(root.get("totalScores"), userCriteriaItems.totalScores()));
        if (userCriteriaItems.countScores() != null)
            predicates.add(builder.equal(root.get("countScores"), userCriteriaItems.countScores()));
        if (userCriteriaItems.isActive()!=null)
            predicates.add(builder.equal(root.get("isActive"), userCriteriaItems.isActive()));


        technicianQuery.where(builder.and(predicates.toArray(predicates.toArray(new Predicate[]{}))));

        return entityManager.createQuery(technicianQuery).getResultList();
    }
}
