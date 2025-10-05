package com.soict.code.repositories;

import com.soict.code.models.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase,Long> {
    List<TestCase> findByProblem_ProblemId(Long problemId);
}
