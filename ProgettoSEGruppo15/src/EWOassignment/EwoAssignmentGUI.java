/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EWOassignment;

import CommonComponents.CommonTableElements.NoEditableTableModel;
import MantainerSelection.ActivityAssignmentGUI;
import PrimoPackege.Planner;
import PrimoPackege.PlannerInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author griec
 */

public class EwoAssignmentGUI extends javax.swing.JFrame {
    PlannerInterface p;
    String id;
    String site;
    String typology;
    String workspacenote;
    List<String> skills;
    ActivityAssignmentGUI a;
    
    
    
    /**
     * Creates new form EwoAssignmentGUI
     * @param p
     * @param site
     * @param id
     * @param typology
     * @param workspacenote
     * @param selectedDayWeek
     * @param activityInfo
     * @param notes
     * @param selectedDate
     * @param skills
     */

    public EwoAssignmentGUI(PlannerInterface p,String site,String id,String typology,String workspacenote,List skills) {
        this.p=p;
        this.site=site;
        this.id=id;
        this.typology=typology;
        this.workspacenote= workspacenote;
        this.skills=skills;
        initComponents();
        
        jTextArea1.setText(workspacenote);
        this.activityInfoLabel1.setText(id+" - "+site+" - "+typology+"'");
       
        
    }

    private EwoAssignmentGUI() {
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
        dayWeek1 = new CommonComponents.DayWeek();
        labelForWeekNumber1 = new CommonComponents.LabelForWeekNumber();
        labelForWeekDay1 = new CommonComponents.LabelForWeekDay();
        weekNumber1 = new CommonComponents.WeekNumber();
        activtyAssign1 = new CommonComponents.ActivtyAssign();
        activityInfoLabel1 = new CommonComponents.ActivityInfoLabel();
        labelLight1 = new CommonComponents.LabelLight();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        labelLight2 = new CommonComponents.LabelLight();
        jScrollPane3 = new javax.swing.JScrollPane();
        maintainerDailyTable1 = new CommonComponents.MaintainerDailyTable();
        minimizeButton1 = new CommonComponents.MinimizeButton();
        closeButton2 = new CommonComponents.CloseButton();
        labelLight3 = new CommonComponents.LabelLight();
        forwardButton1 = new CommonComponents.ForwardButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        activtyAssign1.setBackground(java.awt.Color.red);
        activtyAssign1.setForeground(java.awt.Color.yellow);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        labelLight2.setText("Skills needed");

        maintainerDailyTable1.setModel(new NoEditableTableModel(
            new Object [][] {
                { EwoAssignmentGUI.this.a, "3/5", "60 min", "50 min", "30 min", "55 min", "0 min", "35 min","10 min","15 min","25 min"}
            },
            new String [] {

                "Maintainer",
                "Skills",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>8:00 - 9:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>9:00 - 10:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>10:00 - 11:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>11:00 - 12:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>12:00 - 13:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>13:00 - 14:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>14:00 - 15:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>15:00 - 16:00</span></div></html>\"",
                "<html><div style = 'text-align: center'>Availab.<br><span style = 'font-size: 65%'>16:00 - 17:00</span></div></html>\"",
            }
        ));
        jScrollPane3.setViewportView(maintainerDailyTable1);

        labelLight3.setText("Maintainers AVAILABILITY");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {

            String[] strings = toArrayString(skills);
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i].toString();
            }
            private String[] toArrayString(List<String> list){
                String [] array = new String[list.size()];
                for(int i=0; i<list.size();i++){
                    array[i]=list.get(i);

                }
                return array;
            }

        });
        jScrollPane4.setViewportView(jList1);

        javax.swing.GroupLayout panelBase1Layout = new javax.swing.GroupLayout(panelBase1);
        panelBase1.setLayout(panelBase1Layout);
        panelBase1Layout.setHorizontalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelForWeekDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(dayWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelLight1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(labelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(labelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        panelBase1Layout.setVerticalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelForWeekDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dayWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                                .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)))
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(labelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
/*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EwoAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EwoAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EwoAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EwoAssignmentGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EwoAssignmentGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.ActivityInfoLabel activityInfoLabel1;
    private CommonComponents.ActivtyAssign activtyAssign1;
    private CommonComponents.CloseButton closeButton2;
    private CommonComponents.DayWeek dayWeek1;
    private CommonComponents.ForwardButton forwardButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private CommonComponents.LabelForWeekDay labelForWeekDay1;
    private CommonComponents.LabelForWeekNumber labelForWeekNumber1;
    private CommonComponents.LabelLight labelLight1;
    private CommonComponents.LabelLight labelLight2;
    private CommonComponents.LabelLight labelLight3;
    public CommonComponents.MaintainerDailyTable maintainerDailyTable1;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.PanelBase panelBase1;
    private CommonComponents.WeekNumber weekNumber1;
    // End of variables declaration//GEN-END:variables
}

