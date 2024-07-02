package ir.hosseindn.dto.customer;

import ir.hosseindn.dto.technician.TechnicianSaveResponse;

import java.util.List;

public record UserCriteriaItemsResponse(
        List<CustomerSaveResponse> customerList,
        List<TechnicianSaveResponse> technicianList

) {
}
