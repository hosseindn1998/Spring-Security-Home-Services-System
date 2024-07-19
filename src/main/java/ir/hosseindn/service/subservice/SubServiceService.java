package ir.hosseindn.service.subservice;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.MainService;
import ir.hosseindn.model.SubService;
import ir.hosseindn.repository.subservice.SubServiceRepository;
import ir.hosseindn.service.mainservice.MainServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final MainServiceService mainServiceService;
    public SubService saveRequest(SubService subService,Long mainServiceId){
        subService.setMainService(mainServiceService.findById(mainServiceId));
        return save(subService);
    }
    public SubService save(SubService subService){
        if(subServiceRepository.findByName(subService.getName()).isPresent())
            throw new DuplicateInformationException("A Sub-Service with this name is Already exists!");
        return subServiceRepository.save(subService);
    }
    public SubService update(SubService subService){
        SubService foundedSubService = subServiceRepository.findById(subService.getId()).orElseThrow(
                () -> new NotFoundException("Sub-Service with this name Not found!")
        );
        Optional.ofNullable(subService.getBasePrice()).ifPresent(foundedSubService::setBasePrice);
        Optional.ofNullable(subService.getDescription()).ifPresent(foundedSubService::setDescription);
        subServiceRepository.updateDescriptionAndBasePrice(foundedSubService.getId(), subService.getDescription()
                , subService.getBasePrice());
        return foundedSubService;
    }
    public SubService findById(Long subServiceId) {
        return subServiceRepository.findById(subServiceId).orElseThrow(
                () -> new NotFoundException("Sub-Service with this id Not found!")
        );
    }
    public List<SubService> findAll() {
        List<SubService> subServiceList = subServiceRepository.findAll();
        if(subServiceList.isEmpty())
            throw new NotFoundException("Sub-Service with this name Not found!"
        );
        return subServiceList;
    }
}

