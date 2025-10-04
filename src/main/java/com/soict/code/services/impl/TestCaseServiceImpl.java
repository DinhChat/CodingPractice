package com.soict.code.services.impl;

import com.soict.code.models.Problem;
import com.soict.code.models.TestCase;
import com.soict.code.repositories.ProblemRepository;
import com.soict.code.repositories.TestCaseRepository;
import com.soict.code.requests.TestCaseRequest;
import com.soict.code.services.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCaseServiceImpl implements TestCaseService {

    private final ProblemRepository problemRepository;
    private final TestCaseRepository testCaseRepository;

    @Autowired
    public TestCaseServiceImpl(ProblemRepository problemRepository, TestCaseRepository testCaseRepository) {
        this.problemRepository = problemRepository;
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public TestCase createTestCase(TestCaseRequest req) throws Exception {
        Problem problem = problemRepository.findById(req.getProblemId())
                .orElseThrow(() -> new Exception("Problem not found"));
        TestCase testCase = new TestCase();
        testCase.setProblem(problem);
        testCase.setInputData(req.getInputData());
        testCase.setExpectedOutput(req.getExpectedOutput());
        testCase.setIsSample(req.getIsSample());
        testCase.setTimeLimitMs(req.getTimeLimitMs());
        testCase.setMemoryLimitMb(req.getMemoryLimitMb());

        return testCaseRepository.save(testCase);
    }
}
