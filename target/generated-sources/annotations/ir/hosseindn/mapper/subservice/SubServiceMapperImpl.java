package ir.hosseindn.mapper.subservice;

import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.dto.subservice.SubServiceUpdateRequest;
import ir.hosseindn.dto.subservice.SubServiceUpdateResponse;
import ir.hosseindn.model.MainService;
import ir.hosseindn.model.SubService;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-26T13:34:13+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class SubServiceMapperImpl implements SubServiceMapper {

    @Override
    public SubService subServiceSaveRequestToModel(SubServiceSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        subService.name( request.name() );
        subService.basePrice( request.basePrice() );
        subService.description( request.description() );
        subService.mainService( mainServiceSaveRequestToMainService( request.mainService() ) );

        return subService.build();
    }

    @Override
    public SubServiceSaveResponse modelToSubServiceSaveResponse(SubService subService) {
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

    @Override
    public SubService subServiceUpdateRequestToModel(SubServiceUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        subService.name( request.name() );

        return subService.build();
    }

    @Override
    public SubServiceUpdateResponse modelToSubServiceUpdateResponse(SubService subService) {
        if ( subService == null ) {
            return null;
        }

        String name = null;

        name = subService.getName();

        SubServiceUpdateResponse subServiceUpdateResponse = new SubServiceUpdateResponse( name );

        return subServiceUpdateResponse;
    }

    protected MainService mainServiceSaveRequestToMainService(MainServiceSaveRequest mainServiceSaveRequest) {
        if ( mainServiceSaveRequest == null ) {
            return null;
        }

        MainService.MainServiceBuilder<?, ?> mainService = MainService.builder();

        mainService.name( mainServiceSaveRequest.name() );

        return mainService.build();
    }
}
