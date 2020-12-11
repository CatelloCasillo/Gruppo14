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
public class EwoActivity extends MaintanceActivity {
    
    public EwoActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int weekNumber, String workspacenotes) {
        super(id, site, typology, activityDescription, intervationTime, interruptible, weekNumber, workspacenotes); 
    }
    
    public Category getCategory(){
        return Category.EWO;
    }
    
    
}
