package org.task.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.task.model.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGenericException(RuntimeException ex) {
        System.out.println("handleGenericException ....");
        return getExceptionResponse("Failed: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private ErrorResponse getExceptionResponse(final String message, final Integer errorCode) {
        final ErrorResponse exceptionResponse = new ErrorResponse();
        exceptionResponse.setErrorCode(errorCode.toString());
        exceptionResponse.setErrorDescription(message);
        log.error("message:{}", exceptionResponse);
        return exceptionResponse;
    }
}
