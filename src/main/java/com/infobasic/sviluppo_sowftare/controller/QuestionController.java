package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Question;
import com.infobasic.sviluppo_sowftare.service.QuestionService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class QuestionController {
    private static QuestionService questionService = new QuestionService();

    public void questionRoutes(Javalin app){

        app.before("/question", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/question", this::addQuestion);

        app.before("/question", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/question", this::editQuestion);

        app.before("/question/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/question/{id}", this::findQuestionById);

        app.before("/questions", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/questions", this::findAllQuestions);

        app.before("/question", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/question", this::deleteQuestionById);

        app.before("/questions-by-quiz-id/{quizId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/questions-by-quiz-id/{quizId}", this::findAllQuestions);
    }

    private void addQuestion(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Question question = questionService.addQuestion(ctx.bodyAsClass(Question.class));
            ctx.status(200).json(question);
        } catch (Exception e) {
            ctx.status(404).result("Wrong Data");
        }
    }

    private void editQuestion(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Question question = questionService.editQuestion(ctx.bodyAsClass(Question.class));
            ctx.status(200).json(question);
        } catch (Exception e) {
            ctx.status(404).result("Wrong Data");
        }
    }

    private void findQuestionById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Question question = questionService.findQuestioById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(question);
        } catch (Exception e) {
            ctx.status(404).result("Question not found");
        }
    }

    private void deleteQuestionById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            questionService.deleteQuestionById(Integer.parseInt(ctx.queryParam("id")));
            ctx.status(200).json("Question deleted");
        } catch (Exception e) {
            ctx.status(404).result("Question not found");
        }
    }

    private void findAllQuestions(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Question>list = questionService.findAllQuestion();
            ctx.status(200).json(list);
        } catch (Exception e) {
            ctx.status(404).result("Question not found");
        }
    }

    private void findQuestionsByQuizId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Question> questionByQuizIdList = questionService.findQuestionsByQuizId(Integer.parseInt(ctx.pathParam("quizId")));
            ctx.status(200).json(questionByQuizIdList);
        } catch (Exception e) {
            ctx.status(404).result("Quiz not found");
        }
    }

}
