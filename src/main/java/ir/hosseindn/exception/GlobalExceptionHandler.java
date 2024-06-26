package ir.hosseindn.exception;

import ir.hosseindn.dto.ExceptionDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

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
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto>NotFoundExceptionHandler(NotFoundException e){
        log.warn(e.getMessage());
        ExceptionDto exceptionDto=new ExceptionDto(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto>MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.warn(Arrays.toString(e.getDetailMessageArguments()));
        ExceptionDto exceptionDto=new ExceptionDto(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto,HttpStatus.CONFLICT);
    }
}
