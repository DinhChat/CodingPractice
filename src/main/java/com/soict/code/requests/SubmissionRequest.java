package com.soict.code.requests;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Long submissionId;
    private Long problemId;
    private String language;
    private String code;
}
