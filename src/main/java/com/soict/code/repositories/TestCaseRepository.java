package com.soict.code.repositories;

import com.soict.code.models.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCaseRepository extends JpaRepository<TestCase,Long> {

}
