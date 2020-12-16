/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

import PrimoPackege.MaintanceActivityFactory.Category;
import static PrimoPackege.MaintanceActivityFactory.Category.EWO;
import static PrimoPackege.MaintanceActivityFactory.Category.PLANNED;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class MaintanceActivityFactoryTest {
    
    public MaintanceActivityFactoryTest() {
    }

    /**
     * Test of make method, of class MaintanceActivityFactory to create a planned activity
     */
    @Test
    public void testMakePlannedActivity() {
        System.out.println("make");
        MaintanceActivityFactory.Category category = PLANNED;
        String id = "000";
        Site site = new Site("site0", "fact","area");
        String typology = "Electrical";
        String activityDescription = "descrizione";
        int intervationTime = 20;
        boolean interruptible = false;
        int week = 20;
        String procedureID = "proc";
        String fileSMP = "smp";
        String maintainerID = "mant";
        String workspace = "work";
        MaintanceActivity expResult = createActivity(PLANNED, "000",new Site("site0", "fact","area"), "Electrical", "descrizione", 20, false, 20, "proc", "smp", "mant", "work");
        MaintanceActivity result = MaintanceActivityFactory.make(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of make method, of class MaintanceActivityFactory to create an Ewo Activity
     */
    
    @Test
    public void testMakeEwoActivity() {
        System.out.println("make");
        MaintanceActivityFactory.Category category = EWO;
        String id = "000";
        Site site = new Site("site0", "fact","area");
        String typology = "Electrical";
        String activityDescription = "descrizione";
        int intervationTime = 20;
        boolean interruptible = false;
        int week = 20;
        String procedureID = "proc";
        String fileSMP = "smp";
        String maintainerID = "mant";
        String workspace = "work";
        MaintanceActivity expResult = createActivity(EWO, "000",new Site("site0", "fact","area"), "Electrical", "descrizione", 20, false, 20, "proc", "smp", "mant", "work");
        MaintanceActivity result = MaintanceActivityFactory.make(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        assertEquals(expResult, result);
    }

    /**
     * Method to create an activity
     * @param category
     * @param id
     * @param site
     * @param typology
     * @param activityDescription
     * @param intervationTime
     * @param interruptible
     * @param week
     * @param procedureID
     * @param fileSMP
     * @param maintainerID
     * @param workspace
     * @return activity
     */
    
    private MaintanceActivity createActivity(Category category, String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String procedureID, String fileSMP, String maintainerID, String workspace){
        MaintanceActivity activity= null;
        if(category.equals(MaintanceActivityFactory.Category.PLANNED)){
            activity= new PlannedActivity(category,id, site, typology,activityDescription,intervationTime, interruptible, week,procedureID,fileSMP, maintainerID, workspace );
            
        }else{
            activity= new EwoActivity(category,id, site, typology,activityDescription,intervationTime, interruptible, week,procedureID,fileSMP, maintainerID, workspace );
            
        }
        return activity;
    }
}
