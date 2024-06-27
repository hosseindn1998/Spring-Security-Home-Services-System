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

import java.io.IOException;
import java.nio.file.NoSuchFileException;
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
        ExceptionDto exceptionDto=new ExceptionDto(Arrays.toString(e.getDetailMessageArguments()),LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionDto>IOExceptionHandler(IOException e){
        log.warn(e.getMessage());
        ExceptionDto exceptionDto=new ExceptionDto(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ExceptionDto>NoSuchFileExceptionHandler(NoSuchFileException e){
        log.warn(String.format("file with path %s not found.",e.getMessage()));
        ExceptionDto exceptionDto=new ExceptionDto(String.format("file with path %s not found.",e.getMessage()),LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto>ConstraintViolationExceptionHandler(ConstraintViolationException e){
        log.warn(e.getMessage());
        ExceptionDto exceptionDto=new ExceptionDto(e.getMessage(),LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto,HttpStatus.NOT_FOUND);
    }
}
