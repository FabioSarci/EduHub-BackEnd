package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Notification;
import com.infobasic.sviluppo_sowftare.model.UserCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao extends GenericDao<Notification, Integer>{
    @Override
    protected Notification mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Notification(
                rs.getInt("id"),
                rs.getString("object"),
                rs.getString("body"),
                rs.getInt("userid")

        );
    }

    @Override
    protected String getTableName() {
        return "notification";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (object,body,userId) VALUES (?,?,?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Notification notification) throws SQLException {

        ps.setString(1, notification.getObject());
        ps.setString(2, notification.getBody());
        ps.setInt(3,notification.getUserid());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE" + getTableName() + "SET Object = ?, body = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Notification notification) throws SQLException {

        ps.setString(1,notification.getObject());
        ps.setString(2, notification.getBody());
        ps.setInt(3,notification.getId());

    }

    public List<Notification> getByUserId(int userId){
        String querySQL = "SELECT * from " + getTableName() + " WHERE userid = ? ";

        try{
            PreparedStatement ps = connection.prepareStatement(querySQL);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Notification> NotificationsByIdUserList = new ArrayList<>();
            while(rs.next()){
                NotificationsByIdUserList.add(mapResultSetToEntity(rs));
            }
            return NotificationsByIdUserList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void setGeneratedId(Notification notification, int id) {
        notification.setId(id);
    }
}
