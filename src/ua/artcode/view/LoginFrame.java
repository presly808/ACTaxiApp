package ua.artcode.view;

import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sensej on 19.12.15.
 */
public class LoginFrame extends JFrame {

    private ITaxiController controller;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel label;


    public LoginFrame(ITaxiController taxiController) {

        controller = taxiController;

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

        JPanel panel = new JPanel();

        loginField = new JTextField(30);
        loginField.setText("login");
        loginField.setToolTipText("Login");
        label = new JLabel("Login Form");

        passwordField = new JPasswordField(30);
        passwordField.setToolTipText("Password");
        passwordField.setEchoChar('*');


        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.login(loginField.getText(), passwordField.getText()) != null) {
                    new MenuFrame(controller);
                    LoginFrame.this.dispose();
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
