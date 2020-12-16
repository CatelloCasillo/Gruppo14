/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

import static PrimoPackege.MaintanceActivityFactory.Category.EWO;
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
public class EwoFactoryTest {
    
    public EwoFactoryTest() {
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
     * Test of selectMaintanceActivity method, of class EwoFactory.
     */
    @Test
    public void testSelectMaintanceActivity() {
        System.out.println("selectMaintanceActivity");
        MaintanceActivityFactory.Category category = EWO;
        String id = "300";
        Site site = new Site("site0", "fact","area");
        String typology = "Electrical";
        String activityDescription = "descrizione";
        int intervationTime = 20;
        boolean interruptible = false;
        int week = 30;
        String procedureID = "proc1";
        String fileSMP ="smp";
        String maintainerID = "mant";
        String workspace = "work";
        EwoFactory instance = new EwoFactory();
        MaintanceActivity expResult = new EwoActivity(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        MaintanceActivity result = instance.selectMaintanceActivity(category, id, site, typology, activityDescription, intervationTime, interruptible, week, procedureID, fileSMP, maintainerID, workspace);
        assertEquals(expResult, result);
        
    }
    
}
