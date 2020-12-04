package SelectionGUI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Catello
 */
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.table.DefaultTableCellRenderer;

//renderer che permette la renderizzazione delle celle come bottoni
public class JButtonRenderer extends DefaultTableCellRenderer{ 
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        JButton button= new JButton("Select");
        button.setBackground(new Color(80,80,80));
        button.setForeground(Color.white);
        button.setFocusable(true);
        if(i1==4)
            return button;
        return this;
            
    }
    
}
