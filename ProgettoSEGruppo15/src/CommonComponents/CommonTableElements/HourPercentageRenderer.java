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
 * Renderer di una JTable che permette di visualizzare una stringa del tipo "x min"
 * In base al valore di x lo sfondo della cella verrà colorato in modo diverso
 * a seconda della. In particolare x può assumuere solo un valore compreso fra 0 e 60
 * e ne viene calcolato il suo valore percetuale rispetto a rispetto a 60 e a 
 * partire da questo valore viene scelto il colore
 * <p>
 * A seguito di una seluzione delle celle queste assumeramo uno sfondo nero e il testo in essa contenuto diventerà bianco 
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

