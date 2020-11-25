
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JComponent;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

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
    private class TimeHeaderRenderer extends JTextPane implements TableCellRenderer {
 
        public TimeHeaderRenderer() {
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
            StyledDocument doc = this.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            SimpleAttributeSet background = new SimpleAttributeSet();
            StyleConstants.setBackground(background, new Color(245,171,53));
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            doc.setParagraphAttributes(0, doc.getLength(),background, false);
        }
     
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            return this;
        }
 
    }
    private int getCurrentWeekNumenber(){
        Calendar calendar=new GregorianCalendar();
        Date currentDate = new Date();
        calendar.setTime(currentDate);
        return calendar.get(calendar.WEEK_OF_YEAR);
    }
 
    /**
     * Creates new form SelectActivityGUI
     */
    public SelectActivityGUI() {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                JComponent c = (JComponent)super.prepareRenderer(renderer, row, column);

                //  Alternate row color

                if (!isRowSelected(row))
                if(column!=4){
                    c.setBackground(row % 2 == 0 ? new Color(255, 230, 240) : new Color(255, 240, 255));
                    /*Border outside = new MatteBorder(1, 0, 1, 0, Color.WHITE);
                    Border inside = new EmptyBorder(0, 1, 0, 1);
                    Border highlight = new CompoundBorder(outside, inside);*/
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));

        jPanel1.setBackground(new java.awt.Color(245, 171, 53));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 300));

        jScrollPane1.getViewport().setBackground(new Color(245,171,53));
        jScrollPane1.setBackground(new java.awt.Color(245, 171, 53));
        jScrollPane1.setBorder(null);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 300));

        jTable1.setBackground(new java.awt.Color(245, 171, 53));
        jTable1.setModel(new MyTableModel(
            new String [] {
                "ID", "AREA", "TYPE","Estimated intervention \ntime [min]",""
            },
            new Object [][] {
                {"", "", "","", new JButton("Select")},
                {"", "", "","", new JButton("Select")},
                {"", "", "","", new JButton("Select")},
                {"", "", "","", new JButton("Select")}
            }
        ));
        jTable1.getTableHeader().setDefaultRenderer(new DefaultHeaderRenderer());
        jTable1.getColumnModel().getColumn(4).setHeaderRenderer(new ButtonHeaderRenderer());
        jTable1.getColumnModel().getColumn(3).setHeaderRenderer(new TimeHeaderRenderer());
        jTable1.setDefaultRenderer(JButton.class,new JButtonRenderer());
        jTable1.setDefaultEditor(JButton.class,new JButtonEditor());
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

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Week n°");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(80, 80, 80));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setOpaque(true);
        jLabel2.setText(""+getCurrentWeekNumenber());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
