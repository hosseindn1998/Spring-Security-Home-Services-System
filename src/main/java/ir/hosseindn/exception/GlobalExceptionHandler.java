package ir.hosseindn.exception;

import ir.hosseindn.dto.ExceptionDto;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateInformationException.class)
    public ResponseEntity<ExceptionDto> duplicateInformationExceptionHandler(DuplicateInformationException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotEnoughAccountBalanceException.class)
    public ResponseEntity<ExceptionDto> notEnoughAccountBalanceExceptionHandler(NotEnoughAccountBalanceException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotValidInformation.class)
    public ResponseEntity<ExceptionDto> notValidInformationHandler(NotValidInformation e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> notFoundExceptionHandler(NotFoundException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.warn(Arrays.toString(e.getDetailMessageArguments()));
        ExceptionDto exceptionDto = new ExceptionDto(Arrays.toString(e.getDetailMessageArguments()), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionDto> iOExceptionHandler(IOException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchFileException.class)
    public ResponseEntity<ExceptionDto> noSuchFileExceptionHandler(NoSuchFileException e) {
        log.warn(String.format("file with path %s not found.", e.getMessage()));
        ExceptionDto exceptionDto = new ExceptionDto(String.format("file with path %s not found.", e.getMessage()), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDto> nonstraintViolationExceptionHandler(ConstraintViolationException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) {
        log.warn(e.getMostSpecificCause().toString());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionDto> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.warn(e.getMostSpecificCause().toString());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ExceptionDto> connectExceptionHandler(ConnectException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.PRECONDITION_FAILED);
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ExceptionDto> illegalStateExceptionExceptionHandler(IllegalStateException e) {
        log.warn(e.getMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(exceptionDto, HttpStatus.PRECONDITION_FAILED);
    }

}
