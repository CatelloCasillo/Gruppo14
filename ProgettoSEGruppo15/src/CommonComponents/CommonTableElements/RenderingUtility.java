/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents.CommonTableElements;

import java.awt.Color;

/**
 *
 * @author Catello
 */
public class RenderingUtility {
     
    public static Color colorPicker(int percentage){
        if(percentage<=100 && percentage>80)
            return new Color(0,153,0);
        if(percentage<=80 && percentage>50)
            return new Color(166,255,77);
        if(percentage<=50 && percentage>20)
            return new Color(255,255,0);
        if(percentage<=20 && percentage>0)
            return new Color(255,153,51);
        if(percentage==0)
            return new Color(255,77,77);
        return new Color(255,255,255);
        
    }
}
