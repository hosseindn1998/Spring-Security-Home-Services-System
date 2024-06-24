package ir.hosseindn.exception;

import ir.hosseindn.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateInformationException.class)
    public ResponseEntity<ExceptionDto> duplicateInformationExceptionHandler(DuplicateInformationException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(NotValidInformation.class)
    public ResponseEntity<ExceptionDto> NotValidInformationHandler(NotValidInformation e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

}
