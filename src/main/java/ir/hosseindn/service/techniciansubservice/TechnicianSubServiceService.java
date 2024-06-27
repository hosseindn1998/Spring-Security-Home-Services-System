package ir.hosseindn.service.techniciansubservice;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.repository.techniciansubservice.TechnicianSubServiceRepository;
import ir.hosseindn.service.subservice.SubServiceService;
import ir.hosseindn.service.technician.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechnicianSubServiceService {
    private final TechnicianSubServiceRepository technicianSubServiceRepository;

    public TechnicianSubService save(TechnicianSubService technicianSubService) {
        if (technicianSubServiceRepository.findBySubServiceAndTechnician(technicianSubService.getTechnician(), technicianSubService.getSubService()).isPresent())
            throw new DuplicateInformationException("this technician already has relation by subService");
        return technicianSubServiceRepository.save(technicianSubService);
    }
    public void delete(TechnicianSubService technicianSubService) {
        if (technicianSubServiceRepository.findBySubServiceAndTechnician(technicianSubService.getTechnician(), technicianSubService.getSubService()).isEmpty())
            throw new NotFoundException("this technician already hasn't relation by subService");
        technicianSubServiceRepository.deleteById(technicianSubService.getTechnician().getId()
                ,technicianSubService.getSubService().getId());
    }

}
