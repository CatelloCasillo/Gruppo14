package plannerGUI;


import PrimoPackege.*;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriella
 */
public class ViewActivityGUI extends javax.swing.JFrame {
    //ArrayList<MaintanceActivity> maintanceList;
    Planner p ;
    String header[]= new String []{"ID", "site", "typology", "activityDescription", "intervationTime", "interruptible", "week"};
    DefaultTableModel dtm;
    int row, col;
    

    /** Creates new form ViewActivityGUI */
    public ViewActivityGUI(Planner p) {
        this.p= p;
        initComponents();
        //maintanceList= new ArrayList<>();
        dtm=  new DefaultTableModel(header, 0);
        jTable1.setModel(dtm);
        this.setLocationRelativeTo(null);
        disabledField();
        populateTable();
    }

    private ViewActivityGUI() {
        initComponents();
        dtm=  new DefaultTableModel(header, 0);
        jTable1.setModel(dtm);
        this.setLocationRelativeTo(null);
        disabledField();
   }
    
    public void disabledField(){
        jTextFieldId.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBoxTyp.setEnabled(false);
        jTextFieldDescription.setEnabled(false);
        jTextFieldTime.setEnabled(false);
        jTextFieldWeek.setEnabled(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
    }
   
    public void enabledField(){
       jTextFieldId.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBoxTyp.setEnabled(true);
        jTextFieldDescription.setEnabled(true);
        jTextFieldTime.setEnabled(true);
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
        jTextFieldWeek.setEnabled(true); 
    }
    
    public void clearField(){
        jTextFieldId.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBoxTyp.setSelectedIndex(0);
        jTextFieldDescription.setText("");
        jTextFieldTime.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jTextFieldWeek.setText("");
    }
 
    public boolean verifRadioButton(){
        if (jRadioButton1.isSelected()){
            return true;
        }
        return false;
    }
    
    public void borderWhite(){
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabelClose.setBorder(label_border);
        jLabelClose.setForeground(Color.white);
    }
    
    public void borderBlack(){
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        jLabelClose.setBorder(label_border);
        jLabelClose.setForeground(Color.black);
    }
   
    public void populateTable(){
        dtm.setRowCount(0);
        for(int i=0; i<p.getActivityList().size(); i++){
           Object[] objs = {p.getActivityList().get(i).getId(), p.getActivityList().get(i).getSite().getArea()+ '-'+ p.getActivityList().get(i).getSite().getFactory()  , p.getActivityList().get(i).getTypology(),
               p.getActivityList().get(i).getActivityDescription(), p.getActivityList().get(i).getIntervationTime(), p.getActivityList().get(i).isInterruptible(),
               p.getActivityList().get(i).getWeekNumber()} ;
           dtm.addRow(objs);
    }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTime = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldWeek = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBoxTyp = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(248, 148, 6));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel1.setText("View all Activities");

        jLabelClose.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelClose.setText("X");
        jLabelClose.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelCloseMouseExited(evt);
            }
        });

        jLabelMin.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelMin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMin.setText("-");
        jLabelMin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelMin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelMin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMinMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMinMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMinMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClose)
                    .addComponent(jLabelMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel2.setBackground(new java.awt.Color(245, 171, 53));
        jPanel2.setPreferredSize(new java.awt.Dimension(436, 270));
        jPanel2.setLayout(null);

        jTable1.setBackground(new java.awt.Color(253, 227, 167));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 480, 180);

        jButtonDelete.setBackground(new java.awt.Color(211, 84, 0));
        jButtonDelete.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonDelete);
        jButtonDelete.setBounds(10, 460, 110, 23);

        jButtonUpdate.setBackground(new java.awt.Color(211, 84, 0));
        jButtonUpdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonUpdate);
        jButtonUpdate.setBounds(390, 460, 73, 23);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("activity ID:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 260, 70, 14);

        jTextFieldId.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldId.setPreferredSize(new java.awt.Dimension(6, 25));
        jPanel2.add(jTextFieldId);
        jTextFieldId.setBounds(100, 260, 59, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("site:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 310, 30, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("typology: ");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 360, 60, 14);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("activity Description: ");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 410, 116, 14);

        jTextFieldDescription.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldDescription.setPreferredSize(new java.awt.Dimension(6, 25));
        jPanel2.add(jTextFieldDescription);
        jTextFieldDescription.setBounds(150, 410, 110, 25);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("intervation time:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(240, 260, 100, 14);

        jTextFieldTime.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldTime.setPreferredSize(new java.awt.Dimension(6, 25));
        jPanel2.add(jTextFieldTime);
        jTextFieldTime.setBounds(350, 260, 110, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("interruptible:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(260, 310, 80, 14);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("week:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(310, 350, 34, 14);

        jTextFieldWeek.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldWeek.setPreferredSize(new java.awt.Dimension(6, 25));
        jPanel2.add(jTextFieldWeek);
        jTextFieldWeek.setBounds(350, 350, 80, 25);

        jRadioButton1.setBackground(new java.awt.Color(253, 227, 167));
        jRadioButton1.setText("YES");
        jPanel2.add(jRadioButton1);
        jRadioButton1.setBounds(350, 310, 50, 23);

        jRadioButton2.setBackground(new java.awt.Color(253, 227, 167));
        jRadioButton2.setText("NO");
        jPanel2.add(jRadioButton2);
        jRadioButton2.setBounds(410, 310, 50, 23);

        jComboBoxTyp.setBackground(new java.awt.Color(253, 227, 167));
        jComboBoxTyp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electrical", "Electronic", "Hydraulic", "Mechanical" }));
        jPanel2.add(jComboBoxTyp);
        jComboBoxTyp.setBounds(90, 360, 80, 20);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        for(int i=0; i<p.getSiteList().size(); i++){
            jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { p.getSiteList().get(i).getId().trim()+':'+ p.getSiteList().get(i).getArea()+'-'+ p.getSiteList().get(i).getFactory() }));
        }
        jPanel2.add(jComboBox2);
        jComboBox2.setBounds(60, 310, 80, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseEntered
        borderWhite();
    }//GEN-LAST:event_jLabelCloseMouseEntered

    private void jLabelCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseExited
        borderBlack();
    }//GEN-LAST:event_jLabelCloseMouseExited

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinMouseClicked

    private void jLabelMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseEntered
        borderWhite();
    }//GEN-LAST:event_jLabelMinMouseEntered

    private void jLabelMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseExited
        borderBlack();
    }//GEN-LAST:event_jLabelMinMouseExited

    
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
    if( jTable1.isRowSelected(row)){
        int dialogButton =JOptionPane.YES_NO_OPTION;
    int dialogResult= JOptionPane.showConfirmDialog(this, "Delete this data", "Delete", dialogButton);
    if(dialogResult == 0){
        //dtm.removeRow(row);
        row= jTable1.getSelectedRow();
        p.deleteActivity(dtm.getValueAt(row, 0).toString(), row);
        
        populateTable();
        }
    }clearField();      
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    
    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        if( jTable1.isRowSelected(row)){
        enabledField();
        String updateId = jTextFieldId.getText();
        //String updateSite = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
        String updateTypology = jComboBoxTyp.getItemAt(jComboBoxTyp.getSelectedIndex());
        String updateDescription = jTextFieldDescription.getText();
        int updateTime = Integer.parseInt(jTextFieldTime.getText().trim());
        boolean updateInterruptible= verifRadioButton();
        int updateWeek = Integer.parseInt(jTextFieldWeek.getText().trim());
        
        //row= jTable1.getSelectedRow();
        p.getActivityList().get(row).setId(updateId);
        //p.getActivityList().get(row).setSite(updateSite);
        p.getActivityList().get(row).setTypology(updateTypology);
        p.getActivityList().get(row).setActivityDescription(updateDescription);
        p.getActivityList().get(row).setIntervationTime(updateTime);
        p.getActivityList().get(row).setInterruptible(updateInterruptible);
        p.getActivityList().get(row).setWeek(updateWeek);
        
        p.updateActivity(row, updateId, updateTypology, updateDescription, updateTime, updateInterruptible, updateWeek);
        
        /*p.listmaintance.get(row).id= updateId;
        p.listmaintance.get(row).site = updateSite;
        p.listmaintance.get(row).typology = updateTypology;
        p.listmaintance.get(row).activityDescription= updateDescription;
        p.listmaintance.get(row).intervationTime = updateTime;
        p.listmaintance.get(row).interruptible= updateInterruptible;
        p.listmaintance.get(row).week = updateWeek;*/
        populateTable();
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        row= jTable1.getSelectedRow();
        col = jTable1.getColumnCount();
        System.out.println(row+ ','+ col);
        jTextFieldId.setText(dtm.getValueAt(row, 0).toString());
        //jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
        //jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
        jComboBoxTyp.setSelectedIndex(jComboBoxTyp.getSelectedIndex());
        jTextFieldDescription.setText(dtm.getValueAt(row, 3).toString());
        jTextFieldTime.setText(dtm.getValueAt(row, 4).toString());
        if (dtm.getValueAt(row, 5).equals(true)){
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
        }
        else{
            jRadioButton2.setSelected(true);
            jRadioButton1.setSelected(false);
        }
        jTextFieldWeek.setText(dtm.getValueAt(row, 6).toString());
        
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(ViewActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewActivityGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxTyp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFieldWeek;
    // End of variables declaration//GEN-END:variables

}