package com.soict.code.repositories;

import com.soict.code.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Long> {
    Optional<Submission> findBySubmissionId(Long submissionId);
}