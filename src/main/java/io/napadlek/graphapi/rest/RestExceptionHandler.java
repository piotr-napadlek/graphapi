package io.napadlek.graphapi.rest;

import io.napadlek.graphapi.business.service.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException businessException) {
        ErrorResponse errorResponse = convertToErrorResponse(businessException);
        errorResponse.setBusinessException(true);
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity handleAllExceptions(Exception exception) {
        ErrorResponse errorResponse = convertToErrorResponse(exception);
        errorResponse.setBusinessException(false);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(errorResponse);
    }

    private ErrorResponse convertToErrorResponse(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setExceptionClass(exception.getClass().toString());
        errorResponse.setStackTrace(Stream.of(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()));
        return errorResponse;
    }
}
