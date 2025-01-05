package com.infobasic.sviluppo_sowftare;

import com.infobasic.sviluppo_sowftare.auth.controller.AuthController;
import com.infobasic.sviluppo_sowftare.controller.*;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

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
        AnswerController answerController = new AnswerController();
        UserQuizController userQuizController = new UserQuizController();
        UserAnswerController userAnswerController = new UserAnswerController();

        try{
            Javalin app = Javalin.create(config -> {
                // Abilita il CORS
                config.plugins.enableCors(cors -> {
                    cors.add(corsConfig -> {
                        corsConfig.allowHost("http://localhost:5173"); // Permetti richieste dal client (Vite.js)
                    });
                });
            }).start(7001);
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
            answerController.answerRoutes(app);
            userQuizController.userQuizRoutes(app);
            userAnswerController.userAnswerRoutes(app);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}