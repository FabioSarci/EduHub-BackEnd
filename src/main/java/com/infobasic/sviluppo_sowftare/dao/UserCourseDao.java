package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.User;
import com.infobasic.sviluppo_sowftare.model.UserCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<UserCourse> findUserCoursesByIdCourse(int courseId){

        String querySQL = "SELECT * from " + getTableName() + " WHERE classid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            List<UserCourse> userCourseByIdCourseList = new ArrayList<>();
            while(rs.next()){
                userCourseByIdCourseList.add(mapResultSetToEntity(rs));
            }
            return userCourseByIdCourseList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserCourse> findUserCoursesByIdUser(int userId){

        String querySQL = "SELECT * from " + getTableName() + " WHERE userId = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<UserCourse> userCourseByIdUserList = new ArrayList<>();
            while(rs.next()){
                userCourseByIdUserList.add(mapResultSetToEntity(rs));
            }
            return userCourseByIdUserList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setGeneratedId(UserCourse userCourse, int id) {
        userCourse.setId(id);
    }
}
