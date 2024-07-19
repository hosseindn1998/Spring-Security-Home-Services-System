package ir.hosseindn.mapper.techniciansubservice;

import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.model.Role;
import ir.hosseindn.model.SubService;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianStatus;
import ir.hosseindn.model.TechnicianSubService;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-19T10:06:38+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class TechnicianSubServiceMapperImpl implements TechnicianSubServiceMapper {

    @Override
    public TechnicianSubService technicianSubServiceSaveRequestToModel(TechnicianSubServiceSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        TechnicianSubService.TechnicianSubServiceBuilder<?, ?> technicianSubService = TechnicianSubService.builder();

        return technicianSubService.build();
    }

    @Override
    public TechnicianSubServiceSaveResponse modelToTechnicianSubServiceSaveResponse(TechnicianSubService technicianSubService) {
        if ( technicianSubService == null ) {
            return null;
        }

        TechnicianSaveResponse technician = null;
        SubServiceSaveResponse subService = null;

        technician = technicianToTechnicianSaveResponse( technicianSubService.getTechnician() );
        subService = subServiceToSubServiceSaveResponse( technicianSubService.getSubService() );

        TechnicianSubServiceSaveResponse technicianSubServiceSaveResponse = new TechnicianSubServiceSaveResponse( technician, subService );

        return technicianSubServiceSaveResponse;
    }

    protected TechnicianSaveResponse technicianToTechnicianSaveResponse(Technician technician) {
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
        Boolean isActive = null;
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
        isActive = technician.getIsActive();
        role = technician.getRole();

        TechnicianSaveResponse technicianSaveResponse = new TechnicianSaveResponse( id, firstName, lastName, nationalCode, email, registeredDate, rate, totalScores, countScores, technicianStatus, isActive, role );

        return technicianSaveResponse;
    }

    protected SubServiceSaveResponse subServiceToSubServiceSaveResponse(SubService subService) {
        if ( subService == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Long basePrice = null;
        String description = null;

        id = subService.getId();
        name = subService.getName();
        basePrice = subService.getBasePrice();
        description = subService.getDescription();

        SubServiceSaveResponse subServiceSaveResponse = new SubServiceSaveResponse( id, name, basePrice, description );

        return subServiceSaveResponse;
    }
}
