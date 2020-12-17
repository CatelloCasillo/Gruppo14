/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;

/**
 *
 * @author Catello
 * Una Listener utilizzato come base per realizzare gestire gli eventi sui bottoni di minimizzazione e chiusura dell'applicazione 
 * personalizzati. All'entrata del muose il componente diventerà bianco mentre alla sua uscità diventerà nero cambiando
 * anche il colore del bordo
 */
public class BaseMouseListener extends MouseAdapter{
    /**
     * 
     */
    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        JLabel label=(JLabel)evt.getSource();
        LabelModifier.buttonstyle(label, Color.white, Color.white);
    }
    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        JLabel label=(JLabel)evt.getSource();
        LabelModifier.buttonstyle(label, Color.black, Color.black);
    }
}
