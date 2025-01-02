package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Quiz;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QuizDao extends GenericDao<Quiz, Integer>{


    @Override
    protected Quiz mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Quiz(
                rs.getInt("id"),
                rs.getString("title"),
                LocalDateTime.parse(rs.getDate("publishedat").toString()),
                rs.getInt("courseid")
        );
    }

    @Override
    protected String getTableName() {
        return "quiz";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (title,publishedat,courseid) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Quiz quiz) throws SQLException {

        ps.setString(1,quiz.getTitle());
        ps.setDate(2, Date.valueOf(quiz.getPublishedAt().toLocalDate()));
        ps.setInt(3,quiz.getCourseId());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET title = ?, publishedat = ?, courseid = ? WHERE id = ? ";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Quiz quiz) throws SQLException {

        ps.setString(1,quiz.getTitle());
        ps.setDate(2, Date.valueOf(quiz.getPublishedAt().toLocalDate()));
        ps.setInt(3, quiz.getCourseId());
        ps.setInt(4, quiz.getId());
    }

    @Override
    protected void setGeneratedId(Quiz quiz, int id) {
        quiz.setId(id);
    }

    public List<Quiz> getQuizByCourseId(int courseId){
        String querySQL = "SELECT * from " + getTableName() + " WHERE courseid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            List<Quiz> quizByLessonIdList = new ArrayList<>();
            while(rs.next()){
                quizByLessonIdList.add(mapResultSetToEntity(rs));
            }
            return quizByLessonIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
