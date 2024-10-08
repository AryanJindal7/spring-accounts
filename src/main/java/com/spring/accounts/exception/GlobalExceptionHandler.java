package com.spring.accounts.exception;


import com.spring.accounts.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException customerAlreadyExistsException, WebRequest webRequest){

        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(webRequest.getDescription(false), HttpStatus.BAD_REQUEST,customerAlreadyExistsException.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
    }

}
