package com.example.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Controller {

    private View vview;

    /**
     * 
     * Constructs a Controller object with the specified View.
     * 
     * @param view The View object to associate with the Controller.
     */
    public Controller(View view) {
        vview = view;
        view.addClientListener(new ViewClientListener());
        view.addProductListener(new ViewProductListener());
        view.addOrderListener(new ViewOrderListener());
    }

    /**
     * ActionListener implementation for handling client-related actions in the
     * View.
     */
    class ViewClientListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ClientManagement cl = new ClientManagement();
                new ControllerClients(cl);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for handling client-related actions in the
     * View.
     */
    class ViewProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                ProductManagement pl = new ProductManagement();
                new ControllerProducts(pl);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * ActionListener implementation for handling client-related actions in the
     * View.
     */
    class ViewOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                OrderManagement ol = new OrderManagement();
                new ControllerOrders(ol);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

}
