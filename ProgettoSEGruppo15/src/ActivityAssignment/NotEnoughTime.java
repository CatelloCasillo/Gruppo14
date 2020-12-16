/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivityAssignment;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Catello
 */
public class NotEnoughTime implements SelectionState{

    @Override
    public void confirmSelection() {
        JOptionPane.showMessageDialog(new JPanel(),  "Not enough time","Invalid Selection", JOptionPane.ERROR_MESSAGE);
    }
    
}
