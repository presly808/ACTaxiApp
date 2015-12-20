package ua.artcode.view;


import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.*;

public class TicketsFrame extends JFrame {
    private ITaxiController taxiController;
    private JPanel panel;
    private JLabel label;
    private JTable table;

    public static void main(String[] args) {
        TicketsFrame ticketsFrame = new TicketsFrame(null);
    }

    public TicketsFrame(ITaxiController taxiController) throws HeadlessException {
        this.taxiController = taxiController;
        init();
        setTitle("Taxi App");
        setSize(600 , 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void init(){
        panel = new JPanel();
        table = new JTable();


    }
}
