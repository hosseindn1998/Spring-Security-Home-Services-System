package ir.hosseindn.controller.admin;

import ir.hosseindn.dto.admin.AdminLoginRequest;
import ir.hosseindn.dto.admin.AdminLoginResponse;
import ir.hosseindn.dto.customer.*;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.mapper.admin.AdminMapper;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.model.Admin;
import ir.hosseindn.model.Customer;
import ir.hosseindn.service.admin.AdminService;
import ir.hosseindn.service.customer.CustomerService;
import ir.hosseindn.utility.CustomValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminController {
    private final AdminService adminService;
    @GetMapping("/admin-login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@Valid @RequestBody AdminLoginRequest request){
        Admin mappedAdmin = AdminMapper.INSTANCE.adminLoginRequestToModel(request);
        Admin loggedInAdmin=adminService.login(mappedAdmin.getEmail(),mappedAdmin.getPassword());
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToAdminLoginResponse(loggedInAdmin),HttpStatus.FOUND);
    }
}
