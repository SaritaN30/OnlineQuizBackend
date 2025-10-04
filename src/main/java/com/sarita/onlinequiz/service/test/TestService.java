package com.sarita.onlinequiz.service.test;

import java.util.List;

import com.sarita.onlinequiz.dto.QuestionDTO;
import com.sarita.onlinequiz.dto.SubmitTestDTO;
import com.sarita.onlinequiz.dto.TestDTO;
import com.sarita.onlinequiz.dto.TestDetailsDTO;
import com.sarita.onlinequiz.dto.TestResultDTO;

public interface TestService {

    TestDTO createTest(TestDTO testDTO);

    QuestionDTO addQuestionTest(QuestionDTO dto);

    List<TestDTO> getAllTest();

    TestDetailsDTO getAllQuestionByTestId(Long id);

    TestResultDTO submitTest(SubmitTestDTO request);

    List<TestResultDTO> getAllTestResults();

    List<TestResultDTO> getAllTestResultOfUser(Long userId);

}
