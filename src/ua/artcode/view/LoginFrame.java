package ua.artcode.view;

import org.jdesktop.xswingx.PromptSupport;
import ua.artcode.controller.ITaxiController;
import ua.artcode.controller.Login;

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


        label = new JLabel("Login Form");

        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField(30);
        PromptSupport.setPrompt("Login", loginField);
        PromptSupport.setForeground(Color.GRAY, loginField);


        passwordField = new JPasswordField(30);

        PromptSupport.setPrompt("Password", passwordField);
        PromptSupport.setForeground(Color.GRAY, passwordField);
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
                Login login = new Login();

                ITaxiController controller = login.login(loginField.getText(), passwordField.getText());
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
