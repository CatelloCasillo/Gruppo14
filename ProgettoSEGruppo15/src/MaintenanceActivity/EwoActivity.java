/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaintenanceActivity;

import MaintenanceActivity.MaintanceActivity;
import MaintenanceActivity.MaintanceActivityFactory.Category;

/**
 *
 * @author Gabriella
 */
public class EwoActivity extends MaintanceActivity {
    
    public EwoActivity(Category category,String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int weekNumber,String procedureID, String fileSMP, String MaintainerID, String workspacenotes) {
        super(category, id, site, typology, activityDescription, intervationTime, interruptible, weekNumber,procedureID,fileSMP,MaintainerID, workspacenotes); 
}
}
