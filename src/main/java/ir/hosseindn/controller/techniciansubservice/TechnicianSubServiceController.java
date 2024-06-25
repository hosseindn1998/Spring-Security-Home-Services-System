package ir.hosseindn.controller.techniciansubservice;

import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteResponse;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.mapper.techniciansubservice.TechnicianSubServiceMapper;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.service.techniciansubservice.TechnicianSubServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class TechnicianSubServiceController {
    private final TechnicianSubServiceService technicianSubServiceService;

    @PostMapping("/add-technician-to-subservice")
    public ResponseEntity<TechnicianSubServiceSaveResponse> addTechnicianToSubService(@Validated @RequestBody TechnicianSubServiceSaveRequest request) {
        TechnicianSubService mappedTechnicianSubService = TechnicianSubServiceMapper.INSTANCE.technicianSubServiceSaveRequestToModel(request);
        TechnicianSubService savedTechnicianSubService = technicianSubServiceService.save(mappedTechnicianSubService);
        return new ResponseEntity<>(TechnicianSubServiceMapper.INSTANCE.modelToTechnicianSubServiceSaveResponse(savedTechnicianSubService)
                , HttpStatus.CREATED);
    }

    @PatchMapping("/delete-technician-from-subservice")
    public ResponseEntity<TechnicianSubServiceDeleteResponse> deleteTechnicianFromSubService(@Validated @RequestBody TechnicianSubServiceDeleteRequest request) {
        TechnicianSubService mappedTechnicianSubService = TechnicianSubServiceMapper.INSTANCE.technicianSubServiceDeleteRequestToModel(request);
        technicianSubServiceService.delete(mappedTechnicianSubService);
        return new ResponseEntity<>(TechnicianSubServiceMapper.INSTANCE.modelToTechnicianSubServiceDeleteResponse(mappedTechnicianSubService), HttpStatus.OK);
    }


}
