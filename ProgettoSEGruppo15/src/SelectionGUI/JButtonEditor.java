package SelectionGUI;



import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Catello
 */
public class JButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener{
    private JButton button;
    private Object editorValue;
    private JTable table;
    public JButtonEditor() {
        this.button = new JButton("Select");
        button = new JButton();
        button.setActionCommand("edit");
        button.addActionListener(this);
        button.setBorderPainted(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        editorValue=o;
        table=jtable;
        return button; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getCellEditorValue() {
        return editorValue; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        button.setText("Selezionato");
        button.setBackground(new Color(80,80,80));
        button.setForeground(Color.white);
        int row= table.getSelectedRow();
        int column = 0;
        JOptionPane.showMessageDialog(new JFrame(), "Id attività selezionata: "+table.getModel().getValueAt(row, column).toString(), "Errore", JOptionPane.ERROR_MESSAGE);
    }
    
}
