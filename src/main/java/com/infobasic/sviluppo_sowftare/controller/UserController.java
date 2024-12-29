package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.model.User;
import com.infobasic.sviluppo_sowftare.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    public void userRoutes(Javalin app){

        //Create User
        app.get("/register", this::registerUser);

        //Delete User
        app.delete("/user/delete/{id}", this::deleteUserById);

        //Edit User
        app.put("/user/edit", this::editUser);

        //Get User By Id
        app.get("/user/{id}", this::getUserById);

        //Count all Users
        app.get("/users", this::countAllUsers);
    }

    public void registerUser(Context ctx){
        User user = userService.addUser(ctx.bodyAsClass(User.class));
        ctx.status(200).json(user);
    }

    public void deleteUserById(Context ctx){
        try{
            userService.deleteUserById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json("User deleted successfully");
        }catch (Exception e){
            ctx.status(404).json("User not found");
        }
    }

    public void editUser(Context ctx) {
        try{
            User user = userService.editUser(ctx.bodyAsClass(User.class));
            ctx.status(200).json(user);
        } catch (Exception e) {
            ctx.status(404).json("User not found");
        }
    }

    public void getUserById(Context ctx){
        try{
            User user = userService.findUserById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(user);
        } catch (NumberFormatException e) {
            ctx.status(404).json("User not found");
        }
    }

    public void countAllUsers(Context ctx){
        try {
            long count = userService.countUsers();
            ctx.status(200).json(count);
        } catch (Exception e) {
            ctx.status(404).json("Something gone wrong");
        }
    }
}
