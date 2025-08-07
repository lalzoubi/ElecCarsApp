package com.eleccars.ElecCarsApp.exceptionHandler;

import com.eleccars.ElecCarsApp.types.ExceptionHandlerResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public static ResponseEntity<?> generateRecordNotFoundExceptionResponse(RecordNotFoundException ex) {
        ExceptionHandlerResponse exceptionHandler = new ExceptionHandlerResponse(ex.getIsSuccess(), ex.getMessage(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandler);
    }

    @ExceptionHandler(JwtNotFoundException.class)
    public static ResponseEntity<?> generateJwtNotFoundExceptionResponse(JwtNotFoundException ex) {
        ExceptionHandlerResponse exceptionHandler = new ExceptionHandlerResponse(ex.getIsSuccess(), ex.getMessage(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandler);
    }


    @ExceptionHandler(BindException.class)
    public static ResponseEntity<HashMap<String, List<String>>> generateJwtNotFoundExceptionResponse(BindException ex) {
        List<String> errorsList = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        HashMap<String, List<String>> errors = new HashMap<>();
        errors.put("errors", errorsList);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
