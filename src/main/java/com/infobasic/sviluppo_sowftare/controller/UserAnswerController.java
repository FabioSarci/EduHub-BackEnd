package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.UserAnswer;
import com.infobasic.sviluppo_sowftare.service.UserAnswerService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserAnswerController {

    UserAnswerService userAnswerService = new UserAnswerService();

    public void userAnswerRoutes(Javalin app){

        app.before("/useranswer", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/useranswer", this::addUserAnswer);

        app.before("/useranswer/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/useranswer/{id}", this::getUserAnswerById);

        app.before("/useranswer-by-userquiz/{userQuizId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/useranswer-by-userquiz/{userQuizId}", this::getUserAnswerByUserQuizId);
    }

    private void addUserAnswer(Context ctx){

        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserAnswer userAnswer = userAnswerService.addUserAnswer(ctx.bodyAsClass(UserAnswer.class));
            ctx.status(200).json(userAnswer);
        } catch (Exception e) {
            ctx.status(404).result("wrong data");
        }
    }

    private void getUserAnswerById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            UserAnswer userAnswer = userAnswerService.getUserAnswerById(Integer.parseInt((ctx.pathParam("id"))));
            ctx.status(200).json(userAnswer);
        } catch (Exception e) {
            ctx.status(404).result("UserAnswer not found");
        }
    }

    private void getUserAnswerByUserQuizId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<UserAnswer> userAnswer = userAnswerService.getUserAnswerByUserQuizId(Integer.parseInt((ctx.pathParam("userQuizId"))));
            ctx.status(200).json(userAnswer);
        } catch (Exception e) {
            ctx.status(404).result("UserQuiz not found");
        }
    }
}
