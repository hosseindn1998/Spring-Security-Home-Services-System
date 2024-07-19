package ir.hosseindn.controller.technician;

import ir.hosseindn.dto.order.SeeTechnicianOrdersResponse;
import ir.hosseindn.dto.technician.*;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import ir.hosseindn.service.technician.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class TechnicianController {
    private final TechnicianService technicianService;

    @PostMapping(value = "/technician-register" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TechnicianSaveResponse> technicianRegister(@Validated TechnicianSaveRequest request) throws IOException {
        Technician mappedTechnician = TechnicianMapper.INSTANCE.technicianSaveRequestWithoutPathToModel(request);
        Technician savedTechnician = technicianService.register(mappedTechnician, request.imageFile());
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToUserSaveResponse(savedTechnician), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    @PatchMapping("/technician-changePassword")
    public ResponseEntity<TechnicianChangePasswordResponse> technicianChangePassword(@Valid @RequestBody TechnicianChangePasswordRequest request) {
        Technician savedTechnician = technicianService.checkConfirmationAndChangePassword(request);
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToTechnicianChangePasswordResponse(savedTechnician), HttpStatus.OK);
    }

    @GetMapping("/technician-login")
    public ResponseEntity<TechnicianLoginResponse> technicianLogin(@Valid @RequestBody TechnicianLoginRequest request) {
        Technician mappedTechnician = TechnicianMapper.INSTANCE.INSTANCE.technicianLoginRequestToModel(request);
        Technician LoggedInTechnician = technicianService.login(mappedTechnician.getEmail(), mappedTechnician.getPassword());
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.modelToTechnicianLoginResponse(LoggedInTechnician), HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    @PatchMapping("/technician-verify")
    public ResponseEntity<TechnicianVerifyResponse> technicianVerify(@Valid @RequestBody TechnicianVerifyRequest request) {
        Technician mappedTechnician = TechnicianMapper.INSTANCE.technicianVerifyRequestToModel(request);
        Technician changedStatusToVerify = technicianService.changeStatusToVerify(mappedTechnician);
        return new ResponseEntity<>(TechnicianMapper.INSTANCE.INSTANCE.modelToTechnicianVerifyResponse(changedStatusToVerify), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    @GetMapping("/technician-order-history")
    public ResponseEntity<List<SeeTechnicianOrdersResponse>> technicianOrderHistory(@Valid @RequestParam String orderStatus,
                                                                                  Principal principal) {
        List<Order> orderList = technicianService.ordersHistory(principal.getName(), orderStatus);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelListToSeeTechnicianOrdersResponse(orderList), HttpStatus.FOUND);
    }

    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    @GetMapping("/technician-wallet")
    public ResponseEntity<Long> technicianSeeWallet(Principal principal) {
        return new ResponseEntity<>(technicianService.getWalletAmount(principal.getName()), HttpStatus.FOUND);
    }

    @GetMapping("/see-rate")
    @PreAuthorize("hasRole('ROLE_TECHNICIAN')")
    public ResponseEntity<Double> getRate(Principal principal){
        return new ResponseEntity<>(technicianService.getTechnicianRate(principal.getName()),HttpStatus.FOUND);
    }

}
