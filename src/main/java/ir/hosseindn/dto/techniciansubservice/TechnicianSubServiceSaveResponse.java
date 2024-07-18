package ir.hosseindn.dto.techniciansubservice;

import ir.hosseindn.dto.subservice.SubServiceSaveResponse;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;

public record TechnicianSubServiceSaveResponse(
        TechnicianSaveResponse technician,
        SubServiceSaveResponse subService
) {
}
