package ir.hosseindn.dto.technician;

import jakarta.validation.constraints.Pattern;

public record TechnicianSaveRequest(
        TechnicianSaveRequestWithoutPath technician,
        @Pattern(regexp = "^[^\\s]+:\\\\+[^\\s]+\\.(jpg|jpeg)$",message = "technician's image not found or not valid file path")
        String imagePath
) {
}
