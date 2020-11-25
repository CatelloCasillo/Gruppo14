
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriella
 */
public class CreateAnActivityGUI extends javax.swing.JFrame {
    
    Planner p;

    /**
     * Creates new form CreateAnActivity
     */
    public CreateAnActivityGUI(Planner p) {
        initComponents();
        this.p = p;
       
        this.setLocationRelativeTo(null);
    }

    private CreateAnActivityGUI() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelClose = new javax.swing.JLabel();
        jLabelMin = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldDescription = new javax.swing.JTextField();
        jTextFieldId = new javax.swing.JTextField();
        jTextFieldSite = new javax.swing.JTextField();
        jTextFieldTime = new javax.swing.JTextField();
        jTextFieldMaterial = new javax.swing.JTextField();
        jTextFieldWeek = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaWorkSpace = new javax.swing.JTextArea();
        jButtonCancel = new javax.swing.JButton();
        jButtonCreateee = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(248, 148, 6));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        jLabel1.setText("Create Activity");

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
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabelMin, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClose)
                    .addComponent(jLabelMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(245, 171, 53));
        jPanel2.setPreferredSize(new java.awt.Dimension(436, 270));
        jPanel2.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("site:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(30, 60, 30, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("typology:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 100, 60, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("materials:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(30, 270, 80, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("activity ID:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(30, 14, 70, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("activity description:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 140, 120, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("estimated intervention time:");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(30, 174, 170, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("week:");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(30, 310, 40, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("workspace notes:");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(30, 344, 100, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("interruptible activity:");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(30, 230, 130, 20);

        jTextFieldDescription.setBackground(new java.awt.Color(253, 227, 167));
        jPanel2.add(jTextFieldDescription);
        jTextFieldDescription.setBounds(150, 140, 220, 20);

        jTextFieldId.setBackground(new java.awt.Color(253, 227, 167));
        jTextFieldId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIdFocusLost(evt);
            }
        });
        jPanel2.add(jTextFieldId);
        jTextFieldId.setBounds(100, 20, 90, 20);

        jTextFieldSite.setBackground(new java.awt.Color(253, 227, 167));
        jPanel2.add(jTextFieldSite);
        jTextFieldSite.setBounds(60, 60, 90, 20);

        jTextFieldTime.setBackground(new java.awt.Color(253, 227, 167));
        jPanel2.add(jTextFieldTime);
        jTextFieldTime.setBounds(200, 180, 110, 20);

        jTextFieldMaterial.setBackground(new java.awt.Color(253, 227, 167));
        jPanel2.add(jTextFieldMaterial);
        jTextFieldMaterial.setBounds(100, 270, 150, 20);

        jTextFieldWeek.setBackground(new java.awt.Color(253, 227, 167));
        jPanel2.add(jTextFieldWeek);
        jTextFieldWeek.setBounds(70, 310, 110, 20);

        jRadioButton1.setBackground(new java.awt.Color(253, 227, 167));
        jRadioButton1.setText("YES");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton1);
        jRadioButton1.setBounds(160, 230, 50, 23);

        jRadioButton2.setBackground(new java.awt.Color(253, 227, 167));
        jRadioButton2.setText("NO");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jRadioButton2);
        jRadioButton2.setBounds(230, 230, 50, 23);

        jComboBoxTipo.setBackground(new java.awt.Color(253, 227, 167));
        jComboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Electrical", "Electronic", "Hydraulic", "Mechanical" }));
        jComboBoxTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBoxTipoMouseClicked(evt);
            }
        });
        jComboBoxTipo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jComboBoxTipoCaretPositionChanged(evt);
            }
        });
        jPanel2.add(jComboBoxTipo);
        jComboBoxTipo.setBounds(90, 100, 100, 20);

        jTextAreaWorkSpace.setBackground(new java.awt.Color(253, 227, 167));
        jTextAreaWorkSpace.setColumns(20);
        jTextAreaWorkSpace.setRows(5);
        jScrollPane1.setViewportView(jTextAreaWorkSpace);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(140, 350, 220, 90);

        jButtonCancel.setBackground(new java.awt.Color(211, 84, 0));
        jButtonCancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonCancel.setText("Cancel");
        jButtonCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancelMouseClicked(evt);
            }
        });
        jPanel2.add(jButtonCancel);
        jButtonCancel.setBounds(40, 470, 90, 30);

        jButtonCreateee.setBackground(new java.awt.Color(211, 84, 0));
        jButtonCreateee.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonCreateee.setText("Create");
        jButtonCreateee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCreateee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCreateeeMouseClicked(evt);
            }
        });
        jButtonCreateee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreateeeActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonCreateee);
        jButtonCreateee.setBounds(290, 470, 90, 30);

        jButton1.setBackground(new java.awt.Color(211, 84, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("view activity");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(160, 470, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabelCloseMouseClicked

    private void jLabelMinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabelMinMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    
    // verify if data are empty
    public boolean verifData(){
        if (jTextFieldId.getText().equals("") || jTextFieldSite.getText().equals("") 
                || jTextFieldDescription.getText().equals("") || jTextFieldTime.getText().equals("") 
                || jTextFieldMaterial.getText().equals("") || jTextFieldWeek.getText().equals("") 
                ||(jRadioButton1.isSelected() == false) &&(jRadioButton2.isSelected()== false)){
            JOptionPane.showMessageDialog(null, "One or more fields are empty");
            return false;
        }
        else if (jRadioButton1.isSelected() && jRadioButton2.isSelected()){
            JOptionPane.showMessageDialog(null, "You can choose only Yes or No");
            return false;
        }
        return true;
    }
    public boolean verifRadioButton(){
        if (jRadioButton1.isSelected()){
            return true;
        }
        return false;
    }
    
    public void clearField(){
        jTextFieldId.setText("");
        jTextFieldSite.setText("");
        jComboBoxTipo.setSelectedIndex(0);
        jTextFieldDescription.setText("");
        jTextFieldTime.setText("");
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jTextFieldMaterial.setText("");
        jTextFieldWeek.setText("");
    }
    
    private void jButtonCreateeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreateeeActionPerformed
        if(verifData()){
        p.createActivity(jTextFieldId.getText(),jTextFieldSite.getText(), jComboBoxTipo.getItemAt(jComboBoxTipo.getSelectedIndex()), jTextFieldDescription.getText(),jTextFieldTime.getText(), verifRadioButton() , jTextFieldWeek.getText());
        JOptionPane.showMessageDialog(rootPane, "create successfully");
        clearField();
        /*FirstPagePlannerGUI planner= new FirstPagePlannerGUI();
        planner.setVisible(true);
        planner.pack();
        planner.setLocationRelativeTo(null);
        planner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
        ViewActivityGUI view= new ViewActivityGUI(p);
        view.setVisible(true);
        view.pack();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
        }
    }//GEN-LAST:event_jButtonCreateeeActionPerformed
//non serve
    private void jTextFieldIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIdFocusLost

    }//GEN-LAST:event_jTextFieldIdFocusLost

    private void jLabelMinMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseEntered
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabelMin.setBorder(label_border);
        jLabelMin.setForeground(Color.white);
    }//GEN-LAST:event_jLabelMinMouseEntered

    private void jLabelMinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMinMouseExited
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        jLabelMin.setBorder(label_border);
        jLabelMin.setForeground(Color.black);
    }//GEN-LAST:event_jLabelMinMouseExited

    private void jLabelCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseEntered
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabelClose.setBorder(label_border);
        jLabelClose.setForeground(Color.white);
    }//GEN-LAST:event_jLabelCloseMouseEntered

    private void jLabelCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelCloseMouseExited
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        jLabelClose.setBorder(label_border);
        jLabelClose.setForeground(Color.black);
    }//GEN-LAST:event_jLabelCloseMouseExited

    
    //non serve
    private void jButtonCreateeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCreateeeMouseClicked
       // p.createActivity(jTextFieldId.getText(),jTextFieldSite.getText(), jComboBoxTipo.getItemAt(jComboBoxTipo.getSelectedIndex()), jTextFieldDescription.getText(),jTextFieldTime.getText(), b , jTextFieldWeek.getText());
        //JOptionPane.showMessageDialog(rootPane, "create successfully");
    }//GEN-LAST:event_jButtonCreateeeMouseClicked

    //non serve
    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
      
    }//GEN-LAST:event_jRadioButton1MouseClicked

    
    //non serve
    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked

    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jButtonCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelMouseClicked
        FirstPagePlannerGUI planner= new FirstPagePlannerGUI(p);
        planner.setVisible(true);
        planner.pack();
        planner.setLocationRelativeTo(null);
        planner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButtonCancelMouseClicked

    //non serve
    private void jComboBoxTipoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jComboBoxTipoCaretPositionChanged
        //int i= jComboBoxTipo.getSelectedIndex();
    }//GEN-LAST:event_jComboBoxTipoCaretPositionChanged

    //non serve
    private void jComboBoxTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxTipoMouseClicked
      
    }//GEN-LAST:event_jComboBoxTipoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ViewActivityGUI view= new ViewActivityGUI(p);
        view.setVisible(true);
        view.pack();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
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
            java.util.logging.Logger.getLogger(CreateAnActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAnActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAnActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAnActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAnActivityGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCreateee;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelClose;
    private javax.swing.JLabel jLabelMin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaWorkSpace;
    private javax.swing.JTextField jTextFieldDescription;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldMaterial;
    private javax.swing.JTextField jTextFieldSite;
    private javax.swing.JTextField jTextFieldTime;
    private javax.swing.JTextField jTextFieldWeek;
    // End of variables declaration//GEN-END:variables
}
