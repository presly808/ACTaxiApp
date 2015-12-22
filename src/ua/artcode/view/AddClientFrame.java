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
public class AddClientFrame extends JFrame {


    private JTextField loginField, phoneField, locationField;
    private JPasswordField passwordField;
    private JButton cancelButton, createAccountButton;
    private JPanel southButtonsPanel;
    private JPanel panel;
    private JLabel registrationLabel;


    public AddClientFrame() {


        setSize(400, 300);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);

    }


    private void init() {
        registrationLabel = new JLabel("Add a new Client form");
        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField(30);
        PromptSupport.setPrompt("Login", loginField);
        PromptSupport.setForeground(Color.GRAY, loginField);

        phoneField = new JTextField(30);
        PromptSupport.setPrompt("Phone number", phoneField);
        PromptSupport.setForeground(Color.GRAY, phoneField);


        locationField = new JTextField(30);
        PromptSupport.setPrompt("Client's location", locationField);
        PromptSupport.setForeground(Color.GRAY, locationField);


        passwordField = new JPasswordField(30);
        PromptSupport.setPrompt("Password", passwordField);
        PromptSupport.setForeground(Color.GRAY, passwordField);


        createAccountButton = new JButton("Add Client");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkLoginPasswordFilled()) {

                    Login login = new Login();

                    boolean client = login.addClient(loginField.getText(), Integer.parseInt(phoneField.getText()),
                            locationField.getText(), passwordField.getText());

                    if (client) {
                        JOptionPane.showMessageDialog(AddClientFrame.this,
                                String.format("%s account has been successfully created", loginField.getText()),
                                "Successful registration",
                                JOptionPane.INFORMATION_MESSAGE);
                        AddClientFrame.this.dispose();
                    } else {

                        JOptionPane.showMessageDialog(AddClientFrame.this,
                                String.format("Provided login has been already created",
                                        JOptionPane.ERROR_MESSAGE));
                    }

                } else {

                    JOptionPane.showMessageDialog(AddClientFrame.this,
                            String.format("Login and password fields can't be blank!",
                                    JOptionPane.OK_OPTION));

                }
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

    // check that login and password data provided by user while creating an account;//

    public boolean checkLoginPasswordFilled() {

        return (loginField.getText().equals("") || (passwordField.getText().equals(""))) ? false : true;
    }

}
