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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class TechnicianController {
    private final TechnicianService technicianService;

    @PostMapping("/technician-register")
    public ResponseEntity<TechnicianSaveResponse> technicianRegister(@Validated @RequestBody TechnicianSaveRequest request) {
        if (!CustomValidations.isValidIranianNationalCode(request.nationalCode()))
            throw new NotValidInformation("National Code is Not valid");
        Technician mappedTechnician = TechnicianMapper.INSTANCE.technicianSaveRequestToModel(request);
        Technician savedTechnician = technicianService.register(mappedTechnician);
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToUserSaveResponse(savedTechnician), HttpStatus.CREATED);
    }
    @PatchMapping("/technician-changePassword")
    public ResponseEntity<TechnicianChangePasswordResponse> technicianChangePassword(@Validated @RequestBody TechnicianChangePasswordRequest request) {
        if (!request.newPassword().equals(request.confirmPassword()))
            throw new NotValidInformation("new password must be match by confirm");
        Technician mappedTechnician = TechnicianMapper.INSTANCE.INSTANCE.technicianChangePasswordRequestToModel(request);
        Technician savedTechnician = technicianService.changePassword(mappedTechnician.getEmail(), mappedTechnician.getPassword());
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToTechnicianChangePasswordResponse(savedTechnician), HttpStatus.CREATED);
    }



}
