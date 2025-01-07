package com.infobasic.sviluppo_sowftare.controller;

import com.infobasic.sviluppo_sowftare.auth.middleware.JwtAuthMiddleware;
import com.infobasic.sviluppo_sowftare.model.File;
import com.infobasic.sviluppo_sowftare.service.FileService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class FileController {

    private static FileService fileService = new FileService();

    public void fileRoutes(Javalin app){

        app.before("/file", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/file", this::createFile);

        app.before("/file", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.put("/file", this::editFile);

        app.before("/file", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.delete("/file", this::deleteFile);

        app.before("/file/{id}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.get("/file/{id}", this::getFileById);

        app.before("/file-by-course-id/{courseId}", ctx -> new JwtAuthMiddleware().handle(ctx));
        app.post("/file-by-course-id/{courseId}", this::getFileByCourseId);
    }

    private void createFile(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            File newFile = fileService.addFile(ctx.bodyAsClass(File.class));
            ctx.status(200).json(newFile);
        } catch (Exception e) {
            ctx.status(404).json("wrong data");
        }
    }

    private void editFile(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            File file = fileService.editFile(ctx.bodyAsClass(File.class));
            ctx.status(200).json(file);
        } catch (Exception e) {
            ctx.status(404).result("file not found");
        }
    }

    private void deleteFile(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            fileService.deleteFileById(Integer.parseInt(ctx.queryParam("id")));
            ctx.status(200).result("file deleted");
        }catch (Exception e){
            ctx.status(404).result("file not found");
        }
    }

    private void getFileById(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            File file =fileService.findFileById(Integer.parseInt(ctx.pathParam("id")));
            ctx.status(200).json(file);
        }catch (Exception e){
            ctx.status(404).result("file not found");
        }
    }

    private void getFileByCourseId(Context ctx){
        String email = ctx.attribute("email");
        if (email == null) {
            ctx.status(401).result("Unauthorized");
            return;
        }
        try{
            List<File> filesList =fileService.findFileByCourseId(Integer.parseInt(ctx.pathParam("courseId")));
            ctx.status(200).json(filesList);
        }catch (Exception e){
            ctx.status(404).result("files not found");
        }
    }
}
