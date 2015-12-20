package ua.artcode.view;


import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private  ITaxiController menuController;
    private JPanel panel;
    private JButton showTicketsButton;
    private JButton showDriversButton;
    private JLabel label;


    public MenuFrame(ITaxiController menuController) {
        this.menuController = menuController;
        init();
        setTitle("Taxi App");
        setSize(200, 100);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
                new TicketsFrame(menuController);
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

        panel.add(showTicketsButton);
        panel.add(showDriversButton);
        getContentPane().add(panel);
    }
}
