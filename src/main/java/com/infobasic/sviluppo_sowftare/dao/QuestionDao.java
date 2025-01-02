package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Question;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDao extends GenericDao<Question, Integer>{
    @Override
    protected Question mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Question(
                rs.getInt("id"),
                rs.getString("text"),
                rs.getInt("quizid")
        );
    }

    @Override
    protected String getTableName() {
        return "quiz";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (text,quizid) VALUES (?,?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Question question) throws SQLException {

        ps.setString(1,question.getText());
        ps.setInt(2, question.getQuizId());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET text = ?, quizid = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Question question) throws SQLException {

        ps.setString(1,question.getText());
        ps.setInt(2,question.getQuizId());
        ps.setInt(3, question.getId());
    }

    @Override
    protected void setGeneratedId(Question question, int id) {
        question.setId(id);
    }
}
