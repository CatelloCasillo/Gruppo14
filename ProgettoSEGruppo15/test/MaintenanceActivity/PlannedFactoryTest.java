/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaintenanceActivity;

import MaintenanceActivity.PlannedFactory;
import MaintenanceActivity.PlannedActivity;
import MaintenanceActivity.MaintanceActivityFactory;
import MaintenanceActivity.MaintanceActivity;
import static MaintenanceActivity.MaintanceActivityFactory.Category.PLANNED;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriella
 */
public class PlannedFactoryTest {
    
    public PlannedFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of selectMaintanceActivity method, of class PlannedFactory.
     */
    @Test
    public void testSelectMaintanceActivity() {
        System.out.println("selectMaintanceActivity");
        MaintanceActivityFactory.Category category = PLANNED;
        String id = "000";
        Site site = new Site("site0", "fact","area");
        String typology = "Electrical";
        String activityDescription = "descrizione";
        int intervationTime = 20;
        boolean interruptible = false;
        int week = 10;
        String procedureID = "proc1";
        String fileSMP = "smp";
        String maintainerID = "mant";
        String workspace = "work";
        PlannedFactory instance = new PlannedFactory();
        MaintanceActivity expResult = new PlannedActivity(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        MaintanceActivity result = instance.selectMaintanceActivity(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        assertEquals(expResult, result);
        
    }
    
}
