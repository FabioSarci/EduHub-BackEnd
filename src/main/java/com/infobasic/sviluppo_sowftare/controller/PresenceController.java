package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Presence;
import com.infobasic.sviluppo_sowftare.service.PresenceService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class PresenceController {

    private static PresenceService presenceService = new PresenceService();

    public void presenceRoutes(Javalin app){

        app.before("/presence", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/presence", this::addPresence);

        app.before("/presence", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/presence", this::editPresence);

        app.before("/presence/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/presence/{id}", this::findPresenceById);

        app.before("/presences", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/presences", this::findAllPresence);

        app.before("/presences/count", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/presences/count", this::countPresence);

        app.before("/presences-by-user-id/{userId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/presences-by-user-id/{userId}", this::findPresencesByUserId);

        app.before("/presences-by-lesson-id/{lessonId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/presences-by-lesson-id/{lessonId}", this::findPresencesByLessonId);

    }

    private void addPresence(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Presence presence = presenceService.addPresence(ctx.bodyAsClass(Presence.class));
            ctx.status(200).json(presence);
        } catch (Exception e) {
            ctx.status(404).result("Wrong data");
        }
    }

    private void editPresence(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Presence presence = presenceService.editPresence(ctx.bodyAsClass(Presence.class));
            ctx.status(200).json(presence);
        } catch (Exception e) {
            ctx.status(404).result("Presence not found");
        }
    }

    private void findPresenceById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Presence presence = presenceService.findPresenceById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(presence);
        } catch (Exception e) {
            ctx.status(404).result("Presence not found");
        }
    }

    private void findAllPresence(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        List<Presence> list = presenceService.findAllPresence();
        ctx.status(200).json(list);
    }

    private void countPresence(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        long count = presenceService.countPresence();
        ctx.status(200).json(count);
    }

    private void findPresencesByUserId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try {
            List<Presence> list = presenceService.findPresencesByUserId(Integer.parseInt(ctx.pathParam("userId")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).result("Invalid userId");
        }
    }

    private void findPresencesByLessonId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try {
            List<Presence> list = presenceService.findPresencesByLessonId(Integer.parseInt(ctx.pathParam("lessonId")));
            ctx.status(200).json(list);
        }catch (Exception e){
            ctx.status(404).result("Invalid lessonId");
        }
    }


}
