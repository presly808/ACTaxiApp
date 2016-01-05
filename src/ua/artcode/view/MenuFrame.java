package ua.artcode.view;


import ua.artcode.controller.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private ITaxiController menuController;
    private JPanel panel;
    private JButton showTicketsButton, showDriversButton, addDriverButton, addClientButton, orderTaxiButton;
    private JLabel whoAmIlabel;
    private JButton addAdminButton;

    public MenuFrame(ITaxiController menuController) {
        this.menuController = menuController;
        init();
        hideButtonForUser();
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

        showTicketsButton = new JButton("Show tickets");
        showTicketsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketsListFrame(menuController);
            }
        });

        showDriversButton = new JButton("Show drivers");
        showDriversButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DriversListFrame((AdminController) menuController);
            }
        });

        addClientButton = new JButton("Add Client");
        addAdminButton = new JButton("Add Admin");
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
                new RegistrationFrame(new Login(((AdminController) menuController).getAppDataContainer()));
            }
        });

        addDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDriverFrame((AdminController) menuController);
            }
        });

        addAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddAdminFrame((AdminController) menuController);
            }
        });

        whoAmIlabel = new JLabel(String.format("Signed in as %s", menuController.getClass() == ClientController.class ?
                "client" : menuController.getClass() == DriverController.class ? "driver" : "admin"));

        panel.add(whoAmIlabel);
        panel.add(showTicketsButton);
        panel.add(showDriversButton);
        panel.add(addClientButton);
        panel.add(addDriverButton);
        panel.add(orderTaxiButton);
        panel.add(addAdminButton);
        getContentPane().add(panel);
    }

    private void hideButtonForUser() {
        if ((menuController.getClass() == ClientController.class)) {

            showDriversButton.setVisible(false);
            addClientButton.setVisible(false);
            addDriverButton.setVisible(false);
            addAdminButton.setVisible(false);

        } else if (((menuController.getClass() == DriverController.class))) {

            showDriversButton.setVisible(false);
            addClientButton.setVisible(false);
            addDriverButton.setVisible(false);
            orderTaxiButton.setVisible(false);
            addAdminButton.setVisible(false);

        } else {

            orderTaxiButton.setVisible(false);
        }
    }
}
