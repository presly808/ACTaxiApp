package ua.artcode.view;

import org.jdesktop.xswingx.PromptSupport;
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
    private JPanel southPanel;
    private JButton showTicketsButton, changeStatusButton, doneWithTicketButton, setLocationButton;
    private JLabel label;
    private JPanel northPanel;

    public DriverMenuFrame(DriverController menuController) {
        controller = menuController;
        init();
        setTitle("Taxi App");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();
    }

    private void init() {

        southPanel = new JPanel(new GridLayout(1, 4));
        southPanel.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));

        northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));

        JTextField locationField = new JTextField();
        PromptSupport.setPrompt("Your location", locationField);



        //buttons

        showTicketsButton = new JButton("Show tickets");
        doneWithTicketButton = new JButton("Done with order");
        changeStatusButton = new JButton(controller.isFree() ? "Start working" : "Take a day off");
        repaintChangeStatusButton();
        setLocationButton = new JButton("Set location");


        // listeners

        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    controller.changeStatus();
                    repaintChangeStatusButton();

                } catch (BusyDriverException e1) {
                    JOptionPane.showMessageDialog(DriverMenuFrame.this,
                            e1.getMessage(),
                            "Busy driver error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        showTicketsButton.addActionListener(e -> new TicketsListFrame(controller));
        doneWithTicketButton.addActionListener(e -> controller.finishTrip());
        setLocationButton.addActionListener(e -> controller.setLocation(locationField.getText()));

        label = new JLabel("Signed in as driver");
        northPanel.add(locationField);
        northPanel.add(setLocationButton);
        southPanel.add(label);
        southPanel.add(showTicketsButton);
        southPanel.add(changeStatusButton);
        southPanel.add(doneWithTicketButton);
        getContentPane().add(southPanel);
        getContentPane().add(northPanel, new BorderLayout().NORTH);
    }

    private void repaintChangeStatusButton() {
        if (controller.isFree()) {
            changeStatusButton.setText("Start working");
            changeStatusButton.setBackground(Color.YELLOW);
        } else {
            changeStatusButton.setText("Take a day off");
            changeStatusButton.setBackground(Color.GREEN);
        }
    }

}
