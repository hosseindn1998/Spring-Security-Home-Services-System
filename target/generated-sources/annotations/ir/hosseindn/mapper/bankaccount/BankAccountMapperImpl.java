package ir.hosseindn.mapper.bankaccount;

import ir.hosseindn.dto.bankaccount.BankAccountSaveRequest;
import ir.hosseindn.model.BankAccount;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T06:38:51+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class BankAccountMapperImpl implements BankAccountMapper {

    @Override
    public BankAccount bankAccountSaveRequestToModel(BankAccountSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        BankAccount.BankAccountBuilder<?, ?> bankAccount = BankAccount.builder();

        bankAccount.amount( request.amount() );

        return bankAccount.build();
    }
}
