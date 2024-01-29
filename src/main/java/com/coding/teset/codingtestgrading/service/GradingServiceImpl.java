package com.coding.teset.codingtestgrading.service;

import com.coding.teset.codingtestgrading.dto.request.GradingRequest;
import com.coding.teset.codingtestgrading.global.exception.InputCodeBuildErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class GradingServiceImpl implements  GradingService{


    public String grading(GradingRequest gradingRequest) throws IOException, InterruptedException, TimeoutException {
        String sourceCode = gradingRequest.getInputSourceCode();

        // 소스 코드를 파일에 저장
        String fileName = "Main.java";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(sourceCode);
        fileWriter.close();


        // JavaCompiler 인스턴스 얻기
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        String data = "1";
        InputStream inputData = new ByteArrayInputStream(data.getBytes());
        // 컴파일 수행
        int compilationResult = compiler.run(inputData, null, null, fileName);


        if (compilationResult == 0) {
            log.info("Compilation is successful");

            // 클래스 실행
            Process process = Runtime.getRuntime().exec("java " + fileName.replace(".java", ""));


            // 출력 스트림과 오류 스트림을 가져오기
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();

            // 각각의 스트림을 읽기 위한 BufferedReader 생성
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));

            // 출력 스트림에서 읽기
            String output;
            StringBuilder result = new StringBuilder();
            while ((output = inputReader.readLine()) != null) {
                log.info("Output: " + output);
                result.append(output);
            }

            // 오류 스트림에서 읽기
            int errorCount = 0;
            StringBuilder errorLog = new StringBuilder("Error 발생 \n");
            while ((output = errorReader.readLine()) != null) {
                errorCount = 0;
                log.info("Error: " + output);
                errorLog.append(output).append("\n");
            }

            if(errorCount >=1){
                throw new InputCodeBuildErrorException(errorLog.toString());
                //코드 실행 중 런타임 예외 발생
            }

            // 프로세스가 종료될 때까지 대기
            boolean isNotTimeOut = process.waitFor(gradingRequest.getTimeOut(), TimeUnit.MILLISECONDS);

            if(!isNotTimeOut){
                throw new TimeoutException("제출하신 코드가 시간초과 되었습니다");
            }


            return result.toString();

        } else {
            //빌드 에러 예외 발생
            log.info("Compilation failed");
            throw new InputCodeBuildErrorException("컴파일에 실패하였습니다.");
        }


    }


}
