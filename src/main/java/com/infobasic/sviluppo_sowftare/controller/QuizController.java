package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Quiz;
import com.infobasic.sviluppo_sowftare.service.QuizService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Objects;

public class QuizController {

    private static QuizService quizService = new QuizService();

    public void quizRoutes(Javalin app){

        app.before("/quiz", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/quiz", this::addQuiz);

        app.before("/quiz", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/quiz", this::editQuiz);

        app.before("/quiz/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/quiz/{id}", this::findQuizById);

        app.before("/quizes", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/quizes", this::findAllQuiz);

        app.before("/quiz", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/quiz", this::deleteQuizById);

        app.before("/quiz-by-course/{courseId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/quiz-by-course/{courseId}", this::getQuizByCourseId);

    }

    private void addQuiz(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Quiz quiz = quizService.addQuiz(ctx.bodyAsClass(Quiz.class));
            ctx.status(200).json(quiz);
        } catch (Exception e) {
            ctx.status(404).result("wrong data");
        }

    }

    private void editQuiz(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Quiz quiz = quizService.editQuiz(ctx.bodyAsClass(Quiz.class));
            ctx.status(200).json(quiz);
        } catch (Exception e) {
            ctx.status(404).result("wrong data");
        }
    }

    private void findQuizById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Quiz quiz = quizService.findQuizById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(quiz);
        }catch (Exception e){
            ctx.status(404).result("Quiz not found");
        }
    }

    private void findAllQuiz(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        List<Quiz> list = quizService.findAllQuiz();
        ctx.status(200).json(list);
    }

    private void deleteQuizById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            quizService.deleteQuizById(Integer.parseInt(Objects.requireNonNull(ctx.queryParam("id"))));
            ctx.status(200).json("Quiz deleted");
        }catch (Exception e){
            ctx.status(404).result("Quiz not found");
        }
    }

    private void getQuizByCourseId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Quiz> quizByCourseIdList = quizService.getQuizByCourseId(Integer.parseInt(ctx.pathParam("courseId")));
            ctx.status(200).json(quizByCourseIdList);
        }catch (Exception e){
            ctx.status(404).result("Course not Found");
        }
    }
}
