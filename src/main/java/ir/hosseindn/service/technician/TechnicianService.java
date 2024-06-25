package ir.hosseindn.service.technician;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianStatus;
import ir.hosseindn.repository.technician.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TechnicianService {
    private final TechnicianRepository technicianRepository;

    public Technician register(Technician technician) {
        if (technicianRepository.findByEmailOrNationalCode(technician.getEmail(), technician.getNationalCode()).isPresent())
            throw new DuplicateInformationException("A Technician with this Email/National Code exist.");
        technician.setRegisteredDate(LocalDate.now());
        technician.setTechnicianStatus(TechnicianStatus.NEW_TECHNICIAN);
        return technicianRepository.save(technician);
    }

    public Technician changePassword(String email, String newPassword) {
        Technician technician = technicianRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Technician with email :" + email + " Not found.")
        );
        technicianRepository.updatePassword(email, newPassword);
        return technician;
    }

    public Technician login(String email, String Password) {
        return technicianRepository.findByEmailAndPassword(email, Password).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }
    public Technician changeStatusToVerify(Technician technician){
        if(technicianRepository.findByEmail(technician.getEmail()).isEmpty())
            throw new NotFoundException("Technician with email :" + technician.getEmail() + " Not found.");
        technicianRepository.updateTechnicianStatus(TechnicianStatus.VERIFIED,technician.getEmail());
        return technician;
    }
}
