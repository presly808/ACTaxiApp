package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.exception.BusyDriverExeption;
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
    private JList listbox;
    private Vector<Driver> data;
    private JLabel label;
    private JScrollPane scrollPane;


    public FreeDriversListFrame(AdminController controler) {

        adminController = controler;
        setSize(400, 400);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);
    }


    private void init() {


        data = adminController.getFreeDrivers();
        listbox = new JList(data);
        listbox.setLayoutOrientation(JList.VERTICAL);
        listbox.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(listbox);
        scrollPane.setPreferredSize(new Dimension(200, 200));

        label = new JLabel("List of free drivers");
        label.setLabelFor(scrollPane);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        label.setBorder(border);
        label.setHorizontalAlignment(JLabel.CENTER);

        listbox.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = listbox.locationToIndex(e.getPoint());
                    try {
                        adminController.setDriverToTicket(adminController.getTicketId(), data.get(index).getId());
                    } catch (NotFindInDataBaseException e1) {
                        e1.printStackTrace();
                    } catch (BusyDriverExeption busyDriverExeption) {
                        busyDriverExeption.printStackTrace();
                    }
                    dispose();
                }
            }
        });
        getContentPane().add(label, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

}
