package com.josh.joinus.core.api.controller.meeting.exception;

import com.josh.joinus.core.exception.MultipleMeetingsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> validateError(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        StringBuilder errMessage = new StringBuilder();

        for (FieldError error : result.getFieldErrors()) {
            errMessage.append("[")
                    .append(error.getField())
                    .append("] ")
                    .append(":")
                    .append(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new CustomErrorResponse(errMessage.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MultipleMeetingsException.class)
    public ResponseEntity<CustomErrorResponse> multipleMeetingLeaderUser(MultipleMeetingsException exception) {
        return new ResponseEntity<>(new CustomErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
