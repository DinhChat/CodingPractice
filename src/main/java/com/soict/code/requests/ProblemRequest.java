package com.soict.code.requests;

import lombok.Data;

@Data
public class ProblemRequest {
    private String problemName;
    private String problemDescription;
    private String language;
}
