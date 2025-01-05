package com.infobasic.sviluppo_sowftare.auth.controller;

import com.infobasic.sviluppo_sowftare.auth.util.JwtUtil;
import com.infobasic.sviluppo_sowftare.model.Credential;
import com.infobasic.sviluppo_sowftare.service.CredentialService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AuthController {

    private final CredentialService userService = new CredentialService();

    // Login Route
    public void loginRoutes(Javalin app) {
        app.post("/login", this::login);
    }

    public void login(Context ctx) {
        try {
            Credential formCredential = ctx.bodyAsClass(Credential.class);


            Credential credential = userService.findCredentialByEmail(formCredential.getEmail());

            if(credential != null){
                if (credential.getPassword().equals(formCredential.getPassword())) {
                    String token = JwtUtil.generateToken(credential.getEmail());
                    ctx.json(new TokenResponse(token));
                } else {
                    ctx.status(401).result("Invalid credentials");
                }
            }else{
                ctx.status(401).result("Invalid credentials");
            }
        } catch (Exception e) {
            ctx.status(400).result("Invalid request");
        }
    }

    private static class TokenResponse {
        public final String token;

        public TokenResponse(String token) {
            this.token = token;
        }
    }
}
