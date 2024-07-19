package ir.hosseindn.mapper.offer;

import ir.hosseindn.dto.offer.*;
import ir.hosseindn.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OfferMapper {
    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

    Offer offerSaveRequestToModel(OfferSaveRequest request);

    OfferSaveResponse modelToOfferSaveResponse(Offer offer);

    Offer offerFindByOrderRequestToModel(OfferFindByOrderRequest request);

    List<OfferFindByOrderResponse> modelListToOfferFindByOrderResponseList(List<Offer> offerList);
    Offer offerIdToModel(Long offerId);
}
