package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.Course;
import com.infobasic.sviluppo_sowftare.service.CourseService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class CourseController {

    private final CourseService courseService = new CourseService();

    public void courseRoutes(Javalin app){

        //Create Course
        app.before("/course/create", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/course/create", this::createCourse);

        //Delete Course
        app.before("/course/delete/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/course/delete/{id}", this::deleteCourseById);

        //Edit course
        app.before("/course/edit", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/course/edit", this::editCourse);

        //Get course By Id
        app.before("/course/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/course/{id}", this::getCourseById);

        //Count all course
        app.before("/courses/count", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/courses/count", this::countAllCourses);

        //Find all course
        app.before("/courses", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/courses", this::findAllCourses);
    }

    private void createCourse(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        Course newCourse = courseService.addCourse(ctx.bodyAsClass(Course.class));
        ctx.status(200).json(newCourse);
    }

    private void deleteCourseById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            courseService.deleteCourseById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json("Course deleted successfully");
        }catch (Exception e){
            ctx.status(404).json("User not found");
        }
    }

    private void editCourse(Context ctx) {
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Course course2Edit = courseService.editCourse(ctx.bodyAsClass(Course.class));
            ctx.status(200).json(course2Edit);
        } catch (Exception e) {
            ctx.status(404).json("Course not found");
        }
    }

    private void getCourseById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            Course course = courseService.findCourseById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(course);
        } catch (NumberFormatException e) {
            ctx.status(404).json("Course not found");
        }
    }

    private void countAllCourses(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try {
            long count = courseService.countCourse();
            ctx.status(200).json(count);
        } catch (Exception e) {
            ctx.status(404).json("Something gone wrong");
        }
    }

    private void findAllCourses(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<Course> allUsers = courseService.findAllCourses();
            ctx.status(200).json(allUsers);
        } catch (Exception e) {
            ctx.status(404).json("Something some wrong");
        }
    }
}
