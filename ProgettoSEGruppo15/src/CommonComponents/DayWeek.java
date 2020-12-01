/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

/**
 *
 * @author User
 */
public class DayWeek extends JLabel {

    public DayWeek() {
        super();
        this.setBackground(new java.awt.Color(80, 80, 80));
        this.setFont(new java.awt.Font("Tahoma", 1, 14));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(37,37));
        this.setText(""+getCurrentNumberDay());
    
}
    private int getCurrentNumberDay(){
       Calendar calendar=new GregorianCalendar();
        Date currentDate = new Date();
        calendar.setTime(currentDate);
        return calendar.get(calendar.DAY_OF_YEAR);
    }
}
