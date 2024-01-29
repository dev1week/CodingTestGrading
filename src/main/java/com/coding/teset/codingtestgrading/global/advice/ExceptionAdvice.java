package com.coding.teset.codingtestgrading.global.advice;

import com.coding.teset.codingtestgrading.dto.response.CommonResponse;
import com.coding.teset.codingtestgrading.dto.response.ResultCode;
import com.coding.teset.codingtestgrading.global.exception.DtoValidationException;
import com.coding.teset.codingtestgrading.global.exception.InputCodeBuildErrorException;
import com.coding.teset.codingtestgrading.global.exception.InputCodeErrorException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DtoValidationException.class)
    public CommonResponse<?> dtoValidationException(DtoValidationException e){

        return new CommonResponse<>(ResultCode.ERROR, e, e.getErrorMap());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(TimeoutException.class)
    public CommonResponse<?> badSqlGrammerException(TimeoutException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InputCodeBuildErrorException.class)
    public CommonResponse<?> InputCodeException(InputCodeBuildErrorException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(InputCodeErrorException.class)
    public CommonResponse<?> InputCodeException(InputCodeErrorException e){
        return new CommonResponse<>(ResultCode.ERROR, e);
    }
}
