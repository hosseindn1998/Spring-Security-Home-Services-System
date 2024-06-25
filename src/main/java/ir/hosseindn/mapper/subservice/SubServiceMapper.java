package ir.hosseindn.mapper.subservice;

import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.model.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    SubService subServiceSaveRequestToModel(SubServiceSaveRequest request);

    SubServiceSaveResponse modelToSubServiceSaveResponse(SubService subService);
}
