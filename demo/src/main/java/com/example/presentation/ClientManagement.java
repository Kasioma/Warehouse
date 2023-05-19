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

import com.example.dataAccess.ClientDAO;

public class ClientManagement extends JFrame {
    private JTable table;
    private ClientTableModel clientTableModel;
    private JButton addClient = new JButton("Add");
    private JButton deleteClient = new JButton("Delete");
    private JButton updateClient = new JButton("Update");
    private JTextField clientId = new JTextField();
    private JTextField clientName = new JTextField();
    private JTextField clientAddress = new JTextField();
    private JTextField clientEmail = new JTextField();
    private JTextField clientAge = new JTextField();
    ClientDAO clientDAO = new ClientDAO();

    public ClientManagement() throws SQLException {
        createTable();
        setupFrame();
    }

    /**
     * Creates a table for displaying clients.
     * 
     * @throws SQLException if an error occurs while retrieving client data from the
     *                      database.
     */
    private void createTable() throws SQLException {
        clientTableModel = new ClientTableModel(clientDAO.getAllClients());
        table = new JTable(clientTableModel);
    }

    /**
     * 
     * Sets up the frame for the client management application.
     * The frame includes a table displaying clients, a form for adding and updating
     * clients,
     * and buttons for adding, deleting, and updating client information.
     */
    private void setupFrame() {
        setTitle("Client Management");
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
        formPanel.add(clientId);

        JLabel nameLabel = new JLabel("Name:");
        formPanel.add(nameLabel);
        formPanel.add(clientName);

        JLabel addressLabel = new JLabel("Address:");
        formPanel.add(addressLabel);
        formPanel.add(clientAddress);

        JLabel emailLabel = new JLabel("Email:");
        formPanel.add(emailLabel);
        formPanel.add(clientEmail);

        JLabel ageLabel = new JLabel("Age:");
        formPanel.add(ageLabel);
        formPanel.add(clientAge);

        emptyPanel.add(formPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addClient);
        buttonPanel.add(deleteClient);
        buttonPanel.add(updateClient);
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
        return Integer.parseInt(clientId.getText());
    }

    /**
     * @return String
     */
    public String getName() {
        return clientName.getText();
    }

    /**
     * @return String
     */
    public String getAddress() {
        return clientAddress.getText();
    }

    /**
     * @return String
     */
    public String getEmail() {
        return clientEmail.getText();
    }

    /**
     * @return int
     */
    public int getAge() {
        return Integer.parseInt(clientAge.getText());
    }

    /**
     * @param clientListener
     */
    public void addClient(ActionListener clientListener) {
        addClient.addActionListener(clientListener);
    }

    /**
     * @param clientListener
     */
    public void deleteClient(ActionListener clientListener) {
        deleteClient.addActionListener(clientListener);
    }

    /**
     * @param clientListener
     */
    public void updateClient(ActionListener clientListener) {
        updateClient.addActionListener(clientListener);
    }
}
