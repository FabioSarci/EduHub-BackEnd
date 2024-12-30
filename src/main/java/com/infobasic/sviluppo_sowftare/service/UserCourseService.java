package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.UserCourseDao;
import com.infobasic.sviluppo_sowftare.model.UserCourse;

import java.util.List;

public class UserCourseService {

    private static UserCourseDao userCourseDao = new UserCourseDao();

    public UserCourse addUserCourse(UserCourse userCourse){
        return userCourseDao.create(userCourse);
    }

    public UserCourse editUserCourse(UserCourse userCourse){
        return userCourseDao.update(userCourse);
    }

    public UserCourse findUserCourseById(int id){
        return userCourseDao.findById(id);
    }

    public List<UserCourse> findAllUserCourses(){
        return userCourseDao.findAll();
    }

    public void deleteUserCourseById(int id){
        userCourseDao.deleteById(id);
    }

    public long countUserCourses(){
        return userCourseDao.count();
    }

    public List<UserCourse> getUserCoursesByIdCourse(int courseId){
        return userCourseDao.findUserCoursesByIdCourse(courseId);
    }

    public List<UserCourse> getUserCoursesByIdUser(int userId){
        return userCourseDao.findUserCoursesByIdUser(userId);
    }
}
