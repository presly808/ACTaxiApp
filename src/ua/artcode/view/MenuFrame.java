package ua.artcode.view;


import ua.artcode.controller.ITaxiController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuFrame extends JFrame {
    public static void main(String[] args) {
        new MenuFrame(null);
    }

    private ITaxiController iTaxiController;

    public MenuFrame(ITaxiController menuController) throws HeadlessException {
        this.iTaxiController = menuController;
        setTitle("Choosing some acction");
        setSize(400 , 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        JPanel menuPanel = new JPanel();
        addButtons(menuPanel);

    }

    private void addButtons(JPanel menuPanel){

        JButton buttonShowTikets = new JButton("Show all Tikets");
        AcctionListenerForButtonShowTikets acctionListenerForButtonShowTikets = new AcctionListenerForButtonShowTikets();
        buttonShowTikets.addActionListener(acctionListenerForButtonShowTikets);
        menuPanel.add(buttonShowTikets);


        JButton buttonShowDrivers = new JButton("Show all Drivers");
        //TODO delete unnecessary variable
        AcctionListenerForButtonShowDrivers acctionListenerForButtonShowDrivers = new AcctionListenerForButtonShowDrivers();
        buttonShowDrivers.addActionListener(acctionListenerForButtonShowDrivers);
        menuPanel.add(buttonShowDrivers);
    }




    public class AcctionListenerForButtonShowTikets implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //method who show next window with tikets
        }
    }


    public class AcctionListenerForButtonShowDrivers implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //method who show next window with drivers
        }
    }
}
