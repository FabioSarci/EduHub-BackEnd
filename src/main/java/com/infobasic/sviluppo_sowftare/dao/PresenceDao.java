package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Presence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PresenceDao extends GenericDao<Presence, Integer>{


    @Override
    protected Presence mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Presence(
                rs.getInt("id"),
                rs.getInt("lessonid"),
                rs.getInt("userid"),
                rs.getBoolean("ispresent")
        );
    }

    @Override
    protected String getTableName() {
        return "presence";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (lessonid, userid, ispresent) VALUES (?,?,?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Presence presence) throws SQLException {

        ps.setInt(1,presence.getLessonId());
        ps.setInt(2,presence.getUserId());
        ps.setBoolean(3,presence.isPresent());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET ispresent = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Presence presence) throws SQLException {

        ps.setBoolean(1, presence.isPresent());
        ps.setInt(2,presence.getId());
    }

    public List<Presence> findPresencesByUserId(int userId){

        String querySQL = "SELECT * from " + getTableName() + " WHERE userid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Presence> presencesByUserIdList = new ArrayList<>();
            while(rs.next()){
                presencesByUserIdList.add(mapResultSetToEntity(rs));
            }
            return presencesByUserIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Presence> findPresencesByLessonId(int lessonId){

        String querySQL = "SELECT * from " + getTableName() + " WHERE lessonid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();
            List<Presence> presencesByLessonIdList = new ArrayList<>();
            while(rs.next()){
                presencesByLessonIdList.add(mapResultSetToEntity(rs));
            }
            return presencesByLessonIdList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setGeneratedId(Presence presence, int id) {
        presence.setId(id);
    }
}
