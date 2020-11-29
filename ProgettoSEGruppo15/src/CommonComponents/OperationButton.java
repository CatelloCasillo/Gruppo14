/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author Catello
 */
public class OperationButton extends JButton{

    public OperationButton() {
        super();
        this.setBackground(new java.awt.Color(211, 84, 0));
        this.setFont(new java.awt.Font("Cooper Black", 0, 11)); 
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
    }
    
}
