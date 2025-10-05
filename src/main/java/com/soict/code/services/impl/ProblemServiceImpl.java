package com.soict.code.services.impl;

import com.soict.code.models.Problem;
import com.soict.code.repositories.ProblemRepository;
import com.soict.code.requests.ProblemRequest;
import com.soict.code.responses.ProblemResponse;
import com.soict.code.services.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public ProblemResponse getProblemById(Long problemId) throws Exception {
        Optional<Problem> res = problemRepository.getProblemByProblemId(problemId);
        if (res.isEmpty()) {
            throw new Exception("Problem not found with id: " + problemId);
        }
        ProblemResponse problemResponse = new ProblemResponse();
        problemResponse.setProblemId(res.get().getProblemId());
        problemResponse.setProblemName(res.get().getProblemName());
        problemResponse.setProblemDescription(res.get().getProblemDescription());
        problemResponse.setLanguage(res.get().getLanguage());
        return problemResponse;
    }

    @Override
    public Problem createProblem(ProblemRequest request) throws Exception {
        Problem problem = new Problem();
        problem.setProblemName(request.getProblemName());
        problem.setProblemDescription(request.getProblemDescription());
        problem.setLanguage(request.getLanguage());
        problem.setTimeLimitMs(request.getTimeLimitMs());
        problem.setMemoryLimitMb(request.getMemoryLimitMb());

        return problemRepository.save(problem);
    }
}
