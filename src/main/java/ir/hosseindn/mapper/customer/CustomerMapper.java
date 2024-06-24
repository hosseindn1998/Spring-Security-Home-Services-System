package ir.hosseindn.mapper.customer;

import ir.hosseindn.dto.customer.CustomerSaveRequest;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer customerSaveRequestToModel(CustomerSaveRequest request);
    CustomerSaveResponse modelToUserSaveResponse(Customer customer);
}