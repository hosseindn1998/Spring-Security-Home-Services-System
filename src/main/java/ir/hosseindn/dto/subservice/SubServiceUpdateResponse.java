package ir.hosseindn.dto.subservice;

public record SubServiceUpdateResponse(
        Long id,
        String name,
        Long basePrice,
        String description
) {
}
