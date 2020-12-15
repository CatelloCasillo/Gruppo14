/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

/**
 *
 * @author User
 */
public interface RepositoryActivityInterface {
    public ResultSet getInformationOfMaintenanceActivity();
    public String getActivityID(ResultSet rst);
    public String getActivityDescription(ResultSet rst);
    public int getActivityInterventionTime(ResultSet rst);
    public boolean isInterruptibleActivity(ResultSet rst);
    public int getActivityWeekNumber(ResultSet rst);
    public String getActivityTypology(ResultSet rst);
    public String getWorkSpacenotes(ResultSet rst);
    public String getPlannedActivity(ResultSet rst);
    public boolean insertNewMaintenanceActivity(String activityID, String siteID,
            String activityDescription,int activityInterventionTime,boolean interruptibleActivity,
            int activityWeekNumber, String activityWorkspaceNotes,String activityTypology, String typeactivity);
    public boolean deleteMaintenanceActivity(String activityID);
    public boolean updateMaintenanceActivity(String id,String site,String typology, String description, 
            int time, boolean inter, int week);
    public ResultSet getCompetencesOfActivity(String activityID);
    public boolean updateWorkspceNotes(String activityID, String activityWorkspaceNotes);
    
}
