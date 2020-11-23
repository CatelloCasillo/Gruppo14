/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class MaintanceActivity {
    
    String id;
    String site;
    String typology;
    String activityDescription;
    String intervationTime;
    boolean interruptible;
    String week;

    public MaintanceActivity(String id, String site, String typology, String activityDescription, String intervationTime, boolean interruptible, String week) {
        this.id = id;
        this.site = site;
        this.typology = typology;
        this.activityDescription = activityDescription;
        this.intervationTime = intervationTime;
        this.interruptible = interruptible;
        this.week = week;
        
    }

    
    
}
