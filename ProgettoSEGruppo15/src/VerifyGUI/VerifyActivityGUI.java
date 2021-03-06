package VerifyGUI;

import Navigator.Navigator;
import Planner.PlannerInterface;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicolo
 */
public class VerifyActivityGUI extends javax.swing.JFrame {
    PlannerInterface p;
    String id;
    String activityDescription;
    Image icon;
    String workspacenotes;

    /**
     * Creates new form VerifyActivityGUI
     
     */
    public VerifyActivityGUI(PlannerInterface p, String id, String activityInfo) {
        this.p=p;
        this.id=id;
        this.icon = new ImageIcon("src\\image\\cartella.jpg").getImage();
        initComponents();
        jTextAreaWork.setText(p.getMaintanceActivity(id).getWorkspacenotes());
        jTextAreaIntervention1.setText(p.getMaintanceActivity(id).getActivityDescription());  
        this.activityInfoLabel1.setText(activityInfo);
        
    }

    private VerifyActivityGUI() {
        initComponents();
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
        minimizeButton1 = new CommonComponents.MinimizeButton();
        closeButton1 = new CommonComponents.CloseButton();
        weekNumber1 = new CommonComponents.WeekNumber();
        labelForWeekNumber1 = new CommonComponents.LabelForWeekNumber();
        activtyAssign1 = new CommonComponents.ActivtyAssign();
        activityInfoLabel1 = new CommonComponents.ActivityInfoLabel();
        labelLight1 = new CommonComponents.LabelLight();
        labelLight3 = new CommonComponents.LabelLight();
        labelLight4 = new CommonComponents.LabelLight();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaWork = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaIntervention1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        forwardButton1 = new CommonComponents.ForwardButton();
        operationButton1 = new CommonComponents.OperationButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        labelLight3.setText("Intervention description");

        labelLight4.setText("Skills needed");

        jTextAreaWork.setColumns(20);
        jTextAreaWork.setRows(5);
        jScrollPane5.setViewportView(jTextAreaWork);

        jTextAreaIntervention1.setEditable(false);
        jTextAreaIntervention1.setColumns(20);
        jTextAreaIntervention1.setRows(5);
        jScrollPane2.setViewportView(jTextAreaIntervention1);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {

            String[] strings = toArrayString(p.getCompetencesList(VerifyActivityGUI.this.id));
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i].toString();
            }
            private String[] toArrayString(ArrayList<String> list){
                String [] array = new String[list.size()];
                for(int i=0; i<list.size();i++){
                    array[i]=list.get(i);

                }
                return array;
            }
        });
        jScrollPane4.setViewportView(jList1);

        forwardButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButton1ActionPerformed(evt);
            }
        });

        operationButton1.setText("Back to selection");
        operationButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton1ActionPerformed(evt);
            }
        });

        icon= icon.getScaledInstance(105,81,Image.SCALE_DEFAULT);
        ImageIcon icona=new ImageIcon(icon);
        jButton1.setIcon(icona);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("File SMP");

        javax.swing.GroupLayout panelBase1Layout = new javax.swing.GroupLayout(panelBase1);
        panelBase1.setLayout(panelBase1Layout);
        panelBase1Layout.setHorizontalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBase1Layout.createSequentialGroup()
                                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBase1Layout.createSequentialGroup()
                                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)))
                                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(forwardButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                                        .addComponent(labelLight4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34))))
                            .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGap(282, 282, 282)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBase1Layout.setVerticalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelForWeekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weekNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activtyAssign1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(activityInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLight4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLight3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLight1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(forwardButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBase1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22)
                .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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

    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
       Navigator nav=Navigator.getInstance(p);
       nav.changeToSelectActivityWindow(this);
       /*SelectActivityGUI selection= new SelectActivityGUI(p);
       selection.setVisible(true);
       selection.pack();
       selection.setLocationRelativeTo(null);
       selection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.dispose();*/
    }//GEN-LAST:event_operationButton1ActionPerformed

    private void forwardButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButton1ActionPerformed
       p.updateNotes(id, this.jTextAreaWork.getText());
       Navigator nav=Navigator.getInstance(p);
       nav.changeToMaintainerSelectionWindow(this);
       /*MaintainerSelectionGUI maintainerSelection= new MaintainerSelectionGUI(p,id,this.activityInfoLabel1.getText(),this.jTextAreaWork.getText());
       maintainerSelection.setVisible(true);
       maintainerSelection.pack();
       maintainerSelection.setLocationRelativeTo(null);
       maintainerSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.dispose();*/
    }//GEN-LAST:event_forwardButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            File myfile = new File(("src\\FileSmp").concat("\\").concat(p.getMaintanceActivity(id).getFileSMP().concat(".pdf")));
            if(myfile== (null)){
                JOptionPane.showMessageDialog(rootPane, "There are no pdf associated!", "Error Message", JOptionPane.ERROR_MESSAGE);
            
            }
            else{
            Desktop.getDesktop().open(myfile);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "There are no pdf associated!", "Error Message", JOptionPane.ERROR_MESSAGE);
        } catch(NullPointerException ex){
            JOptionPane.showMessageDialog(rootPane, "There are no pdf", "Error Message", JOptionPane.ERROR_MESSAGE);
        
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VerifyActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerifyActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerifyActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerifyActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerifyActivityGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.ActivityInfoLabel activityInfoLabel1;
    private CommonComponents.ActivtyAssign activtyAssign1;
    private CommonComponents.CloseButton closeButton1;
    private CommonComponents.ForwardButton forwardButton1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextAreaIntervention1;
    private javax.swing.JTextArea jTextAreaWork;
    private CommonComponents.LabelForWeekNumber labelForWeekNumber1;
    private CommonComponents.LabelLight labelLight1;
    private CommonComponents.LabelLight labelLight3;
    private CommonComponents.LabelLight labelLight4;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.PanelBase panelBase1;
    private CommonComponents.WeekNumber weekNumber1;
    // End of variables declaration//GEN-END:variables
}
