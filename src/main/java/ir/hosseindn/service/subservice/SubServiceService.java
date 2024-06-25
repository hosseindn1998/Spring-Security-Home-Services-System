package ir.hosseindn.service.subservice;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.SubService;
import ir.hosseindn.repository.subservice.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;
    public SubService save(SubService subService){
        if(subServiceRepository.findByName(subService.getName()).isPresent())
            throw new DuplicateInformationException("A Sub-Service with this name is Already exists!");
        return subServiceRepository.save(subService);
    }
    public SubService update(SubService subService){
        SubService foundedSubService = subServiceRepository.findByName(subService.getName()).orElseThrow(
                () -> new NotFoundException("Sub-Service with this name Not found!")
        );
        Optional.ofNullable(subService.getBasePrice()).ifPresent(foundedSubService::setBasePrice);
        Optional.ofNullable(subService.getDescription()).ifPresent(foundedSubService::setDescription);
        return subServiceRepository.save(foundedSubService);
    }
    public SubService findByName(SubService subService) {
        return subServiceRepository.findByName(subService.getName()).orElseThrow(
                () -> new NotFoundException("Sub-Service with this name Not found!")
        );
    }
}

