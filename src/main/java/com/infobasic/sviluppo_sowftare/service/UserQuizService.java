package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.UserQuizDao;
import com.infobasic.sviluppo_sowftare.model.UserQuiz;

import java.util.List;

public class UserQuizService {

    private static UserQuizDao userQuizDao = new UserQuizDao();

    public UserQuiz addUserQuiz(UserQuiz userQuiz){
        return userQuizDao.create(userQuiz);
    }

    public UserQuiz editUserQuiz(UserQuiz userQuiz){
        return userQuizDao.update(userQuiz);
    }

    public UserQuiz findUserQuizById(int id){
        return userQuizDao.findById(id);
    }

    public List<UserQuiz> findAllUserQuiz(){
        return userQuizDao.findAll();
    }

    public void deleteUserQuizById(int id){
        userQuizDao.deleteById(id);
    }

    public List<UserQuiz> findUserQuizByUserId(int userId){
        return userQuizDao.findUserQuizByUserId(userId);
    }

    public List<UserQuiz> findUserQuizByQuizId(int userId){
        return userQuizDao.findUserQuizByQuizId(userId);
    }
}
