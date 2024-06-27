package ir.hosseindn.mapper.subservice;

import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.dto.subservice.SubServiceUpdateRequest;
import ir.hosseindn.dto.subservice.SubServiceUpdateResponse;
import ir.hosseindn.dto.subservice.SubServiceWithoutMainService;
import ir.hosseindn.model.SubService;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-27T08:48:05+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class SubServiceMapperImpl implements SubServiceMapper {

    @Override
    public SubService subServiceWithoutMainServiceSaveRequestToModel(SubServiceWithoutMainService request) {
        if ( request == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        subService.name( request.name() );
        subService.basePrice( request.basePrice() );
        subService.description( request.description() );

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
}
