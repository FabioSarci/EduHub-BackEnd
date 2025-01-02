package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.PresenceDao;
import com.infobasic.sviluppo_sowftare.model.Presence;

import java.util.List;

public class PresenceService {
    private  static PresenceDao presenceDao = new PresenceDao();

    public Presence addPresence(Presence presence){
        return presenceDao.create(presence);
    }

    public Presence editPresence(Presence presence){
        return presenceDao.update(presence);
    }

    public Presence findPresenceById(int id){
        return presenceDao.findById(id);
    }

    public List<Presence> findAllPresence(){
        return presenceDao.findAll();
    }

    public long countPresence(){
        return presenceDao.count();
    }

    public List<Presence> findPresencesByUserId(int userId){
        return presenceDao.findPresencesByUserId(userId);
    }

    public List<Presence> findPresencesByLessonId(int lessonId){
        return presenceDao.findPresencesByLessonId(lessonId);
    }
}
