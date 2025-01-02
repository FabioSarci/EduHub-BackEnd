package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.QuizDao;
import com.infobasic.sviluppo_sowftare.model.Quiz;

import java.util.List;

public class QuizService {

    private static QuizDao quizDao = new QuizDao();

    public Quiz addQuiz (Quiz quiz){
        return quizDao.create(quiz);
    }

    public Quiz editQuiz (Quiz quiz){
        return quizDao.update(quiz);
    }

    public Quiz findQuizById(int id){
        return quizDao.findById(id);
    }

    public List<Quiz> findAllQuiz(){
        return quizDao.findAll();
    }

    public void deleteQuizById(int id){
        quizDao.deleteById(id);
    }

    public List<Quiz> getQuizByCourseId(int courseId){
        return quizDao.getQuizByCourseId(courseId);
    }
}
