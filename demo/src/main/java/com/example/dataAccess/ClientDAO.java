package com.example.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.example.model.Client;

public class ClientDAO extends AbstractDAO<Client> {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatement = "INSERT INTO warehouse.clients (name, address, email, age) VALUES (?,?,?,?)";
    private final static String findStatement = "SELECT * FROM warehouse.clients where id = ?";
    private final static String deleteStatement = "DELETE FROM warehouse.clients WHERE id=?";
    private static final String updateStatement = "UPDATE warehouse.clients SET name = ?, address = ?, email = ?, age = ? WHERE id = ?";
    private static final String findAllStatement = "SELECT * FROM warehouse.clients";

    
    /** 
     * @return Client Class
     */
    @Override
    protected Class<Client> getEntityType() {
        return Client.class;
    }

    
    /** 
     * @return String
     */
    @Override
    protected String getFindStatement() {
        return findStatement;
    }

    
    /** 
     * @return String
     */
    @Override
    protected String getInsertStatement() {
        return insertStatement;
    }

    
    /** 
     * @return String
     */
    @Override
    protected String getDeleteStatement() {
        return deleteStatement;
    }

    
    /** 
     * @return String
     */
    @Override
    protected String getUpdateStatement() {
        return updateStatement;
    }

    
    /** 
     * @param rs
     * @return Client
     * @throws SQLException
     */
    @Override
    protected Client mapResultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String email = rs.getString("email");
        int age = rs.getInt("age");
        return new Client(id, name, address, email, age);
    }

    
    /** 
     * @param ps
     * @param entity
     * @throws SQLException
     */
    @Override
    protected void setInsertStatementParameters(PreparedStatement ps, Client entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getAddress());
        ps.setString(3, entity.getEmail());
        ps.setInt(4, entity.getAge());
    }

    
    /** 
     * @param ps
     * @param entity
     * @throws SQLException
     */
    @Override
    protected void setUpdateStatementParameters(PreparedStatement ps, Client entity) throws SQLException {
        ps.setString(1, entity.getName());
        ps.setString(2, entity.getAddress());
        ps.setString(3, entity.getEmail());
        ps.setInt(4, entity.getAge());
        ps.setInt(5, entity.getId());
    }

    
    /** 
     * @return List of clients
     * @throws SQLException
     */
    public List<Client> getAllClients() throws SQLException {
        return executeQuery(findAllStatement);
    }
}
