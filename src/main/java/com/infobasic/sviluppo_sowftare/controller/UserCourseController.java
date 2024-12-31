package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.UserCourse;
import com.infobasic.sviluppo_sowftare.service.UserCourseService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserCourseController {

    private static UserCourseService userCourseService = new UserCourseService();

    public void userCourseRoutes(Javalin app){
        //Create UserCourse
        app.post("/usercourse/create", this::addUserCourse);

        //Edit UserCourse
        app.before("/usercourse/edit", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/usercourse/edit", this::editUserCourse);

        //Delete UserCourse
        app.before("/usercourse/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/usercourse/{id}", this::deleteUserCourseById);

        //Get UserCourse By Email
        app.before("/usercourse-by-id", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/usercourse-by-id", this::getUserCourseById);

        //Count all UserCourse
        app.before("/usercourses/count", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/usercourses/count", this::countUserCourses);

        //Get all UserCourse
        app.before("/usercourses", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/usercourses", this::getAllUserCourses);

        //Get UserCourse List By courseId
        app.before("/usercourses-by-courseid/{courseId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/usercourses-by-courseid/{courseId}", this::getUserCoursesByCourseId);

        //Get UserCourse List By userId
        app.before("/usercourses-by-userid/{userId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/usercourses-by-userid/{userId}", this::getUserCoursesByUserId);
    }

    private void addUserCourse(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserCourse credential = userCourseService.addUserCourse(ctx.bodyAsClass(UserCourse.class));
            ctx.status(200).json(credential);
        } catch (Exception e) {
            ctx.json(404).json("User or Course not found");
        }
    }

    private void editUserCourse(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserCourse credential = userCourseService.editUserCourse(ctx.bodyAsClass(UserCourse.class));
            ctx.status(200).json(credential);
        } catch (Exception e) {
            ctx.status(404).json("User Course not found");
        }
    }

    private void deleteUserCourseById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            userCourseService.deleteUserCourseById(Integer.parseInt(ctx.queryParam("id")));
            ctx.status(200).json("UserCourse Deleted");
        } catch (Exception e) {
            ctx.status(404).json(e);
        }
    }

    private void getUserCourseById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserCourse userCourse = userCourseService.findUserCourseById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(userCourse);
        } catch (Exception e) {
            ctx.status(404).json("wrong courseId");
        }
    }

    private void countUserCourses(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            long count = userCourseService.countUserCourses();
            ctx.status(200).json(count);
        } catch (Exception e) {
            ctx.status(404).json("wrong userId");
        }
    }

    private void getAllUserCourses(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try {
            List<UserCourse> list = userCourseService.findAllUserCourses();
            ctx.status(200).json(list);
        } catch (Exception e) {
            ctx.status(404).json(e);
        }
    }

    private void getUserCoursesByCourseId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<UserCourse> list = userCourseService.getUserCoursesByIdCourse(Integer.parseInt(ctx.pathParam("courseId")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).json(e);
        }
    }

    private void getUserCoursesByUserId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<UserCourse> list = userCourseService.getUserCoursesByIdUser(Integer.parseInt(ctx.pathParam("userId")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).json(e);
        }
    }


}
