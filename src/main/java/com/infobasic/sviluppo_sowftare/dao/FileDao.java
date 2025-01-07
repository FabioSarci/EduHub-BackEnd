package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.File;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDao extends GenericDao<File, Integer>{
    @Override
    protected File mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new File(
                rs.getInt("id"),
                rs.getString("filename"),
                rs.getString("path"),
                rs.getInt("userid"),
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
        return "INSERT INTO" + getTableName() + " (filename, path, userid, title, createdat, updatedat, content) VALUES (?,?,?,?,?,?,?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, File file) throws SQLException {

        ps.setString(1,file.getFilename());
        ps.setString(2,file.getPath());
        ps.setInt(3,file.getId());
        ps.setString(4,file.getTitle());
        ps.setDate(5, Date.valueOf(file.getCreatedAt()));
        ps.setDate(6, Date.valueOf(file.getUpdatedAt()));
        ps.setString(7,file.getContent());
    }

    @Override
    protected String getUpdateQuery() {
        return "";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, File entity) throws SQLException {

    }

    @Override
    protected void setGeneratedId(File entity, int id) {

    }
}
