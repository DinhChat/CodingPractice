package com.soict.code.responses;

import lombok.Data;

@Data
public class ProblemResponse {
    private Long problemId;
    private String problemName;
    private String problemDescription;
    private String language;
}
