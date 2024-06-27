package ir.hosseindn.controller.technician;

import ir.hosseindn.dto.customer.CustomerChangePasswordResponse;
import ir.hosseindn.dto.customer.CustomerLoginResponse;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.technician.*;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.Technician;
import ir.hosseindn.service.technician.TechnicianService;
import ir.hosseindn.utility.CustomValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class TechnicianController {
    private final TechnicianService technicianService;

    @PostMapping("/technician-register")
    public ResponseEntity<TechnicianSaveResponse> technicianRegister(@Valid @RequestBody TechnicianSaveRequest request) throws IOException {
        Technician mappedTechnician = TechnicianMapper.INSTANCE.technicianSaveRequestWithoutPathToModel(request.technician());
        Technician savedTechnician = technicianService.register(mappedTechnician, request.imagePath());
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToUserSaveResponse(savedTechnician), HttpStatus.CREATED);
    }
    @PatchMapping("/technician-changePassword")
    public ResponseEntity<TechnicianChangePasswordResponse> technicianChangePassword(@Valid @RequestBody TechnicianChangePasswordRequest request) {
        if (!request.password().equals(request.confirmPassword()))
            throw new NotValidInformation("new password must be match by confirm");
        Technician mappedTechnician = TechnicianMapper.INSTANCE.INSTANCE.technicianChangePasswordRequestToModel(request);
        Technician savedTechnician = technicianService.changePassword(mappedTechnician.getEmail(), mappedTechnician.getPassword());
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToTechnicianChangePasswordResponse(savedTechnician), HttpStatus.OK);
    }
    @GetMapping("/technician-login")
    public ResponseEntity<TechnicianLoginResponse> technicianLogin(@Valid @RequestBody TechnicianLoginRequest request) {
        Technician mappedTechnician = TechnicianMapper.INSTANCE.INSTANCE.technicianLoginRequestToModel(request);
        Technician LoggedInTechnician = technicianService.login(mappedTechnician.getEmail(), mappedTechnician.getPassword());
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToTechnicianLoginResponse(LoggedInTechnician), HttpStatus.FOUND);
    }
    @PatchMapping ("/technician-verify")
    public ResponseEntity<TechnicianVerifyResponse>technicianVerify(@Valid @RequestBody TechnicianVerifyRequest request){
        Technician mappedTechnician=TechnicianMapper.INSTANCE.technicianVerifyRequestToModel(request);
        Technician changedStatusToVerify = technicianService.changeStatusToVerify(mappedTechnician);
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.INSTANCE.modelToTechnicianVerifyResponse(changedStatusToVerify),HttpStatus.OK );
    }

}
