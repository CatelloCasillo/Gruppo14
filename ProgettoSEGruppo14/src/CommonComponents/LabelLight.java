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
public class LabelLight extends JLabel {

    public LabelLight() {
        super();
        this.setBackground(new java.awt.Color(245,171,53));
        this.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText("Workspace Notes");
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(200,50));
    }
     
     
}
