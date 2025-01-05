package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.User;
import com.infobasic.sviluppo_sowftare.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    public void userRoutes(Javalin app){

        //Create User
        app.post("/user/register", this::registerUser);

        //Delete User
        app.before("/user/delete/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/user/delete/{id}", this::deleteUserById);

        //Edit User
        app.before("/user/edit", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/user/edit", this::editUser);

        //Get User By Id
        app.before("/user/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/user/{id}", this::getUserById);

        //Count all Users
        app.before("/users/count", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/users/count", this::countAllUsers);

        //Find all Users
        app.before("/users", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/users", this::findAllUsers);
    }

    private void registerUser(Context ctx){
        User user = userService.addUser(ctx.bodyAsClass(User.class));
        ctx.status(200).json(user);
    }

    private void deleteUserById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            userService.deleteUserById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json("User deleted successfully");
        }catch (Exception e){
            ctx.status(404).json("User not found");
        }
    }

    private void editUser(Context ctx) {
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            User user = userService.editUser(ctx.bodyAsClass(User.class));
            ctx.status(200).json(user);
        } catch (Exception e) {
            ctx.status(404).json("User not found");
        }
    }

    private void getUserById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            User user = userService.findUserById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(user);
        } catch (NumberFormatException e) {
            ctx.status(404).json("User not found");
        }
    }

    private void countAllUsers(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try {
            long count = userService.countUsers();
            ctx.status(200).json(count);
        } catch (Exception e) {
            ctx.status(404).json("Something gone wrong");
        }
    }

    private void findAllUsers(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<User> allUsers = userService.findAllUsers();
            ctx.status(200).json(allUsers);
        } catch (Exception e) {
            ctx.status(404).json("Something some wrong");
        }
    }
}
