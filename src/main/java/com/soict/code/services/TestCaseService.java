package com.soict.code.services;

import com.soict.code.models.TestCase;
import com.soict.code.requests.TestCaseRequest;

import java.util.List;

public interface TestCaseService {
    TestCase createTestCase(TestCaseRequest req) throws Exception;
    List<TestCase> getTestCasesByProblemId(Long problemId) throws Exception;
}
