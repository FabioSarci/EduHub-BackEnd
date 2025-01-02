package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.QuestionDao;
import com.infobasic.sviluppo_sowftare.model.Question;

import java.util.List;

public class QuestionService {
    private static QuestionDao questionDao = new QuestionDao();

    public Question addQuestion(Question question){
        return questionDao.create(question);
    }

    public Question editQuestion(Question question){
        return questionDao.update(question);
    }

    public Question findQuestioById(int id){
        return questionDao.findById(id);
    }

    public void deleteQuestionById(int id){
        questionDao.deleteById(id);
    }

    public List<Question> findAllQuestion(){
        return questionDao.findAll();
    }

    public List<Question> findQuestionsByQuizId(int quizId){
        return questionDao.findQuestionsByQuizId(quizId);
    }
}
