package com.aseel.QuizApp.Service;

import com.aseel.QuizApp.Model.Question;
import com.aseel.QuizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll() , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category) , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        try {
            return new ResponseEntity<>("Question added successfully" , HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Question added successfully" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
