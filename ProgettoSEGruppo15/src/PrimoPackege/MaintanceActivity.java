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

    public void setSite(Site site) {
        this.site = site;
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
    
    public String getWorkspacenotes() {
        return workspacenotes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory(){    
        return category;
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

    public String getMaintainerID() {
        return maintainerID;
    }

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
