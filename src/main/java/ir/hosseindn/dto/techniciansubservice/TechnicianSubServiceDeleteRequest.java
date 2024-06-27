package ir.hosseindn.dto.techniciansubservice;

import ir.hosseindn.dto.subservice.SubServiceId;
import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.dto.technician.TechnicianId;
import ir.hosseindn.dto.technician.TechnicianSaveRequest;

public record TechnicianSubServiceDeleteRequest(
        TechnicianId technician,
        SubServiceId subService
) {
}
