package com.soict.code.controllers;

import com.soict.code.models.Problem;
import com.soict.code.requests.ProblemRequest;
import com.soict.code.responses.ProblemResponse;
import com.soict.code.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/{id}")
    public ProblemResponse getProblem(@PathVariable Long id) throws Exception {
        return problemService.getProblemById(id);
    }

    @PostMapping
    public ResponseEntity<Problem> createProblem(
            @RequestBody ProblemRequest request
    ) throws Exception {
        Problem problem = problemService.createProblem(request);
        return new ResponseEntity<>(problem, HttpStatus.CREATED);
    }
}
