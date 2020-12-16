/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plannerGUI;

import Navigator.Navigator;
import PrimoPackege.Planner;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;


/**
 *
 * @author User
 */
public class TicketList extends javax.swing.JFrame{
    
    private Planner p;
    private int COLUMNS = 1;
    private Dimension size;
    private String [] s;
    private File[] f;


     private class MyCellRenderer extends JLabel implements ListCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            Border b = BorderFactory.createCompoundBorder();
                   b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(1,0,0,0,Color.WHITE));
            if (value instanceof File) {
                File file = (File) value;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                setBorder(b);
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setPreferredSize(new Dimension(250, 25));
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
            }
            return this;
        }
    }
    

    /**
     * Creates new form TicketList
     */
    public TicketList() {
        initComponents();
        this.setLocationRelativeTo(null);
        //leggi();
    }
    public TicketList(Planner p, File[] f) {
        this.p=p;
        this.f= f;
        initComponents();
        this.setLocationRelativeTo(null);
        //leggi();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new JList(f){
            //new JList(new File("C:\\Users\\User\\Tickets").listFiles()){

                //   private static final long serialVersionUID = 1L;

                @Override
                public Dimension getPreferredScrollableViewportSize() {
                    if (size != null) {
                        return new Dimension(size);
                    }
                    return super.getPreferredScrollableViewportSize();
                }
            };
            minimizeButton2 = new CommonComponents.MinimizeButton();
            closeButton2 = new CommonComponents.CloseButton();
            operationButton1 = new CommonComponents.OperationButton();
            jLabel1 = new javax.swing.JLabel();
            operationButton2 = new CommonComponents.OperationButton();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setBackground(new java.awt.Color(248, 148, 6));
            setUndecorated(true);

            jPanel1.setBackground(new java.awt.Color(248, 148, 6));

            jScrollPane1.setMinimumSize(new java.awt.Dimension(30, 30));

            list.setBackground(new java.awt.Color(245, 171, 53));
            list.setSelectionBackground(new java.awt.Color(252, 142, 38));
            list.setFixedCellHeight(50);
            list.setFixedCellWidth(400);
            size = list.getPreferredScrollableViewportSize();
            size.width *= COLUMNS;
            list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            list.setCellRenderer(new MyCellRenderer());

            list.setVisibleRowCount(0);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    listMouseClicked(evt);
                }
            });
            jScrollPane1.setViewportView(list);

            operationButton1.setText("create EWO");
            operationButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    operationButton1ActionPerformed(evt);
                }
            });

            jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 48)); // NOI18N
            jLabel1.setText("Tickets");

            operationButton2.setText("back to Homepage");
            operationButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    operationButton2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(191, 191, 191)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(155, 155, 155)
                    .addComponent(minimizeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(closeButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(operationButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(78, 78, 78))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(minimizeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jLabel1)))
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(operationButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 13, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

   
    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
            s=null;
            s= p.getStringInFile(f[list.getSelectedIndex()]);
             if(s==null ){
                JOptionPane.showMessageDialog(rootPane, "il Ticket è vuoto!");
            }
    }//GEN-LAST:event_listMouseClicked

    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
         if(s==null){
                JOptionPane.showMessageDialog(rootPane, "Devi selezionare un Ticket!");
        }
        else{
             //System.out.println(s[1]);
        Navigator nav=Navigator.getInstance(p);
        nav.changeToActivityEWO(this, s[0], s[1], s[2], s[3]);    
        /*     
        ActivityEWO a= new ActivityEWO(p, s[0], s[1], s[2], s[3]);
        a.setVisible(true);
        a.pack();
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();*/
        }
    }//GEN-LAST:event_operationButton1ActionPerformed

    private void operationButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton2ActionPerformed
         Navigator nav=Navigator.getInstance(p);
        nav.changeToWelcomeWindow(this);
    }//GEN-LAST:event_operationButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(TicketList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicketList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicketList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicketList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new TicketList().setVisible(true);
          }
        });
}
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.CloseButton closeButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList list;
    private CommonComponents.MinimizeButton minimizeButton2;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.OperationButton operationButton2;
    // End of variables declaration//GEN-END:variables
}
