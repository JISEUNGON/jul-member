package com.julindang.member.exception;

import com.julindang.member.exception.member.MemberEmailNotFoundException;
import com.julindang.member.exception.member.MemberIdNotFoundException;
import com.julindang.member.exception.member.MemberLoginTypeNotExistException;
import com.julindang.member.exception.member.MemberNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * DEM
     * */
    @ExceptionHandler(MemberIdNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberIdNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-001", "Member id not found: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberEmailNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberEmailNotFoundException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-002", "Member email is not found: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-003", "There is not member.");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberLoginTypeNotExistException.class)
    public ResponseEntity<ApiErrorResponse> handleException(MemberLoginTypeNotExistException ex) {
        ApiErrorResponse response = new ApiErrorResponse("JEM-004", "This login type is not exist: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
