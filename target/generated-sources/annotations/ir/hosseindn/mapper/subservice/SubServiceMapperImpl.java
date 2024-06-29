package ir.hosseindn.mapper.subservice;

import ir.hosseindn.dto.subservice.SubServiceFindAllRequest;
import ir.hosseindn.dto.subservice.SubServiceFindAllResponse;
import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.dto.subservice.SubServiceUpdateRequest;
import ir.hosseindn.dto.subservice.SubServiceUpdateResponse;
import ir.hosseindn.dto.subservice.SubServiceWithoutMainService;
import ir.hosseindn.model.SubService;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T10:24:53+0330",
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

        subService.id( request.id() );
        subService.basePrice( request.basePrice() );
        subService.description( request.description() );

        return subService.build();
    }

    @Override
    public SubServiceUpdateResponse modelToSubServiceUpdateResponse(SubService subService) {
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

        SubServiceUpdateResponse subServiceUpdateResponse = new SubServiceUpdateResponse( id, name, basePrice, description );

        return subServiceUpdateResponse;
    }

    @Override
    public SubService subServiceFindAllRequestToModel(SubServiceFindAllRequest request) {
        if ( request == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        return subService.build();
    }

    @Override
    public SubServiceFindAllResponse modelToSubServiceFindAllResponse(SubService subService) {
        if ( subService == null ) {
            return null;
        }

        String name = null;
        Long basePrice = null;
        String description = null;

        name = subService.getName();
        basePrice = subService.getBasePrice();
        description = subService.getDescription();

        SubServiceFindAllResponse subServiceFindAllResponse = new SubServiceFindAllResponse( name, basePrice, description );

        return subServiceFindAllResponse;
    }

    @Override
    public SubService subServiceIdToModel(SubServiceId subServiceId) {
        if ( subServiceId == null ) {
            return null;
        }

        SubService.SubServiceBuilder<?, ?> subService = SubService.builder();

        subService.id( subServiceId.id() );

        return subService.build();
    }

    @Override
    public SubServiceId modelToSubServiceId(SubService subService) {
        if ( subService == null ) {
            return null;
        }

        Long id = null;

        id = subService.getId();

        SubServiceId subServiceId = new SubServiceId( id );

        return subServiceId;
    }
}
