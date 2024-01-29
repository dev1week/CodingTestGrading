package com.coding.teset.codingtestgrading.controller;

import com.coding.teset.codingtestgrading.dto.request.GradingRequest;
import com.coding.teset.codingtestgrading.dto.response.GradingResponse;
import com.coding.teset.codingtestgrading.service.GradingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
@RequestMapping("grading")
@RequiredArgsConstructor
public class GradingController {

    private final GradingService gradingService;

    @PostMapping()
    public GradingResponse gradingPlusProb(@RequestBody @Valid GradingRequest gradingRequest, BindingResult bindingResult) throws IOException, InterruptedException, TimeoutException {
        log.info(gradingRequest.toString());
        String  output = gradingService.grading(gradingRequest);

        GradingResponse response = GradingResponse.builder()
                .output(output).build();


        if(output.equals(gradingRequest.getAnswer())){
            response.setCode("0000");
            response.setMsg("맞았습니다.");
        }else{
            response.setCode("9999");
            response.setMsg("답이 틀렸습니다.");
        }

        return response;
    }


}
