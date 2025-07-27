package com.eleccars.ElecCarsApp.exceptionHandler;

import com.eleccars.ElecCarsApp.types.ExceptionHandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public static ResponseEntity<?> generateRecordNotFoundExceptionResponse(RecordNotFoundException ex) {
        ExceptionHandlerResponse exceptionHandler = new ExceptionHandlerResponse(ex.getIsSuccess(), ex.getMessage(), ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandler);
    }

}
