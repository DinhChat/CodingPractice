package com.soict.code.requests;

import lombok.Data;

@Data
public class TestCaseDTO {
    private String inputData;
    private String expectedOutput;
    private Boolean isSample = false;

    public TestCaseDTO(String inputData, String expectedOutput, Boolean isSample) {
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
        this.isSample = isSample;
    }
}
