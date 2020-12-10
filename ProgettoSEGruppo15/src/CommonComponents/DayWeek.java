/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CommonComponents;

import java.awt.Dimension;
import java.time.LocalDate;
import java.time.ZoneId;
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
        this.initComponent(LocalDate.now());
    }
    public DayWeek(LocalDate date) {
        super();
        this.initComponent(date);
    }
    private int getCurrentNumberDay(LocalDate date){
       Calendar calendar=new GregorianCalendar();
       ZoneId defaultZoneId = ZoneId.systemDefault();
        calendar.setTime(Date.from(date.atStartOfDay(defaultZoneId).toInstant()));
        return calendar.get(calendar.DAY_OF_MONTH);
    }
    private void initComponent(LocalDate date){
        this.setBackground(new java.awt.Color(80, 80, 80));
        this.setFont(new java.awt.Font("Tahoma", 1, 14));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(37,37));
        this.setText(""+getCurrentNumberDay(date));
    }
    public void setDayWeek(LocalDate date){
        this.setText(""+getCurrentNumberDay(date));
    }
}
