package com.example.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.connection.MyDataBase;
import com.example.model.Client;

public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    protected abstract String getFindStatement();

    protected abstract String getInsertStatement();

    protected abstract String getDeleteStatement();

    protected abstract String getUpdateStatement();

    protected abstract T mapResultSetToEntity(ResultSet rs) throws SQLException;

    protected abstract Class<T> getEntityType();

    /**
     * Closes the given database resources, including the Connection,
     * PreparedStatement, and ResultSet.
     * 
     * @param conn The Connection object to close.
     * @param ps   The PreparedStatement object to close.
     * @param rs   The ResultSet object to close.
     */
    private void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing result set: " + e.getMessage());
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing prepared statement: " + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Executes the specified SQL query and returns a list of entities resulting
     * from the query.
     * 
     * @param sql The SQL query to execute.
     * @return A list of entities resulting from the query.
     * @throws SQLException if an error occurs while executing the query.
     */
    protected List<T> executeQuery(String sql) throws SQLException {
        List<T> entities = new ArrayList<>();
        Connection conn = MyDataBase.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                T entity = mapResultSetToEntity(rs);
                entities.add(entity);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:executeQuery " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }

        return entities;
    }

    /**
     * Retrieves an entity with the specified ID from the database.
     * 
     * @param id The ID of the entity to retrieve.
     * @return The retrieved entity, or null if no entity is found with the given
     *         ID.
     * @throws SQLException if an error occurs while retrieving the entity.
     */
    public T findById(int id) throws SQLException {
        T entity = null;
        Connection conn = MyDataBase.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String findStatement = getFindStatement();
            ps = conn.prepareStatement(findStatement);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                entity = mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:findById " + e.getMessage());
        } finally {
            closeResources(conn, ps, rs);
        }

        return entity;
    }

    /**
     * Inserts the specified entity into the database.
     * 
     * @param entity The entity to insert.
     * @return The ID of the inserted entity, or -1 if the insertion fails.
     * @throws SQLException if an error occurs while inserting the entity.
     */
    public int insert(T entity) throws SQLException {
        Connection conn = MyDataBase.getConnection();
        PreparedStatement ps = null;
        int insertedId = -1;

        try {
            String insertStatement = getInsertStatement();
            ps = conn.prepareStatement(insertStatement, PreparedStatement.RETURN_GENERATED_KEYS);
            setInsertStatementParameters(ps, entity);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:insert " + e.getMessage());
        } finally {
            closeResources(conn, ps, null);
        }

        return insertedId;
    }

    /**
     * Deletes an entity with the specified ID from the database.
     * 
     * @param id The ID of the entity to delete.
     * @throws SQLException if an error occurs while deleting the entity.
     */
    public void delete(int id) throws SQLException {
        Connection conn = MyDataBase.getConnection();
        PreparedStatement ps = null;

        try {
            if (Client.class.equals(getEntityType())) {
                String deleteOrdersStatement = "DELETE FROM warehouse.orders WHERE clientId = ?";
                ps = conn.prepareStatement(deleteOrdersStatement);
                ps.setInt(1, id);
                ps.executeUpdate();
                ps.close();
            }
            String deleteStatement = getDeleteStatement();
            ps = conn.prepareStatement(deleteStatement);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:delete " + e.getMessage());
        } finally {
            closeResources(conn, ps, null);
        }
    }

    /**
     * 
     * Updates the specified entity in the database.
     * 
     * @param entity The entity to update.
     * 
     * @throws SQLException if an error occurs while updating the entity.
     */
    public void update(T entity) throws SQLException {
        Connection conn = MyDataBase.getConnection();
        PreparedStatement ps = null;

        try {
            String updateStatement = getUpdateStatement();
            ps = conn.prepareStatement(updateStatement);
            setUpdateStatementParameters(ps, entity);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:update " + e.getMessage());
        } finally {
            closeResources(conn, ps, null);
        }
    }

    /**
     * 
     * Sets the parameters of the PreparedStatement for inserting the specified
     * entity.
     * 
     * @param ps     The PreparedStatement object to set parameters for.
     * @param entity The entity to insert.
     * @throws SQLException if an error occurs while setting the parameters.
     */
    protected abstract void setInsertStatementParameters(PreparedStatement ps, T entity) throws SQLException;

    /**
     * 
     * Sets the parameters of the PreparedStatement for updating the specified
     * entity.
     * 
     * @param ps     The PreparedStatement object to set parameters for.
     * @param entity The entity to update.
     * @throws SQLException if an error occurs while setting the parameters.
     */
    protected abstract void setUpdateStatementParameters(PreparedStatement ps, T entity) throws SQLException;
}
