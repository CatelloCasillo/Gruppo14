
import PrimoPackege.MaintanceActivity;
import PrimoPackege.Site;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gabriella
 */
public class EwoActivity extends MaintanceActivity {
    
    public EwoActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int weekNumber,String procedureID, String fileSMP, String maintainerID, String workspacenotes) {
        super(id, site, typology, activityDescription, intervationTime, interruptible, weekNumber,procedureID ,fileSMP ,maintainerID, workspacenotes); 
    }
 
}
