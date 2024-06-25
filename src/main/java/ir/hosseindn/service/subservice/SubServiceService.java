package ir.hosseindn.service.subservice;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.model.SubService;
import ir.hosseindn.repository.subservice.SubServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceRepository subServiceRepository;
    public SubService save(SubService subService){
        if(subServiceRepository.findByName(subService.getName()).isPresent())
            throw new DuplicateInformationException("A Sub-Service with this name is Already exists!");
        return subServiceRepository.save(subService);
    }
}
