package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.model.Credential;
import com.infobasic.sviluppo_sowftare.service.CredentialService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class CredentialController {

    private final CredentialService credentialService = new CredentialService();

    public void credentialRoutes(Javalin app){

        //Create Credential
        app.get("/credential/register", this::registerCredential);

        //Edit Credential
        app.put("/credential/edit", this::editCredential);

        //Get User By Email
        app.get("/credential-by-email", this::getCredentialByEmail);

        //Count all Credential
        app.get("/credentials/count", this::countCredentials);

        //Find all Credential
        app.get("/credentials", this::findAllCredentials);
    }

    public void registerCredential(Context ctx){
        Credential credential = credentialService.addCredential(ctx.bodyAsClass(Credential.class));
        ctx.status(200).json(credential);
    }

    public void editCredential(Context ctx){
        Credential credential = credentialService.editCredential(ctx.bodyAsClass(Credential.class));
        ctx.status(200).json(credential);
    }

    public void getCredentialByEmail(Context ctx){
        Credential credential = credentialService.findCredentialByEmail(ctx.queryParam("email"));
        ctx.status(200).json(credential);
    }

    public void countCredentials(Context ctx){
        long count = credentialService.countCredentials();
        ctx.status(200).json(count);
    }

    public void findAllCredentials(Context ctx){
        List<Credential> list = credentialService.findAllCredentials();
        ctx.status(200).json(list);
    }


}
