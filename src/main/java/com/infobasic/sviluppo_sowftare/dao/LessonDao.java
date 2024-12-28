package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LessonDao extends GenericDao<Lesson, Integer>{
    @Override
    protected Lesson mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Lesson(
                rs.getInt("id"),
                rs.getString("topic"),
                rs.getString("description"),
                LocalDateTime.parse(rs.getString("date")),
                rs.getInt("courseid")
        );
    }

    @Override
    protected String getTableName() {
        return "lesson";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (topic,description,date,courseid) VALUES (?,?,?,?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Lesson lesson) throws SQLException {

        ps.setString(1, lesson.getTopic());
        ps.setString(2, lesson.getDescription());
        ps.setString(3,lesson.getDate().toString());
        ps.setInt(4,lesson.getCourseId());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET topic = ?, description = ?, date = ?, WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Lesson lesson) throws SQLException {

        ps.setString(1, lesson.getTopic());
        ps.setString(2, lesson.getDescription());
        ps.setString(3,lesson.getDate().toString());
        ps.setInt(4,lesson.getId());
    }

    @Override
    protected void setGeneratedId(Lesson lesson, int id) {
        lesson.setId(id);
    }
}
