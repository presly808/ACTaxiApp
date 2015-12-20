package ua.artcode.view;

import ua.artcode.controller.Login;
import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by sensej on 19.12.15.
 */
public class LoginFrame extends JFrame {

    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;
    private JLabel label;
    private JPanel southButtonsPanel;
    private JPanel panel;

    public LoginFrame() {


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
        label = new JLabel("Login Form");

        passwordField = new JPasswordField(30);
        passwordField.setToolTipText("Password");
        passwordField.setEchoChar('*');


        registerButton = new JButton("Sign Up");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationFrame();
            }
        });

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ITaxiController controller = Login.login(loginField.getText(), Arrays.toString(passwordField.getPassword()));
                if (controller != null) {
                    new MenuFrame(controller);
                    LoginFrame.this.dispose();
                } else
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Wrong username/password",
                            "Login error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });
        southButtonsPanel.add(registerButton);
        southButtonsPanel.add(loginButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);
        loginField.requestFocus();
        panel.add(label);
        panel.add(loginField);
        panel.add(passwordField);
        this.add(panel);

    }


}
