package ir.hosseindn.mapper.techniciansubservice;

import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteResponse;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.model.TechnicianSubService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TechnicianSubServiceMapper {

    TechnicianSubServiceMapper INSTANCE = Mappers.getMapper(TechnicianSubServiceMapper.class);

    TechnicianSubService technicianSubServiceSaveRequestToModel(TechnicianSubServiceSaveRequest request);

    TechnicianSubServiceSaveResponse modelToTechnicianSubServiceSaveResponse(TechnicianSubService technicianSubService);


}