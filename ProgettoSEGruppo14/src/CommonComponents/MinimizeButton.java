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
