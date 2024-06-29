package ir.hosseindn.mapper.technician;

import ir.hosseindn.dto.technician.TechnicianChangePasswordRequest;
import ir.hosseindn.dto.technician.TechnicianChangePasswordResponse;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.dto.technician.TechnicianLoginRequest;
import ir.hosseindn.dto.technician.TechnicianLoginResponse;
import ir.hosseindn.dto.technician.TechnicianSaveRequestWithoutPath;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.dto.technician.TechnicianVerifyRequest;
import ir.hosseindn.dto.technician.TechnicianVerifyResponse;
import ir.hosseindn.dto.wallet.WalletSaveRequest;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.Wallet;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T08:25:30+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class TechnicianMapperImpl implements TechnicianMapper {

    @Override
    public Technician technicianSaveRequestWithoutPathToModel(TechnicianSaveRequestWithoutPath request) {
        if ( request == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.firstName( request.firstName() );
        technician.lastName( request.lastName() );
        technician.nationalCode( request.nationalCode() );
        technician.email( request.email() );
        technician.password( request.password() );
        technician.wallet( walletSaveRequestToWallet( request.wallet() ) );

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
        LocalDate registeredDate = null;

        id = technician.getId();
        firstName = technician.getFirstName();
        lastName = technician.getLastName();
        nationalCode = technician.getNationalCode();
        email = technician.getEmail();
        registeredDate = technician.getRegisteredDate();

        TechnicianSaveResponse technicianSaveResponse = new TechnicianSaveResponse( id, firstName, lastName, nationalCode, email, registeredDate );

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
    public Technician technicianIdToModel(TechnicianId technicianId) {
        if ( technicianId == null ) {
            return null;
        }

        Technician.TechnicianBuilder<?, ?> technician = Technician.builder();

        technician.id( technicianId.id() );

        return technician.build();
    }

    protected Wallet walletSaveRequestToWallet(WalletSaveRequest walletSaveRequest) {
        if ( walletSaveRequest == null ) {
            return null;
        }

        Wallet.WalletBuilder<?, ?> wallet = Wallet.builder();

        wallet.amount( walletSaveRequest.amount() );

        return wallet.build();
    }
}
