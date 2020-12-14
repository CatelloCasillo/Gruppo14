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
public class PlannedActivity extends MaintanceActivity {
    /*
    public PlannedActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int weekNumber, String workspacenotes) {
        super(id, site, typology, activityDescription, intervationTime, interruptible, weekNumber, workspacenotes);
    }

    public PlannedActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String procedureID, String fileSMP) {
        super(id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP);
    }*/
    public PlannedActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String procedureID, String fileSMP, String maintainerID, String workspace) {
        super(id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
    }

}
