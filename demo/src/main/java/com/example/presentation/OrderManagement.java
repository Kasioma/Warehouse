package com.example.presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.example.model.ClientTableModel;
import com.example.model.OrderTableModel;
import com.example.model.ProductTableModel;

import com.example.dataAccess.*;

public class OrderManagement extends JFrame {
    private JTable tableOrders;
    private JTable tableClients;
    private JTable tableProducts;
    private ProductTableModel productTableModel;
    private ClientTableModel clientTableModel;
    private OrderTableModel orderTableModel;
    private JButton addOrder = new JButton("Add");
    private JButton deleteOrder = new JButton("Delete");
    private JButton updateOrder = new JButton("Update");
    private JTextField productId = new JTextField();
    private JTextField clientId = new JTextField();
    private JTextField orderId = new JTextField();
    private JTextField quantity = new JTextField();
    ProductDAO productDAO = new ProductDAO();
    ClientDAO clientDAO = new ClientDAO();
    OrderDAO orderDAO = new OrderDAO();

    /**
     * OrderManagement class responsible for managing orders.
     */
    public OrderManagement() throws SQLException {
        createTableClients();
        createTableProducts();
        createTableOrders();
        setupFrame();
    }

    /**
     * Creates the table for products.
     * 
     * @throws SQLException if a SQL error occurs
     */
    private void createTableProducts() throws SQLException {
        productTableModel = new ProductTableModel(productDAO.getAllClients());
        tableProducts = new JTable(productTableModel);
    }

    /**
     * Creates the table for clients.
     * 
     * @throws SQLException if a SQL error occurs
     */
    private void createTableClients() throws SQLException {
        clientTableModel = new ClientTableModel(clientDAO.getAllClients());
        tableClients = new JTable(clientTableModel);
    }

    /**
     * Creates the table for orders.
     * 
     * @throws SQLException if a SQL error occurs
     */

    private void createTableOrders() throws SQLException {
        orderTableModel = new OrderTableModel(orderDAO.getAllClients());
        tableOrders = new JTable(orderTableModel);
    }

    /**
     * 
     * Sets up the frame for the order management application.
     * The frame includes a table displaying clients, a form for adding and updating
     * clients,
     * and buttons for adding, deleting, and updating order information.
     */
    private void setupFrame() {
        setTitle("Order Management");
        setSize(1400, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 3));

        JScrollPane clientPane = new JScrollPane(tableClients);
        tablePanel.add(clientPane);

        JScrollPane productPane = new JScrollPane(tableProducts);
        tablePanel.add(productPane);

        JScrollPane orderPane = new JScrollPane(tableOrders);
        tablePanel.add(orderPane);

        panel.add(tablePanel, BorderLayout.CENTER);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BorderLayout());
        emptyPanel.setPreferredSize(new Dimension(400, 600));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        formPanel.add(idLabel);
        formPanel.add(orderId);

        JLabel idClientLabel = new JLabel("ClientId:");
        formPanel.add(idClientLabel);
        formPanel.add(clientId);

        JLabel idProductLabel = new JLabel("ProductId:");
        formPanel.add(idProductLabel);
        formPanel.add(productId);

        JLabel stockLabel = new JLabel("Stock:");
        formPanel.add(stockLabel);
        formPanel.add(quantity);

        emptyPanel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addOrder);
        buttonPanel.add(deleteOrder);
        buttonPanel.add(updateOrder);
        emptyPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(emptyPanel, BorderLayout.EAST);
        getContentPane().add(panel);
        setVisible(true);

        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return Integer.parseInt(orderId.getText());
    }

    
    /** 
     * @return int
     */
    public int getClientId() {
        return Integer.parseInt(clientId.getText());
    }

    
    /** 
     * @return int
     */
    public int getProductId() {
        return Integer.parseInt(productId.getText());
    }

    
    /** 
     * @return int
     */
    public int getQuantity() {
        return Integer.parseInt(quantity.getText());
    }

    
    /** 
     * @param orderListener
     */
    public void addOrder(ActionListener orderListener) {
        addOrder.addActionListener(orderListener);
    }

    
    /** 
     * @param orderListener
     */
    public void deleteOrder(ActionListener orderListener) {
        deleteOrder.addActionListener(orderListener);
    }

    
    /** 
     * @param orderListener
     */
    public void updateOrder(ActionListener orderListener) {
        updateOrder.addActionListener(orderListener);
    }
}
