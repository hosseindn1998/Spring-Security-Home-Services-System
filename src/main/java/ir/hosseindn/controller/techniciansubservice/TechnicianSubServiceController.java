package ir.hosseindn.controller.techniciansubservice;

import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceDeleteRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveRequest;
import ir.hosseindn.dto.techniciansubservice.TechnicianSubServiceSaveResponse;
import ir.hosseindn.mapper.techniciansubservice.TechnicianSubServiceMapper;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.service.techniciansubservice.TechnicianSubServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class TechnicianSubServiceController {
    private final TechnicianSubServiceService technicianSubServiceService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-technician-to-subservice")
    public ResponseEntity<TechnicianSubServiceSaveResponse> addTechnicianToSubService(@Valid @RequestBody TechnicianSubServiceSaveRequest request) {
        TechnicianSubService savedTechnicianSubService = technicianSubServiceService.save(request.technicianId(), request.subServiceId());
        return new ResponseEntity<>(TechnicianSubServiceMapper.INSTANCE.modelToTechnicianSubServiceSaveResponse(savedTechnicianSubService)
                , HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete-technician-from-subservice")
    public ResponseEntity<String> deleteTechnicianFromSubService(@Valid @RequestBody TechnicianSubServiceDeleteRequest request) {
        String result = technicianSubServiceService.delete(request.technicianId(), request.subServiceId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
