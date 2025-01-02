package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Presence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PresenceDao extends GenericDao<Presence, Integer>{


    @Override
    protected Presence mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new Presence(
                rs.getInt("id"),
                rs.getInt("lessionid"),
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
        return "INSERT INTO " + getTableName() + " (lessioid, userid, ispresent) VALUES (?,?,?)";
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

    @Override
    protected void setGeneratedId(Presence presence, int id) {
        presence.setId(id);
    }
}
