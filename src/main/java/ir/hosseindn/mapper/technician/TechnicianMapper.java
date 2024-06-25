package ir.hosseindn.mapper.technician;

import ir.hosseindn.dto.customer.CustomerChangePasswordResponse;
import ir.hosseindn.dto.customer.CustomerLoginResponse;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.technician.*;
import ir.hosseindn.model.Technician;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TechnicianMapper {

    TechnicianMapper INSTANCE = Mappers.getMapper(TechnicianMapper.class);

    Technician technicianSaveRequestToModel(TechnicianSaveRequest request);

    TechnicianSaveResponse modelToUserSaveResponse(Technician technician);

    Technician technicianChangePasswordRequestToModel(TechnicianChangePasswordRequest request);

    TechnicianChangePasswordResponse modelToTechnicianChangePasswordResponse(Technician technician);

    Technician technicianLoginRequestToModel(TechnicianLoginRequest request);

    TechnicianLoginResponse modelToTechnicianLoginResponse(Technician technician);
    Technician technicianVerifyRequestToModel(TechnicianVerifyRequest request);
    TechnicianVerifyResponse modelToTechnicianVerifyResponse(Technician technician);
}