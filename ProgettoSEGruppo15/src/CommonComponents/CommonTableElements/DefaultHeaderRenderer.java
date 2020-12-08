/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents.CommonTableElements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Catello
 */
public class DefaultHeaderRenderer extends JLabel implements TableCellRenderer{
 
    public DefaultHeaderRenderer() {
        setOpaque(true);
        setBackground(new Color(245,171,53));
        Border b = BorderFactory.createCompoundBorder();
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(1,0,0,0,Color.WHITE));
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,1,0,0,Color.WHITE));
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,1,Color.WHITE));
        setBorder(b);
        Dimension d=this.getPreferredSize();
        d.height=40;
        this.setPreferredSize(d);
        this.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
 
}
