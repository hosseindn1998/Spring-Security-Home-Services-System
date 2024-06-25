package ir.hosseindn.mapper.admin;

import ir.hosseindn.dto.admin.AdminLoginRequest;
import ir.hosseindn.dto.admin.AdminLoginResponse;
import ir.hosseindn.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    Admin adminLoginRequestToModel(AdminLoginRequest request);

    AdminLoginResponse modelToAdminLoginResponse(Admin admin);
}