package com.aseel.QuizApp.Service;

import com.aseel.QuizApp.Model.Question;
import com.aseel.QuizApp.Model.QuestionWrapper;
import com.aseel.QuizApp.Model.Quiz;
import com.aseel.QuizApp.Model.Response;
import com.aseel.QuizApp.dao.QuestionDao;
import com.aseel.QuizApp.dao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category , numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return  new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>() ;

        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId() , q.getQuestionTitle(), q.getOption1() , q.getOption2() , q.getOption3() , q.getOption4());
            questionsForUser.add(qw);
        }
        return  new ResponseEntity<>(questionsForUser  , HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        int score = 0 ;
        int i = 0 ;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                score++;

            i++;
        }
        return new ResponseEntity<>(score , HttpStatus.OK);
    }
}
