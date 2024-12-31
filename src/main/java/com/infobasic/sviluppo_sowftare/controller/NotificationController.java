package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Notification;
import com.infobasic.sviluppo_sowftare.service.NotificationService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class NotificationController {

    private static NotificationService notificationService = new NotificationService();

    public void userRoutes(Javalin app){

        app.before("/notification", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/notification", this::createNotification);

        app.before("/notification-by-id/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/notification-by-id/{id}", this::findNotificationById);

        app.before("/user-notifications/{userid}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/user-notifications/{userid}", this::findAllUserNotification);

        app.before("/notifications", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/notifications", this::getAllNotifications);

        app.before("/notification", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/notification", this::deleteNofiticationsById);

    }


    private void createNotification(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Notification notification = notificationService.addNotification(ctx.bodyAsClass(Notification.class));
            ctx.status(200).json(notification);
        } catch (Exception e) {
            ctx.status(404).json("Error");
        }
    }

    private void findNotificationById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Notification notification = notificationService.findNotificationById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(notification);
        }catch (Exception e){
            ctx.status(404).json("Notification not Found");
        }
    }

    private void findAllUserNotification(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Notification> list = notificationService.getNotificationListByUserId(Integer.parseInt(ctx.pathParam("userid")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).json("User not Found");
        }
    }

    private void getAllNotifications(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        List<Notification> list = notificationService.getAllNotification();
        ctx.status(200).json(list);
    }

    private void deleteNofiticationsById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            notificationService.deleteNotificationById(Integer.parseInt(ctx.queryParam("id")));
        }catch (Exception e){
            ctx.status(404).json("notification not found");
        }
    }
}
