package ir.hosseindn.mapper.techniciansubservice;

import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.dto.technician.TechnicianSaveRequest;
import ir.hosseindn.dto.technician.TechnicianSaveRequestWithoutPath;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteResponse;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.dto.wallet.WalletSaveRequest;
import ir.hosseindn.model.SubService;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.model.Wallet;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T13:45:20+0330",
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

        TechnicianSaveRequest technician = null;

        technician = technicianToTechnicianSaveRequest( technicianSubService.getTechnician() );

        TechnicianSubServiceSaveResponse technicianSubServiceSaveResponse = new TechnicianSubServiceSaveResponse( technician );

        return technicianSubServiceSaveResponse;
    }

    @Override
    public TechnicianSubService technicianSubServiceDeleteRequestToModel(TechnicianSubServiceDeleteRequest request) {
        if ( request == null ) {
            return null;
        }

        TechnicianSubService.TechnicianSubServiceBuilder<?, ?> technicianSubService = TechnicianSubService.builder();

        technicianSubService.technician( technicianIdToTechnician( request.technician() ) );
        technicianSubService.subService( subServiceIdToSubService( request.subService() ) );

        return technicianSubService.build();
    }

    @Override
    public TechnicianSubServiceDeleteResponse modelToTechnicianSubServiceDeleteResponse(TechnicianSubService technicianSubService) {
        if ( technicianSubService == null ) {
            return null;
        }

        TechnicianSubServiceDeleteResponse technicianSubServiceDeleteResponse = new TechnicianSubServiceDeleteResponse();

        return technicianSubServiceDeleteResponse;
    }

    protected WalletSaveRequest walletToWalletSaveRequest(Wallet wallet) {
        if ( wallet == null ) {
            return null;
        }

        Long amount = null;

        amount = wallet.getAmount();

        WalletSaveRequest walletSaveRequest = new WalletSaveRequest( amount );

        return walletSaveRequest;
    }

    protected TechnicianSaveRequestWithoutPath technicianToTechnicianSaveRequestWithoutPath(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String nationalCode = null;
        String email = null;
        String password = null;
        WalletSaveRequest wallet = null;

        firstName = technician.getFirstName();
        lastName = technician.getLastName();
        nationalCode = technician.getNationalCode();
        email = technician.getEmail();
        password = technician.getPassword();
        wallet = walletToWalletSaveRequest( technician.getWallet() );

        TechnicianSaveRequestWithoutPath technicianSaveRequestWithoutPath = new TechnicianSaveRequestWithoutPath( firstName, lastName, nationalCode, email, password, wallet );

        return technicianSaveRequestWithoutPath;
    }

    protected TechnicianSaveRequest technicianToTechnicianSaveRequest(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        TechnicianSaveRequestWithoutPath technician1 = null;

        technician1 = technicianToTechnicianSaveRequestWithoutPath( technician );

        String imagePath = null;

        TechnicianSaveRequest technicianSaveRequest = new TechnicianSaveRequest( technician1, imagePath );

        return technicianSaveRequest;
    }

    protected Technician technicianIdToTechnician(TechnicianId technicianId) {
        if ( technicianId == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.id( technicianId.id() );

        return technician.build();
    }

    protected SubService subServiceIdToSubService(SubServiceId subServiceId) {
        if ( subServiceId == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        subService.id( subServiceId.id() );

        return subService.build();
    }
}
