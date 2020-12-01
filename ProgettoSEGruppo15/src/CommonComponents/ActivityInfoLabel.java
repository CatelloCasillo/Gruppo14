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
public class ActivityInfoLabel extends JLabel {

    public ActivityInfoLabel() {
        super();
        this.setText(" ");
        this.setBackground(new java.awt.Color(80, 80, 80));
        this.setFont(new java.awt.Font("Tahoma", 1, 14));
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(300,50));
    }
 
}
