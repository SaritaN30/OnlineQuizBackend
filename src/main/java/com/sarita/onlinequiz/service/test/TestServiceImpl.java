package com.sarita.onlinequiz.service.test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarita.onlinequiz.dto.QuestionDTO;
import com.sarita.onlinequiz.dto.QuestionResponse;
import com.sarita.onlinequiz.dto.SubmitTestDTO;
import com.sarita.onlinequiz.dto.TestDTO;
import com.sarita.onlinequiz.dto.TestDetailsDTO;
import com.sarita.onlinequiz.dto.TestResultDTO;
import com.sarita.onlinequiz.entities.Question;
import com.sarita.onlinequiz.entities.Test;
import com.sarita.onlinequiz.entities.TestResult;
import com.sarita.onlinequiz.entities.User;
import com.sarita.onlinequiz.repository.QuestionRepository;
import com.sarita.onlinequiz.repository.TestRepository;
import com.sarita.onlinequiz.repository.TestResultRepository;
import com.sarita.onlinequiz.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private UserRepository userRepository;

    public TestDTO createTest(TestDTO testDTO){
        Test test = new Test();

        test.setTitle(testDTO.getTitle());
        test.setDescription(testDTO.getDescription());
        test.setTime(testDTO.getTime());

        return testRepository.save(test).getDto();
        
    }

    public QuestionDTO addQuestionTest(QuestionDTO dto){
        Optional<Test> optionalTest = testRepository.findById(dto.getId());
        if(optionalTest.isPresent()){

            Question question = new Question();
            question.setTest(optionalTest.get());
            question.setQuestionText(dto.getQuestionText());
            question.setOptionA(dto.getOptionA());
            question.setOptionB(dto.getOptionB());
            question.setOptionC(dto.getOptionC());
            question.setOptionD(dto.getOptionD());
            question.setCorrectOption(dto.getCorrectOption());

            return questionRepository.save(question).getDto();

        }
        throw new EntityNotFoundException("Test Not Found");
        
    }

    public List<TestDTO> getAllTest(){
        return testRepository.findAll().stream().peek(test->test.setTime(test.getQuestions().size() * test.getTime())).collect(Collectors.toList())
        .stream().map(Test::getDto).collect(Collectors.toList());
    }

    public TestDetailsDTO getAllQuestionByTestId(Long id){
        Optional<Test> optionalTest = testRepository.findById(id);
        TestDetailsDTO testDetailsDTO = new TestDetailsDTO();
        if(optionalTest.isPresent()){
            TestDTO testDTO = optionalTest.get().getDto();
            testDTO.setTime(optionalTest.get().getTime() * optionalTest.get().getQuestions().size());
            testDetailsDTO.setTestDTO(testDTO);

            testDetailsDTO.setQuestion(optionalTest.get().getQuestions().stream().map(Question::getDto).toList());
            return testDetailsDTO;
        }
        return testDetailsDTO;

    }

    public TestResultDTO submitTest(SubmitTestDTO request){
        Test test = testRepository.findById(request.getTestId()).orElseThrow(()->new EntityNotFoundException("Test Not Found"));

        User user = userRepository.findById(request.getUserId()).orElseThrow(()->new EntityNotFoundException("Test Not Found"));
        
        int correctAnswers = 0;
        for(QuestionResponse response : request.getResponses()){
            Question question = questionRepository.findById(response.getQuestionId())
                    .orElseThrow(()->new EntityNotFoundException("Question not Found"));
            if(question.getCorrectOption().equals(response.getSelectedOption())){
                correctAnswers++;
            }
        }

        int totalQuestions = test.getQuestions().size();
        double percentage = ((double)correctAnswers/totalQuestions)*100;

        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setUser(user);
        testResult.setTotalQuestions(totalQuestions);
        testResult.setCorrectAnswers(correctAnswers);
        testResult.setPercentage(percentage);

        return testResultRepository.save(testResult).getDto();
   
    }

    public List<TestResultDTO> getAllTestResults(){

        return testResultRepository.findAll().stream().map(TestResult::getDto).collect(Collectors.toList());
    }

    public List<TestResultDTO> getAllTestResultOfUser(Long userId){
        return testResultRepository.findAllByUserId(userId).stream().map(TestResult::getDto).collect(Collectors.toList());
    }


}
