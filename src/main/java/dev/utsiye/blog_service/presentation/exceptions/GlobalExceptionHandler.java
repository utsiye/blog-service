package dev.utsiye.blog_service.presentation.exceptions;

import dev.utsiye.blog_service.domain.exceptions.BaseException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 *   Global Exception Handler
 *   1. If it is custom BaseException (or subtype) -> getting HTTP STATUS CODE from EXCEPTION_STATUS_MAP
 *   2. If there is no note for this type of exception in EXCEPTION_STATUS_MAP -> Returning 500
 *   4. If it is some throwable exception (not custom) -> Returning 500
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends BaseException>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
        dev.utsiye.blog_service.domain.exceptions.UserNotFoundException.class,   HttpStatus.NOT_FOUND,
        dev.utsiye.blog_service.domain.exceptions.UserAlreadyExistsException.class, HttpStatus.CONFLICT,
        dev.utsiye.blog_service.domain.exceptions.InvalidCredentialsException.class, HttpStatus.UNAUTHORIZED
    );

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ProblemDetail> handleAppException(BaseException ex) {
        HttpStatus status = resolveStatus(ex);
        log.warn("App exception [{}]: {}", status, ex.getMessage());
        return buildResponse(status, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleUnexpected(Exception ex) {
        log.error("Unexpected error", ex);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }



    private static HttpStatus resolveStatus(BaseException ex) {
        return EXCEPTION_STATUS_MAP.getOrDefault(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ProblemDetail> buildResponse(HttpStatus status, String message) {
        ProblemDetail body = ProblemDetail.forStatus(status.value());
        body.setDetail(message);
        return ResponseEntity.status(status).body(body);
    }
}