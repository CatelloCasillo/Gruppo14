/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author Catello
 * Classe di utilità che possiede metodi statici che applicano trasformazioni comuni a più JLabel
 */
public class LabelModifier {
    /**
     * Crea un bordo lienare di spessore 1 di colore borderColor e imposta il colore del testo a foregroundColor 
     * @param label JLabel sulla quale si vuole effettuare la trasformazione
     * @param foregroundColor Colore del testo
     * @param borderColor Colore del bordo che si vule applicare
     */
    
    static void buttonstyle(JLabel label, Color foregroundColor, Color borderColor){
        Border label_border= BorderFactory.createMatteBorder(1, 1, 1, 1, borderColor);
        label.setBorder(label_border);
        label.setForeground(foregroundColor);
    }
}
