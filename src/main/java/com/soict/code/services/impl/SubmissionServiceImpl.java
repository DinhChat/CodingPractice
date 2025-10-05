package com.soict.code.services.impl;

import com.soict.code.models.Problem;
import com.soict.code.models.Submission;
import com.soict.code.models.TestCase;
import com.soict.code.repositories.ProblemRepository;
import com.soict.code.repositories.SubmissionRepository;
import com.soict.code.repositories.TestCaseRepository;
import com.soict.code.requests.SubmissionPayload;
import com.soict.code.requests.SubmissionRequest;
import com.soict.code.requests.TestCaseDTO;
import com.soict.code.responses.SandboxResponse;
import com.soict.code.services.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    private final ProblemRepository problemRepository;
    private final SubmissionRepository submissionRepository;
    private final TestCaseRepository testCaseRepository;
    private final RestTemplate restTemplate;
    private final String sandboxUrl;

    @Autowired
    public SubmissionServiceImpl(
            ProblemRepository problemRepository,
            SubmissionRepository submissionRepository,
            TestCaseRepository testCaseRepository,
            RestTemplateBuilder builder,
            @Value("${sandbox.url}")
            String sandboxUrl
            ) {
        this.problemRepository = problemRepository;
        this.submissionRepository = submissionRepository;
        this.testCaseRepository = testCaseRepository;
        this.restTemplate = builder.build();
        this.sandboxUrl = sandboxUrl;
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
        Problem problem = problemRepository.findById(submissionReq.getProblemId())
                .orElseThrow(() -> new Exception("Problem not found"));
        Submission submission = new Submission();
        submission.setProblem(problem);
        submission.setLanguage(submissionReq.getLanguage());
        submission.setSubmissionCode(submissionReq.getSubmissionCode());
        submission.setStatus("QUEUED");

        List<TestCase> testCases = testCaseRepository.findByProblem_ProblemId(submissionReq.getProblemId());
        List<TestCaseDTO> testCaseDTOs = testCases.stream()
                .map(tc -> new TestCaseDTO(tc.getInputData(), tc.getExpectedOutput(),tc.getIsSample()))
                .toList();


        try {
            SubmissionPayload payload = new SubmissionPayload();
            payload.setSubmissionCode(submissionReq.getSubmissionCode());
            payload.setLanguage(submissionReq.getLanguage());
            payload.setTestcases(testCaseDTOs);
            payload.setTimeLimit(problem.getTimeLimitMs());
            payload.setMemoryLimit(problem.getMemoryLimitMb());


            ResponseEntity<SandboxResponse> response = restTemplate.postForEntity(
                    sandboxUrl + "/submissions/run",
                    payload,
                    SandboxResponse.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                SandboxResponse result = response.getBody();
                submission.setStatus("FINISHED");
                submission.setResult("ACCEPT");
            } else {
                submission.setStatus("FAILED");
            }
        } catch (Exception e) {
            submission.setStatus("ERROR");
            submission.setResult(e.getMessage());
        }

        return submissionRepository.save(submission);
    }
}
