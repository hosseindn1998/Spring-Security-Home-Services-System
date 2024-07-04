package ir.hosseindn.dto.mainservice;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MainServiceSaveRequest(
        @Column(unique = true)
        @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$",message = "Could be 1-29 char and contain only alphabet")
        @NotNull
        String name
) {
}
