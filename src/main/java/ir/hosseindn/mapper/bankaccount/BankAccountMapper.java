package ir.hosseindn.mapper.bankaccount;

import ir.hosseindn.dto.bankaccount.BankAccountSaveRequest;
import ir.hosseindn.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface BankAccountMapper {
    BankAccountMapper INSTANCE= Mappers.getMapper(BankAccountMapper.class);
    BankAccount bankAccountSaveRequestToModel(BankAccountSaveRequest request);
}
