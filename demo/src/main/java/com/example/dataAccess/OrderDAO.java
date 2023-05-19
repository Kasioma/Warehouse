package com.example.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.example.model.Order;

public class OrderDAO extends AbstractDAO<Order> {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatement = "INSERT INTO warehouse.orders (clientId, productId, quantity) VALUES (?,?,?)";
    private static final String findStatement = "SELECT orders.id AS id, clients.id AS clientId, products.id AS productId, orders.quantity AS quantity FROM warehouse.orders INNER JOIN warehouse.clients ON orders.clientId = clients.id INNER JOIN warehouse.products ON orders.productId = products.id where orders.id = ?";
    private static final String updateStatement = "UPDATE warehouse.orders SET clientId = ?, productId = ?, quantity = ? WHERE id = ?";
    public static final String deleteStatement = "DELETE FROM warehouse.orders WHERE id = ?";
    private static final String findAllStatement = "SELECT * FROM warehouse.orders";

    
    /** 
     * @return Class<Order>
     */
    @Override
    protected Class<Order> getEntityType() {
        return Order.class;
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
     * @return Order
     * @throws SQLException
     */
    @Override
    protected Order mapResultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        int clientId = rs.getInt(2);
        int productId = rs.getInt(3);
        int stock = rs.getInt(4);
        return new Order(id, clientId, productId, stock);
    }

    
    /** 
     * @param ps
     * @param entity
     * @throws SQLException
     */
    @Override
    protected void setInsertStatementParameters(PreparedStatement ps, Order entity) throws SQLException {
        ps.setInt(1, entity.getClientId());
        ps.setInt(2, entity.getProductId());
        ps.setInt(3, entity.getQuantity());
    }

    
    /** 
     * @param ps
     * @param entity
     * @throws SQLException
     */
    @Override
    protected void setUpdateStatementParameters(PreparedStatement ps, Order entity) throws SQLException {
        ps.setInt(4, entity.getOrderId());
        ps.setInt(1, entity.getClientId());
        ps.setInt(2, entity.getProductId());
        ps.setInt(3, entity.getQuantity());
    }

    
    /** 
     * @return List<Order>
     * @throws SQLException
     */
    public List<Order> getAllClients() throws SQLException {
        return executeQuery(findAllStatement);
    }
}
