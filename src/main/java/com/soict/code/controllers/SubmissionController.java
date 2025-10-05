package com.soict.code.controllers;

import com.soict.code.models.Submission;
import com.soict.code.requests.SubmissionRequest;
import com.soict.code.responses.ProblemResponse;
import com.soict.code.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping
    public ResponseEntity<Submission> createSubmission(
            @RequestBody SubmissionRequest req
    ) throws Exception {
        Submission submission = submissionService.saveSubmission(req);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<Submission> getSubmission(
            @PathVariable Long id
    ) throws Exception {
        Submission submission = submissionService.findBySubmissionId(id);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }
}
