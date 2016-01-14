package ua.artcode.view;

import org.jdesktop.swingx.prompt.PromptSupport;
import ua.artcode.controller.AdminController;
import ua.artcode.exception.LoginHasAlreadyUsed;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddAdminFrame extends JFrame {

    private AdminController adminController;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton cancelButton, createAccountButton;
    private JPanel southButtonsPanel;
    private JPanel panel;
    private JLabel registrationLabel;


    public AddAdminFrame(AdminController controller) {

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
        registrationLabel = new JLabel("Add a new Admin form");
        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField(30);
        setField("Login", loginField);

        passwordField = new JPasswordField(30);
        setField("Password", passwordField);


        createAccountButton = new JButton("Add Admin");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addAdmin();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAdminFrame.this.dispose();
            }
        });
        southButtonsPanel.add(createAccountButton);
        southButtonsPanel.add(cancelButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);

        panel.add(registrationLabel);
        panel.add(loginField);
        panel.add(passwordField);

        this.add(panel);

    }

    private void setField(String prompt, JTextField field) {
        PromptSupport.setPrompt(prompt, field);
        PromptSupport.setForeground(Color.GRAY, field);
    }

    private void addAdmin() {
        if (!checkLoginPasswordFilled()) {

            try {

                adminController.addAdmin(loginField.getText(), passwordField.getText());

                JOptionPane.showMessageDialog(AddAdminFrame.this,
                        String.format("%s admin account has been successfully created", loginField.getText()),
                        "Admin registration",
                        JOptionPane.INFORMATION_MESSAGE);

                AddAdminFrame.this.dispose();

            } catch (LoginHasAlreadyUsed loginHasAlreadyUsed) {

                JOptionPane.showMessageDialog(AddAdminFrame.this,
                        "Provided login has been already created",
                        "Existing login error",
                        JOptionPane.ERROR_MESSAGE);

            }

        } else {

            JOptionPane.showMessageDialog(AddAdminFrame.this,
                    "Login and password fields can't be blank!", "Empty fields error",
                    JOptionPane.OK_OPTION);

        }
    }

    public boolean checkLoginPasswordFilled() {

        return (loginField.getText().isEmpty() || (passwordField.getPassword().length == 0));
    }

}
