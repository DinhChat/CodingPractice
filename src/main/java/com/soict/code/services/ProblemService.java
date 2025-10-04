package com.soict.code.services;

import com.soict.code.models.Problem;
import com.soict.code.requests.ProblemRequest;
import com.soict.code.responses.ProblemResponse;

public interface ProblemService {
    ProblemResponse getProblemById(Long problemId) throws Exception;
    Problem createProblem(ProblemRequest request) throws Exception;
}
