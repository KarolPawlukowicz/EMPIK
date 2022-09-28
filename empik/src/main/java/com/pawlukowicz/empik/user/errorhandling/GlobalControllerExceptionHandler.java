package com.pawlukowicz.empik.user.errorhandling;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    private static final String NOT_FOUND_MESSAGE = "An item with the given identifier was not found";
    private static final String UNEXPECTED_ERROR = "An unexpected error occurred";

    @ExceptionHandler(Exception.class)
    public final @NonNull ResponseEntity<ErrorResponse> handleOtherException(final @NonNull Exception e) {
        String message;
        HttpStatus httpStatus;

        if(e instanceof HttpClientErrorException) {
            message = NOT_FOUND_MESSAGE;
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            log.error(e.getMessage(), e);
            message = UNEXPECTED_ERROR;
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        final var errorResponse = new ErrorResponse(httpStatus.value(), message);

        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
