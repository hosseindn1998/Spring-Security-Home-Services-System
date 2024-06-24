package ir.hosseindn.mapper.customer;

import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.wallet.WalletSaveRequest;
import ir.hosseindn.model.Customer;
import ir.hosseindn.model.Wallet;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T19:11:06+0330",
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
        customer.registeredDate( request.registeredDate() );
        customer.wallet( walletSaveRequestToWallet( request.wallet() ) );

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
        LocalDate registeredDate = null;

        id = customer.getId();
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        nationalCode = customer.getNationalCode();
        email = customer.getEmail();
        registeredDate = customer.getRegisteredDate();

        CustomerSaveResponse customerSaveResponse = new CustomerSaveResponse( id, firstName, lastName, nationalCode, email, registeredDate );

        return customerSaveResponse;
    }

    protected Wallet walletSaveRequestToWallet(WalletSaveRequest walletSaveRequest) {
        if ( walletSaveRequest == null ) {
            return null;
        }

        Wallet.WalletBuilder<?, ?> wallet = Wallet.builder();

        wallet.amount( walletSaveRequest.amount() );

        return wallet.build();
    }
}
