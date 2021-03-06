package SelectionGUI;

import Navigator.Navigator;
import Planner.PlannerInterface;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Catello
 * Editor di una JTable che contiene nella sua quinta colonna dei JButton
 * in particolare questo editor fa si che il bottone sia cliccabile e
 * posso produrre l'evento relativo al click del muose
 */

public class JButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener{
    private JButton button;
    private Object editorValue;
    private JTable table;
    private PlannerInterface planner;
    private JFrame frame;
    public JButtonEditor(PlannerInterface p, JFrame frame) {
        this.frame=frame;
        this.planner=p;
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
        return button; 
    }

    @Override
    public Object getCellEditorValue() {
        return editorValue;
    }
    /**
     * Gestisce l'evento generato dalla pressione della cella.
     * Evidenzia la cella selezionata per poi recuperare la prima cella della riga selezionata
     * per poi istanziare le finestra successiva.
     * @param ae evento che rappresenta la pressione del bottone 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        button.setText("Selezionato");
        button.setBackground(new Color(80,80,80));
        button.setForeground(Color.white);
        int row= table.getSelectedRow();
        String id=table.getModel().getValueAt(row, 0).toString();
        Navigator nav=Navigator.getInstance(planner);
        nav.changeToVerifyActivityWindow(frame, id);
        /*String site= table.getModel().getValueAt(row, 1).toString();
        String typology = table.getModel().getValueAt(row, 2).toString();
        int time=parseInt(table.getModel().getValueAt(row, 3).toString().trim());
        /*VerifyActivityGUI verify= new VerifyActivityGUI(planner, id.trim(), time, site,typology);
        verify.setVisible(true);
        verify.pack();
        verify.setLocationRelativeTo(null);
        verify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.dispose();*/
    }
    
}
