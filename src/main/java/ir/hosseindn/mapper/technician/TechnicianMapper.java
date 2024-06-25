package ir.hosseindn.mapper.technician;

import ir.hosseindn.dto.customer.CustomerChangePasswordResponse;
import ir.hosseindn.dto.customer.CustomerLoginResponse;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.technician.TechnicianChangePasswordRequest;
import ir.hosseindn.dto.technician.TechnicianLoginRequest;
import ir.hosseindn.dto.technician.TechnicianSaveRequest;
import ir.hosseindn.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TechnicianMapper {

    TechnicianMapper INSTANCE = Mappers.getMapper(TechnicianMapper.class);

    Technician technicianSaveRequestToModel(TechnicianSaveRequest request);

    CustomerSaveResponse modelToUserSaveResponse(Technician technician);

    Technician technicianChangePasswordRequestToModel(TechnicianChangePasswordRequest request);

    CustomerChangePasswordResponse modelToTechnicianChangePasswordResponse(Technician technician);

    Technician technicianLoginRequestToModel(TechnicianLoginRequest request);

    CustomerLoginResponse modelToTechnicianLoginResponse(Technician technician);
}