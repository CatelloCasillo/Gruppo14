/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Catello
 *
 * Una label il cui funzionamento realizza il bottone di minimizzazione dell'applicazione personalizzato. 
 * In particolare la label ottenuta avrà un font di dimensione 18, contiene un "-" al suo
 * interno il quale è orizzontalmente centrato.
 * <p>
 * Quando il cursore del muose si sovrapporà alla label diventerà una
 * mano e la label diventa di colore bianco. All'uscita del cursore label avrà nuovamente il suo colore originale.
 * <p>
 * Al click della Label l'applicazione verrà ridotta ad icona
 */
public class MinimizeButton extends BaseLabelButton{
     public MinimizeButton(){
        super();
        this.setText("-");
        this.addMouseListener(new BaseMouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {            
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(MinimizeButton.this);
                topFrame.setState(JFrame.ICONIFIED);
            }
        });
    }
}
