package ir.hosseindn.controller.subservice;

import ir.hosseindn.dto.subservice.*;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.model.SubService;
import ir.hosseindn.service.mainservice.MainServiceService;
import ir.hosseindn.service.subservice.SubServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class SubServiceController {
    private final SubServiceService subServiceService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-SubService")
    public ResponseEntity<SubServiceSaveResponse> addSubService(@Valid @RequestBody SubServiceSaveRequest request) {
        SubService mappedSubService = SubServiceMapper.INSTANCE.subServiceSaveRequestToModel(request);
        SubService savedSubService = subServiceService.saveRequest(mappedSubService, request.mainServiceId());
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelToSubServiceSaveResponse(savedSubService), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/update-SubService")
    public ResponseEntity<SubServiceUpdateResponse> updateSubService(@Valid @RequestBody SubServiceUpdateRequest request) {
        SubService mappedSubService = SubServiceMapper.INSTANCE.subServiceUpdateRequestToModel(request);
        SubService savedSubService = subServiceService.update(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelToSubServiceUpdateResponse(savedSubService), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TECHNICIAN','ROLE_CUSTOMER')")
    @GetMapping("/see-sub-services")
    public ResponseEntity<List<SubServiceFindAllResponse>> seeSubServices() {
        List<SubService> subServiceList = subServiceService.findAll();
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelListToSubServiceFindAllResponseList(subServiceList), HttpStatus.FOUND);
    }
}
