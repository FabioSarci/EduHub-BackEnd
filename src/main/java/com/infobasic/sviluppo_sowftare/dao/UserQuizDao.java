package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Presence;
import com.infobasic.sviluppo_sowftare.model.UserQuiz;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<UserQuiz> findUserQuizByUserId(int userId){

        String querySQL = "SELECT * from " + getTableName() + " WHERE userid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<UserQuiz> presencesByUserIdList = new ArrayList<>();
            while(rs.next()){
                presencesByUserIdList.add(mapResultSetToEntity(rs));
            }
            return presencesByUserIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserQuiz> findUserQuizByQuizId(int quizId){

        String querySQL = "SELECT * from " + getTableName() + " WHERE quizid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            List<UserQuiz> presencesByUserIdList = new ArrayList<>();
            while(rs.next()){
                presencesByUserIdList.add(mapResultSetToEntity(rs));
            }
            return presencesByUserIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
