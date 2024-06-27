package ir.hosseindn.service.mainservice;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.MainService;
import ir.hosseindn.repository.mainservice.MainServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceService {
    private final MainServiceRepository mainServiceRepository;

    public MainService save(MainService mainService) {
        if (mainServiceRepository.findByName(mainService.getName()).isPresent())
            throw new DuplicateInformationException("A Main-Service with this name Already Exists!");
        return mainServiceRepository.save(mainService);
    }
    public List<MainService> findAll() {
        if (mainServiceRepository.findAll().isEmpty())
            throw new NotFoundException("No Main-Service Found!");
        return mainServiceRepository.findAll();
    }
    public MainService findById(Long id){
        return mainServiceRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Main service not found")
        );
    }
}
