package ua.artcode.view;

import org.jdesktop.xswingx.PromptSupport;
import ua.artcode.controller.Login;

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
        setPrompt("Login", loginField);

        phoneField = new JTextField(30);
        setPrompt("Phone number", phoneField);


        locationField = new JTextField(30);
        setPrompt("Location", locationField);


        passwordField = new JPasswordField(30);
        setPrompt("Password", passwordField);


        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
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

        panel.add(registrationLabel);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(locationField);
        panel.add(phoneField);

        this.add(panel);

    }

    private void setPrompt(String promptText, JTextField field) {
        PromptSupport.setPrompt(promptText, field);
        PromptSupport.setForeground(Color.GRAY, field);
    }

    private void createAccount() {
        if (!checkLoginPasswordFilled()) {

            Login login = new Login();

            boolean client = login.addClient(loginField.getText(), Integer.parseInt(phoneField.getText()),
                    locationField.getText(), passwordField.getText());

            if (client) {
                JOptionPane.showMessageDialog(RegistrationFrame.this,
                        String.format("%s account has been successfully created", loginField.getText()),
                        "Successful registration",
                        JOptionPane.INFORMATION_MESSAGE);
                RegistrationFrame.this.dispose();
            } else {

                JOptionPane.showMessageDialog(RegistrationFrame.this,
                        "Provided login has been already created", "Existing login error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(RegistrationFrame.this,
                    "Login and password fields can't be blank!", "Blank fields error",
                    JOptionPane.OK_OPTION);

        }
    }

    // check that login and password data provided by user while creating an account;//

    public boolean checkLoginPasswordFilled() {

        return (loginField.getText().isEmpty() || (passwordField.getPassword().length == 0));
    }

}
