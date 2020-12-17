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
 * Una label utilizzata come base per realizzare bottoni di minimizzazione e chiusura dell'applicazione 
 * personalizzati. <p>
 * In particolare la label ottenuta avrà un font di dimensione 18, il testo contenuto al suo
 * interno viene allineato orizzontalmente, ha un bordo lineare nero di spessore 1 e quando il cursore 
 * del muose di sovrapporà alla label diventerà una mano
 */
public class BaseLabelButton extends JLabel{
    public BaseLabelButton(){
        super();
        initComponent();
    }
    
    public BaseLabelButton(String label){
       super(label);
       initComponent();
    }
       
    private void initComponent(){
        this.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}
