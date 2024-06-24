package ir.hosseindn.mapper.customer;

import ir.hosseindn.dto.customer.*;
import ir.hosseindn.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer customerSaveRequestToModel(CustomerSaveRequest request);
    CustomerSaveResponse modelToUserSaveResponse(Customer customer);
    Customer customerChangePasswordRequestToModel(CustomerChangePasswordRequest request);
    CustomerChangePasswordResponse modelToCustomerChangePasswordResponse(Customer customer);
    Customer customerLoginRequestToModel(CustomerLoginRequest request);
    CustomerLoginResponse modelToCustomerLoginResponse(Customer customer);
}