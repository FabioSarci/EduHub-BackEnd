package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.UserAnswerDao;
import com.infobasic.sviluppo_sowftare.model.UserAnswer;

import java.util.List;

public class UserAnswerService {

    private static UserAnswerDao userAnswerDao = new UserAnswerDao();

    public UserAnswer addUserAnswer(UserAnswer userAnswer){
        return userAnswerDao.create(userAnswer);
    }

    public UserAnswer getUserAnswerById(int id){
        return userAnswerDao.findById(id);
    }

    public List<UserAnswer> getUserAnswerByUserQuizId(int id){
        return userAnswerDao.getByUserQuizId(id);
    }
}
