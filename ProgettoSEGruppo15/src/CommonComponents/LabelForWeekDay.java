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
public class LabelForWeekDay extends JLabel {

    public LabelForWeekDay() {
    super();
        this.setBackground(new java.awt.Color(255, 255, 255));
        this.setFont(new java.awt.Font("Tahoma", 0, 14)); 
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setText(""+getCurrentNumberDay());
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(168,37));
}
    private String getCurrentNumberDay(){
       Calendar calendar=new GregorianCalendar();
        Date currentDate = new Date();
        calendar.setTime(currentDate);
        int p= calendar.get(calendar.DAY_OF_WEEK);
        if(p== 2)
            return "Monday";
        if(p==3)
            return "Tuesday";
        if(p==4)
            return "Wednsday";
        if(p==5)
            return "Thursday";
        if(p==6)
            return "Friday";
        if(p==7)
            return "Saturday";
        return "Sunday";
    }
}
