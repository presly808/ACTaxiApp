package ua.artcode.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    public MenuFrame(JButton buttonShowTikets) throws HeadlessException {
        setTitle("Choosing some acction");
        setSize(400 , 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        addButtons();

    }

    private void addButtons(){
        JButton buttonShowTikets = new JButton("Show all Tikets");
        AcctionListenerForButtonShowTikets acctionListenerForButtonShowTikets = new AcctionListenerForButtonShowTikets();
        buttonShowTikets.addActionListener(acctionListenerForButtonShowTikets);
        setLayout(new GridLayout(2 , 1 ));
        getContentPane().add(buttonShowTikets);

    }




    public class AcctionListenerForButtonShowTikets implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //method who show next window with tikets
        }
    }
}
