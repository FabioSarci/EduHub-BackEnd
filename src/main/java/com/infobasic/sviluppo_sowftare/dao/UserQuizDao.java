package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.UserQuiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserQuizDao extends GenericDao<UserQuiz,Integer>{
    @Override
    protected UserQuiz mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new UserQuiz(
                rs.getInt("id"),
                rs.getInt("quizid"),
                rs.getInt("userid"),
                rs.getTimestamp("completedat").toLocalDateTime()
        );
    }

    @Override
    protected String getTableName() {
        return "user_quiz";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (quizid, userid, completedat) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, UserQuiz userQuiz) throws SQLException {

        ps.setInt(1,userQuiz.getQuizId());
        ps.setInt(2,userQuiz.getUserId());
        ps.setTimestamp(3, Timestamp.valueOf(userQuiz.getCompletedAt()));
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET completedat = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, UserQuiz userQuiz) throws SQLException {

        ps.setTimestamp(1,Timestamp.valueOf(userQuiz.getCompletedAt()));
    }

    @Override
    protected void setGeneratedId(UserQuiz userQuiz, int id) {
        userQuiz.setId(id);
    }
}
