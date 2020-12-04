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
 */
public class BaseMouseListener extends MouseAdapter{
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
