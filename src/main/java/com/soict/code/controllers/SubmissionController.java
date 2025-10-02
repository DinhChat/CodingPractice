package com.soict.code.controllers;

import com.soict.code.models.Submission;
import com.soict.code.requests.SubmissionRequest;
import com.soict.code.services.SubmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SubmissionController {
    private SubmissionService submissionService;

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
