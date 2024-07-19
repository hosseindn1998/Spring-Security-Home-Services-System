package ir.hosseindn.controller.mainservice;

import ir.hosseindn.dto.mainservice.MainServiceFindAllResponse;
import ir.hosseindn.dto.mainservice.MainServiceSaveRequest;
import ir.hosseindn.dto.mainservice.MainServiceSaveResponse;
import ir.hosseindn.mapper.mainservice.MainServiceMapper;
import ir.hosseindn.model.MainService;
import ir.hosseindn.service.mainservice.MainServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class MainServiceController {
    private final MainServiceService mainServiceService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-MainService")
    public ResponseEntity<MainServiceSaveResponse> addMainService(@Valid @RequestBody MainServiceSaveRequest request) {
        MainService mappedMainService = MainServiceMapper.INSTANCE.mainServiceSaveRequestToModel(request);
        MainService saveMainService = mainServiceService.save(mappedMainService);
        return new ResponseEntity<>(MainServiceMapper.INSTANCE.modelToMainServiceSaveResponse(saveMainService), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TECHNICIAN','ROLE_CUSTOMER')")
    @GetMapping("/see-MainServices")
    public ResponseEntity<List<MainServiceFindAllResponse>> seeMainServices() {
        List<MainService> mainServiceList = mainServiceService.findAll();
        ;
        return new ResponseEntity<>(mainServiceList.stream()
                .map(MainServiceMapper.INSTANCE::modelToMainServiceFindAllResponse)
                .toList(), HttpStatus.FOUND);
    }
}
