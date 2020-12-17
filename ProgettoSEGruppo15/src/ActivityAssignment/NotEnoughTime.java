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
 * Rappresenta una selezione non valida quando la somma dei minuti rimaneti in ogni cella è minore del tempo necessario 
 * ad eseguire un'attività
 */
public class NotEnoughTime implements SelectionState{

    @Override
    /**
     * Se questa selezione viene confermata viene mostrato una finestra contenete un messaggio per infromare il planner
     * dell'errore commeso sul questo tipo si selezione non valida
     */
    public void confirmSelection() {
        JOptionPane.showMessageDialog(new JPanel(),  "Not enough time","Invalid Selection", JOptionPane.ERROR_MESSAGE);
    }
    
}
