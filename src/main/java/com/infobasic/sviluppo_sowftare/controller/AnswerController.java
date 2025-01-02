package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Answer;
import com.infobasic.sviluppo_sowftare.service.AnswerService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class AnswerController {

    private static AnswerService answerService = new AnswerService();

    public void answerRoutes(Javalin app){

        app.before("/answer", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/answer", this::addAnswer);

        app.before("/answer", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/answer", this::editAnswer);

        app.before("/answer/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/answer/{id}", this::findAnswerById);

        app.before("/answer", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/answer", this::deleteAnswer);

        app.before("/answers", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/answers", this::findAllAnswer);

        app.before("/answer-by-question/{questionId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/answer-by-question/{questionId}", this::getAnswersByQuestionId);
    }

    private void addAnswer(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Answer answer = answerService.addAnswer(ctx.bodyAsClass(Answer.class));
            ctx.status(200).json(answer);
        } catch (Exception e) {
            ctx.status(404).result("Wrong data");
        }
    }

    private void editAnswer(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Answer answer = answerService.editAnswer(ctx.bodyAsClass(Answer.class));
            ctx.status(200).json(answer);
        } catch (Exception e) {
            ctx.status(404).result("Wrong  data");
        }
    }

    private void findAnswerById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Answer answer = answerService.findAnswerById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(answer);
        } catch (Exception e) {
            ctx.status(404).result("Answer not found");
        }
    }

    private void findAllAnswer(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Answer> answerList = answerService.findAllAnswer();
            ctx.status(200).json(answerList);
        } catch (Exception e) {
            ctx.status(404).result("Error");
        }
    }

    private void deleteAnswer(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            answerService.deleteAnswer(Integer.parseInt(ctx.queryParam("id")));
            ctx.status(200).result("Answer deleted");
        } catch (Exception e) {
            ctx.status(404).result("Answer not found");
        }
    }

    private void getAnswersByQuestionId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Answer> answerByQuestionIdList = answerService.getAnswersByQuestionId(Integer.parseInt(ctx.pathParam("questionId")));
            ctx.status(200).json(answerByQuestionIdList);
        }catch (Exception e){
            ctx.status(404).result("Question not found");
        }
    }


}
