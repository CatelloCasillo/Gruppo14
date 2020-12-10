package SelectionGUI;

import PrimoPackege.Planner;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import plannerGUI.FirstPagePlannerGUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Catello
 */
public class SelectActivityGUI extends javax.swing.JFrame {
    private Planner planner;
    //Creazione di un modello personalizzato per la JTable
    private class MyTableModel extends AbstractTableModel{
        private String[] columnNames;
        private Object[][] data;

        public MyTableModel(String[] columnNames, Object[][] data) {
            this.columnNames = columnNames;
            this.data = data;
        }
           
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
           return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
           return data[row][col];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass(); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col]; //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            if (col < 4) {
                return false;
            } else {
                return true;
            } //To change body of generated methods, choose Tools | Templates.
        }
    }
    //Creazione di un header renderer vuoto e dello stesso colore dello sfondo per la quainta colonna
    private class ButtonHeaderRenderer extends JLabel implements TableCellRenderer{

        public ButtonHeaderRenderer() {
            setOpaque(true);
            setBackground(new Color(245,171,53));
        }
        
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
           setText(o.toString());
           return this;
        }
    }
    //Creazione di un header renderer per tutte le altre colonne che non sono l'ultima
    public class DefaultHeaderRenderer extends JLabel implements TableCellRenderer {
 
        public DefaultHeaderRenderer() {
            setOpaque(true);
            setBackground(new Color(245,171,53));
            Border b = BorderFactory.createCompoundBorder();
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(1,0,0,0,Color.WHITE));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,1,0,0,Color.WHITE));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,0,Color.WHITE));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,1,Color.WHITE));
            setBorder(b);
            Dimension d=this.getPreferredSize();
            d.height=40;
            this.setPreferredSize(d);
            this.setHorizontalAlignment(SwingConstants.CENTER);
        }
     
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
 
    }
    
    /**
     * Creates new form SelectActivityGUI
     */
    public SelectActivityGUI(Planner p) {
        planner=p;
        initComponents();
        this.setLocationRelativeTo(null);
    }
    private SelectActivityGUI() {
        planner=new Planner();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                JComponent c = (JComponent)super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row))
                if(column!=4){
                    c.setBackground(row % 2 == 0 ? new Color(255, 230, 240) : new Color(255, 240, 255));
                    Border b = BorderFactory.createCompoundBorder();
                    b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(1,0,0,0,Color.WHITE));
                    b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,1,0,0,Color.WHITE));
                    b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,1,0,Color.WHITE));
                    b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,1,Color.WHITE));
                    c.setBorder(b);
                }
                return c;
            }
        };
        closeButton1 = new CommonComponents.CloseButton();
        minimizeButton1 = new CommonComponents.MinimizeButton();
        weekNumber2 = new CommonComponents.WeekNumber();
        labelForWeekNumber2 = new CommonComponents.LabelForWeekNumber();
        operationButton1 = new CommonComponents.OperationButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(245, 171, 53));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 300));

        jScrollPane1.getViewport().setBackground(new Color(245,171,53));
        jScrollPane1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jScrollPane1.setBackground(new java.awt.Color(245, 171, 53));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(400, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 300));

        jTable1.setBackground(new java.awt.Color(245, 171, 53));
        jTable1.setModel(new MyTableModel(
            new String [] {
                "ID", "AREA", "TYPE","<html><div style = 'text-align: center'>Estimated intervention<br> time [min]</div></html>",""
            },
            planner.getSelectionableAttribute(weekNumber2.getText())
        ));
        //setting dei vari renderer
        jTable1.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        jTable1.getColumnModel().getColumn(4).setHeaderRenderer(new ButtonHeaderRenderer());
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.setDefaultRenderer(JButton.class,new JButtonRenderer());
        jTable1.setDefaultEditor(JButton.class,new JButtonEditor(planner,this));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        int[] columnsWidth = {
            10, 175, 75, 100
        };
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = jTable1.getColumnModel().getColumn(i++);
            column.setPreferredWidth(width);
        }
        jTable1.setRowHeight(20);
        jTable1.setSelectionBackground(new java.awt.Color(255, 102, 0));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        operationButton1.setText("Backto welcome window");
        operationButton1.setFont(new java.awt.Font("Cooper Black", 0, 13)); // NOI18N
        operationButton1.setPreferredSize(new java.awt.Dimension(155, 22));
        operationButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operationButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelForWeekNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(weekNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minimizeButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelForWeekNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weekNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(operationButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Chiusura di questa inferfaccia e apertura di FirtstPagePlannerGUI alla pressione del bottone corrispondente
    private void operationButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operationButton1ActionPerformed
        FirstPagePlannerGUI welcome= new FirstPagePlannerGUI(planner);
        welcome.setVisible(true);
        welcome.pack();
        welcome.setLocationRelativeTo(null);
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
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
            java.util.logging.Logger.getLogger(SelectActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SelectActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SelectActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SelectActivityGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SelectActivityGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CommonComponents.CloseButton closeButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private CommonComponents.LabelForWeekNumber labelForWeekNumber2;
    private CommonComponents.MinimizeButton minimizeButton1;
    private CommonComponents.OperationButton operationButton1;
    private CommonComponents.WeekNumber weekNumber2;
    // End of variables declaration//GEN-END:variables
}
