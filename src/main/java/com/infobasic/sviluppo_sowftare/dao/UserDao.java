package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.User;
import com.infobasic.sviluppo_sowftare.utility.UserType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDao extends GenericDao<User, Integer> {

    @Override
    protected User mapResultSetToEntity(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                LocalDate.parse(rs.getString("birthdate")),
                UserType.valueOf(rs.getString("idtype"))
        );
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (name, surname, birthdate, idtype) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, User user) throws SQLException {

        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getBirthdate().toString());
        ps.setString(2, user.getRole().toString());
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET name = ?, surname = ?, birthdate = ?, idtype = ?, WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, User user) throws SQLException {

        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getBirthdate().toString());
        ps.setString(2, user.getRole().toString());
        ps.setInt(4, user.getId());
    }

    @Override
    protected void setGeneratedId(User user, int id) {
        user.setId(id);
    }
}



