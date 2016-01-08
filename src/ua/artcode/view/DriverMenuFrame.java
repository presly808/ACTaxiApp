package ua.artcode.view;

import ua.artcode.controller.*;
import ua.artcode.exception.BusyDriverException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dexter on 08.01.16.
 */
public class DriverMenuFrame extends JFrame {

    private DriverController controller;
    private JPanel panel;
    private JButton showTicketsButton, changeStatus;
    private JLabel label;

    public DriverMenuFrame(DriverController menuController) {
        controller = menuController;
        init();
        setTitle("Taxi App");
        setSize(200, 100);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void init() {

        panel = new JPanel();

        //buttons

        showTicketsButton = new JButton("Show tickets");
        changeStatus = new JButton("Change Status");

        // listeners

        changeStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    controller.changeStatus();
                    if (controller.isFree() == true) {
                        changeStatus.setText("Start working");
                        changeStatus.setBackground(Color.YELLOW);
                    }else {
                        changeStatus.setText("Take a day off");
                        changeStatus.setBackground(Color.GREEN);
                    }

                } catch (BusyDriverException e1) {
                    JOptionPane.showMessageDialog(DriverMenuFrame.this,
                            e1.getMessage(),
                            "Busy driver error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        showTicketsButton.addActionListener((e) -> new TicketsListFrame(controller));

        label = new JLabel("Signed in as driver");

        panel.add(label);
        panel.add(showTicketsButton);
        panel.add(changeStatus);
        getContentPane().add(panel);
    }

}
