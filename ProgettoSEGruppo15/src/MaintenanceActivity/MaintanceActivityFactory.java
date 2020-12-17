/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaintenanceActivity;

import MaintenanceActivity.MaintanceActivity;

/**
 *
 * @author Gabriella
 */
public abstract class MaintanceActivityFactory {
    
    public enum Category {EWO, PLANNED};
    
    /**
     * This is a static method that returns correct Maintance activity according to "Category"
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
     * @return the right instance of activity
     */
    
    public static MaintanceActivity make(Category category, String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week,
             String procedureID,String fileSMP, String maintainerID, String workspace){
        MaintanceActivityFactory factory= null;
        if (category == Category.PLANNED){
            factory = new PlannedFactory();
        }
        else {
            factory= new EwoFactory();
        }
    return factory.build(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
}
  
    /**
     * This method build the correct maintance activity
     * @param category (enum) indicates the type of maintenance activity
     * @param id (String) indicates the id ofactivity to instantiate it
     * @param site (Site) indicates the site of activity to instantiate it
     * @param typology (String) indicates the typology of activity to instantiate it
     * @param activityDescription (String) indicates the description of activity to instantiate it
     * @param intervationTime (int) indicates the required time of activity to instantiate it
     * @param interruptible (boolean) indicates if activity is interruptible to instantiate it
     * @param week (int) indicates the week of activity to instantiate it
     * @param procedureID (String) indicates the procedure of activity to instantiate it
     * @param fileSMP (String) indicates the fileSMP associated to activity to instantiate it
     * @param maintainerID (String) indicates the maintainer of activity to instantiate it
     * @param workspace (String) indicates the workspace of activity to instantiate it
     * @return the right instance of activity
     */
    private MaintanceActivity build(Category category, String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week,
             String procedureID,String fileSMP, String maintainerID, String workspace){
        MaintanceActivity a = selectMaintanceActivity(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        return a;
    }
   
    /**
     * This method return the instance of activity factory class that 
     * extends MaintenanceActivityFactory and implements
     * selectMaintenanceActivity
     *
     * @param category (enum) indicates the type of maintenance activity
     * @param id (String) indicates the id ofactivity to instantiate it
     * @param site (Site) indicates the site of activity to instantiate it
     * @param typology (String) indicates the typology of activity to instantiate it
     * @param activityDescription (String) indicates the description of activity to instantiate it
     * @param intervationTime (int) indicates the required time of activity to instantiate it
     * @param interruptible (boolean) indicates if activity is interruptible to instantiate it
     * @param week (int) indicates the week of activity to instantiate it
     * @param procedureID (String) indicates the procedure of activity to instantiate it
     * @param fileSMP (String) indicates the fileSMP associated to activity to instantiate it
     * @param maintainerID (String) indicates the maintainer of activity to instantiate it
     * @param workspacenotes (String) indicates the workspace of activity to instantiate it
     * @return the right instance of activity
     */
    protected abstract MaintanceActivity selectMaintanceActivity(Category category, String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week,
             String procedureID,String fileSMP, String maintainerID, String workspacenotes);
}