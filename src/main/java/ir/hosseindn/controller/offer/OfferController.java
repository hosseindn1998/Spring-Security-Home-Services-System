package ir.hosseindn.controller.offer;

import ir.hosseindn.dto.offer.OfferSaveRequest;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.mapper.offer.OfferMapper;
import ir.hosseindn.model.Offer;
import ir.hosseindn.service.offer.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Slf4j
public class OfferController {
    private final OfferService offerService;
    @PostMapping("/add-offer")
    public ResponseEntity<OfferSaveResponse>addOffer(@Validated @RequestBody OfferSaveRequest request){
        Offer mappedOffer= OfferMapper.INSTANCE.offerSaveRequestToModel(request);
        Offer savedOffer=offerService.save(mappedOffer);
        return new ResponseEntity<>(OfferMapper.INSTANCE.modelToOfferSaveResponse(savedOffer), HttpStatus.CREATED);
    }
}
