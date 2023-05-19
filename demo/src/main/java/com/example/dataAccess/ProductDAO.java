package com.example.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.example.model.Product;

public class ProductDAO extends AbstractDAO<Product> {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatement = "INSERT INTO warehouse.products (name, stock) VALUES (?,?)";
    private final static String findStatement = "SELECT * FROM warehouse.products where id = ?";
    private final static String deleteStatement = "DELETE FROM warehouse.products WHERE id=?";
    private static final String updateStatement = "UPDATE warehouse.products SET name = ?, stock = ? WHERE id = ?";
    private static final String findAllStatement = "SELECT * FROM warehouse.products";

    
    /** 
     * @return Class<Product>
     */
    @Override
    protected Class<Product> getEntityType() {
        return Product.class;
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
     * @return Product
     * @throws SQLException
     */
    @Override
    protected Product mapResultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int stock = rs.getInt("stock");
        return new Product(id, name, stock);
    }

    
    /** 
     * @param ps
     * @param entity
     * @throws SQLException
     */
    @Override
    protected void setInsertStatementParameters(PreparedStatement ps, Product entity) throws SQLException {
        ps.setString(1, entity.getProductName());
        ps.setInt(2, entity.getStock());
    }

    
    /** 
     * @param ps
     * @param entity
     * @throws SQLException
     */
    @Override
    protected void setUpdateStatementParameters(PreparedStatement ps, Product entity) throws SQLException {
        ps.setString(1, entity.getProductName());
        ps.setInt(2, entity.getStock());
        ps.setInt(3, entity.getProductId());
    }

    
    /** 
     * @return List<Product>
     * @throws SQLException
     */
    public List<Product> getAllClients() throws SQLException {
        return executeQuery(findAllStatement);
    }
}
