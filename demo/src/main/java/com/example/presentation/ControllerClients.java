package com.example.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.example.bussinessLogic.ClientBLL;
import com.example.dataAccess.ClientDAO;
import com.example.dataAccess.OrderDAO;
import com.example.model.Client;

public class ControllerClients {
    private ClientManagement cClientManagement;
    private ClientBLL clientBLL = new ClientBLL();
    OrderDAO orderDAO = new OrderDAO();
    ClientDAO clientDAO = new ClientDAO();

    /**
     * Constructs a new ControllerClients object.
     * 
     * @param clientManagement The ClientManagement object to associate with this
     *                         controller.
     *                         It is used to add action listeners for adding,
     *                         deleting, and updating clients.
     */
    public ControllerClients(ClientManagement clientManagement) {
        cClientManagement = clientManagement;
        clientManagement.addClient(new AddClient());
        clientManagement.deleteClient(new DeleteClient());
        clientManagement.updateClient(new UpdateClient());
    }

    /**
     * ActionListener implementation for adding a client.
     */
    class AddClient implements ActionListener {
        /**
         * Invoked when the add client action is performed.
         * Attempts to insert a new client into the database.
         *
         * @param e The ActionEvent associated with the add client action.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Client client = new Client(cClientManagement.getId(), cClientManagement.getName(),
                        cClientManagement.getAddress(), cClientManagement.getEmail(), cClientManagement.getAge());
                clientBLL.insertClient(client);
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
     * ActionListener implementation for deleting a client.
     */
    class DeleteClient implements ActionListener {
        /**
         * Invoked when the delete client action is performed.
         * Attempts to delete a client from the database.
         *
         * @param e The ActionEvent associated with the delete client action.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                int id = cClientManagement.getId();

                if (id == -1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (clientDAO.findById(id) == null) {
                    JOptionPane.showMessageDialog(null, "ID not good u bozo :v", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                orderDAO.delete(id);
                clientDAO.delete(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid ID format. Please enter a valid integer", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for updating a client.
     */
    class UpdateClient implements ActionListener {
        /**
         * Invoked when the update client action is performed.
         * Attempts to update a client in the database.
         *
         * @param e The ActionEvent associated with the update client action.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                Client client = new Client(cClientManagement.getId(),
                        cClientManagement.getName(),
                        cClientManagement.getAddress(), cClientManagement.getEmail(),
                        cClientManagement.getAge());
                int id = cClientManagement.getId();

                if (id == -1) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (clientDAO.findById(id) == null) {
                    JOptionPane.showMessageDialog(null, "ID not good u bozo :v", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                clientBLL.updateClient(client);
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
