/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents.CommonTableElements;

import static CommonComponents.CommonTableElements.RenderingUtility.colorPicker;
import java.awt.Color;
import java.awt.Component;
import static java.lang.Integer.parseInt;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Catello
 */
public class HourPercentageRenderer extends DefaultTableCellRenderer{
    @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel cell=(JLabel)super.getTableCellRendererComponent(jtable,value,isSelected,hasFocus,row,column);
            if(column>1){
                Object cellContent=jtable.getModel().getValueAt(row, column);
                if(cellContent != null){
                    String content = cellContent.toString().trim();
                    String [] fraction= content.split(" ");
                    int percentage = (parseInt(fraction[0])*100)/60;
                    Color color = colorPicker(percentage);
                    cell.setOpaque(true);
                    cell.setBackground(color);
                    cell.setHorizontalAlignment(SwingConstants.CENTER);
                }
            }
            if(isSelected){
                cell.setBackground(Color.BLACK);
            }
            return cell;
        }
    }
