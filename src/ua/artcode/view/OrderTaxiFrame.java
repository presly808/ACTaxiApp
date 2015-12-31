package ua.artcode.view;

import org.jdesktop.xswingx.PromptSupport;
import ua.artcode.controller.ClientController;
import ua.artcode.exception.ClientHaveAlreadyHadATicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sensej on 23.12.15.
 */
public class OrderTaxiFrame extends JFrame {

    private ClientController controller;
    private JTextField fromLocation, toLocation;
    private JButton cancelButton, orderButton;
    private JPanel southButtonsPanel;
    private JPanel panel;
    private JLabel orderTaxiLabel;


    public OrderTaxiFrame(ClientController controller) {

        this.controller = controller;
        setSize(400, 300);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void init() {

        orderTaxiLabel = new JLabel("Order Taxi Form");
        panel = new JPanel();
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        fromLocation = new JTextField(30);
        PromptSupport.setPrompt("From Location", fromLocation);
        PromptSupport.setForeground(Color.GRAY, fromLocation);

        toLocation = new JTextField(30);
        PromptSupport.setPrompt("Destination", toLocation);
        PromptSupport.setForeground(Color.GRAY, toLocation);

        orderButton = new JButton("Order Taxi");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (checkFieldsFilled()) {

                    long ticketId = 0;

                    try {
                        ticketId = controller.callTaxi(fromLocation.getText(), toLocation.getText());
                        JOptionPane.showMessageDialog(OrderTaxiFrame.this,
                                String.format("Taxi has been ordered, your ticket number is %d", ticketId),
                                "Successful order",
                                JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                    } catch (ClientHaveAlreadyHadATicket clientHaveAlreadyHadATicket) {
                        JOptionPane.showMessageDialog(OrderTaxiFrame.this,
                                clientHaveAlreadyHadATicket.getMessage(),
                                "Multiple tickets error",
                                JOptionPane.WARNING_MESSAGE);
                        dispose();

                    }


                } else {

                    JOptionPane.showMessageDialog(OrderTaxiFrame.this,
                            String.format("Fields for start and destination locations must be defined!",
                                    JOptionPane.OK_OPTION));

                }
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderTaxiFrame.this.dispose();
            }
        });

        southButtonsPanel.add(orderButton);
        southButtonsPanel.add(cancelButton);

        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);

        panel.add(orderTaxiLabel);
        panel.add(fromLocation);
        panel.add(toLocation);

        this.add(panel);

    }


    public boolean checkFieldsFilled() {

        return !(fromLocation.getText().equals("") || toLocation.getText().equals(""));
    }

}


