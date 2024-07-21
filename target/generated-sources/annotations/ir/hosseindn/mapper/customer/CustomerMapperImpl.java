package ir.hosseindn.mapper.customer;

import ir.hosseindn.dto.customer.CustomerChangePasswordRequest;
import ir.hosseindn.dto.customer.CustomerChangePasswordResponse;
import ir.hosseindn.dto.customer.CustomerLoginRequest;
import ir.hosseindn.dto.customer.CustomerLoginResponse;
import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.model.Customer;
import ir.hosseindn.model.enums.Role;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T15:20:12+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer customerSaveRequestToModel(CustomerSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.firstName( request.firstName() );
        customer.lastName( request.lastName() );
        customer.nationalCode( request.nationalCode() );
        customer.email( request.email() );
        customer.password( request.password() );

        return customer.build();
    }

    @Override
    public CustomerSaveResponse modelToUserSaveResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String nationalCode = null;
        String email = null;
        LocalDateTime registeredDate = null;
        Role role = null;

        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        nationalCode = customer.getNationalCode();
        email = customer.getEmail();
        registeredDate = customer.getRegisteredDate();
        role = customer.getRole();

        CustomerSaveResponse customerSaveResponse = new CustomerSaveResponse( id, firstName, lastName, nationalCode, email, registeredDate, role );

        return customerSaveResponse;
    }

    @Override
    public Customer customerChangePasswordRequestToModel(CustomerChangePasswordRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.email( request.email() );
        customer.password( request.password() );

        return customer.build();
    }

    @Override
    public CustomerChangePasswordResponse modelToCustomerChangePasswordResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        String email = null;

        email = customer.getEmail();

        CustomerChangePasswordResponse customerChangePasswordResponse = new CustomerChangePasswordResponse( email );

        return customerChangePasswordResponse;
    }

    @Override
    public Customer customerLoginRequestToModel(CustomerLoginRequest request) {
        if ( request == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        customer.email( request.email() );
        customer.password( request.password() );

        return customer.build();
    }

    @Override
    public CustomerLoginResponse modelToCustomerLoginResponse(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String email = null;

        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        email = customer.getEmail();

        CustomerLoginResponse customerLoginResponse = new CustomerLoginResponse( firstName, lastName, email );

        return customerLoginResponse;
    }

    @Override
    public Customer customerIdToModel(Long customerId) {
        if ( customerId == null ) {
            return null;
        }

        Customer.CustomerBuilder<?, ?> customer = Customer.builder();

        return customer.build();
    }
}
