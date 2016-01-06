package ua.artcode.utils.table_utils;

import ua.artcode.model.TicketStatus;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by dexter on 05.01.16.
 */
public class MyCellRenderer extends JLabel implements TableCellRenderer {

    public MyCellRenderer() {
        setHorizontalTextPosition(JLabel.LEFT);
        setHorizontalAlignment(JLabel.LEFT);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int col) {
        if(value.getClass() == TicketStatus.class){

            TicketStatus status = (TicketStatus) value;
            if(status.equals(TicketStatus.NEW)){
                setBackground(Color.GREEN);
                setForeground(Color.BLACK);
                setText(status.toString());
                return this;
            } else if(status.equals(TicketStatus.REJECTED)){
                setBackground(Color.RED);
                setForeground(Color.BLACK);
                setText(status.toString());
                return this;
            }

            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            setText(status.toString());

        }
        return this;
    }
}