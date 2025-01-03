package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.UserAnswer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAnswerDao extends GenericDao<UserAnswer, Integer>{
    @Override
    protected UserAnswer mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new UserAnswer(
                rs.getInt("id"),
                rs.getInt("questionid"),
                rs.getInt("userquizid"),
                rs.getInt("answerid")
        );
    }

    @Override
    protected String getTableName() {
        return "user_answer";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (questionid, userquizid, answerid) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, UserAnswer userAnswer) throws SQLException {

        ps.setInt(1,userAnswer.getQuestionId());
        ps.setInt(2,userAnswer.getUserQuizId());
        ps.setInt(3,userAnswer.getAnswerId());
    }

    @Override
    protected String getUpdateQuery() {
        return "";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, UserAnswer userAnswer) throws SQLException {

    }

    @Override
    protected void setGeneratedId(UserAnswer userAnswer, int id) {
        userAnswer.setId(id);
    }

    public List<UserAnswer> getByUserQuizId(int id){
        String querySQL = "SELECT * from " + getTableName() + " WHERE userquizid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<UserAnswer> userAnswerByUserQuizIdList = new ArrayList<>();
            while(rs.next()){
                userAnswerByUserQuizIdList.add(mapResultSetToEntity(rs));
            }
            return userAnswerByUserQuizIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
