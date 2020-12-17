/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivityAssignment;



/**
 *
 * @author Catello
 * Rappresenta una selezione non ancora effettuata
 */
public class NoSelection implements SelectionState{
    /**
     * Non avendo ancora effettuato la selezione questa non risuta ne giusta ne sbagliata quindi se il planner conferma
     * questo tipo di selezione semplicemente non succede nulla
     */
    @Override
    public void confirmSelection() {
        return;
    }
    
}