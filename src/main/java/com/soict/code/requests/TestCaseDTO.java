package com.soict.code.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TestCaseDTO {
    @JsonProperty("input")
    private String inputData;
    @JsonProperty("expected_output")
    private String expectedOutput;

    public TestCaseDTO(String inputData, String expectedOutput) {
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
    }
}
