package com.soict.code.controllers;

import com.soict.code.models.Submission;
import com.soict.code.requests.SubmissionRequest;
import com.soict.code.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SubmissionController {
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Submission> createMenuItem(
            @RequestBody SubmissionRequest req
    ) throws Exception {
        Submission submission = submissionService.saveSubmission(req);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/result/{id}")
    public Submission getResult(@PathVariable Long id) throws Exception {
        return submissionService.findBySubmissionId(id);
    }
}
