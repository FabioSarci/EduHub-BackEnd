package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.NotificationDao;
import com.infobasic.sviluppo_sowftare.model.Notification;

import java.util.List;

public class NotificationService {

    private  static NotificationDao notificationDao = new NotificationDao();

    public Notification addNotification(Notification notification){
        return notificationDao.create(notification);
    }

    public Notification findNotificationById(int id){
        return  notificationDao.findById(id);
    }

    public List<Notification> getNotificationListByUserId(int id){
        return notificationDao.getByUserId(id);
    }

    public List<Notification> getAllNotification(){
        return notificationDao.findAll();
    }

    public void deleteNotificationById(int id){
        notificationDao.deleteById(id);
    }

}
