package com.infobasic.sviluppo_sowftare;

import com.infobasic.sviluppo_sowftare.auth.controller.AuthController;
import com.infobasic.sviluppo_sowftare.controller.*;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7001);



        UserController userController = new UserController();
        CredentialController credentialController = new CredentialController();
        AuthController authController = new AuthController();
        CourseController courseController = new CourseController();
        UserCourseController userCourseController = new UserCourseController();
        NotificationController notificationController = new NotificationController();
        LessonController lessonController = new LessonController();
        PresenceController presenceController = new PresenceController();
        QuizController quizController = new QuizController();
        QuestionController questionController = new QuestionController();




        authController.loginRoutes(app);
        userController.userRoutes(app);
        credentialController.credentialRoutes(app);
        courseController.courseRoutes(app);
        userCourseController.userCourseRoutes(app);
        notificationController.userRoutes(app);
        lessonController.LessonRoutes(app);
        presenceController.presenceRoutes(app);
        quizController.quizRoutes(app);
        questionController.questionRoutes(app);

    }
}