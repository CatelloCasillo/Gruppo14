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
 * Rappresenta una selezione che comprende anche una o più celle che non corrispondono a fasce orarie
 */
public class NoMinutesCells implements SelectionState{
     /** Se questa selezione viene confermata viene mostrato una finestra contenete un messaggio per infromare il planner
     * dell'errore commeso sul questo tipo si selezione non valida
     */ 
    @Override
    public void confirmSelection() {
        JOptionPane.showMessageDialog(new JPanel(),  "You must select the cells that contain time ","Invalid Selection", JOptionPane.ERROR_MESSAGE);
    }
    
}
