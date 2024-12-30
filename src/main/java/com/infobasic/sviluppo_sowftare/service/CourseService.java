package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.CourseDao;
import com.infobasic.sviluppo_sowftare.model.Course;

import java.util.List;

public class CourseService {

    private static CourseDao courseDao = new CourseDao();

    public Course addCourse(Course course){
        return courseDao.create(course);
    }

    public Course editCourse(Course course){
        return courseDao.update(course);
    }

    public Course findCourseById(int id){
        return courseDao.findById(id);
    }

    public List<Course> findAllCourses(){
        return courseDao.findAll();
    }

    public void deleteCourseById(int id){
        courseDao.deleteById(id);
    }

    public long countCourse(){
        return courseDao.count();
    }
}
