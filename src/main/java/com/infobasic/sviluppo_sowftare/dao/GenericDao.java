package com.infobasic.sviluppo_sowftare.dao;

import com.infobasic.sviluppo_sowftare.utility.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericDao<T, ID> implements GenericDaoInterface<T, ID> {

    protected final Connection connection = DBConnection.getIstance().getConnection();


    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    protected abstract String getTableName();

    protected abstract String getInsertQuery();

    protected abstract void setInsertStatement(PreparedStatement ps, T entity) throws SQLException;

    protected abstract String getUpdateQuery();

    protected abstract void setUpdateStatement(PreparedStatement ps, T entity) throws SQLException;

    @Override
    public Optional<T> findById(ID id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEntity(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la ricerca per ID", e);
        }
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        String query = "SELECT * FROM " + getTableName();
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            return mapResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la ricerca di tutti i record", e);
        }
    }

    protected List<T> mapResultSetToList(ResultSet rs) throws SQLException {
        List<T> entities = new ArrayList<>();
        while (rs.next()) {
            entities.add(mapResultSetToEntity(rs));
        }
        return entities;
    }

    @Override
    public T create(T entity) {
        String query = getInsertQuery();
        try (PreparedStatement ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            setInsertStatement(ps, entity);
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    setGeneratedId(entity, generatedKeys.getInt(1));
                }
            }
            return entity; // Restituisce l'entità creata
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante la creazione dell'entità", e);
        }
    }

    @Override
    public T update(T entity) {
        String query = getUpdateQuery();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            setUpdateStatement(ps, entity);
            ps.executeUpdate();
            return entity; // Restituisce l'entità aggiornata
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'aggiornamento dell'entità", e);
        }
    }

    @Override
    public void deleteById(ID id) {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante l'eliminazione per ID", e);
        }
    }

    @Override
    public long count() {
        String query = "SELECT COUNT(*) FROM " + getTableName();
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il conteggio dei record", e);
        }
        return 0;
    }

    protected abstract void setGeneratedId(T entity, int id);
}
