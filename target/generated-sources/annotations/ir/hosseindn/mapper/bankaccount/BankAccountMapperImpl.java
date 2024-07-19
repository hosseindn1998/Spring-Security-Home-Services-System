package ir.hosseindn.mapper.bankaccount;

import ir.hosseindn.dto.bankaccount.BankAccountSaveRequest;
import ir.hosseindn.model.BankAccount;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T10:06:38+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public BankAccount bankAccountSaveRequestToModel(BankAccountSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        BankAccount.BankAccountBuilder<?, ?> bankAccount = BankAccount.builder();

        bankAccount.cardNumber( request.cardNumber() );
        bankAccount.cvv( request.cvv() );
        bankAccount.mm( request.mm() );
        bankAccount.yy( request.yy() );
        bankAccount.password( request.password() );
        bankAccount.amount( request.amount() );

        return bankAccount.build();
    }
}
