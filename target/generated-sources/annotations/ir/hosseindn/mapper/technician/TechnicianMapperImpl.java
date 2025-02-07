package ir.hosseindn.mapper.technician;

import ir.hosseindn.dto.technician.TechnicianChangePasswordRequest;
import ir.hosseindn.dto.technician.TechnicianChangePasswordResponse;
import ir.hosseindn.dto.technician.TechnicianLoginRequest;
import ir.hosseindn.dto.technician.TechnicianLoginResponse;
import ir.hosseindn.dto.technician.TechnicianSaveRequest;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.dto.technician.TechnicianVerifyRequest;
import ir.hosseindn.dto.technician.TechnicianVerifyResponse;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.enums.Role;
import ir.hosseindn.model.enums.TechnicianStatus;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-21T17:35:48+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class TechnicianMapperImpl implements TechnicianMapper {

    @Override
    public Technician technicianSaveRequestWithoutPathToModel(TechnicianSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.firstName( request.firstName() );
        technician.lastName( request.lastName() );
        technician.nationalCode( request.nationalCode() );
        technician.email( request.email() );
        technician.password( request.password() );

        return technician.build();
    }

    @Override
    public TechnicianSaveResponse modelToUserSaveResponse(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String nationalCode = null;
        String email = null;
        LocalDateTime registeredDate = null;
        Double rate = null;
        Integer totalScores = null;
        Long countScores = null;
        TechnicianStatus technicianStatus = null;
        Role role = null;

        id = technician.getId();
        firstName = technician.getFirstName();
        lastName = technician.getLastName();
        nationalCode = technician.getNationalCode();
        email = technician.getEmail();
        registeredDate = technician.getRegisteredDate();
        rate = technician.getRate();
        totalScores = technician.getTotalScores();
        if ( technician.getCountScores() != null ) {
            countScores = technician.getCountScores().longValue();
        }
        technicianStatus = technician.getTechnicianStatus();
        role = technician.getRole();

        Boolean isActive = null;

        TechnicianSaveResponse technicianSaveResponse = new TechnicianSaveResponse( id, firstName, lastName, nationalCode, email, registeredDate, rate, totalScores, countScores, technicianStatus, isActive, role );

        return technicianSaveResponse;
    }

    @Override
    public Technician technicianChangePasswordRequestToModel(TechnicianChangePasswordRequest request) {
        if ( request == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.email( request.email() );
        technician.password( request.password() );

        return technician.build();
    }

    @Override
    public TechnicianChangePasswordResponse modelToTechnicianChangePasswordResponse(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        String email = null;

        email = technician.getEmail();

        TechnicianChangePasswordResponse technicianChangePasswordResponse = new TechnicianChangePasswordResponse( email );

        return technicianChangePasswordResponse;
    }

    @Override
    public Technician technicianLoginRequestToModel(TechnicianLoginRequest request) {
        if ( request == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.email( request.email() );
        technician.password( request.password() );

        return technician.build();
    }

    @Override
    public TechnicianLoginResponse modelToTechnicianLoginResponse(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String email = null;

        firstName = technician.getFirstName();
        lastName = technician.getLastName();
        email = technician.getEmail();

        TechnicianLoginResponse technicianLoginResponse = new TechnicianLoginResponse( firstName, lastName, email );

        return technicianLoginResponse;
    }

    @Override
    public Technician technicianVerifyRequestToModel(TechnicianVerifyRequest request) {
        if ( request == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.email( request.email() );

        return technician.build();
    }

    @Override
    public TechnicianVerifyResponse modelToTechnicianVerifyResponse(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        String email = null;
        String firstName = null;
        String lastName = null;

        email = technician.getEmail();
        firstName = technician.getFirstName();
        lastName = technician.getLastName();

        TechnicianVerifyResponse technicianVerifyResponse = new TechnicianVerifyResponse( email, firstName, lastName );

        return technicianVerifyResponse;
    }

    @Override
    public Technician technicianIdToModel(Long technicianId) {
        if ( technicianId == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        return technician.build();
    }
}
