package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.controller.ClientController;
import ua.artcode.controller.DriverController;
import ua.artcode.controller.ITaxiController;
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

    private ITaxiController controller;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel southButtonPanel;
    private JButton rejectTicketButton, acceptTicketButton, menuButton;
    private DriverController driverController;

    public TicketsListFrame(ITaxiController menuController) {
        this.controller = menuController;
        setSize(1000, 300);
        init();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setVisible(true);
    }

    private void init() {

        setControllerType();

        MyTableModel model = new MyTableModel(controller.getTickets());
        table = new JTable(model);

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(sorter);
        southButtonPanel = new JPanel(new GridLayout(1, 3));

        menuButton = new JButton("Back to Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame(controller);
                dispose();
            }
        });


        acceptTicketButton = new JButton("Accept  Ticket");
        acceptTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driverController.takeATicket();
            }
        });

        rejectTicketButton = new JButton("Reject Ticket");
        rejectTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driverController.dropCurrentTicket();
            }
        });

        provideDriverSetting();

        southButtonPanel.add(menuButton);
        southButtonPanel.add(acceptTicketButton);
        southButtonPanel.add(rejectTicketButton);

        getContentPane().add(scrollPane, new BorderLayout().CENTER);
        getContentPane().add(southButtonPanel, new BorderLayout().SOUTH);

    }

    private void provideDriverSetting() {

        if (controller.getClass() == DriverController.class) {

            driverController = (DriverController) controller;
        } else {

            acceptTicketButton.setVisible(false);
            rejectTicketButton.setVisible(false);
        }
    }

    private void setControllerType() {

        controller = controller.getClass() == ClientController.class ?
                (ClientController) controller : (controller.getClass() == DriverController.class) ? (DriverController) controller
                : (AdminController) controller;
    }

    public class MyTableModel implements TableModel {

        private static final int TICKET_ID = 0;
        private static final int CLIENT_ID = 1;
        private static final int DRiVER_ID = 2;
        private static final int FROM = 3;
        private static final int TO = 4;
        private static final int PRICE = 5;
        private static final int REQUEST_TIME = 6;
        private static final int ARRIVE_TO_CLIENT = 7;
        private static final int ARRIVE_TO_PLACE = 8;
        private static final int STATUS = 9;

        private String[] columnNames = {"TicketID", "ClientID", "DriverID", "From", "To", "Price",
                "RequestTime", "ArriveToClient", "ArriveToPlace", "Status"};
        private List<Ticket> tickets;
        private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");


        private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

        public MyTableModel(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        public Class<?> getColumnClass(int columnIndex) {
            if (tickets.isEmpty()) {
                return Object.class;
            }
            return getValueAt(0, columnIndex).getClass();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return tickets.size();
        }

        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Ticket ticket = tickets.get(rowIndex);
            Object returnedValue = null;

            switch (columnIndex) {
                case TICKET_ID:
                    returnedValue = ticket.getiDTicket();
                    break;
                case CLIENT_ID:
                    returnedValue = ticket.getIdClient();
                    break;
                case DRiVER_ID:
                    returnedValue = ticket.getIdDriver();
                    break;
                case FROM:
                    returnedValue = ticket.getFromLocation();
                    break;
                case TO:
                    returnedValue = ticket.getToLocation();
                    break;
                case PRICE:
                    returnedValue = ticket.getPrice();
                    break;
                case REQUEST_TIME:
                    returnedValue = dateFormat.format(ticket.getRequestTime());
                    break;
                case ARRIVE_TO_CLIENT:
                    returnedValue = dateFormat.format(ticket.getArrivalTaxiTime());
                    break;
                case ARRIVE_TO_PLACE:
                    returnedValue = dateFormat.format(ticket.getArrivalDestinationTime());
                    break;
                case STATUS:
                    returnedValue = ticket.getStatus();
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

    }
}
