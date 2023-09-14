package com.aseel.QuizApp.Controller;

import com.aseel.QuizApp.Model.Question;
import com.aseel.QuizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }
}
