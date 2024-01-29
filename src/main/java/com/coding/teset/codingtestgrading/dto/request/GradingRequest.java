package com.coding.teset.codingtestgrading.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GradingRequest {

    @NotEmpty(message="값이 존재하지 않습니다")
    private String inputSourceCode;

    @NotNull(message="값이 존재하지 않습니다.")
    private Long timeOut;

    @NotEmpty(message="값이 존재하지 않습니다.")
    private String answer;


}
