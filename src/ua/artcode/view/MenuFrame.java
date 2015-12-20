package ua.artcode.view;


import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    private  ITaxiController menuController;
    private JPanel panel;
    private JButton showTicketsButton;
    private JButton showDriversButton;
    private JLabel label;
    private JButton addDriverButton;
    private JButton addClientButton;

    public MenuFrame(ITaxiController menuController) {
        this.menuController = menuController;
        init();
        setTitle("Taxi App");
        setSize(200, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();
    }

    private void init() {

        panel = new JPanel();
        label = new JLabel("ADMIN PANEL");
        showTicketsButton = new JButton("Show tickets");
        showTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TicketsFrame();
                MenuFrame.this.dispose();
            }
        });

        showDriversButton = new JButton("Show drivers");
        showDriversButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DriversFrame();
                MenuFrame.this.dispose();
            }
        });

        addClientButton = new JButton("Add Client");
        addDriverButton = new JButton("Add Driver");

        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new AddClientFrame();
            }
        });


        panel.add(showTicketsButton);
        panel.add(showDriversButton);
        panel.add(addClientButton);
        panel.add(addDriverButton);
        getContentPane().add(panel);
    }
}
