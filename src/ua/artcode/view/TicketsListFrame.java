package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.model.Ticket;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sensej on 21.12.15.
 */
public class TicketsListFrame extends JFrame {

    private AdminController controller;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton button;

    public TicketsListFrame(AdminController menuController) {
        this.controller = menuController;
        setSize(1000, 300);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);
    }

    private void init() {

        MyTableModel model = new MyTableModel(controller.getTickets());
        table = new JTable(model);

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter
                = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

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

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

        private List<Ticket> tickets;

        public MyTableModel(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        public int getColumnCount() {
            return 10;
        }

        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "TicketID";
                case 1:
                    return "ClientID";
                case 2:
                    return "DriverID";
                case 3:
                    return "From";
                case 4:
                    return "To";
                case 5:
                    return "Price";
                case 6:
                    return "RequestTime";
                case 7:
                    return "ArriveToClient";
                case 8:
                    return "ArriveToPlace";
                case 9:
                    return "Status";
            }
            return "";
        }

        public int getRowCount() {
            return tickets.size();
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Ticket ticket = tickets.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return ticket.getiDTicket();
                case 1:
                    return ticket.getIdClient();
                case 2:
                    return ticket.getIdDriver();
                case 3:
                    return ticket.getFromLocation();
                case 4:
                    return ticket.getToLocation();
                case 5:
                    return ticket.getPrice();
                case 6:
                    return dateFormat.format(ticket.getRequestTime());
                case 7:
                    return dateFormat.format(ticket.getArrivalTaxiTime());
                case 8:
                    return dateFormat.format(ticket.getArrivalDestinationTime());
                case 9:
                    return ticket.getStatus();
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
