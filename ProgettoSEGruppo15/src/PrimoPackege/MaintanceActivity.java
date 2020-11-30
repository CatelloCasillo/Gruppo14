package PrimoPackege;

import java.util.Objects;
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
    String typology;
    String activityDescription;
    boolean interruptible;
    Site site;
    int weekNumber;
    int intervationTime;
    String workspacenotes;
    String procedureID;
    String fileSMP;

    public String getWorkspacenotes() {
        return workspacenotes;
    }
    
    //constructor used to create a new maintance activity
    public MaintanceActivity(String id, Site site, String typology, 
            String activityDescription, int intervationTime, boolean interruptible, int weekNumber,
            String workspacenotes) {
        this.id = id;
        this.site = site;
        this.typology = typology;
        this.activityDescription = activityDescription;
        this.intervationTime = intervationTime;
        this.interruptible = interruptible;
        this.weekNumber = weekNumber;
        this.workspacenotes= workspacenotes;
        
    }

    public MaintanceActivity(String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week,
             String procedureID,String fileSMP) {
        this.id = id;
        this.site = site;
        this.typology = typology;
        this.activityDescription = activityDescription;
        this.intervationTime = intervationTime;
        this.interruptible = interruptible;
        this.weekNumber = week;
        this.fileSMP=fileSMP;
        this.procedureID=procedureID;
    
    }
    
    public String getId() {
        return id;
    }

    public Site getSite() {
        return site;
    }

    public String getTypology() {
        return typology;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public int getIntervationTime() {
        return intervationTime;
    }

    public boolean isInterruptible() {
        return interruptible;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public void setTypology(String typology) {
        this.typology = typology;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public void setIntervationTime(int intervationTime) {
        this.intervationTime = intervationTime;
    }

    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }

    public void setWeek(int week) {
        this.weekNumber = week;
    }
     public String getProcedureID() {
        return procedureID;
    }

    public String getFileSMP() {
        return fileSMP;
    }

    public void setProcedureID(String procedureID) {
        this.procedureID = procedureID;
    }

    public void setFileSMP(String fileSMP) {
        this.fileSMP = fileSMP;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MaintanceActivity other = (MaintanceActivity) obj;
        if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    
}
