package ua.artcode.view;

import ua.artcode.controller.AdminController;
import ua.artcode.controller.ClientController;
import ua.artcode.controller.DriverController;
import ua.artcode.controller.ITaxiController;
import ua.artcode.exception.HaveNotNewTickets;
import ua.artcode.exception.NoTicketsException;
import ua.artcode.model.Ticket;
import ua.artcode.model.TicketStatus;
import ua.artcode.utils.table_utils.MyCellRenderer;

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
    private JButton rejectTicketButton, acceptTicketButton, menuButton, cancelOrderButton, assignTicket;
    private DriverController driverController;
    private ClientController clientController;
    private AdminController adminController;

    public TicketsListFrame(ITaxiController menuController) {
        this.controller = menuController;
        setSize(1000, 300);
        init();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Taxi App");
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init() {

        setControllerType();

        MyTableModel model = new MyTableModel(controller.getTickets());
        table = new JTable(model);

        table.setDefaultRenderer(TicketStatus.class, new MyCellRenderer());

        scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());

        table.setRowSorter(sorter);
        southButtonPanel = new JPanel(new GridLayout(1, 4));

        menuButton = new JButton("Back to Menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        acceptTicketButton = new JButton("Accept  Ticket");
        acceptTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Ticket acceptedTicket = driverController.takeATicket();
                    table.repaint();

                    JOptionPane.showMessageDialog(TicketsListFrame.this,
                            String.format("The new ticket with %d id has been accepted", acceptedTicket.getiDTicket()),
                            "Information message",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (HaveNotNewTickets haveNotNewTickets) {
                    JOptionPane.showMessageDialog(TicketsListFrame.this,
                            haveNotNewTickets.getMessage(),
                            "No Tickets",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        rejectTicketButton = new JButton("Reject Ticket");
        rejectTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driverController.dropCurrentTicket();
            }
        });


        assignTicket = new JButton("Assign Ticket");


        assignTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if (row >= 0 && adminController.getTickets().get(row).getStatus() == TicketStatus.NEW) {
                    adminController.setTicketId(adminController.getTickets().get(row).getiDTicket());
                    new FreeDriversListFrame(adminController);

                } else {
                    JOptionPane.showMessageDialog(TicketsListFrame.this,
                            "The ticket has been already processed",
                            "Processed ticket",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        cancelOrderButton = new JButton("Cancel order");
        cancelOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clientController.rejectTaxi();
                    repaint();
                } catch (NoTicketsException noTicketsException) {
                    JOptionPane.showMessageDialog(TicketsListFrame.this,
                            noTicketsException.getMessage(),
                            "No Tickets",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        setButtonsVisibility();
        southButtonPanel.add(assignTicket);
        southButtonPanel.add(menuButton);
        southButtonPanel.add(cancelOrderButton);
        southButtonPanel.add(acceptTicketButton);
        southButtonPanel.add(rejectTicketButton);


        getContentPane().add(scrollPane, new BorderLayout().CENTER);
        getContentPane().add(southButtonPanel, new BorderLayout().SOUTH);

    }

    private void setButtonsVisibility() {

        if (controller.getClass() == DriverController.class) {

            driverController = (DriverController) controller;
            cancelOrderButton.setVisible(false);
            assignTicket.setVisible(false);

        } else if (controller.getClass() == ClientController.class) {

            clientController = (ClientController) controller;
            acceptTicketButton.setVisible(false);
            rejectTicketButton.setVisible(false);
            assignTicket.setVisible(false);

        } else {

            adminController = (AdminController) controller;
            acceptTicketButton.setVisible(false);
            rejectTicketButton.setVisible(false);
            cancelOrderButton.setVisible(false);
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

        @Override
        public void addTableModelListener(TableModelListener listener) {
            listeners.add(listener);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (tickets.isEmpty()) {
                return Object.class;
            }
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return tickets.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
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
                    returnedValue = ticket.getStatus() != TicketStatus.REJECTED ?
                            dateFormat.format(ticket.getArrivalDestinationTime()) :"-";
                    break;
                case STATUS:
                    returnedValue = ticket.getStatus();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid column index");
            }
            return returnedValue;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public void removeTableModelListener(TableModelListener listener) {
            listeners.remove(listener);
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {

        }

    }
}
