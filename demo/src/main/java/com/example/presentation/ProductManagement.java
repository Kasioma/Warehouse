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

import com.example.model.ProductTableModel;

import com.example.dataAccess.ProductDAO;

public class ProductManagement extends JFrame {
    private JTable table;
    private ProductTableModel productTableModel;
    private JButton addProduct = new JButton("Add");
    private JButton deleteProduct = new JButton("Delete");
    private JButton updateProduct = new JButton("Update");
    private JTextField productId = new JTextField();
    private JTextField productName = new JTextField();
    private JTextField stock = new JTextField();
    ProductDAO productDAO = new ProductDAO();

    public ProductManagement() throws SQLException {
        createTable();
        setupFrame();
    }

    /**
     * Creates the table for products.
     * 
     * @throws SQLException if a SQL error occurs
     */
    private void createTable() throws SQLException {
        productTableModel = new ProductTableModel(productDAO.getAllClients());
        table = new JTable(productTableModel);
    }

    /**
     * 
     * Sets up the frame for the product management application.
     * The frame includes a table displaying clients, a form for adding and updating
     * clients,
     * and buttons for adding, deleting, and updating product information.
     */
    private void setupFrame() {
        setTitle("Product Management");
        setSize(1000, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new BorderLayout());
        emptyPanel.setPreferredSize(new Dimension(400, 600));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        formPanel.add(idLabel);
        formPanel.add(productId);

        JLabel nameLabel = new JLabel("Name:");
        formPanel.add(nameLabel);
        formPanel.add(productName);

        JLabel stockLabel = new JLabel("Stock:");
        formPanel.add(stockLabel);
        formPanel.add(stock);

        emptyPanel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addProduct);
        buttonPanel.add(deleteProduct);
        buttonPanel.add(updateProduct);
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
        return Integer.parseInt(productId.getText());
    }

    
    /** 
     * @return String
     */
    public String getName() {
        return productName.getText();
    }

    
    /** 
     * @return int
     */
    public int getStock() {
        return Integer.parseInt(stock.getText());
    }

    
    /** 
     * @param clientListener
     */
    public void addProduct(ActionListener clientListener) {
        addProduct.addActionListener(clientListener);
    }

    
    /** 
     * @param clientListener
     */
    public void deleteProduct(ActionListener clientListener) {
        deleteProduct.addActionListener(clientListener);
    }

    
    /** 
     * @param clientListener
     */
    public void updateProduct(ActionListener clientListener) {
        updateProduct.addActionListener(clientListener);
    }
}
