package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.UserCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserCourseDao extends GenericDao<UserCourse, Integer>{
    @Override
    protected UserCourse mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new UserCourse(
                rs.getInt("id"),
                rs.getInt("classid"),
                rs.getInt("userid")
        );
    }

    @Override
    protected String getTableName() {
        return "user_course";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (classid, userid) VALUES (?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, UserCourse userCourse) throws SQLException {

        ps.setInt(1,userCourse.getCourseId());
        ps.setInt(2,userCourse.getUserId());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET classid = ?, userid = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, UserCourse userCourse) throws SQLException {

        ps.setInt(1,userCourse.getCourseId());
        ps.setInt(2,userCourse.getUserId());
        ps.setInt(3,userCourse.getId());
    }

    @Override
    protected void setGeneratedId(UserCourse userCourse, int id) {
        userCourse.setId(id);
    }
}
