package com.coding.teset.codingtestgrading.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GradingResponse {

    private String output;
    private String code;

    private String msg;
}
