package com.coding.teset.codingtestgrading.service;

import com.coding.teset.codingtestgrading.dto.request.GradingRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public interface GradingService {

    public String grading(GradingRequest gradingRequest) throws IOException, InterruptedException, TimeoutException;

}
