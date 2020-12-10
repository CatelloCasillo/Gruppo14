/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import CommonComponents.CommonTableElements.DefaultHeaderRenderer;
import CommonComponents.CommonTableElements.HourPercentageRenderer;
import CommonComponents.CommonTableElements.MaintenerColumnRenderer;
import CommonComponents.CommonTableElements.NoEditableTableModel;
import CommonComponents.CommonTableElements.SkillColumnRenderer;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Catello
 */
public class MaintainerDailyTable extends JTable{

    public MaintainerDailyTable() {
        super();
        this.setColumnSelectionAllowed(true);
        this.setRowSelectionAllowed(false);
        this.setSelectionBackground(new java.awt.Color(153, 0, 153));
        this.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
    }
    public void setNoEditableModel(NoEditableTableModel model){
        this.setModel(model);
        this.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        TableColumn firstColumn = this.getColumnModel().getColumn(0);
        firstColumn.setPreferredWidth(150);
        firstColumn.setCellRenderer(new MaintenerColumnRenderer());
        TableColumn secondColumn = this.getColumnModel().getColumn(1);
        secondColumn.setCellRenderer(new SkillColumnRenderer());
        for(int i=2;i<11;i++){
            TableColumn column=this.getColumnModel().getColumn(i);
            column.setCellRenderer(new HourPercentageRenderer());
        }
    }
    
}
