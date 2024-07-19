package ir.hosseindn.controller.offer;

import ir.hosseindn.dto.offer.OfferFindByOrderResponse;
import ir.hosseindn.dto.offer.OfferSaveRequest;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.mapper.offer.OfferMapper;
import ir.hosseindn.model.Offer;
import ir.hosseindn.service.offer.OfferService;
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
public class OfferController {
    private final OfferService offerService;

    @PostMapping("/add-offer")
    public ResponseEntity<OfferSaveResponse> addOffer(@Valid @RequestBody OfferSaveRequest request) {
        Offer mappedOffer = OfferMapper.INSTANCE.offerSaveRequestToModel(request);
        Offer savedOffer = offerService.addOfferByTechnician(mappedOffer,request.technicianId(),request.odrerId());
        return new ResponseEntity<>(OfferMapper.INSTANCE.modelToOfferSaveResponse(savedOffer), HttpStatus.CREATED);
    }

    @GetMapping("/get-offers-of-order")
    public ResponseEntity<List<OfferFindByOrderResponse>> getOfferOfOrder(@Valid @RequestParam Long orderId) {
        List<Offer> offerList = offerService.findAllByOrder(orderId);
        return new ResponseEntity<>(OfferMapper.INSTANCE.modelListToOfferFindByOrderResponseList(offerList), HttpStatus.FOUND);
    }

}
