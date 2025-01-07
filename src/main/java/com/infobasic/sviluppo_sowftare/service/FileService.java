package com.infobasic.sviluppo_sowftare.service;

import com.infobasic.sviluppo_sowftare.dao.FileDao;
import com.infobasic.sviluppo_sowftare.model.File;

import java.util.List;

public class FileService {

    private static FileDao fileDao = new FileDao();

    public File addFile(File file){
        return fileDao.create(file);
    }

    public File editFile(File file){
        return fileDao.update(file);
    }

    public File findFileById(int id){
        return  fileDao.findById(id);
    }

    public List<File> findFileByCourseId(int courseId){
        return fileDao.findFilesByCourseId(courseId);
    }

    public void deleteFileById(int id){
        fileDao.deleteById(id);
    }
}
