package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.exception.BusyDriverException;
import ua.artcode.exception.NotFindInDataBaseException;
import ua.artcode.model.Driver;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;


class FreeDriversListFrame extends JFrame {

    private AdminController adminController;
    private JList listBox;
    private Vector<Driver> data;
    private JLabel label;
    private JScrollPane scrollPane;


    public FreeDriversListFrame(AdminController controller) {

        adminController = controller;
        setSize(400, 400);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }


    private void init() {


        try {
            data = adminController.getFreeDrivers();
        } catch (NotFindInDataBaseException e) {
            JOptionPane.showMessageDialog(FreeDriversListFrame.this,
                    "All drivers are busy at the moment",
                    "No free drivers error",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
        listBox = new JList(data);
        listBox.setLayoutOrientation(JList.VERTICAL);
        listBox.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(listBox);
        scrollPane.setPreferredSize(new Dimension(200, 200));

        label = new JLabel("List of free drivers");
        label.setLabelFor(scrollPane);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setHorizontalAlignment(JLabel.CENTER);

        listBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listBox.locationToIndex(e.getPoint());
                    try {
                        adminController.setDriverToTicket(adminController.getTicketId(), data.get(index).getId());
                    } catch (NotFindInDataBaseException e1) {
                        e1.printStackTrace();
                    } catch (BusyDriverException busyDriverException) {
                        busyDriverException.printStackTrace();
                    }
                    dispose();
                }
            }
        });
        getContentPane().add(label, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

}
