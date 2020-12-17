/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author Catello
 * Una label il cui funzionamento realizza il bottone di chiusura dell'applicazione personalizzato. 
 * In particolare la label ottenuta avrà un font di dimensione 18, contiene una "X" al suo
 * interno la quale viene allineato centralmente.
 * <p>
 * Quando il cursore del muose si sovrapporà alla label diventerà una
 * mano e la label diventa di colore bianco. All'uscita del cursore label avrà nuovamente il suo colore originale.
 * <p>
 * Al click della Label l'applicazione verrà chiusa con esito postivo
 */
public class CloseButton extends BaseLabelButton{
    
    public CloseButton(){
        super();
        this.setText("X");
        this.addMouseListener(new BaseMouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(0);
            }
        });
    }
}
