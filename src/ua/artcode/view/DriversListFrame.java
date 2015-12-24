package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.model.Driver;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sensej on 20.12.15.
 */
public class DriversListFrame extends JFrame {

    private AdminController controller;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton button;

    public DriversListFrame(AdminController menuController) {
        this.controller = menuController;
        setSize(700, 300);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);
    }

    private void init() {

        MyTableModel model = new MyTableModel(controller.getAllDrivers());
        table = new JTable(model);

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        button = new JButton("Back to Menu");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(controller);
                dispose();
            }
        });

        getContentPane().add(scrollPane, new BorderLayout().CENTER);
        getContentPane().add(button, new BorderLayout().SOUTH);

    }

    public class MyTableModel implements TableModel {


        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

        private java.util.List<Driver> drivers;

        public MyTableModel(java.util.List<Driver> drivers) {
            this.drivers = drivers;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public int getColumnCount() {
            return 4;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "DriverID";
                case 1:
                    return "Name";
                case 2:
                    return "Car Numder";
                case 3:
                    return "Status";
            }
            return "";
        }

        public int getRowCount() {
            return drivers.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Driver driver = drivers.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return driver.getIdDriver();
                case 1:
                    return driver.getName();
                case 2:
                    return driver.getCar().getNumb();
                case 3:
                    return driver.isFree() ? "Free" : "On Duty";
            }
            return "";
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }

    }
}