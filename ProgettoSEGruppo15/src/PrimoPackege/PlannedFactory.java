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
public class PlannedFactory extends MaintanceActivityFactory {

    
    @Override
    protected MaintanceActivity selectMaintanceActivity(MaintanceActivityFactory.Category category, String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String procedureID, String fileSMP, String maintainerID, String workspace) {
      return new PlannedActivity(id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace); 
    }
    
}
