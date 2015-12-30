package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.exception.BusyDriverExeption;
import ua.artcode.exception.NotFindInDataBaseException;
import ua.artcode.model.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;


class FreeDriversListFrame extends JFrame  {

    private AdminController adminController;
    private JList listbox;
    private JButton cancelButton, assignDriverButton;
    private JPanel southButtonsPanel;
    private Vector<Driver> data;


    public FreeDriversListFrame(AdminController controler) {

        adminController = controler;
        setSize(400, 200);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);
    }


    private void init() {

        data = adminController.getFreeDrivers();
        listbox = new JList(data);
        listbox.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scrollPane = new JScrollPane(listbox);
        scrollPane.setPreferredSize(new Dimension(100, 100));
        southButtonsPanel = new JPanel(new GridLayout(1, 2));

        listbox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listbox.locationToIndex(e.getPoint());
                    try {
                        adminController.setDriverToTicket(adminController.getTicketId(),data.get(index).getId());
                    } catch (NotFindInDataBaseException e1) {
                        e1.printStackTrace();
                    } catch (BusyDriverExeption busyDriverExeption) {
                        busyDriverExeption.printStackTrace();
                    }
                    dispose();
                }
            }
        });



//        cancelButton = new JButton("Cancel");
//        cancelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//
//        southButtonsPanel.add(cancelButton);
//        getContentPane().add(southButtonsPanel, BorderLayout.SOUTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

}
