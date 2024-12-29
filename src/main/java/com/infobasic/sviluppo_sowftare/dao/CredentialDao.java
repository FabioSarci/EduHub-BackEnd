package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.model.Credential;
import com.infobasic.sviluppo_sowftare.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialDao extends GenericDao<Credential, Integer> {


    @Override
    protected Credential mapResultSetToEntity(ResultSet rs) throws SQLException {

        return new Credential(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("userid")

        );
    }

    @Override
    protected String getTableName() {
        return "credential";
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO " + getTableName() + " (email, password, userid) VALUES (?, ?, ?)";
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Credential credential) throws SQLException {
        ps.setString(1, credential.getEmail());
        ps.setString(2,credential.getPassword());
        ps.setInt(3,credential.getId());

    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE " + getTableName() + " SET email = ?, password = ?, WHERE id = ?";
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, Credential credential) throws SQLException {

        ps.setString(1, credential.getEmail());
        ps.setString(2, credential.getPassword());
        ps.setInt(3,credential.getId());
    }

    @Override
    protected void setGeneratedId(Credential credential, int id) {
        credential.setId(id);
    }

    public Credential findByEmail(Credential credential){
        String query = "SELECT * FROM " + getTableName() + " WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, credential.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la ricerca per ID", e);
        }
        return null;
    }
}
