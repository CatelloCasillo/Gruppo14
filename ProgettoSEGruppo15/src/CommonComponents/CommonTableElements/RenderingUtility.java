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
 * Classe che contiene metodi di utili utili a oggetti renderer differenti
 */
public class RenderingUtility {
    /**
     * Restituisce un colore a seconda di percentage passato
     * @param percentage
     * @return Verde scuro se percetage è compreso fra 100(incluso) e 80 (escluso)
     * Verde chiaro se è compreso fra 80(incluso) e 50(escluso)
     * Giallo se è compreso fra 50(incluso) e 20(escluso)
     * Arancione  se è compreso fra 20(incluso) e 0(escluso)
     * Rosso se è zero
     * Per tutti gli altri valori di percentege viene restituito biaco
     */ 
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
