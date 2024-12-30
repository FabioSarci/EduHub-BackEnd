package com.infobasic.sviluppo_sowftare;

import com.infobasic.sviluppo_sowftare.auth.controller.AuthController;
import com.infobasic.sviluppo_sowftare.controller.CredentialController;
import com.infobasic.sviluppo_sowftare.controller.UserController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7001);



        UserController userController = new UserController();
        CredentialController credentialController = new CredentialController();
        AuthController authController = new AuthController();

        authController.loginRoutes(app);
        userController.userRoutes(app);
        credentialController.credentialRoutes(app);

    }
}