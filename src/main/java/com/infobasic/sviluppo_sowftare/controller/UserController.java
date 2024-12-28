package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.model.User;
import com.infobasic.sviluppo_sowftare.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    private final UserService userService = new UserService();

    public void userRoutes(Javalin app){

        //Registration
        app.get("/register", this::registerUser);
    }

    public void registerUser(Context ctx){
        userService.addUser(ctx.bodyAsClass(User.class));
    }

    public void deleteUserById(Context ctx){
        userService.deleteUserById(Integer.parseInt(ctx.pathParam("id")));
        ctx.status(201);
    }
}
