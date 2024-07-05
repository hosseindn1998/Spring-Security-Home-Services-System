package ir.hosseindn.controller.offer;

import ir.hosseindn.dto.offer.OfferFindByOrderRequest;
import ir.hosseindn.dto.offer.OfferFindByOrderResponse;
import ir.hosseindn.dto.offer.OfferSaveRequest;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.mapper.offer.OfferMapper;
import ir.hosseindn.mapper.order.OrderMapper;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import ir.hosseindn.service.offer.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class OfferController {
    private final OfferService offerService;

    @PostMapping("/add-offer")
    public ResponseEntity<OfferSaveResponse> addOffer(@Valid @RequestBody OfferSaveRequest request) {
        Order order = OrderMapper.INSTANCE.orderIdToModel(request.odrer());
        Technician technician = TechnicianMapper.INSTANCE.technicianIdToModel(request.technician());
        Offer mappedOffer = OfferMapper.INSTANCE.offerSaveRequestToModel(request.offer());
        mappedOffer.setOdrer(order);
        mappedOffer.setTechnician(technician);
        Offer savedOffer = offerService.save(mappedOffer);
        return new ResponseEntity<>(OfferMapper.INSTANCE.modelToOfferSaveResponse(savedOffer), HttpStatus.CREATED);
    }

    @GetMapping("/get-offers-of-order")
    public ResponseEntity<List<OfferFindByOrderResponse>> getOfferOfOrder(@Valid @RequestBody OfferFindByOrderRequest request) {
        Order mappedOrder = OrderMapper.INSTANCE.orderIdToModel(request.odrer());
        List<Offer> offerList = offerService.findAllByOrder(mappedOrder.getId());
        List<OfferFindByOrderResponse> list = offerList.stream().map(OfferMapper.INSTANCE::modelToOfferFindByOrderResponse).toList();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }
}
