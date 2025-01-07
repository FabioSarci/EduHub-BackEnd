package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.File;
import com.infobasic.sviluppo_sowftare.model.Notification;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDao extends GenericDao<File, Integer>{
    @Override
    protected File mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new File(
                rs.getInt("id"),
                rs.getString("filename"),
                rs.getString("path"),
                rs.getInt("userid"),
                rs.getInt("courseid"),
                rs.getString("title"),
                rs.getDate("createdat").toLocalDate(),
                rs.getDate("createdat").toLocalDate(),
                rs.getString("content")
        );
    }

    @Override
    protected String getTableName() {
        return "file";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO" + getTableName() + " (filename, path, userid, courseid, title, createdat, updatedat, content) VALUES (?,?,?,?,?,?,?,?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, File file) throws SQLException {

        ps.setString(1,file.getFilename());
        ps.setString(2,file.getPath());
        ps.setInt(3,file.getUserId());
        ps.setInt(4,file.getCourseId());
        ps.setString(5,file.getTitle());
        ps.setDate(6, Date.valueOf(file.getCreatedAt()));
        ps.setDate(7, Date.valueOf(file.getUpdatedAt()));
        ps.setString(8,file.getContent());
    }

    @Override
    protected String getUpdateQuery() {
        return "INSERT INTO " + getTableName() + " (filename, path, title, content) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, File entity) throws SQLException {
        ps.setString(1, entity.getFilename());
        ps.setString(2, entity.getPath());
        ps.setString(3, entity.getTitle());
        ps.setString(4, entity.getContent());

    }

    public List<File> findFilesByCourseId(int courseId){
        String querySQL = "SELECT * from " + getTableName() + " WHERE courseid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            List<File> filesByCourseIdList = new ArrayList<>();
            while(rs.next()){
                filesByCourseIdList.add(mapResultSetToEntity(rs));
            }
            return filesByCourseIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setGeneratedId(File entity, int id) {
        entity.setId(id);
    }
}
