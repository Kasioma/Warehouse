package com.example.presentation;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class View extends JFrame {
    private JButton clients = new JButton("Client Management");
    private JButton products = new JButton("Products Management");
    private JButton orders = new JButton("Orders Management");

    public View() {
        clients.setBounds(10, 110, 290, 70);
        products.setBounds(10, 190, 290, 70);
        orders.setBounds(10, 270, 290, 70);

        JFrame frame = new JFrame();
        frame.add(clients);
        frame.add(products);
        frame.add(orders);
        frame.setSize(320, 420);
        frame.setTitle("Pt project");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    
    /** 
     * @param clientListener
     */
    public void addClientListener(ActionListener clientListener) {
        clients.addActionListener(clientListener);
    }

    
    /** 
     * @param productListener
     */
    public void addProductListener(ActionListener productListener) {
        products.addActionListener(productListener);
    }

    
    /** 
     * @param orderListener
     */
    public void addOrderListener(ActionListener orderListener) {
        orders.addActionListener(orderListener);
    }
}
