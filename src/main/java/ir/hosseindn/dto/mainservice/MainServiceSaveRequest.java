package ir.hosseindn.dto.mainservice;

import jakarta.persistence.Column;

public record MainServiceSaveRequest(
        @Column(unique = true)
        String name
) {
}
