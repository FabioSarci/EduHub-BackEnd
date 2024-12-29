package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.UserDao;
import com.infobasic.sviluppo_sowftare.model.User;

import java.util.List;
import java.util.Optional;

public class UserService {


    private  static UserDao userDao = new UserDao();

        public User addUser(User user){
            return userDao.create(user);
        }

        public User editUser(User user){
            return userDao.update(user);
        }

        public User findUserById(int id){
            return userDao.findById(id);
        }

        public List<User> findAllUsers(){
            return userDao.findAll();
        }

        public void deleteUserById(int id){
            userDao.deleteById(id);
        }

        public long countUsers(){
            return userDao.count();
        }


}
