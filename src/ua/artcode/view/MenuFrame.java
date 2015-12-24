package ua.artcode.view;


import ua.artcode.controller.AdminController;
import ua.artcode.controller.ClientController;
import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private ITaxiController menuController;
    private JPanel panel;
    private JButton showTicketsButton;
    private JButton showDriversButton;
    private JLabel label;
    private JButton addDriverButton;
    private JButton addClientButton;
    private JLabel whoAmIlabel;
    private JButton orderTaxiButton;

    public MenuFrame(ITaxiController menuController) {
        this.menuController = menuController;
        init();
        hideButtonForUser();
        setTitle("Taxi App");
        setSize(200, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();
    }

    private void init() {

        panel = new JPanel();
        label = new JLabel("ADMIN PANEL");


        showTicketsButton = new JButton("Show tickets");
        showTicketsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketsListFrame((AdminController) menuController);
                MenuFrame.this.dispose();
            }
        });

        showDriversButton = new JButton("Show drivers");
        showDriversButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DriversListFrame((AdminController) menuController);
                MenuFrame.this.dispose();
            }
        });

        addClientButton = new JButton("Add Client");
        addDriverButton = new JButton("Add Driver");
        orderTaxiButton = new JButton("Order Taxi");

        orderTaxiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderTaxiFrame((ClientController) menuController);
            }
        });

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClientFrame((AdminController) menuController);
            }
        });

        whoAmIlabel = new JLabel(String.format("Signed up as %s", menuController.getClass() == ClientController.class ? "client"
                : "admin"));

        panel.add(whoAmIlabel);
        panel.add(showTicketsButton);
        panel.add(showDriversButton);
        panel.add(addClientButton);
        panel.add(addDriverButton);
        panel.add(orderTaxiButton);
        getContentPane().add(panel);
    }

    private void hideButtonForUser() {
        if ((menuController.getClass() == ClientController.class)) {
            showDriversButton.setVisible(false);
            showTicketsButton.setVisible(false);
            addClientButton.setVisible(false);
            addDriverButton.setVisible(false);
        } else {
            orderTaxiButton.setEnabled(false);
        }
    }
}
