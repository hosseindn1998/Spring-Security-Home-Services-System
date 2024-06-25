package ir.hosseindn.dto.techniciansubservice;

import ir.hosseindn.dto.subservice.SubServiceSaveRequest;
import ir.hosseindn.dto.technician.TechnicianSaveRequest;

public record TechnicianSubServiceSaveRequest(
        TechnicianSaveRequest technician,
        SubServiceSaveRequest subService
) {
}
