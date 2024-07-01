package ir.hosseindn.service.technician;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianStatus;
import ir.hosseindn.repository.technician.TechnicianRepository;
import ir.hosseindn.utility.CustomValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

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
        technician.setActive(Boolean.TRUE);
        return technicianRepository.save(technician);
    }
    public Technician update(Technician technician){
        return technicianRepository.save(technician);
    }

    public Technician changePassword(String email, String newPassword) {
        Technician technician = technicianRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Technician with email :" + email + " Not found.")
        );
        technicianRepository.updatePassword(email, newPassword);
        return technician;
    }

    public void update(Long id, Long minusScore) {
        Technician technician = findById(id);
        if(technician.getTotalScores()+minusScore<0){
            technician.setActive(Boolean.FALSE);
            technician.setTotalScores(0);
            technicianRepository.save(technician);
        }else {
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
}
