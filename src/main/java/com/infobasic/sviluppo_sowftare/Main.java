package com.infobasic.sviluppo_sowftare;

import com.infobasic.sviluppo_sowftare.auth.controller.AuthController;
import com.infobasic.sviluppo_sowftare.controller.CourseController;
import com.infobasic.sviluppo_sowftare.controller.CredentialController;
import com.infobasic.sviluppo_sowftare.controller.UserController;
import com.infobasic.sviluppo_sowftare.controller.UserCourseController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7001);



        UserController userController = new UserController();
        CredentialController credentialController = new CredentialController();
        AuthController authController = new AuthController();
        CourseController courseController = new CourseController();
        UserCourseController userCourseController = new UserCourseController();

        authController.loginRoutes(app);
        userController.userRoutes(app);
        credentialController.credentialRoutes(app);
        courseController.courseRoutes(app);
        userCourseController.userCourseRoutes(app);

    }
}