package com.soict.code.services;

import com.soict.code.models.Submission;
import com.soict.code.requests.SubmissionRequest;

public interface SubmissionService {
    Submission findBySubmissionId(Long submissionId) throws Exception;
    Submission saveSubmission(SubmissionRequest submissionReq) throws Exception;
}
