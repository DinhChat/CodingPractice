package com.soict.code.controllers;

import com.soict.code.models.TestCase;
import com.soict.code.requests.TestCaseRequest;
import com.soict.code.services.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/testcases")
public class TestCaseController {
    private final TestCaseService testCaseService;

    @Autowired
    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping
    public ResponseEntity<TestCase> addTestCase(
            @RequestBody TestCaseRequest request
    ) throws Exception {
        TestCase testCase = testCaseService.createTestCase(request);
        return new ResponseEntity<>(testCase,HttpStatus.CREATED);
    }
}
