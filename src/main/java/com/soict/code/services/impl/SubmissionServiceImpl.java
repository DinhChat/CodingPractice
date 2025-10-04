package com.soict.code.services.impl;

import com.soict.code.models.Submission;
import com.soict.code.repositories.SubmissionRepository;
import com.soict.code.requests.SubmissionRequest;
import com.soict.code.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Override
    public Submission findBySubmissionId(Long submissionId) throws Exception {
        Optional<Submission> submission = submissionRepository.findBySubmissionId(submissionId);
        if (submission.isEmpty()) {
            throw new Exception("Submission not found with id: " + submissionId);
        }
        return submission.get();
    }

    @Override
    public Submission saveSubmission(SubmissionRequest submissionReq) throws Exception {
        Submission submission = new Submission();
        submission.setLanguage(submissionReq.getLanguage());
        submission.setCode(submissionReq.getCode());
        submission.setStatus("QUEUED");
        return submissionRepository.save(submission);
    }
}
