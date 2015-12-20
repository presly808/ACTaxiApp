package ua.artcode.view;

import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sensej on 19.12.15.
 */
public class LoginFrame extends JFrame {

    private ITaxiController controller;
    private JTextField loginField, passwordField;
    private JButton loginButton;
    private JLabel label;


    public LoginFrame(ITaxiController taxiController) {

        controller = taxiController;

        setSize(400, 300);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi App");
        setVisible(true);
    }

    private void init() {

        JPanel panel = new JPanel();

        loginField = new JTextField(30);
        loginField.setText("login");
        loginField.setToolTipText("Login");
        label = new JLabel("Login Form");

        passwordField = new JTextField(30);
        passwordField.setText("password");
        passwordField.setToolTipText("Password");

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.login(loginField.getText(), passwordField.getText()) != null) {
                    new MenuFrame(controller);
                } else
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Wrong username/password",
                            "Login error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        loginField.requestFocus();
        panel.add(label);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(loginButton);
        this.add(panel);

    }


}
