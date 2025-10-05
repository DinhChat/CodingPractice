package com.soict.code.requests;

import lombok.Data;

@Data
public class SubmissionRequest {
    private Long problemId;
    private String language;
    private String submissionCode;
}
