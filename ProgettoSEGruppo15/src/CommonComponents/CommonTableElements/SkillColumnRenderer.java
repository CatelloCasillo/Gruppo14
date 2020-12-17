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
 * Renderer di una JTable che permette di visualizzare una stringa del tipo "n1/n2"
 * Si assume che n1 ed n2 devono essere numeri interi pena NumberFormatException
 * <p>
 * In base al valore percetuale (n1/n2)*100 lo sfondo della cella verrà colorato in modo diverso
 * <p>
 * Se n2 è uguale a zero si assume che la percetuale è massima
 * <p>
 * Se la cella è vuota viene il suo sfondo viene lasciato a quello di default
 * <p>
 * A seguito di una seluzione delle celle queste assumeramo uno sfondo nero e il testo in essa contenuto diventerà bianco
 */
public class SkillColumnRenderer extends DefaultTableCellRenderer{
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel cell=(JLabel)super.getTableCellRendererComponent(jtable,value,isSelected,hasFocus,row,column);
            if(column==1){
                Object cellContent=jtable.getModel().getValueAt(row, column);
                if(cellContent != null){
                String content = cellContent.toString().trim();
                    String [] fraction= content.split("/");
                    Color color;
                    if(!fraction[1].equals("0")){
                        int percentage = (parseInt(fraction[0])*100)/parseInt(fraction[1]);
                        color = colorPicker(percentage);
                    }
                    else{
                        color = new Color(0,153,0);
                    }
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
