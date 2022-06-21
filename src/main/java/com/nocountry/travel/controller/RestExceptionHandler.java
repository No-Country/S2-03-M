package com.nocountry.travel.controller;

import com.nocountry.travel.dto.ApiErrorDTO;
import com.nocountry.travel.exception.ParamNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ParamNotFound.class})
    protected ResponseEntity<Object> handleParamNotFound(RuntimeException exception, WebRequest webRequest){
        ApiErrorDTO apiErrorDTO= new ApiErrorDTO(HttpStatus.BAD_REQUEST, exception.getMessage(), Arrays.asList("Exception: Param Not Found"));
        return handleExceptionInternal(exception,apiErrorDTO,new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
