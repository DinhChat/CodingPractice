package com.soict.code.services;

import com.soict.code.models.Submission;
import com.soict.code.requests.SubmissionRequest;

public interface SubmissionService {
    public Submission findBySubmissionId(Long submissionId) throws Exception;
    public Submission saveSubmission(SubmissionRequest submissionReq) throws Exception;
}
