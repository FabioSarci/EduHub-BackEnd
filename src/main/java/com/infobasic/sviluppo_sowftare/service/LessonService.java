package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.LessonDao;
import com.infobasic.sviluppo_sowftare.model.Lesson;

import java.util.List;

public class LessonService {

    private static LessonDao lessonDao = new LessonDao();

    public Lesson createLesson(Lesson lesson){
        return  lessonDao.create(lesson);
    }

    public Lesson editLesson(Lesson lesson){
        return lessonDao.update(lesson);
    }

    public List<Lesson> getAllLessons(){
        return lessonDao.findAll();
    }

    public List<Lesson> getLessonsByCourseId(int id){
        return lessonDao.findAllLessonsByCourseId(id);
    }

    public void deleteLesson (int id){
        lessonDao.deleteById(id);
    }
}
