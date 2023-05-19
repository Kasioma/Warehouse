package com.example.bussinessLogic;

import java.util.List;
import java.util.NoSuchElementException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.bussinessLogic.validators.ProductNameVal;
import com.example.bussinessLogic.validators.StockValidator;
import com.example.bussinessLogic.validators.Validator;
import com.example.dataAccess.ProductDAO;
import com.example.model.Product;

public class ProductBLL {
    private List<Validator<Product>> validators;
    ProductDAO productDAO = new ProductDAO();

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductNameVal());
        validators.add(new StockValidator());
    }

    
    /** 
     * @param id
     * @return Product
     * @throws SQLException
     */
    public Product findProductById(int id) throws SQLException {
        Product cl = productDAO.findById(id);
        if (cl == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return cl;
    }

    
    /** 
     * @param product
     * @return int
     * @throws SQLException
     */
    public int insertProduct(Product product) throws SQLException {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return productDAO.insert(product);
    }

    
    /** 
     * @param product
     * @throws SQLException
     */
    public void updateProduct(Product product) throws SQLException {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        productDAO.update(product);
        return;
    }
}
