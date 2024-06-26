package ir.hosseindn.mapper.offer;

import ir.hosseindn.dto.offer.OfferFindByOrderRequest;
import ir.hosseindn.dto.offer.OfferSaveRequest;
import ir.hosseindn.dto.offer.OfferSaveResponse;
import ir.hosseindn.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE= Mappers.getMapper(OfferMapper.class);
    Offer offerSaveRequestToModel(OfferSaveRequest request);
    OfferSaveResponse modelToOfferSaveResponse(Offer offer);
    Offer offerFindByOrderRequestToModel(OfferFindByOrderRequest request);

}
