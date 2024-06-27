package ir.hosseindn.controller.subservice;

import ir.hosseindn.dto.mainservice.MainServiceFindAllResponse;
import ir.hosseindn.dto.subservice.*;
import ir.hosseindn.mapper.mainservice.MainServiceMapper;
import ir.hosseindn.mapper.subservice.SubServiceMapper;
import ir.hosseindn.model.MainService;
import ir.hosseindn.model.SubService;
import ir.hosseindn.service.mainservice.MainServiceService;
import ir.hosseindn.service.subservice.SubServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class SubServiceController {
    private final SubServiceService subServiceService;
    private final MainServiceService mainServiceService;
    @PostMapping("/add-SubService")
    public ResponseEntity<SubServiceSaveResponse>addSubService(@Valid @RequestBody SubServiceSaveRequest request){
        SubService mappedSubService= SubServiceMapper.INSTANCE.subServiceWithoutMainServiceSaveRequestToModel(request.subService());
        mappedSubService.setMainService(mainServiceService.findById(request.mainServiceId()));
        SubService savedSubService=subServiceService.save(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelToSubServiceSaveResponse(savedSubService), HttpStatus.CREATED);
    }
    @PatchMapping ("/update-SubService")
    public ResponseEntity<SubServiceUpdateResponse>updateSubService(@Valid @RequestBody SubServiceUpdateRequest request){
        SubService mappedSubService= SubServiceMapper.INSTANCE.subServiceUpdateRequestToModel(request);
        SubService savedSubService=subServiceService.update(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelToSubServiceUpdateResponse(savedSubService), HttpStatus.OK);
    }
    @GetMapping("/see-sub-services")
    public ResponseEntity<List<SubServiceFindAllResponse>> seeSubServices() {
        List<SubService> subServiceList = subServiceService.findAll();;
        return new ResponseEntity<>(subServiceList.stream()
                .map(SubServiceMapper.INSTANCE::modelToSubServiceFindAllResponse)
                .toList(), HttpStatus.FOUND);
    }
}
