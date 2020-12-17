/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ActivityAssignment;

/**
 *
 * @author Catello
 * Rappresenta lo stato che può essumere una selezione sulle celle di una tabelle
 */
public interface SelectionState {
    /**
     * Azione svolta nel caso di conferma della selezione
     */
    public void confirmSelection();
    
}
