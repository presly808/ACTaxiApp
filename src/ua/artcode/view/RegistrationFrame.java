package ua.artcode.view;

import ua.artcode.controller.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sensej on 20.12.15.
 */
public class RegistrationFrame extends JFrame {


    //Client addClient(String name, int phone, String location, String pass, long cash);
    private JTextField loginField, phoneField, locationField;
    private JPasswordField passwordField;
    private JButton cancelButton, createAccountButton;
    private JPanel southButtonsPanel;
    private JPanel panel;


    public RegistrationFrame() {


        setSize(400, 200);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setInFrameCenter();
        setVisible(true);

    }

    private void setInFrameCenter() {

        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);

        setLocation(xPos, yPos);

    }

    private void init() {

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
                Login.addClient(loginField.getText(), Integer.parseInt(phoneField.getText()),
                        locationField.getText(), passwordField.getText());

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
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(locationField);
        panel.add(phoneField);

        this.add(panel);

    }


}
