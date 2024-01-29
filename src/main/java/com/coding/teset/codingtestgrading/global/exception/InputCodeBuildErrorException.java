package com.coding.teset.codingtestgrading.global.exception;


public class InputCodeBuildErrorException extends RuntimeException{

    public InputCodeBuildErrorException(String message) {
        super("빌드에 실패하였습니다.");
    }
}
