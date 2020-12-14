/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents.CommonTableElements;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Catello
 */
public class MaintenerColumnRenderer extends DefaultTableCellRenderer{
     @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            JLabel cell=(JLabel)super.getTableCellRendererComponent(jtable,value,isSelected,hasFocus,row,column);
            cell.setOpaque(true);
            if(column==0){
                if(row%2==0)
                    cell.setBackground(new Color(255, 230, 240));
                else
                    cell.setBackground(new Color(255, 240, 255));
            }
            if(isSelected){
                cell.setBackground(Color.BLACK);
            }
            return cell;
        }
        
}

