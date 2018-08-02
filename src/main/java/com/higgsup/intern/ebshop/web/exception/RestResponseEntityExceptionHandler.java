package com.higgsup.intern.ebshop.web.exception;

import com.higgsup.intern.ebshop.dto.ApiErrorDTO;
import com.higgsup.intern.ebshop.exception.ResourceNotFoundException;
import com.higgsup.intern.ebshop.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(Exception ex, WebRequest request) {
        ApiErrorDTO responseBody = new ApiErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(Exception ex, WebRequest request) {
        ApiErrorDTO responseBody = new ApiErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        ApiErrorDTO responseBody = new ApiErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
