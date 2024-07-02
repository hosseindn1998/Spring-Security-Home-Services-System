package ir.hosseindn.controller.admin;

import ir.hosseindn.dto.admin.AdminLoginRequest;
import ir.hosseindn.dto.admin.AdminLoginResponse;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.customer.UserCriteriaItems;
import ir.hosseindn.dto.customer.UserCriteriaItemsResponse;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.mapper.admin.AdminMapper;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.Admin;
import ir.hosseindn.model.Technician;
import ir.hosseindn.service.admin.AdminService;
import ir.hosseindn.service.customer.CustomerService;
import ir.hosseindn.service.technician.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final CustomerService customerService;
    private final TechnicianService technicianService;

    @GetMapping("/admin-login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@Valid @RequestBody AdminLoginRequest request) {
        Admin mappedAdmin = AdminMapper.INSTANCE.adminLoginRequestToModel(request);
        Admin loggedInAdmin = adminService.login(mappedAdmin.getEmail(), mappedAdmin.getPassword());
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToAdminLoginResponse(loggedInAdmin), HttpStatus.FOUND);
    }

    @GetMapping("/get-users-list")
    public ResponseEntity<UserCriteriaItemsResponse> getUserList(@Validated @RequestBody UserCriteriaItems request) {
        List<CustomerSaveResponse> customerSaveResponseList = customerService.findByCriteria(request)
                .stream()
                .map(CustomerMapper.INSTANCE::modelToUserSaveResponse).
                toList();
        List<Technician> technicianList = technicianService.findByCriteria(request);
        List<TechnicianSaveResponse> technicianSaveResponseList = technicianList.stream().map(TechnicianMapper.INSTANCE::modelToUserSaveResponse).toList();
        UserCriteriaItemsResponse userCriteriaItemsResponse = new UserCriteriaItemsResponse( customerSaveResponseList, technicianSaveResponseList);
        return new ResponseEntity<>(userCriteriaItemsResponse, HttpStatus.FOUND);
    }
}
