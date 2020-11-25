
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
    
    
    public JButtonEditor() {
        this.button = new JButton("Select");
        button = new JButton();
        button.setActionCommand("edit");
        button.addActionListener(this);
        button.setBorderPainted(false);
       
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        editorValue=o;
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
        JOptionPane.showMessageDialog(new JFrame(), "Serve l'interfaccia di Niccolò", "Errore", JOptionPane.ERROR_MESSAGE);
    }

   
    
    
    

    
    
}
