package ua.artcode.view;

import org.jdesktop.xswingx.PromptSupport;
import ua.artcode.controller.AdminController;
import ua.artcode.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddClientFrame extends JFrame {

    private AdminController adminController;
    private JTextField loginField, phoneField, locationField;
    private JPasswordField passwordField;
    private JButton cancelButton, createAccountButton;
    private JPanel southButtonsPanel;
    private JPanel panel;
    private JLabel registrationLabel;


    public AddClientFrame(AdminController controller) {

        this.adminController = controller;
        setSize(400, 300);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void init() {
        registrationLabel = new JLabel("Add a new Client form");
        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField(30);
        setField("Login", loginField);

        phoneField = new JTextField(30);
        setField("Phone number", phoneField);

        locationField = new JTextField(30);
        setField("Location", locationField);

        passwordField = new JPasswordField(30);
        setField("Password", passwordField);


        createAccountButton = new JButton("Add Client");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addClient();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClientFrame.this.dispose();
            }
        });
        southButtonsPanel.add(createAccountButton);
        southButtonsPanel.add(cancelButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);

        panel.add(registrationLabel);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(locationField);
        panel.add(phoneField);

        this.add(panel);

    }

    private void setField(String prompt, JTextField field) {
        PromptSupport.setPrompt(prompt, field);
        PromptSupport.setForeground(Color.GRAY, field);
    }

    private void addClient() {
        if (!checkLoginPasswordFilled()) {

            Client client = adminController.addClient(loginField.getText(), Integer.parseInt(phoneField.getText()),
                    locationField.getText(), passwordField.getText());

            if (client != null) {
                JOptionPane.showMessageDialog(AddClientFrame.this,
                        String.format("%s account has been successfully created", loginField.getText()),
                        "Successful registration",
                        JOptionPane.INFORMATION_MESSAGE);
                AddClientFrame.this.dispose();
            } else {

                JOptionPane.showMessageDialog(AddClientFrame.this,
                        "Provided login has been already created",
                        "Existing login error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(AddClientFrame.this,
                    "Login and password fields can't be blank!", "Empty fields error",
                    JOptionPane.OK_OPTION);

        }
    }

    // check that login and password data provided by user while creating an account;//

    public boolean checkLoginPasswordFilled() {

        return (loginField.getText().isEmpty() || (passwordField.getPassword().length == 0));
    }

}
