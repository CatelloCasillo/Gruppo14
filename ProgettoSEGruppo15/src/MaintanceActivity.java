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
    
    //attributi aggiunti(Enrico)
    Site site;
    int weekNumber;
    int intervationTime;
    //String materials;
    String procedureID;
    String fileSMP;
    


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
    public MaintanceActivity(String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week) {
        this.id = id;
        this.site = site;
        this.typology = typology;
        this.activityDescription = activityDescription;
        this.intervationTime = intervationTime;
        this.interruptible = interruptible;
        this.weekNumber = week;
        this.fileSMP=null;
        this.procedureID=null;
     
    
    }

    public String getId() {
        return id;
    }

 
    public String getTypology() {
        return typology;
    }

    public String getActivityDescription() {
        return activityDescription;
    }


    public boolean isInterruptible() {
        return interruptible;
    }

    public Site getSite() {
        return site;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public int getIntervationTime() {
        return intervationTime;
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

  
}
