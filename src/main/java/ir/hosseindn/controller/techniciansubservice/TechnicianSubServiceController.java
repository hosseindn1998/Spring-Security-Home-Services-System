package ir.hosseindn.controller.techniciansubservice;

import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteResponse;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.mapper.techniciansubservice.TechnicianSubServiceMapper;
import ir.hosseindn.model.SubService;
import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.service.techniciansubservice.TechnicianSubServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class TechnicianSubServiceController {
    private final TechnicianSubServiceService technicianSubServiceService;

    @PostMapping("/add-technician-to-subservice")
    public ResponseEntity<TechnicianSubServiceSaveResponse> addTechnicianToSubService(@Valid @RequestBody TechnicianSubServiceSaveRequest request) {
        Technician technician= TechnicianMapper.INSTANCE.technicianIdToModel(request.technicianId());
        SubService subService= SubServiceMapper.INSTANCE.subServiceIdToModel(request.subServiceId());
        TechnicianSubService mappedTechnicianSubService = TechnicianSubService.builder()
                .subService(subService)
                .technician(technician)
                .build();
        TechnicianSubService savedTechnicianSubService = technicianSubServiceService.save(mappedTechnicianSubService);
        return new ResponseEntity<>(TechnicianSubServiceMapper.INSTANCE.modelToTechnicianSubServiceSaveResponse(savedTechnicianSubService)
                , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-technician-from-subservice")
    public ResponseEntity<TechnicianSubServiceDeleteResponse> deleteTechnicianFromSubService(@Valid @RequestBody TechnicianSubServiceDeleteRequest request) {
        Technician technician= TechnicianMapper.INSTANCE.technicianIdToModel(request.technician());
        SubService subService= SubServiceMapper.INSTANCE.subServiceIdToModel(request.subService());
        TechnicianSubService mappedTechnicianSubService = TechnicianSubService.builder()
                .subService(subService)
                .technician(technician)
                .build();
        technicianSubServiceService.delete(mappedTechnicianSubService);
        return new ResponseEntity<>(TechnicianSubServiceMapper.INSTANCE.modelToTechnicianSubServiceDeleteResponse(mappedTechnicianSubService), HttpStatus.OK);
    }


}
