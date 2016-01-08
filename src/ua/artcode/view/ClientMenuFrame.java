package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.controller.ClientController;
import ua.artcode.controller.DriverController;
import ua.artcode.controller.Login;
import ua.artcode.exception.BusyDriverException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dexter on 08.01.16.
 */
public class ClientMenuFrame extends JFrame {

    private ClientController controller;
    private JPanel panel;
    private JButton showTicketsButton, orderTaxiButton;
    private JLabel label;

    public ClientMenuFrame(ClientController menuController) {
        controller = menuController;
        init();
        setTitle("Taxi App");
        setSize(200, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void init() {
        panel = new JPanel();

        //buttons

        showTicketsButton = new JButton("Show tickets");
        orderTaxiButton = new JButton("Order Taxi");

        // listeners

        showTicketsButton.addActionListener((e) -> new TicketsListFrame(controller));

        orderTaxiButton.addActionListener((e) -> new OrderTaxiFrame(controller));

        label = new JLabel("Signed in as client");

        panel.add(label);
        panel.add(showTicketsButton);
        panel.add(orderTaxiButton);
        getContentPane().add(panel);

    }
}
