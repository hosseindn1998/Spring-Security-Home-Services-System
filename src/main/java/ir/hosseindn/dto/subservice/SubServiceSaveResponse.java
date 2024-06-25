package ir.hosseindn.dto.subservice;

public record SubServiceSaveResponse(
        Long id,
        String name,
        Long basePrice,
        String description
) {
}
