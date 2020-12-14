/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plannerGUI;

import Navigator.Navigator;
import PrimoPackege.Planner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
//import EWOassignment.EwoAssignmentGUI;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class ActivityEWO extends javax.swing.JFrame {
    Planner p;
    String id;
    String site; 
    String typology; 
    String workspacenote;
    /**
     * Creates new form ActivityEWO
     * @param p
     * @param id
     * @param site
     * @param typology
     * @param workspacenote
     */
    public ActivityEWO(Planner p, String id, String site, String typology, String workspacenote) {
        this.p= p;
        this.id= id;
        this.site= site;
        this.typology= typology;
        this.workspacenote= workspacenote;        
        initComponents();
        this.setLocationRelativeTo(null);
        activityInfoLabel1.setText(id+" - "+site+" - "+typology);
        jTextArea1.setText(workspacenote);
    }
     public ActivityEWO() {
        initComponents();
        this.setLocationRelativeTo(null);
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
        weekNumber1 = new CommonComponents.WeekNumber();
        minimizeButton1 = new CommonComponents.MinimizeButton();
        closeButton1 = new CommonComponents.CloseButton();
        labelForWeekNumber3 = new CommonComponents.LabelForWeekNumber();
        labelLight2 = new CommonComponents.LabelLight();
        labelLight3 = new CommonComponents.LabelLight();
        labelLight4 = new CommonComponents.LabelLight();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        activtyAssign1 = new CommonComponents.ActivtyAssign();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        forwardButton1 = new CommonComponents.ForwardButton();
        activityInfoLabel1 = new CommonComponents.ActivityInfoLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        dayWeek1 = new CommonComponents.DayWeek();
        labelForWeekDay2 = new CommonComponents.LabelForWeekDay();
        operationButton1 = new CommonComponents.OperationButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        labelLight3.setText("Intervention description");

        labelLight4.setText("Skills needed");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        activtyAssign1.setBackground(java.awt.Color.red);
        activtyAssign1.setForeground(java.awt.Color.yellow);

        DefaultListModel lm = new DefaultListModel();
        for(String s: p.getCompetenceTypology(typology)){
            lm.addElement(s);
            //lm.add(s);
        };
        jList1.setModel(lm);
        jList1.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jList1MouseExited(evt);
            }
        });
        jScrollPane4.setViewportView(jList1);

        forwardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButton1ActionPerformed(evt);
            }
        });

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        operationButton1.setText("back to tickets");
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
                        .addContainerGap()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelLight2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(labelForWeekNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addComponent(labelForWeekDay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(dayWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelLight3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelLight4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4)
                                    .addGroup(panelBase1Layout.createSequentialGroup()
                                        .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        panelBase1Layout.setVerticalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelForWeekNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelForWeekDay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dayWeek1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLight2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLight4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
        Navigator nav=Navigator.getInstance(p);
        nav.changeToTicketList(this);
        
        /*        TicketList planner= new TicketList(p);
        planner.setVisible(true);
        planner.pack();
        planner.setLocationRelativeTo(null);
        planner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_operationButton1ActionPerformed

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        Robot r;
        try {
            r = new Robot();
            r.keyPress(KeyEvent.VK_CONTROL);                              
        }catch (AWTException ex) {                               
        }                           

    }//GEN-LAST:event_jList1MouseEntered

    private void jList1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseExited
        Robot r;
        try {
            r = new Robot();
            r.keyRelease(KeyEvent.VK_CONTROL);
        }catch (AWTException ex) {
            }
    }//GEN-LAST:event_jList1MouseExited

    private void forwardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButton1ActionPerformed
        //System.out.println(jList1.getSelectedValuesList());
        /*
        EwoAssignmentGUI c= new EwoAssignmentGUI(p, site, id, typology, workspacenote, jList1.getSelectedValuesList());
        c.setVisible(true);
        c.pack();
        c.setLocationRelativeTo(null);
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.dispose();
                //System.out.println(jList1.getSelectedValuesList());
        */
        
    }//GEN-LAST:event_forwardButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ActivityEWO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActivityEWO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActivityEWO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActivityEWO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActivityEWO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.ActivityInfoLabel activityInfoLabel1;
    private CommonComponents.ActivtyAssign activtyAssign1;
    private CommonComponents.CloseButton closeButton1;
    private CommonComponents.DayWeek dayWeek1;
    private CommonComponents.ForwardButton forwardButton1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private CommonComponents.LabelForWeekDay labelForWeekDay2;
    private CommonComponents.LabelForWeekNumber labelForWeekNumber3;
    private CommonComponents.LabelLight labelLight2;
    private CommonComponents.LabelLight labelLight3;
    private CommonComponents.LabelLight labelLight4;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.PanelBase panelBase1;
    private CommonComponents.WeekNumber weekNumber1;
    // End of variables declaration//GEN-END:variables
}
