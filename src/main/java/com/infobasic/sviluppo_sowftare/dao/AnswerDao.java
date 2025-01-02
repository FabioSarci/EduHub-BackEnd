package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Answer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerDao extends GenericDao<Answer,Integer>{
    @Override
    protected Answer mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Answer(
                rs.getInt("id"),
                rs.getString("text"),
                rs.getBoolean("iscorrect"),
                rs.getInt("questionid")
        );
    }

    @Override
    protected String getTableName() {
        return "question";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (text,iscorrect,questionid) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Answer answer) throws SQLException {

        ps.setString(1,answer.getText());
        ps.setBoolean(2, answer.isCorrect());
        ps.setInt(3,answer.getQuestionId());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET text = ?, iscorrect = ?, questionid = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Answer answer) throws SQLException {

        ps.setString(1,answer.getText());
        ps.setBoolean(2,answer.isCorrect());
        ps.setInt(3,answer.getQuestionId());
        ps.setInt(4,answer.getId());
    }

    @Override
    protected void setGeneratedId(Answer question, int id) {
        question.setId(id);
    }
}
