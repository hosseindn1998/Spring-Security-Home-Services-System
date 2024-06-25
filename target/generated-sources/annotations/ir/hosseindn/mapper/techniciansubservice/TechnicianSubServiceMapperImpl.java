package ir.hosseindn.mapper.techniciansubservice;

import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.dto.technician.TechnicianSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteResponse;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.dto.wallet.WalletSaveRequest;
import ir.hosseindn.model.MainService;
import ir.hosseindn.model.SubService;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.model.Wallet;
import java.util.Arrays;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-25T19:19:54+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class TechnicianSubServiceMapperImpl implements TechnicianSubServiceMapper {

    @Override
    public TechnicianSubService technicianSubServiceSaveRequestToModel(TechnicianSubServiceSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        TechnicianSubService.TechnicianSubServiceBuilder<?, ?> technicianSubService = TechnicianSubService.builder();

        technicianSubService.technician( technicianSaveRequestToTechnician( request.technician() ) );
        technicianSubService.subService( subServiceSaveRequestToSubService( request.subService() ) );

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

    protected Wallet walletSaveRequestToWallet(WalletSaveRequest walletSaveRequest) {
        if ( walletSaveRequest == null ) {
            return null;
        }

        Wallet.WalletBuilder<?, ?> wallet = Wallet.builder();

        wallet.amount( walletSaveRequest.amount() );

        return wallet.build();
    }

    protected Technician technicianSaveRequestToTechnician(TechnicianSaveRequest technicianSaveRequest) {
        if ( technicianSaveRequest == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.firstName( technicianSaveRequest.firstName() );
        technician.lastName( technicianSaveRequest.lastName() );
        technician.nationalCode( technicianSaveRequest.nationalCode() );
        technician.email( technicianSaveRequest.email() );
        technician.password( technicianSaveRequest.password() );
        technician.wallet( walletSaveRequestToWallet( technicianSaveRequest.wallet() ) );
        byte[] avatar = technicianSaveRequest.avatar();
        if ( avatar != null ) {
            technician.avatar( Arrays.copyOf( avatar, avatar.length ) );
        }

        return technician.build();
    }

    protected MainService mainServiceSaveRequestToMainService(MainServiceSaveRequest mainServiceSaveRequest) {
        if ( mainServiceSaveRequest == null ) {
            return null;
        }

        MainService.MainServiceBuilder<?, ?> mainService = MainService.builder();

        mainService.name( mainServiceSaveRequest.name() );

        return mainService.build();
    }

    protected SubService subServiceSaveRequestToSubService(SubServiceSaveRequest subServiceSaveRequest) {
        if ( subServiceSaveRequest == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        subService.name( subServiceSaveRequest.name() );
        subService.basePrice( subServiceSaveRequest.basePrice() );
        subService.description( subServiceSaveRequest.description() );
        subService.mainService( mainServiceSaveRequestToMainService( subServiceSaveRequest.mainService() ) );

        return subService.build();
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

    protected TechnicianSaveRequest technicianToTechnicianSaveRequest(Technician technician) {
        if ( technician == null ) {
            return null;
        }

        String firstName = null;
        String lastName = null;
        String nationalCode = null;
        String email = null;
        String password = null;
        byte[] avatar = null;
        WalletSaveRequest wallet = null;

        firstName = technician.getFirstName();
        lastName = technician.getLastName();
        nationalCode = technician.getNationalCode();
        email = technician.getEmail();
        password = technician.getPassword();
        byte[] avatar1 = technician.getAvatar();
        if ( avatar1 != null ) {
            avatar = Arrays.copyOf( avatar1, avatar1.length );
        }
        wallet = walletToWalletSaveRequest( technician.getWallet() );

        TechnicianSaveRequest technicianSaveRequest = new TechnicianSaveRequest( firstName, lastName, nationalCode, email, password, avatar, wallet );

        return technicianSaveRequest;
    }
}
