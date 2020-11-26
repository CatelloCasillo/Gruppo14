package PrimoPackege;

import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriella
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

    public String getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public String getTypology() {
        return typology;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public String getIntervationTime() {
        return intervationTime;
    }

    public boolean isInterruptible() {
        return interruptible;
    }

    public String getWeek() {
        return week;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public void setIntervationTime(String intervationTime) {
        this.intervationTime = intervationTime;
    }

    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }

    public void setWeek(String week) {
        this.week = week;
    }
    
    
}
