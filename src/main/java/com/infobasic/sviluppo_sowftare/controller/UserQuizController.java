package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.UserQuiz;
import com.infobasic.sviluppo_sowftare.service.UserQuizService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserQuizController {
    private static UserQuizService userQuizService = new UserQuizService();

    public void userQuizRoutes(Javalin app){

        app.before("/userquiz", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/userquiz", this::addUserQuiz);

        app.before("/userquiz", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/userquiz", this::editUserQuiz);

        app.before("/userquiz", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/userquiz", this::deleteUserQuizById);

        app.before("/userquiz/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/userquiz/{id}", this::findUserQuizById);

        app.before("/userquizes", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/userquizes", this::findAllUserQuizes);

        app.before("/userquiz-by-user-id/{userId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/userquiz-by-user-id/{userId}", this::findUserQuizByUserId);

        app.before("/userquiz-by-quiz-id/{quizId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/userquiz-by-quiz-id/{quizId}", this::findUserQuizByQuizId);
    }

    private void addUserQuiz(Context ctx){

        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserQuiz userQuiz = userQuizService.addUserQuiz(ctx.bodyAsClass(UserQuiz.class));
            ctx.status(200).json(userQuiz);
        } catch (Exception e) {
            ctx.status(404).json("Wrong data");
        }
    }

    private void editUserQuiz(Context ctx){

        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserQuiz userQuiz = userQuizService.editUserQuiz(ctx.bodyAsClass(UserQuiz.class));
            ctx.status(200).json(userQuiz);
        } catch (Exception e) {
            ctx.status(404).result("Wrong data");
        }
    }

    private void findUserQuizById(Context ctx){

        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserQuiz userQuiz = userQuizService.findUserQuizById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(userQuiz);
        }catch (Exception e){
            ctx.status(404).result("userquiz not found");
        }
    }

    private  void findAllUserQuizes(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        List<UserQuiz> userQuizList = userQuizService.findAllUserQuiz();
        ctx.status(200).json(userQuizList);
    }

    private void deleteUserQuizById(Context ctx){

        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            userQuizService.deleteUserQuizById(Integer.parseInt(ctx.queryParam("id")));
            ctx.status(200).json("UserQuizDeleted");
        }catch (Exception e){
            ctx.status(404).result("userquiz not found");
        }
    }

    private void findUserQuizByUserId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<UserQuiz> list = userQuizService.findUserQuizByUserId(Integer.parseInt(ctx.pathParam("userId")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).result("user not found");
        }
    }

    private void findUserQuizByQuizId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<UserQuiz> list = userQuizService.findUserQuizByQuizId(Integer.parseInt(ctx.pathParam("quizId")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).result("quiz not found");
        }
    }

}
