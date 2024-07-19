package ir.hosseindn.mapper.subservice;

import ir.hosseindn.dto.subservice.*;
import ir.hosseindn.model.SubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubServiceMapper {
    SubServiceMapper INSTANCE = Mappers.getMapper(SubServiceMapper.class);

    SubService subServiceSaveRequestToModel(SubServiceSaveRequest request);

    SubServiceSaveResponse modelToSubServiceSaveResponse(SubService subService);
    SubService subServiceUpdateRequestToModel(SubServiceUpdateRequest request);
    SubServiceUpdateResponse modelToSubServiceUpdateResponse(SubService subService);
    SubService subServiceFindAllRequestToModel(SubServiceFindAllRequest request);
    List<SubServiceFindAllResponse> modelListToSubServiceFindAllResponseList(List<SubService> subService);
    SubService subServiceIdToModel(Long subServiceId);
    SubServiceId modelToSubServiceId(SubService subService);
}
