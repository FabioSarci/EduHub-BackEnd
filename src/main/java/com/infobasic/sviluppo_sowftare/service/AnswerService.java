package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.AnswerDao;
import com.infobasic.sviluppo_sowftare.model.Answer;

import java.util.List;

public class AnswerService {
    private static AnswerDao answerDao = new AnswerDao();

    public Answer addAnswer(Answer answer){
        return answerDao.create(answer);
    }

    public Answer editAnswer(Answer answer){
        return answerDao.update(answer);
    }

    public Answer findAnswerById(int id){
        return answerDao.findById(id);
    }

    public List<Answer> findAllAnswer(){
        return answerDao.findAll();
    }

    public void deleteAnswer(int id){
        answerDao.deleteById(id);
    }

    public List<Answer> getAnswersByQuestionId(int questionId){
        return answerDao.getAnswersByQuestionId(questionId);
    }
}
