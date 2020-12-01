/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author User
 */
public class ForwardButton extends JButton {

    public ForwardButton() {
        super();
        this.setText("FORWARD");
        this.setBackground(new java.awt.Color(80, 80, 80));
        this.setFont(new java.awt.Font("Tahoma", 1, 14));
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(300,50));
        this.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    
}
