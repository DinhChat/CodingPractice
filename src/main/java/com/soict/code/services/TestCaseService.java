package com.soict.code.services;

import com.soict.code.models.TestCase;
import com.soict.code.requests.TestCaseRequest;

public interface TestCaseService {
    TestCase createTestCase(TestCaseRequest req) throws Exception;
}
