package ua.artcode.view;

import org.jdesktop.xswingx.PromptSupport;
import ua.artcode.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private Login login = new Login();

    public LoginFrame() {

        setSize(400, 200);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void init() {


        label = new JLabel("Login Form");

        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

// added prompts ;

        loginField = new JTextField(30);
        PromptSupport.setPrompt("Login", loginField);
        PromptSupport.setForeground(Color.GRAY, loginField);


        passwordField = new JPasswordField(30);

        PromptSupport.setPrompt("Password", passwordField);
        PromptSupport.setForeground(Color.GRAY, passwordField);
        passwordField.setEchoChar('*');


        registerButton = new JButton("Sign Up");
        registerButton.addActionListener((e) -> new RegistrationFrame(login));

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ITaxiController controller = login.login(loginField.getText(), passwordField.getText());
                if (controller == null) {

                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Wrong username/password",
                            "Login error",
                            JOptionPane.ERROR_MESSAGE);

                } else if(controller.getClass() == AdminController.class){
                    new AdminMenuFrame((AdminController)controller);
                    LoginFrame.this.dispose();
                } else if(controller.getClass() == DriverController.class){
                    new DriverMenuFrame((DriverController)controller);
                    LoginFrame.this.dispose();
                } else if(controller.getClass() == ClientController.class){
                    new ClientMenuFrame((ClientController)controller);
                    LoginFrame.this.dispose();
                }
            }
        });
        southButtonsPanel.add(registerButton);
        southButtonsPanel.add(loginButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);
        loginField.requestFocus();
        panel.add(label);
        panel.add(loginField);
        panel.add(passwordField);
        getRootPane().setDefaultButton(loginButton);
        this.add(panel);

    }


}
