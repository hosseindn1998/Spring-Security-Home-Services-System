package ir.hosseindn.mapper.subservice;

import ir.hosseindn.dto.subservice.*;
import ir.hosseindn.model.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    SubService subServiceWithoutMainServiceSaveRequestToModel(SubServiceWithoutMainService request);

    SubServiceSaveResponse modelToSubServiceSaveResponse(SubService subService);
    SubService subServiceUpdateRequestToModel(SubServiceUpdateRequest request);
    SubServiceUpdateResponse modelToSubServiceUpdateResponse(SubService subService);
    SubService subServiceFindAllRequestToModel(SubServiceFindAllRequest request);
    SubServiceFindAllResponse modelToSubServiceFindAllResponse(SubService subService);
    SubService subServiceIdToModel(SubServiceId subServiceId);
}
