package com.infobasic.sviluppo_sowftare;

import com.infobasic.sviluppo_sowftare.controller.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7001);



        UserController userController = new UserController();

        userController.userRoutes(app);

    }
}