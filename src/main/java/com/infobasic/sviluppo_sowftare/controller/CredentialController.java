package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Credential;
import com.infobasic.sviluppo_sowftare.model.User;
import com.infobasic.sviluppo_sowftare.service.CredentialService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.List;

public class CredentialController {

    private final CredentialService credentialService = new CredentialService();

    public void credentialRoutes(Javalin app){

        //Create Credential
        app.post("/credential/register", this::registerCredential);

        //Edit Credential
        app.before("/credential/edit", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/credential/edit", this::editCredential);

        //Get Credential By Email
        app.before("/credential-by-email", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/credential-by-email", this::getCredentialByEmail);

        //Count all Credential
        app.before("/credentials/count", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/credentials/count", this::countCredentials);

        //Find all Credential
        app.before("/credentials", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/credentials", this::findAllCredentials);

        app.get("/check", ctx -> new JwtAuthMiddleware().handle(ctx));

        app.before("/credential-user-by-email", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/credential-user-by-email", this::findCredentialUserByEmail);
    }

    private void registerCredential(Context ctx){
        Credential credential = credentialService.addCredential(ctx.bodyAsClass(Credential.class));
        ctx.status(200).json(credential);
    }

    private void editCredential(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Credential credential = credentialService.editCredential(ctx.bodyAsClass(Credential.class));
            ctx.status(200).json(credential);
        } catch (Exception e) {
            ctx.status(404).json("Credential not found");
        }
    }

    private void getCredentialByEmail(Context ctx){
        String email = ctx.attribute("email");
        try {
            Credential credential = credentialService.findCredentialByEmail(email);
            ctx.status(200).json(credential);
        } catch (Exception e) {
            ctx.json("Wrong credentials");
        }
    }

    private void countCredentials(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        long count = credentialService.countCredentials();
        ctx.status(200).json(count);
    }

    private void findAllCredentials(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        List<Credential> list = credentialService.findAllCredentials();
        ctx.status(200).json(list);
    }

    private void findCredentialUserByEmail(Context ctx) throws SQLException {
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            User user = credentialService.findCredentialUserByEmail(email);
            ctx.status(200).json(user);
        } catch (SQLException e) {
            ctx.status(404).result("User Not Found");
        }
    }


}
