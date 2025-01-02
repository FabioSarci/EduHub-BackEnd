package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Lesson;
import com.infobasic.sviluppo_sowftare.service.LessonService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class LessonController {

    private static LessonService lessonService = new LessonService();

    public void LessonRoutes(Javalin app){

        app.before("/lesson/create", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("lesson/create",this::createLesson);

        app.before("/lesson/edit", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("lesson/edit",this::editLesson);

        app.before("/lessons", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("lessons",this::getAllLessons);

        app.before("/lesson-by-course/{courseid}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/lesson-by-course/{courseid}",this::getLessonsByCourseId);

        app.before("/lesson", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("lesson",this::deleteLessonById);
    }


    private void createLesson(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Lesson newLwsson = lessonService.createLesson(ctx.bodyAsClass(Lesson.class));
            ctx.status(200).json(newLwsson);
        } catch (Exception e) {
            ctx.status(404).json("Wrong Data");
        }
    }

    private void editLesson(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try {
            Lesson lesson = lessonService.editLesson(ctx.bodyAsClass(Lesson.class));
            ctx.status(200).json(lesson);
        } catch (Exception e) {
            ctx.status(404).json("Lesson not found");
        }
    }

    private void getAllLessons(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        List<Lesson> list = lessonService.getAllLessons();
        ctx.status(200).json(list);
    }

    private void getLessonsByCourseId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Lesson> list = lessonService.getLessonsByCourseId(Integer.parseInt(ctx.pathParam("courseid")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).json("Wrong CourseId");
        }
    }

    private void deleteLessonById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            lessonService.deleteLessonById(Integer.parseInt(ctx.queryParam("id")));
            ctx.status(200).json("lesson deleted");
        }catch (Exception e){
            ctx.status(404).json("Invalid id");
        }
    }
}
