package ir.hosseindn.dto;

import java.time.LocalDateTime;

public record ExceptionDto(String message,
                           LocalDateTime localDateTime) {
}
