package ir.hosseindn.mapper.admin;

import ir.hosseindn.dto.admin.AdminLoginRequest;
import ir.hosseindn.dto.admin.AdminLoginResponse;
import ir.hosseindn.model.Admin;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T19:58:39+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class AdminMapperImpl implements AdminMapper {

    @Override
    public Admin adminLoginRequestToModel(AdminLoginRequest request) {
        if ( request == null ) {
            return null;
        }

        Admin.AdminBuilder<?, ?> admin = Admin.builder();

        admin.email( request.email() );
        admin.password( request.password() );

        return admin.build();
    }

    @Override
    public AdminLoginResponse modelToAdminLoginResponse(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String email = null;

        firstName = admin.getFirstName();
        lastName = admin.getLastName();
        email = admin.getEmail();

        AdminLoginResponse adminLoginResponse = new AdminLoginResponse( firstName, lastName, email );

        return adminLoginResponse;
    }
}
