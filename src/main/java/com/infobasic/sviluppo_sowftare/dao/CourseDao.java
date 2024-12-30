package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDao extends GenericDao<Course,Integer>{
    @Override
    protected Course mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Course(
                rs.getInt("id"),
                rs.getString("coursename"),
                rs.getString("section"),
                rs.getString("subject")
        );
    }

    @Override
    protected String getTableName() {
        return "course";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (coursename, section, subject) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Course course) throws SQLException {

        ps.setString(1,course.getCoursename());
        ps.setString(2,course.getSection());
        ps.setString(3,course.getSubject());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET coursename = ?, section = ?, subject = ?, WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Course course) throws SQLException {

        ps.setString(1,course.getCoursename());
        ps.setString(2,course.getSection());
        ps.setString(3,course.getSubject());
        ps.setInt(4, course.getId());
    }

    @Override
    protected void setGeneratedId(Course course, int id) {
        course.setId(id);
    }
}
