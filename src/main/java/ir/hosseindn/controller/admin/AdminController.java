package ir.hosseindn.controller.admin;

import ir.hosseindn.dto.admin.AdminLoginRequest;
import ir.hosseindn.dto.admin.AdminLoginResponse;
import ir.hosseindn.dto.admin.AdminSaveRequest;
import ir.hosseindn.dto.admin.AdminSaveResponse;
import ir.hosseindn.dto.order.OrderSearchItemResponse;
import ir.hosseindn.dto.order.OrderSearchItemsRequest;
import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.dto.user.UserCriteriaItemsResponse;
import ir.hosseindn.mapper.admin.AdminMapper;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.model.Admin;
import ir.hosseindn.model.Order;
import ir.hosseindn.service.admin.AdminService;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.technician.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final TechnicianService technicianService;
    private final OrderService orderService;


    @GetMapping("/admin-logged-in")
    public ResponseEntity<AdminLoginResponse> adminLogin(@Valid @RequestBody AdminLoginRequest request) {
        Admin mappedAdmin = AdminMapper.INSTANCE.adminLoginRequestToModel(request);
        Admin loggedInAdmin = adminService.login(mappedAdmin);
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToAdminLoginResponse(loggedInAdmin), HttpStatus.FOUND);
    }

    @PostMapping("/admin-register")
    public ResponseEntity<AdminSaveResponse> adminRegister(@Valid @RequestBody AdminSaveRequest request) {
        Admin mappedAdmin = AdminMapper.INSTANCE.adminSaveRequestToModel(request);
        Admin savedAdmin =adminService.save(mappedAdmin);
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToAdminSaveResponse(savedAdmin), HttpStatus.CREATED);
    }

    @GetMapping("/get-users-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserCriteriaItemsResponse> getUserList(@Valid @RequestBody UserCriteriaItems request) {
        List<Object> users = adminService.searchUsers(request);
        return new ResponseEntity<>(new UserCriteriaItemsResponse(users), HttpStatus.FOUND);
    }


    @GetMapping("/get-orders-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<OrderSearchItemResponse>> getOrdersList(@Valid @RequestBody OrderSearchItemsRequest request) {
        List<Order> foundedOrders = orderService.findByCriteria(request);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderSearchItemResponse(foundedOrders), HttpStatus.FOUND);
    }

    @GetMapping("/get-technician-image")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getTechnicianImage(@RequestParam Long technicianId,
                                                     @RequestParam String fileAddressForSave) {
        String result = technicianService.fetchAvatarFile(technicianId, fileAddressForSave);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }


}
