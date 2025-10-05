package com.soict.code.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseRequest {
    private Long problemId;
    private String inputData;
    private String expectedOutput;
    private Boolean isSample = false;
}
