package ir.hosseindn.mapper.mainservice;

import ir.hosseindn.dto.mainservice.MainServiceFindAllRequest;
import ir.hosseindn.dto.mainservice.MainServiceFindAllResponse;
import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import ir.hosseindn.dto.mainservice.MainServiceSaveResponse;
import ir.hosseindn.model.MainService;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T10:24:53+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class MainServiceMapperImpl implements MainServiceMapper {

    @Override
    public MainService mainServiceSaveRequestToModel(MainServiceSaveRequest request) {
        if ( request == null ) {
            return null;
        }

        MainService.MainServiceBuilder<?, ?> mainService = MainService.builder();

        mainService.name( request.name() );

        return mainService.build();
    }

    @Override
    public MainServiceSaveResponse modelToMainServiceSaveResponse(MainService mainService) {
        if ( mainService == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = mainService.getId();
        name = mainService.getName();

        MainServiceSaveResponse mainServiceSaveResponse = new MainServiceSaveResponse( id, name );

        return mainServiceSaveResponse;
    }

    @Override
    public MainService mainServiceFindAllRequestToModel(MainServiceFindAllRequest request) {
        if ( request == null ) {
            return null;
        }

        MainService.MainServiceBuilder<?, ?> mainService = MainService.builder();

        return mainService.build();
    }

    @Override
    public MainServiceFindAllResponse modelToMainServiceFindAllResponse(MainService mainService) {
        if ( mainService == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = mainService.getId();
        name = mainService.getName();

        MainServiceFindAllResponse mainServiceFindAllResponse = new MainServiceFindAllResponse( id, name );

        return mainServiceFindAllResponse;
    }
}
