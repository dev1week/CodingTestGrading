package com.coding.teset.codingtestgrading.global.exception;


import lombok.Getter;

import java.util.Map;

@Getter
public class DtoValidationException extends IllegalArgumentException{
    private Map<String, String> errorMap;

    public DtoValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

}

