package com.example.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.example.connection.MyDataBase;
import com.example.dataAccess.*;
import com.example.model.Order;
import com.example.model.Product;
import com.example.bussinessLogic.ProductBLL;

public class ControllerOrders {

    private OrderManagement oOrderManagement;
    ProductBLL productBLL = new ProductBLL();

    /**
     * Constructs a new ControllerOrders object.
     *
     * @param orderManagement the OrderManagement object to be controlled
     */
    public ControllerOrders(OrderManagement orderManagement) {
        oOrderManagement = orderManagement;
        orderManagement.addOrder(new AddOrder());
        orderManagement.deleteOrder(new DeleteOrder());
        orderManagement.updateOrder(new UpdateOrder());
    }

    /**
     * ActionListener implementation for adding an order.
     */
    class AddOrder implements ActionListener {
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();

        /**
         * Performs the action of adding an order.
         *
         * @param e the ActionEvent representing the action performed
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Order order = new Order(oOrderManagement.getClientId(), oOrderManagement.getProductId(),
                        oOrderManagement.getQuantity());
                Product product = productDAO.findById(order.getProductId());
                int updatedStock = product.getStock() - order.getQuantity();
                if (updatedStock < 0) {
                    JOptionPane.showMessageDialog(null, "Not enough stock to create order", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String updateQuery = "UPDATE warehouse.products SET stock = ? WHERE id = ?";
                try (Connection conn = MyDataBase.getConnection();
                        PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                    ps.setInt(1, updatedStock);
                    ps.setInt(2, product.getProductId());
                    ps.executeUpdate();
                }

                orderDAO.insert(order);
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
     * ActionListener implementation for deleting an order.
     */
    class DeleteOrder implements ActionListener {
        OrderDAO orderDAO = new OrderDAO();

        /**
         * Performs the action of deleting an order.
         *
         * @param e the ActionEvent representing the action performed
         */
        public void actionPerformed(ActionEvent e) {
            try {
                int id = oOrderManagement.getId();

                if (id == -1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (orderDAO.findById(id) == null) {
                    JOptionPane.showMessageDialog(null, "ID not good u bozo :v", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                orderDAO.delete(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for updating an order.
     */
    class UpdateOrder implements ActionListener {
        OrderDAO orderDAO = new OrderDAO();

        /**
         * Performs the action of updating an order.
         *
         * @param e the ActionEvent representing the action performed
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Order order = new Order(oOrderManagement.getId(), oOrderManagement.getClientId(),
                        oOrderManagement.getProductId(),
                        oOrderManagement.getQuantity());
                int id = oOrderManagement.getId();

                if (id == -1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (orderDAO.findById(id) == null) {
                    JOptionPane.showMessageDialog(null, "ID not good u bozo :v", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Order or = orderDAO.findById(id);

                if (or.getProductId() == order.getProductId()) {
                    Product product = productBLL.findProductById(order.getProductId());
                    int updatedStock = (product.getStock() + or.getQuantity()) - order.getQuantity();
                    if (updatedStock < 0) {
                        JOptionPane.showMessageDialog(null, "Not enough stock to create order", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String updateQuery = "UPDATE warehouse.products SET stock = ? WHERE id = ?";
                    try (Connection conn = MyDataBase.getConnection();
                            PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                        ps.setInt(1, updatedStock);
                        ps.setInt(2, product.getProductId());
                        ps.executeUpdate();
                    }
                } else {
                    Product product = productBLL.findProductById(or.getProductId());
                    int updatedStock = product.getStock() + or.getQuantity();
                    if (updatedStock < 0) {
                        JOptionPane.showMessageDialog(null, "Not enough stock to create order", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String updateQuery = "UPDATE warehouse.products SET stock = ? WHERE id = ?";
                    try (Connection conn = MyDataBase.getConnection();
                            PreparedStatement ps = conn.prepareStatement(updateQuery)) {
                        ps.setInt(1, updatedStock);
                        ps.setInt(2, product.getProductId());
                        ps.executeUpdate();
                    }
                    product = productBLL.findProductById(id);
                    updatedStock = product.getStock() - order.getQuantity();
                    if (updatedStock < 0) {
                        JOptionPane.showMessageDialog(null, "Not enough stock to create order", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String updateQuery1 = "UPDATE warehouse.products SET stock = ? WHERE id = ?";
                    try (Connection conn = MyDataBase.getConnection();
                            PreparedStatement ps = conn.prepareStatement(updateQuery1)) {
                        ps.setInt(1, updatedStock);
                        ps.setInt(2, product.getProductId());
                        ps.executeUpdate();
                    }
                }
                orderDAO.update(order);
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
