package ua.artcode.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by sensej on 19.12.15.
 */
public class Login extends JFrame {

    private JTextField loginField, passwordField;
    private JButton loginButton;

    public static void main(String[] args) {
        new Login();
    }

    Login() {

        setSize(400, 400);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setVisible(true);
    }

    private void init() {

        JPanel panel = new JPanel();

        loginField = new JTextField(30);

        passwordField = new JTextField(30);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.add(loginField);
        panel.add(passwordField);
        panel.add(loginButton);
        this.add(panel);

    }


}
