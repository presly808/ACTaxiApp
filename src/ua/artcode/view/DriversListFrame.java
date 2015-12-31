package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.exception.NotFindInDataBaseException;
import ua.artcode.model.Driver;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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
    private JButton backToMenubutton, deleteDriverButton;

    public DriversListFrame(AdminController menuController) {
        this.controller = menuController;
        setSize(700, 300);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {

        MyTableModel model = new MyTableModel(controller.getAllDrivers());
        table = new JTable(model);

        TableRowSorter<TableModel> sorter
                = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        deleteDriverButton = new JButton("Fire driver");
        deleteDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();
                if (row >= 0) {
                    table.removeRowSelectionInterval(row,row); // delete from table
                    model.deleteFromModel(row);
                }

                repaint();
            }
        });


        backToMenubutton = new JButton("Back to Menu");
        backToMenubutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(controller);
                dispose();
            }
        });
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(backToMenubutton);
        panel.add(deleteDriverButton);
        getContentPane().add(scrollPane, new BorderLayout().CENTER);
        getContentPane().add(panel, new BorderLayout().SOUTH);


    }

    public class MyTableModel extends AbstractTableModel {

        private static final int DRIVER_ID = 0;
        private static final int NAME = 1;
        private static final int CAR_NUMBER = 2;
        private static final int STATUS = 3;

        protected String[] columnNames = {"DriverID", "Name", "Car Number", "isFree"};
        protected Class[] types = new Class[]{Long.class, String.class, Integer.class, Boolean.class};


        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

        private java.util.List<Driver> drivers;

        public MyTableModel(java.util.List<Driver> drivers) {
            this.drivers = drivers;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return types[columnIndex];
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        public int getRowCount() {
            return drivers.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Driver driver = drivers.get(rowIndex);
            Object returnedValue = null;

            switch (columnIndex) {
                case DRIVER_ID:
                    returnedValue = driver.getId();
                    break;
                case NAME:
                    returnedValue = driver.getLogin();
                    break;
                case CAR_NUMBER:
                    returnedValue = driver.getCar().getNumb();
                    break;
                case STATUS:
                    returnedValue = driver.isFree();//? "Free" : "On Duty";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column index");
            }
            return returnedValue;
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }

        public void deleteFromModel(int row) {

            try {
                controller.deleteDriver(drivers.get(row).getId());
            } catch (NotFindInDataBaseException e) {
                JOptionPane.showMessageDialog(DriversListFrame.this,
                        e.getMessage(),
                        "This driver has been deleted",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

    }
}
