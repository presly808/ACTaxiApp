package ua.artcode.view;


import ua.artcode.controller.*;
import ua.artcode.exception.BusyDriverException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private ITaxiController menuController;
    private DriverController driverController;
    private JPanel panel;
    private JButton showTicketsButton, showDriversButton, addDriverButton, addClientButton, orderTaxiButton;
    private JLabel whoAmIlabel;
    private JButton addAdminButton;
    private JButton changeStatus;

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

        //buttons

        showTicketsButton = new JButton("Show tickets");
        showDriversButton = new JButton("Show drivers");
        addClientButton = new JButton("Add Client");
        addAdminButton = new JButton("Add Admin");
        addDriverButton = new JButton("Add Driver");
        orderTaxiButton = new JButton("Order Taxi");
        changeStatus = new JButton("Change Status");


        // listeners

        changeStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    driverController.changeStatus();
                    if (driverController.isFree() == true) {
                        changeStatus.setText("Start working");
                        changeStatus.setBackground(Color.YELLOW);
                    }else {
                        changeStatus.setText("Take a day off");
                        changeStatus.setBackground(Color.GREEN);
                    }

                } catch (BusyDriverException e1) {
                    JOptionPane.showMessageDialog(MenuFrame.this,
                            e1.getMessage(),
                            "Busy driver error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        showDriversButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DriversListFrame((AdminController) menuController);
            }
        });

        showTicketsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketsListFrame(menuController);
            }
        });

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
        panel.add(changeStatus);
        getContentPane().add(panel);
    }

    private void hideButtonForUser() {
        if ((menuController.getClass() == ClientController.class)) {

            showDriversButton.setVisible(false);
            addClientButton.setVisible(false);
            addDriverButton.setVisible(false);
            addAdminButton.setVisible(false);
            changeStatus.setVisible(false);

        } else if (((menuController.getClass() == DriverController.class))) {
            driverController = (DriverController) menuController;

            showDriversButton.setVisible(false);
            addClientButton.setVisible(false);
            addDriverButton.setVisible(false);
            orderTaxiButton.setVisible(false);
            addAdminButton.setVisible(false);

        } else {

            orderTaxiButton.setVisible(false);
            changeStatus.setVisible(false);
        }
    }
}
