package ua.artcode.view;


import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TicketsFrame extends JFrame {
    private ITaxiController taxiController;
    private JPanel panel;
    private JLabel tickets;
    private JTable table;

    public static void main(String[] args) {
        TicketsFrame ticketsFrame = new TicketsFrame(null);
    }

    public TicketsFrame(ITaxiController taxiController) throws HeadlessException {
        this.taxiController = taxiController;
        init();
        tickets = new JLabel("Tickets");
        setTitle("Taxi App");
        setSize(600 , 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void init(){
        panel = new JPanel();
        table = new JTable();
        TableColumn columnTo = new TableColumn();
        columnTo.setHeaderValue("To");
        table.addColumn(columnTo);

        TableColumn columnFrom = new TableColumn();
        columnFrom.setHeaderValue("From");
        table.addColumn(columnFrom);

        TableColumn columnName = new TableColumn();
        columnName.setHeaderValue("Name");
        table.addColumn(columnName);


    }
}
