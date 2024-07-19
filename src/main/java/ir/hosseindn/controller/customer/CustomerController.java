package ir.hosseindn.controller.customer;

import ir.hosseindn.dto.customer.*;
import ir.hosseindn.dto.order.SeeCustomerOrdersResponse;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.model.Customer;
import ir.hosseindn.model.Order;
import ir.hosseindn.service.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer-register")
    public ResponseEntity<CustomerSaveResponse> customerRegister(@Valid @RequestBody CustomerSaveRequest request) {
        Customer mappedCustomer = CustomerMapper.INSTANCE.customerSaveRequestToModel(request);
        Customer savedCustomer = customerService.register(mappedCustomer);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelToUserSaveResponse(savedCustomer), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PatchMapping("/customer-changePassword")
    public ResponseEntity<CustomerChangePasswordResponse> customerChangePassword(
            @Valid @RequestBody CustomerChangePasswordRequest request) {
        Customer savedCustomer = customerService.changePassword(request.email(), request.password(), request.confirmPassword());
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelToCustomerChangePasswordResponse(savedCustomer), HttpStatus.OK);
    }

    @GetMapping("/customer-login")
    public ResponseEntity<CustomerLoginResponse> customerLogin(@Valid @RequestBody CustomerLoginRequest request) {
        Customer mappedCustomer = CustomerMapper.INSTANCE.customerLoginRequestToModel(request);
        Customer loggedInCustomer = customerService.login(mappedCustomer.getEmail(), mappedCustomer.getPassword());
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelToCustomerLoginResponse(loggedInCustomer), HttpStatus.FOUND);
    }
    @GetMapping("/customer-order-history")
    public ResponseEntity<List<SeeCustomerOrdersResponse>> customerOrderHistory(@Valid @RequestParam String orderStatus, Principal principal) {
        List<Order> orderList = customerService.ordersHistory(principal.getName(), orderStatus);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelListToSeeCustomerOrdersResponse(orderList), HttpStatus.FOUND);
    }

    @GetMapping("/customer-wallet")
    public ResponseEntity<Long> customerSeeWallet(Principal principal) {
        return new ResponseEntity<>(customerService.getWalletAmount(principal.getName()), HttpStatus.FOUND);
    }

}
