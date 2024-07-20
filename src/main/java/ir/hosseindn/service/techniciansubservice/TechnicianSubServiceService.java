package ir.hosseindn.service.techniciansubservice;

import ir.hosseindn.exception.DuplicateInformationException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.SubService;
import ir.hosseindn.model.TechnicianSubService;
import ir.hosseindn.repository.techniciansubservice.TechnicianSubServiceRepository;
import ir.hosseindn.service.order.OrderService;
import ir.hosseindn.service.subservice.SubServiceService;
import ir.hosseindn.service.technician.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianSubServiceService {
    private final TechnicianSubServiceRepository technicianSubServiceRepository;
    private final OrderService orderService;
    private final TechnicianService technicianService;
    private final SubServiceService subServiceService;


    public TechnicianSubService save(Long technicianId, Long subServiceId) {
        if (technicianSubServiceRepository.findBySubServiceAndTechnician(technicianId, subServiceId).isPresent())
            throw new DuplicateInformationException("this technician already has relation by subService");
        TechnicianSubService technicianSubService = TechnicianSubService.builder()
                .subService(subServiceService.findById(subServiceId))
                .technician(technicianService.findById(technicianId))
                .build();
        return technicianSubServiceRepository.save(technicianSubService);
    }

    public String delete(Long technicianId, Long subServiceId) {
        if (technicianSubServiceRepository.findBySubServiceAndTechnician(technicianId, subServiceId).isEmpty())
            throw new NotFoundException("this technician already hasn't relation by subService");
        technicianSubServiceRepository.deleteById(technicianId, subServiceId);
        return String.format("relation between technician id %s Sub-service id %s deleted", technicianId, subServiceId);
    }

    public List<SubService> findByTechnician(String email) {
        List<SubService> subServiceList = technicianSubServiceRepository.findByTechnician(email);
        if (subServiceList.isEmpty())
            throw new NotFoundException("Technician dosn't have any sub-service");
        return subServiceList;
    }

    public List<Order> seeTechnicianOrderList(String email) {
        List<SubService> subServiceList = findByTechnician(email);
        List<Order> orderList = new ArrayList<>();
        subServiceList.forEach(s -> orderList.addAll(orderService.seeAllBySubService(s.getName())));
        if (orderList.isEmpty())
            throw new NotFoundException("Any order Not found ");
        return orderList;
    }


}
