/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MantainerSelection;

import CommonComponents.AbstractActvityListModel;
import CommonComponents.CommonTableElements.DefaultHeaderRenderer;
import CommonComponents.CommonTableElements.MaintenerColumnRenderer;
import CommonComponents.CommonTableElements.NoEditableTableModel;
import static CommonComponents.CommonTableElements.RenderingUtility.colorPicker;
import CommonComponents.CommonTableElements.SkillColumnRenderer;
import Navigator.Navigator;
import PrimoPackege.Planner;
import PrimoPackege.PlannerInterface;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author Catello
 */
public class MaintainerSelectionGUI extends javax.swing.JFrame {
    private PlannerInterface p;
    private String selectedActivityId;
    /**
     
    * Renderer di una JTable che permette di visualizzare una stringa del tipo "x%"
    * x viene supposto essere un numero intero pena NumberFormatException
    * In base al valore di x lo sfondo della cella verrà colorato in modo diverso 
    * <p>
    * A seguito di una selezione delle celle queste assumeramo uno sfondo nero e il testo in essa contenuto diventerà bianco 
    */
     
    private class OtherColumnRenderer extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel cell=(JLabel)super.getTableCellRendererComponent(jtable,value,isSelected,hasFocus,row,column);
            if(column>1){
                String content=jtable.getModel().getValueAt(row, column).toString().trim();
                String [] percentage= content.split("%");
                Color color = colorPicker(parseInt(percentage[0].toString().trim()));
                cell.setOpaque(true);
                cell.setBackground(color);
                cell.setHorizontalAlignment(SwingConstants.CENTER);
            }
            if(isSelected){
                cell.setBackground(Color.BLACK);
            }
            return cell;
        }
    }

        
    /**
     * 
     * @param planner Oggetto che rappresenta il Planner che sta operando al momento
     * @param selectedActivityId identificativo dell'attività selezionata e che si vuole assegnarw
     * @param activityInformation stringa con informazioniaggiuntive sull'attività selezionata
     */
    public MaintainerSelectionGUI(PlannerInterface planner,String selectedActivityId, String activityInformation) {
        this.p=planner;
        this.selectedActivityId=selectedActivityId;
        initComponents();
        this.setLocationRelativeTo(null);
        this.jList2.setModel(new AbstractActvityListModel(p,selectedActivityId));
        this.activityInfoLabel1.setText(activityInformation);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBase1 = new CommonComponents.PanelBase();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable()
        ;
        labelLight1 = new CommonComponents.LabelLight();
        labelLight2 = new CommonComponents.LabelLight();
        closeButton1 = new CommonComponents.CloseButton();
        minimizeButton1 = new CommonComponents.MinimizeButton();
        weekNumber1 = new CommonComponents.WeekNumber();
        labelForWeekNumber1 = new CommonComponents.LabelForWeekNumber();
        activtyAssign1 = new CommonComponents.ActivtyAssign();
        activityInfoLabel1 = new CommonComponents.ActivityInfoLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        operationButton1 = new CommonComponents.OperationButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jScrollPane1.setBackground(new java.awt.Color(248, 148, 6));
        jScrollPane1.getViewport().setBackground(new Color(248,148,6));
        jScrollPane1.setBorder(null);

        jTable1.setBackground(new java.awt.Color(248, 148, 6));
        jTable1.setModel(new NoEditableTableModel(
            /*new Object [][] {
                {"Pippo", "3/5", "80%", "100%", "50%", "20%", "0%", "90%", "75%"},
                {"Paperino", "2/5", "80%", "100%", "50%", "20%", "0%", "90%", "75%"},
                {"Sembronio", "1/5", "80%", "100%", "50%", "20%", "0%", "90%", "75%"},
                {"IO", "4/5", "80%", "100%", "50%", "20%", "0%", "90%", "75%"},
                {"Frodo", "5/5", "80%", "100%", "50%", "20%", "0%", "90%", "75%"},
                {"Mitrandir", "0/5", "80%", "100%", "50%", "20%", "0%", "90%", "75%"},

            },*/
            p.getMaintainerWeekCalendar(selectedActivityId),
            new String [] {
                "Maintainer",
                "Skills",
                "<html><div style = 'text-align: center'>Availab.<br>Mon</div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br>Tue</div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br>Wed</div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br>Thu</div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br>Fri</div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br>Sat</div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br>Sun</div></html>\""
            }

        ));
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        TableColumn firstColumn = jTable1.getColumnModel().getColumn(0);
        firstColumn.setPreferredWidth(150);
        firstColumn.setCellRenderer(new MaintenerColumnRenderer());
        TableColumn secondColumn = jTable1.getColumnModel().getColumn(1);
        secondColumn.setCellRenderer(new SkillColumnRenderer());
        for(int i=2;i<9;i++){
            TableColumn column=jTable1.getColumnModel().getColumn(i);
            column.setCellRenderer(new OtherColumnRenderer());
        }
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        labelLight1.setText("Maintainer AVAILABILITY");

        labelLight2.setText("Skill needed");

        weekNumber1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.setPreferredSize(new java.awt.Dimension(33, 170));
        jScrollPane4.setViewportView(jList2);

        operationButton1.setText("Return to Activity Selecion");
        operationButton1.setFont(new java.awt.Font("Cooper Black", 0, 14)); // NOI18N
        operationButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBase1Layout = new javax.swing.GroupLayout(panelBase1);
        panelBase1.setLayout(panelBase1Layout);
        panelBase1Layout.setHorizontalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(activityInfoLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLight2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134))))
        );
        panelBase1Layout.setVerticalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(weekNumber1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(activityInfoLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(operationButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Alla pressione del bottone Recupera dalla tabella le informazioni necessarie alla visualizzazione del prossimo JFrame dalla stessa
     * riga della cella selezionata come
     * il nome del manutentore corrispondente alla disponibilità selezionata(colonna 0),skillcompliance(colonna 1)
     * la percetuale di occupazione presente nella cella selezionata.
     * <p>
     * Viene calcolato il giorno in cui assegnare l'attività a partire dal giorno corrente e dal 
     * dall'indice della cella selezionata. 
     * <p>
     * Infine elimina dalla visualizzazione questo JFrame e viene visualizzato il frame AcitivitySelectionGUI
     * @param evt Action event generato dal click di un cella con indice di colonna maggiore di 1
     * 
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row= jTable1.getSelectedRow();
        int col= jTable1.getSelectedColumn();
        if(col>1){
            TableModel model=jTable1.getModel();
            String mantainerName = model.getValueAt(row, 0).toString();
            String skillCompliance = model.getValueAt(row, 1).toString();
            LocalDate currentDate = LocalDate.now();
            int weekDay = currentDate.getDayOfWeek().getValue();
            LocalDate mondayDate = currentDate.minusDays(weekDay);
            LocalDate selectionDate = mondayDate.plusDays(col-1);
            String selectedWeekDay = selectionDate.getDayOfWeek().toString();
            String percentage = model.getValueAt(row, col).toString();
            String[] s= percentage.split("%");
            Color c= colorPicker(parseInt(s[0]));
            Navigator nav=Navigator.getInstance(p);
            nav.changeToActivityAssignmentWindow(this, mantainerName,skillCompliance,selectedWeekDay, selectionDate,c, percentage, row);
            
        }
    }//GEN-LAST:event_jTable1MouseClicked
/**
*  Alla pressione del botttone Elimina dalla visualizzazione questo JFrame e viene visualizzato 
*  il frame AcitivitySelectionGUI
 * @param evt Action Event prodotto da operationButton1 
 */
    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
        Navigator nav=Navigator.getInstance(p);
        nav.changeToSelectActivityWindow(this);
    }//GEN-LAST:event_operationButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MaintainerSelectionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintainerSelectionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintainerSelectionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintainerSelectionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MaintainerSelectionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.ActivityInfoLabel activityInfoLabel1;
    private CommonComponents.ActivtyAssign activtyAssign1;
    private CommonComponents.CloseButton closeButton1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private CommonComponents.LabelForWeekNumber labelForWeekNumber1;
    private CommonComponents.LabelLight labelLight1;
    private CommonComponents.LabelLight labelLight2;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.PanelBase panelBase1;
    private CommonComponents.WeekNumber weekNumber1;
    // End of variables declaration//GEN-END:variables
}
