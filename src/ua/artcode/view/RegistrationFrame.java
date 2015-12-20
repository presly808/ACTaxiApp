package ua.artcode.view;

import ua.artcode.controller.Login;
import ua.artcode.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sensej on 20.12.15.
 */
public class RegistrationFrame extends JFrame {


    private JTextField loginField, phoneField, locationField;
    private JPasswordField passwordField;
    private JButton cancelButton, createAccountButton;
    private JPanel southButtonsPanel;
    private JPanel panel;
    private JLabel registrationLabel;


    public RegistrationFrame() {


        setSize(400, 300);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);

    }


    private void init() {
        registrationLabel = new JLabel("Registration form");
        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField(30);
        loginField.setText("login");
        loginField.setToolTipText("Login");


        phoneField = new JTextField(30);
        phoneField.setText("Phone number");
        phoneField.setToolTipText("Phone number");

        locationField = new JTextField(30);
        locationField.setText("Your location");
        locationField.setToolTipText("Location");

        passwordField = new JPasswordField(30);
        passwordField.setToolTipText("Password");


        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                Client client = login.addClient(loginField.getText(), Integer.parseInt(phoneField.getText()),
                        locationField.getText(), passwordField.getText());
                if (client != null) {
                    JOptionPane.showMessageDialog(RegistrationFrame.this,
                            String.format("%s account has been succcessfully created", loginField.getText()),
                            "Successful registration",
                            JOptionPane.INFORMATION_MESSAGE);
                    RegistrationFrame.this.dispose();
                }
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationFrame.this.dispose();
            }
        });
        southButtonsPanel.add(createAccountButton);
        southButtonsPanel.add(cancelButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);

        loginField.requestFocus();

        panel.add(registrationLabel);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(locationField);
        panel.add(phoneField);

        this.add(panel);

    }
}
