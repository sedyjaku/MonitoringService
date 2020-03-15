package cz.sedy.monitoringservice.exception.handler;

import cz.sedy.monitoringservice.controller.dto.response.ErrorResponse;
import cz.sedy.monitoringservice.exception.NotFoundException;
import cz.sedy.monitoringservice.exception.UnknownUserException;
import cz.sedy.monitoringservice.exception.UserNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static lombok.AccessLevel.PRIVATE;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception){
        log.error("Domain was not found", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ExceptionHandler(UnknownUserException.class)
    ResponseEntity<ErrorResponse> handleUnknownUser(UnknownUserException exception){
        log.error("User was not found", exception);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Unknown user"));
    }

    @ExceptionHandler(UserNotAuthorizedException.class)
    ResponseEntity<ErrorResponse> handleUserNotAuthorized(UserNotAuthorizedException exception){
        log.error("User has no authority", exception);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorResponse(HttpStatus.FORBIDDEN.value(), "User not authorized"));
    }
}
