package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        ps.setInt(3,notification.getUserId());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE" + getTableName() + "SET Object = ?, body = ?, WJERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Notification notification) throws SQLException {

        ps.setString(1,notification.getObject());
        ps.setString(2, notification.getBody());
        ps.setInt(3,notification.getId());

    }

    @Override
    protected void setGeneratedId(Notification notification, int id) {
        notification.setId(id);
    }
}
