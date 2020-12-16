/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

public interface RepositoryActivityInterface {
    /**
     * 
     * @return A resultSet with the information of Activity present on an exsternal Database
     */
    public ResultSet getInformationOfMaintenanceActivity();
    
    /**
     * 
     * @param rst
     * @return The ID of an Activity present in the ResultSet
     */
    public String getActivityID(ResultSet rst);
    
   /**
    * 
    * @param rst
    * @return The description of an Activity present in the ResultSet
    */
    public String getActivityDescription(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The extimated intervention time of an Activity present in the ResultSet
     */
    public int getActivityInterventionTime(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return True, if an activity is interruptible. False otherwise.
     */
    public boolean isInterruptibleActivity(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The week number assigner to an Activity present in the ResultSet
     */
    public int getActivityWeekNumber(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The typology of an Activity present in the ResultSet
     */
    public String getActivityTypology(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The work space notes of an Activity present in the ResultSet
     */
    public String getWorkSpacenotes(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return PLANNED if an Activity is a planned one.
     */
    public String getPlannedActivity(ResultSet rst);
    
    /**
     * 
     * @param activityID
     * @param siteID
     * @param activityDescription
     * @param activityInterventionTime
     * @param interruptibleActivity
     * @param activityWeekNumber
     * @param activityWorkspaceNotes
     * @param activityTypology
     * @param typeactivity
     * @return True if an Activity is correctly insert in the Database, False otherwise.
     */
    public boolean insertNewMaintenanceActivity(String activityID, String siteID,
            String activityDescription, int activityInterventionTime, boolean interruptibleActivity,
            int activityWeekNumber, String activityWorkspaceNotes, String activityTypology, String typeactivity);
    
    /**
     * 
     * @param activityID
     * @return True if an activity is correctly deleted, False otherwise.
     */
    public boolean deleteMaintenanceActivity(String activityID);
    
    /**
     * 
     * @param id
     * @param site
     * @param typology
     * @param description
     * @param time
     * @param inter
     * @param week
     * @return True if an Activity is correctly updated, False otherwise.
     */
    public boolean updateMaintenanceActivity(String id, String site, String typology, String description,
            int time, boolean inter, int week);
    
    /**
     * 
     * @param activityID
     * @return A ResultSet that cointains all the competences required for the specified Activity.
     */
    public ResultSet getCompetencesOfActivity(String activityID);
    
    /**
     * 
     * @param activityID
     * @param activityWorkspaceNotes
     * @return True, if the workspace notes of the specified Activity are correctly updated. False otherwise.
     */
    public boolean updateWorkspceNotes(String activityID, String activityWorkspaceNotes);

}
