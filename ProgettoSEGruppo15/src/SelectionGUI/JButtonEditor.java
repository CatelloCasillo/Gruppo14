package SelectionGUI;

import Navigator.Navigator;
import PrimoPackege.Planner;
import PrimoPackege.PlannerInterface;
import VerifyGUI.VerifyActivityGUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Integer.parseInt;
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
//editor che permette ai bottoni di essere cliccabili e di aprire l'interfaccia successiva a click avvenuto
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
