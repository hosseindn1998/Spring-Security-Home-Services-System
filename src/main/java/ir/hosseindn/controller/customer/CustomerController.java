package ir.hosseindn.controller.customer;

import ir.hosseindn.dto.customer.*;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.model.Customer;
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
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer-register")
    public ResponseEntity<CustomerSaveResponse> customerRegister(@Valid @RequestBody CustomerSaveRequest request) {
        if (CustomValidations.isNotValidIranianNationalCode(request.nationalCode()))
            throw new NotValidInformation("National Code is Not valid");
        Customer mappedCustomer = CustomerMapper.INSTANCE.customerSaveRequestToModel(request);
        Customer savedCustomer = customerService.register(mappedCustomer);
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelToUserSaveResponse(savedCustomer), HttpStatus.CREATED);
    }

    @PatchMapping("/customer-changePassword")
    public ResponseEntity<CustomerChangePasswordResponse> customerChangePassword(@Valid @RequestBody CustomerChangePasswordRequest request) {
        if (!request.newPassword().equals(request.confirmPassword()))
            throw new NotValidInformation("new password must be match by confirm");
        Customer mappedCustomer = CustomerMapper.INSTANCE.INSTANCE.customerChangePasswordRequestToModel(request);
        Customer savedCustomer = customerService.changePassword(mappedCustomer.getEmail(), mappedCustomer.getPassword());
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelToCustomerChangePasswordResponse(savedCustomer), HttpStatus.OK);
    }

    @GetMapping("/customer-login")
    public ResponseEntity<CustomerLoginResponse> customerLogin(@Valid @RequestBody CustomerLoginRequest request) {
        Customer mappedCustomer = CustomerMapper.INSTANCE.customerLoginRequestToModel(request);
        Customer loggedInCustomer = customerService.login(mappedCustomer.getEmail(), mappedCustomer.getPassword());
        return new ResponseEntity<>(CustomerMapper.INSTANCE.modelToCustomerLoginResponse(loggedInCustomer), HttpStatus.FOUND);
    }
}
