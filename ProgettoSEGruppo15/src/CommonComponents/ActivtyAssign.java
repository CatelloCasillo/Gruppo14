/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author Gabriella
 */
public class ActivtyAssign extends JLabel {

    public ActivtyAssign() {
        super();
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText("Activity to assign");
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(137,60));
    }
    
}
