package com.example.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.example.dataAccess.ProductDAO;
import com.example.bussinessLogic.ProductBLL;
import com.example.dataAccess.OrderDAO;
import com.example.model.Product;

/**
 * Controller class for managing products.
 */
public class ControllerProducts {
    private ProductManagement pProductManagement;
    ProductDAO productDAO = new ProductDAO();
    OrderDAO orderDAO = new OrderDAO();
    private ProductBLL productBLL = new ProductBLL();

    /**
     * Constructs a new instance of the ControllerProducts class.
     *
     * @param productManagement the ProductManagement object to be associated with
     *                          this controller
     */
    public ControllerProducts(ProductManagement productManagement) {
        pProductManagement = productManagement;
        productManagement.addProduct(new AddProduct());
        productManagement.deleteProduct(new DeleteProduct());
        productManagement.updateProduct(new UpdateProduct());
    }

    /**
     * ActionListener implementation for adding a product.
     */
    class AddProduct implements ActionListener {
        /**
         * Performs the action of adding a product.
         *
         * @param e the ActionEvent representing the action
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Product product = new Product(pProductManagement.getId(), pProductManagement.getName(),
                        pProductManagement.getStock());
                productBLL.insertProduct(product);
            } catch (IllegalArgumentException e1) {
                JOptionPane.showMessageDialog(null, "Input error, check types", "Error",
                        JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Invalid");
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "SQL error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for deleting a product.
     */
    class DeleteProduct implements ActionListener {
        ProductDAO productDAO = new ProductDAO();

        /**
         * Performs the action of deleting a product.
         *
         * @param e the ActionEvent representing the action
         */
        public void actionPerformed(ActionEvent e) {
            try {
                int id = pProductManagement.getId();

                if (id == -1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (productDAO.findById(id) == null) {
                    JOptionPane.showMessageDialog(null, "ID not good u bozo :v", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                orderDAO.delete(id);
                productDAO.delete(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for updating a product.
     */
    class UpdateProduct implements ActionListener {
        ProductDAO productDAO = new ProductDAO();

        /**
         * Performs the action of updating a product.
         *
         * @param e the ActionEvent representing the action
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Product product = new Product(pProductManagement.getId(),
                        pProductManagement.getName(),
                        pProductManagement.getStock());
                int id = pProductManagement.getId();

                if (id == -1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (productDAO.findById(id) == null) {
                    JOptionPane.showMessageDialog(null, "ID not good u bozo :v", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                productBLL.updateProduct(product);
            } catch (IllegalArgumentException e1) {
                JOptionPane.showMessageDialog(null, "Input error, check types", "Error",
                        JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Invalid");
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "SQL error", "Error",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }
}
