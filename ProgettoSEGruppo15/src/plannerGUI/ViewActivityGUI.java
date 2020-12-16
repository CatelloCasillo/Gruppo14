package plannerGUI;


import Navigator.Navigator;
import PrimoPackege.*;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    Planner p ;
    String header[]= new String []{"ID", "site", "typology", "activityDescription", "intervationTime", "interruptible", "week"};
    DefaultTableModel dtm;
    int row, col;
    int lastSelected=-1;
    
    

    /** Creates new form ViewActivityGUI */
    public ViewActivityGUI(Planner p) {
        this.p= p;
        initComponents();
        dtm=  new DefaultTableModel(header, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }    
        }
                ;
        jTable1.setModel(dtm);
        this.setLocationRelativeTo(null);
        disabledField();
        populateTable();
    }

    private ViewActivityGUI(){
        initComponents();
        dtm=  new DefaultTableModel(header, 0);
        jTable1.setModel(dtm);
        this.setLocationRelativeTo(null);
        disabledField();
   }
    
    private void disabledField(){
        jTextFieldId.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBoxTyp.setEnabled(false);
        jTextFieldDescription.setEnabled(false);
        jTextFieldTime.setEnabled(false);
        jTextFieldWeek.setEnabled(false);
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
    }
   
    private void enabledField(){
        jComboBox2.setEnabled(true);
        jComboBoxTyp.setEnabled(true);
        jTextFieldDescription.setEnabled(true);
        jTextFieldTime.setEnabled(true);
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
        jTextFieldWeek.setEnabled(true); 
    }
    
    private void clearField(){
        jTextFieldId.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBoxTyp.setSelectedIndex(0);
        jTextFieldDescription.setText("");
        jTextFieldTime.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jTextFieldWeek.setText("");
    }
    
    private boolean activityAssigned(int i){
        if (p.getActivityList().isEmpty())
            return false;
           if(p.getActivityList().get(i).getMaintainerID()!=null){
               return true;
           }
        return false;
}
    
    private void populateTable(){
        dtm.setRowCount(0);
        for(int i=0; i<p.getActivityList().size(); i++){
           Object[] objs = {p.getActivityList().get(i).getId(),p.getActivityList().get(i).getSite().getId().trim()+':' + p.getActivityList().get(i).getSite().getArea()+ '-'+ p.getActivityList().get(i).getSite().getFactory()  , p.getActivityList().get(i).getTypology(),
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

        panelBase1 = new CommonComponents.PanelBase();
        jLabel9 = new javax.swing.JLabel();
        minimizeButton1 = new CommonComponents.MinimizeButton();
        closeButton1 = new CommonComponents.CloseButton();
        panelBase2 = new CommonComponents.PanelBase();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
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
        operationButton1 = new CommonComponents.OperationButton();
        operationButton2 = new CommonComponents.OperationButton();
        operationButton3 = new CommonComponents.OperationButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel9.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel9.setText("View all Activities");

        javax.swing.GroupLayout panelBase1Layout = new javax.swing.GroupLayout(panelBase1);
        panelBase1.setLayout(panelBase1Layout);
        panelBase1Layout.setHorizontalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBase1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBase1Layout.setVerticalGroup(
            panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBase1Layout.createSequentialGroup()
                .addGroup(panelBase1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );

        panelBase2.setBackground(new java.awt.Color(245, 171, 53));
        panelBase2.setLayout(null);

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

        panelBase2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 480, 180);

        jTextFieldId.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldId.setPreferredSize(new java.awt.Dimension(6, 25));
        panelBase2.add(jTextFieldId);
        jTextFieldId.setBounds(100, 260, 59, 25);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("activity ID:");
        panelBase2.add(jLabel2);
        jLabel2.setBounds(20, 260, 70, 14);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("site:");
        panelBase2.add(jLabel3);
        jLabel3.setBounds(20, 310, 30, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("typology: ");
        panelBase2.add(jLabel4);
        jLabel4.setBounds(20, 360, 60, 14);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("activity Description: ");
        panelBase2.add(jLabel5);
        jLabel5.setBounds(20, 410, 116, 14);

        jTextFieldDescription.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldDescription.setPreferredSize(new java.awt.Dimension(6, 25));
        panelBase2.add(jTextFieldDescription);
        jTextFieldDescription.setBounds(150, 410, 110, 25);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("intervation time:");
        panelBase2.add(jLabel6);
        jLabel6.setBounds(240, 260, 100, 14);

        jTextFieldTime.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldTime.setPreferredSize(new java.awt.Dimension(6, 25));
        panelBase2.add(jTextFieldTime);
        jTextFieldTime.setBounds(350, 260, 110, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("interruptible:");
        panelBase2.add(jLabel7);
        jLabel7.setBounds(260, 310, 80, 14);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("week:");
        panelBase2.add(jLabel8);
        jLabel8.setBounds(310, 350, 34, 14);

        jTextFieldWeek.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldWeek.setPreferredSize(new java.awt.Dimension(6, 25));
        panelBase2.add(jTextFieldWeek);
        jTextFieldWeek.setBounds(350, 350, 80, 25);

        jRadioButton1.setBackground(new java.awt.Color(253, 227, 167));
        jRadioButton1.setText("YES");
        panelBase2.add(jRadioButton1);
        jRadioButton1.setBounds(350, 310, 50, 23);

        jRadioButton2.setBackground(new java.awt.Color(253, 227, 167));
        jRadioButton2.setText("NO");
        panelBase2.add(jRadioButton2);
        jRadioButton2.setBounds(410, 310, 50, 23);

        jComboBoxTyp.setBackground(new java.awt.Color(253, 227, 167));
        for(int i=0; i<p.getTypology().size(); i++){
            jComboBoxTyp.addItem(p.getTypology().get(i));
        }
        panelBase2.add(jComboBoxTyp);
        jComboBoxTyp.setBounds(90, 360, 80, 20);

        for(int i=0; i<p.getSiteList().size(); i++){

            jComboBox2.addItem(p.getSiteList().get(i).getId().trim()+':'+ p.getSiteList().get(i).getArea()+'-'+ p.getSiteList().get(i).getFactory());

        };
        panelBase2.add(jComboBox2);
        jComboBox2.setBounds(60, 310, 80, 20);

        operationButton1.setText("delete");
        operationButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton1ActionPerformed(evt);
            }
        });
        panelBase2.add(operationButton1);
        operationButton1.setBounds(20, 475, 120, 30);

        operationButton2.setText("update");
        operationButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton2ActionPerformed(evt);
            }
        });
        panelBase2.add(operationButton2);
        operationButton2.setBounds(370, 475, 120, 30);

        operationButton3.setText("back to homepage");
        operationButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton3ActionPerformed(evt);
            }
        });
        panelBase2.add(operationButton3);
        operationButton3.setBounds(190, 475, 130, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBase1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBase2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBase1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelBase2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        row= jTable1.getSelectedRow();
        col = jTable1.getColumnCount();
        jTextFieldId.setText(dtm.getValueAt(row, 0).toString());
        String loc1= dtm.getValueAt(row, 2).toString().trim();
        for(int i=0 ; i<(jComboBoxTyp.getItemCount()); i++){
            if(jComboBoxTyp.getItemAt(i).equalsIgnoreCase(loc1)){
                jComboBoxTyp.setSelectedIndex(i);
            }
        }
        
        String loc2= dtm.getValueAt(row, 1).toString();
        for(int i=0 ; i<(jComboBox2.getItemCount()); i++){
            if(jComboBox2.getItemAt(i).equalsIgnoreCase(loc2)){
                jComboBox2.setSelectedIndex(i);
            }
        }
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

    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
        if( jTable1.isRowSelected(row)){
        int dialogButton =JOptionPane.YES_NO_OPTION;
        int dialogResult= JOptionPane.showConfirmDialog(this, "Delete this data", "Delete", dialogButton);
        if(dialogResult == 0){
        row= jTable1.getSelectedRow();
        p.deleteActivity(dtm.getValueAt(row, 0).toString(), row);
        populateTable();
        }
    }clearField();                                                 
    }//GEN-LAST:event_operationButton1ActionPerformed

    private void operationButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton2ActionPerformed
        //if( jTable1.isRowSelected(row)){
        lastSelected=jTable1.getSelectedRow();
        if(p.getActivityList().isEmpty() || lastSelected<0){
            return;
        }
        if(activityAssigned( jTable1.getSelectedRow())){
            disabledField();
            JOptionPane.showMessageDialog(rootPane, "Attività già assegnata impossibile modificare!", "Error message", JOptionPane.ERROR_MESSAGE);
        }
        else{
        enabledField();
        String updateSite= jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
        String updateTypology = jComboBoxTyp.getItemAt(jComboBoxTyp.getSelectedIndex());
        String updateDescription = jTextFieldDescription.getText();
        int updateTime = Integer.parseInt(jTextFieldTime.getText().trim());
        boolean updateInterruptible= jRadioButton1.isSelected();
        int updateWeek = Integer.parseInt(jTextFieldWeek.getText().trim());
        
        /*
        p.getActivityList().get(row).setTypology(updateTypology);
        p.getActivityList().get(row).setActivityDescription(updateDescription);
        p.getActivityList().get(row).setIntervationTime(updateTime);
        p.getActivityList().get(row).setInterruptible(updateInterruptible);
        p.getActivityList().get(row).setWeek(updateWeek);
        */
        if( jTable1.isRowSelected(row))
            p.updateActivity(row, jTextFieldId.getText(), updateSite, updateTypology, updateDescription, updateTime, updateInterruptible, updateWeek);
        else
             p.updateActivity(lastSelected, jTextFieldId.getText(), updateSite, updateTypology, updateDescription, updateTime, updateInterruptible, updateWeek);

        populateTable();
        }
    }//GEN-LAST:event_operationButton2ActionPerformed

    private void operationButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton3ActionPerformed
        Navigator nav=Navigator.getInstance(p);
        nav.changeToWelcomeWindow(this);
        /*       FirstPagePlannerGUI welcome= new FirstPagePlannerGUI(p);
        welcome.setVisible(true);
        welcome.pack();
        welcome.setLocationRelativeTo(null);
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
    }//GEN-LAST:event_operationButton3ActionPerformed

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
    private CommonComponents.CloseButton closeButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxTyp;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFieldWeek;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.OperationButton operationButton2;
    private CommonComponents.OperationButton operationButton3;
    private CommonComponents.PanelBase panelBase1;
    private CommonComponents.PanelBase panelBase2;
    // End of variables declaration//GEN-END:variables

}
