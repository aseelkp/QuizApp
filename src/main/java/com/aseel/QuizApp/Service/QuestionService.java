package com.aseel.QuizApp.Service;

import com.aseel.QuizApp.Model.Question;
import com.aseel.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {

        return questionDao.findAll();
    }
}
