package ua.artcode.utils.table_utils;

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
//        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row,
                                                   int col) {
//        JLabel l = new JLabel();
//        l.setHorizontalTextPosition(JLabel.CENTER);
//        l.setHorizontalAlignment(JLabel.CENTER);

        return this;
    }
}