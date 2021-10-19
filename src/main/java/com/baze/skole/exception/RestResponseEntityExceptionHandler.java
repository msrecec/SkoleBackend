package com.baze.skole.exception;

import com.baze.skole.model.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorMessage message =  new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> badParamsException(BadRequestException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorMessage> internalServerError(InternalServerErrorException exception, WebRequest request) {
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
