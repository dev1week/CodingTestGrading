package com.coding.teset.codingtestgrading.global.exception;

public class InputCodeErrorException extends RuntimeException{

    public InputCodeErrorException(String message){
        super("빌드에 실패하였습니다.");
    }


}
