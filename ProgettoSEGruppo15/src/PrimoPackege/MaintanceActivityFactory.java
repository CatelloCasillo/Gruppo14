/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

/**
 *
 * @author Gabriella
 */
public abstract class MaintanceActivityFactory {
    
    public enum Category {EWO, PLANNED};
    
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
  
    private MaintanceActivity build(Category category, String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week,
             String procedureID,String fileSMP, String maintainerID, String workspace){
        MaintanceActivity a = selectMaintanceActivity(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        return a;
    }
   
    
    protected abstract MaintanceActivity selectMaintanceActivity(Category category, String id, Site site, String typology, 
             String activityDescription, int intervationTime, boolean interruptible, int week,
             String procedureID,String fileSMP, String maintainerID, String workspacenotes);
}