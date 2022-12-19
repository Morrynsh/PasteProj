package ua.pasta.pasteproj.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionClass> anyExceptionHandler(Exception exception){

        ExceptionClass exceptionClass = new ExceptionClass();
        exceptionClass.setMessage(exception.getMessage());
        exceptionClass.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionClass, HttpStatus.BAD_REQUEST);

    }

}
