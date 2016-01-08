package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.controller.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dexter on 08.01.16.
 */
public class AdminMenuFrame extends JFrame {

    private AdminController controller;
    private JPanel panel;
    private JButton showTicketsButton, showDriversButton, addDriverButton, addClientButton, addAdminButton;
    private JLabel label;

    public AdminMenuFrame(AdminController menuController) {
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
        showDriversButton = new JButton("Show drivers");
        addClientButton = new JButton("Add Client");
        addAdminButton = new JButton("Add Admin");
        addDriverButton = new JButton("Add Driver");

        // listeners

        showDriversButton.addActionListener((e) ->  new DriversListFrame(controller));

        showTicketsButton.addActionListener((e) -> new TicketsListFrame(controller));

        addClientButton.addActionListener((e) -> new RegistrationFrame(
                new Login(controller.getAppDataContainer())
                ));

        addDriverButton.addActionListener((e) -> new AddDriverFrame(controller));

        addAdminButton.addActionListener((e) -> new AddAdminFrame(controller));

        label = new JLabel("Signed in as admin");

        panel.add(label);
        panel.add(showTicketsButton);
        panel.add(showDriversButton);
        panel.add(addClientButton);
        panel.add(addDriverButton);
        panel.add(addAdminButton);
        getContentPane().add(panel);
    }
}
