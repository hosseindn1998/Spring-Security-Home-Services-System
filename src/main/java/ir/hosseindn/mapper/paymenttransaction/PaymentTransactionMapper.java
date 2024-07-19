package ir.hosseindn.mapper.paymenttransaction;

import ir.hosseindn.dto.paymenttransaction.PaymentTransactionSaveResponse;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.PaymentTransaction;
import org.mapstruct.factory.Mappers;

public interface PaymentTransactionMapper {
    PaymentTransactionMapper INSTANCE = Mappers.getMapper(PaymentTransactionMapper.class);
    PaymentTransactionSaveResponse modelToPaymentTransactionResponse(PaymentTransaction paymentTransaction);
}
