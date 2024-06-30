package ir.hosseindn.dto.technician;

public record TechnicianSaveRequest(
        TechnicianSaveRequestWithoutPath technician,
        String imagePath
) {
}
