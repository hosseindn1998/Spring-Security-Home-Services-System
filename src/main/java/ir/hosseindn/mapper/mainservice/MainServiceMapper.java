package ir.hosseindn.mapper.mainservice;

import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import ir.hosseindn.dto.mainservice.MainServiceSaveResponse;
import ir.hosseindn.model.MainService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MainServiceMapper {
    MainServiceMapper INSTANCE = Mappers.getMapper(MainServiceMapper.class);

    MainService mainServiceSaveRequestToModel(MainServiceSaveRequest request);

    MainServiceSaveResponse modelToMainServiceSaveResponse(MainService mainService);
}
