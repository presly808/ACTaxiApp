package ua.artcode.view;

import org.jdesktop.swingx.prompt.PromptSupport;
import ua.artcode.controller.AdminController;
import ua.artcode.model.Car;
import ua.artcode.model.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddDriverFrame extends JFrame {

    private AdminController adminController;
    private JTextField loginField, carNumberField, carColorField, carModelNumber;
    private JPasswordField passwordField;
    private JButton cancelButton, createAccountButton;
    private JPanel southButtonsPanel;
    private JPanel panel;
    private JLabel registrationLabel;


    public AddDriverFrame(AdminController controller) {

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
        registrationLabel = new JLabel("Add a new Driver form");
        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        loginField = new JTextField(30);
        setField("Login", loginField);

        passwordField = new JPasswordField(30);
        setField("Password", passwordField);

        carModelNumber = new JTextField(30);
        setField("Car model", carModelNumber);

        carNumberField = new JTextField(30);
        setField("Car number", carNumberField);

        carColorField = new JTextField(30);
        setField("Car color", carColorField);


        createAccountButton = new JButton("Add Driver");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addDriver();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddDriverFrame.this.dispose();
            }
        });
        southButtonsPanel.add(createAccountButton);
        southButtonsPanel.add(cancelButton);
        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);

        panel.add(registrationLabel);
        panel.add(loginField);
        panel.add(passwordField);
        panel.add(carModelNumber);
        panel.add(carNumberField);
        panel.add(carColorField);

        this.add(panel);

    }
    private void setField(String prompt, JTextField field) {
        PromptSupport.setPrompt(prompt, field);
        PromptSupport.setForeground(Color.GRAY, field);
    }

    private void addDriver() {
        if (!checkLoginPasswordFilled()) {

            Driver driver = adminController.addDriver(loginField.getText(), new Car(carModelNumber.getText(),
                    carNumberField.getText(), carColorField.getText()), passwordField.getText());

            if (driver != null) {
                JOptionPane.showMessageDialog(AddDriverFrame.this,
                        String.format("Account for %s driver  has been successfully created", loginField.getText()),
                        "Successful registration",
                        JOptionPane.INFORMATION_MESSAGE);
                AddDriverFrame.this.dispose();
            } else {

                JOptionPane.showMessageDialog(AddDriverFrame.this,
                        "Provided login has been already created",
                        "Existing login error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else {

            JOptionPane.showMessageDialog(AddDriverFrame.this,
                    "Login and password fields can't be blank!", "Empty fields error",
                    JOptionPane.OK_OPTION);

        }
    }


    public boolean checkLoginPasswordFilled() {

        return (loginField.getText().isEmpty() || (passwordField.getPassword().length == 0));
    }

}
