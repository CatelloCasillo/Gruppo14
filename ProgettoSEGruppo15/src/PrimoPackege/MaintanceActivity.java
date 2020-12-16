package PrimoPackege;

import PrimoPackege.MaintanceActivityFactory.Category;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriella
 */
public abstract class MaintanceActivity {
    
    private String id;
    private String typology;
    private String activityDescription;
    private boolean interruptible;
    private Site site;
    private int weekNumber;
    private int intervationTime;
    private String workspacenotes;
    private String procedureID;
    private String fileSMP;
    private String maintainerID;
    private Category category;

/**
 * 
 * @param category (enum) indicates the type of maintenance activity
 * @param id(String) indicates the id ofactivity to instantiate it
 * @param site(Site) indicates the site of activity to instantiate it
 * @param typology (String) indicates the typology of activity to instantiate it
 * @param activityDescription (String) indicates the description of activity to instantiate it
 * @param intervationTime (int) indicates the required time of activity to instantiate it
 * @param interruptible (boolean) indicates if activity is interruptible to instantiate it
 * @param week (int) indicates the week of activity to instantiate it
 * @param procedureID(String) indicates the procedure of activity to instantiate it
 * @param fileSMP (String) indicates the fileSMP associated to activity to instantiate it
 * @param maintainerID (String) indicates the maintainer of activity to instantiate it
 * @param workspace(Strring) indicates the workspace of activity to instantiate it
 */  

    public MaintanceActivity(Category category, String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String procedureID, String fileSMP, String maintainerID, String workspace) {
        this.category= category;
        this.id = id;
        this.site = site;
        this.typology = typology;
        this.activityDescription = activityDescription;
        this.intervationTime = intervationTime;
        this.interruptible = interruptible;
        this.weekNumber = week;
        this.fileSMP=fileSMP;
        this.procedureID=procedureID;
        this.maintainerID= maintainerID;
        this.workspacenotes= workspace;
    }
/**
 * 
 * @param site object Site to be set
 */
    public void setSite(Site site) {
        this.site = site;
    }
    
/**
 * 
 * @return id of an activity
 */    
    public String getId() {
        return id;
    }
/**
 * 
 * @return obcject site
 */
    public Site getSite() {
        return site;
    }
/**
 * 
 * @return typology of activity
 */
    public String getTypology() {
        return typology;
    }
/**
 * 
 * @return activity description of activity
 */
    public String getActivityDescription() {
        return activityDescription;
    }
/**
 * 
 * @return intervation time of an activity
 */
    public int getIntervationTime() {
        return intervationTime;
    }
/**
 * 
 * @return true if an activity is interruptible, false otherwise 
 */
    public boolean isInterruptible() {
        return interruptible;
    }
/**
 * 
 * @return week of an activity
 */
    public int getWeekNumber() {
        return weekNumber;
    }
 /**
  * 
  * @return workspace of an activity
  */   
    public String getWorkspacenotes() {
        return workspacenotes;
    }
 /**
  * 
  * @return category of activity
  */   
    public Category getCategory(){    
        return category;
    }
/**
 * 
 * @param typology typology of activity to be set
 */    
    public void setTypology(String typology) {
        this.typology = typology;
    }
/**
 * 
 * @param activityDescription description of activity to be set
 */
    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
/**
 * 
 * @param intervationTime intervention time of activity to be set
 */
    public void setIntervationTime(int intervationTime) {
        this.intervationTime = intervationTime;
    }
/**
 * 
 * @param interruptible 
 */
    public void setInterruptible(boolean interruptible) {
        this.interruptible = interruptible;
    }
/**
 * 
 * @param week week of activity to be set
 */
    public void setWeek(int week) {
        this.weekNumber = week;
    }
/**
 * 
 * @return id of procedure associated to activity
 */
     public String getProcedureID() {
        return procedureID;
    }
/**
 * 
 * @return name of file associated to activity
 */
    public String getFileSMP() {
        return fileSMP;
    }
/**
 * 
 * @param procedureID 
 */
    public void setProcedureID(String procedureID) {
        this.procedureID = procedureID;
    }
/**
 * 
 * @param fileSMP 
 */
    public void setFileSMP(String fileSMP) {
        this.fileSMP = fileSMP;
    }
/**
 * 
 * @return id of maintainer associated to activity
 */
    public String getMaintainerID() {
        return maintainerID;
    }
/**
 * 
 * @param maintainerID id of maintainer to be set
 */
    public void setMaintainerID(String maintainerID) {
        this.maintainerID = maintainerID;
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
        if (this.interruptible != other.interruptible) {
            return false;
        }
        if (this.weekNumber != other.weekNumber) {
            return false;
        }
        if (this.intervationTime != other.intervationTime) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.typology, other.typology)) {
            return false;
        }
        if (!Objects.equals(this.activityDescription, other.activityDescription)) {
            return false;
        }
        if (!Objects.equals(this.workspacenotes, other.workspacenotes)) {
            return false;
        }
        if (!Objects.equals(this.procedureID, other.procedureID)) {
            return false;
        }
        if (!Objects.equals(this.fileSMP, other.fileSMP)) {
            return false;
        }
        if (!Objects.equals(this.site, other.site)) {
            return false;
        }
        return true;
    }

    public void setWorkspacenotes(String workspacenotes) {
        this.workspacenotes = workspacenotes;
    }
    
    
    

}