/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

public interface RepositoryActivityInterface {
    /**
     * Execute a query on the connected DBMS to retrive some information of the Maintenance Activities. It is usefull to build MaintenanceActivity object.
     * @return A resultSet with the information of Activity present on an exsternal Database
     */
    public ResultSet getInformationOfMaintenanceActivity();
    
    /**
     * 
     * @param rst A ResultSet that has to contain the ID of an Activity
     * @return The ID of an Activity present in the ResultSet
     */
    public String getActivityID(ResultSet rst);
    
   /**
    * 
    * @param rst A ResultSet that has to contain the description of an Activity
    * @return The description of an Activity present in the ResultSet
    */
    public String getActivityDescription(ResultSet rst);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the eximate intervention time of an Activity
     * @return The extimated intervention time of an Activity present in the ResultSet
     */
    public int getActivityInterventionTime(ResultSet rst);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the parameter of an Activity that specified if it's interruptible.
     * @return True, if an activity is interruptible. False otherwise.
     */
    public boolean isInterruptibleActivity(ResultSet rst);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the week number tha has assigned to an Activity
     * @return The week number assigner to an Activity present in the ResultSet
     */
    public int getActivityWeekNumber(ResultSet rst);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the typology of an Activity
     * @return The typology of an Activity present in the ResultSet
     */
    public String getActivityTypology(ResultSet rst);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the workspace notes of an Activity
     * @return The work space notes of an Activity present in the ResultSet
     */
    public String getWorkSpacenotes(ResultSet rst);
    
    /**
     * 
     * Get from the ResultSet a String that specified if the corresponded Activity is a planned one or not.
     * @param rst A ResultSet that has to contain the parameter:"plannedActivity" of an Activity.
     * @return A String tha is "PLANNED" if an Activity is a planned one, different string otherwise.
     */
    public String getPlannedActivity(ResultSet rst);
    
    /**
     * 
     * @param activityID The new ID of an Activity
     * @param siteID     The ID of a Site already present.
     * @param activityDescription The description of the Activity
     * @param activityInterventionTime The eximated intervention time of the Activity
     * @param interruptibleActivity If the Activity is interruptible or not.
     * @param activityWeekNumber The week number associeted to the Activity
     * @param activityWorkspaceNotes The workspace notes of the Activity
     * @param activityTypology   The typology of the Activity
     * @param typeactivity Represent what type of Activity is. Insert "PLANNED" for a planned Activity.
     * @return True if an Activity is correctly insert in the Database, False otherwise.
     */
    public boolean insertNewMaintenanceActivity(String activityID, String siteID,
            String activityDescription, int activityInterventionTime, boolean interruptibleActivity,
            int activityWeekNumber, String activityWorkspaceNotes, String activityTypology, String typeactivity);
    
    /**
     * Delete an Activity on the DB by giving the ID.
     * @param activityID The ID of the Activity.
     * @return True if an activity is correctly deleted, False otherwise.
     */
    public boolean deleteMaintenanceActivity(String activityID);
    
    /**
     * Update the parameters of Activity that correspond to the ID in the Database.
     * @param id The ID of the Activity.
     * @param site The Site of the Activity.
     * @param typology The Typology of the Activity.
     * @param description The description of the Activity.
     * @param time The eximated intervention time for the Activity
     * @param interruptible If the Activity is interruptible or not.
     * @param week The week number associated to the Activity.
     * @return True if an Activity is correctly updated, False otherwise.
     */
    public boolean updateMaintenanceActivity(String id, String site, String typology, String description,
            int time, boolean interruptible, int week);
    
    /**
     * Get all the competences required by Activity selected by the given ID.
     * @param activityID The Activity ID
     * @return A ResultSet that cointains all the competences required for the specified Activity.
     */
    public ResultSet getCompetencesOfActivity(String activityID);
    
    /**
     * Update on Database of the workspace notes of an Activity selected by the given ID.
     * @param activityID The Activity ID.
     * @param activityWorkspaceNotes The workspace notes of the Activity.
     * @return True, if the workspace notes of the specified Activity are correctly updated. False otherwise.
     */
    public boolean updateWorkspceNotes(String activityID, String activityWorkspaceNotes);

}
