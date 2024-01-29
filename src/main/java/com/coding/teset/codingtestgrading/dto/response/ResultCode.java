package com.coding.teset.codingtestgrading.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS("0000", "SUCCESS"),
    ERROR("9999", "FAIL");

    private String code;
    private String message;
}

