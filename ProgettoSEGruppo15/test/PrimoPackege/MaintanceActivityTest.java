/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

import static PrimoPackege.MaintanceActivityFactory.Category.PLANNED;
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
public class MaintanceActivityTest {
    MaintanceActivity instance;
    
    public MaintanceActivityTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    /**
     * create an instance of maintanceActivity to Test
     */
    @Before
    public void setUp() {
        instance= new MaintanceActivityImpl(PLANNED, "9999", new Site("site100", "fact", "are"), "Hydraulic", "descrizione", 30, true, 51, "proc01","smp1", "manttest", "workspaceNotesTest" );
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setSite method, of class MaintanceActivity.
     */
    @Test
    public void testSetSite() {
        System.out.println("setSite");
        Site site = null;
        instance.setSite(site);
        assertEquals(site, instance.getSite());
    }

    /**
     * Test of getId method, of class MaintanceActivity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        String expResult = "9999";
        String result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getSite method, of class MaintanceActivity.
     */
    @Test
    public void testGetSite() {
        System.out.println("getSite");
        Site expResult = new Site("site100", "fact", "are");
        Site result = instance.getSite();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getTypology method, of class MaintanceActivity.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        String expResult = "Hydraulic";
        String result = instance.getTypology();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getActivityDescription method, of class MaintanceActivity.
     */
    @Test
    public void testGetActivityDescription() {
        System.out.println("getActivityDescription");
        String expResult = "descrizione";
        String result = instance.getActivityDescription();
        assertEquals(expResult, result);
     
    }

    /**
     * Test of getIntervationTime method, of class MaintanceActivity.
     */
    @Test
    public void testGetIntervationTime() {
        System.out.println("getIntervationTime");
        int expResult = 30;
        int result = instance.getIntervationTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of isInterruptible method, of class MaintanceActivity.
     */
    @Test
    public void testIsInterruptible() {
        System.out.println("isInterruptible");
        boolean expResult = true;
        boolean result = instance.isInterruptible();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeekNumber method, of class MaintanceActivity.
     */
    @Test
    public void testGetWeekNumber() {
        System.out.println("getWeekNumber");
        int expResult = 51;
        int result = instance.getWeekNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWorkspacenotes method, of class MaintanceActivity.
     */
    @Test
    public void testGetWorkspacenotes() {
        System.out.println("workspaceNotesTest");
        String expResult = "workspaceNotesTest";
        String result = instance.getWorkspacenotes();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setId method, of class MaintanceActivity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "000";
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getCategory method, of class MaintanceActivity.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        MaintanceActivityFactory.Category expResult = PLANNED;
        MaintanceActivityFactory.Category result = instance.getCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypology method, of class MaintanceActivity.
     */
    @Test
    public void testSetTypology() {
        System.out.println("setTypology");
        String typology = "Electrical";
        instance.setTypology(typology);
        assertEquals(typology, instance.getTypology());
    }

    /**
     * Test of setActivityDescription method, of class MaintanceActivity.
     */
    @Test
    public void testSetActivityDescription() {
        System.out.println("setActivityDescription");
        String activityDescription = "descrizione1";
        instance.setActivityDescription(activityDescription);
        assertEquals(activityDescription, instance.getActivityDescription());
    }

    /**
     * Test of setIntervationTime method, of class MaintanceActivity.
     */
    @Test
    public void testSetIntervationTime() {
        System.out.println("setIntervationTime");
        int intervationTime = 60;
        instance.setIntervationTime(intervationTime);
        assertEquals(intervationTime, instance.getIntervationTime());
    }

    /**
     * Test of setInterruptible method, of class MaintanceActivity.
     */
    @Test
    public void testSetInterruptible() {
        System.out.println("setInterruptible");
        boolean interruptible = false;
        instance.setInterruptible(interruptible);
        assertEquals(interruptible, instance.isInterruptible());
    }

    /**
     * Test of setWeek method, of class MaintanceActivity.
     */
    @Test
    public void testSetWeek() {
        System.out.println("setWeek");
        int week = 40;
        instance.setWeek(week);
        assertEquals(week, instance.getWeekNumber());
    }

    /**
     * Test of getProcedureID method, of class MaintanceActivity.
     */
    @Test
    public void testGetProcedureID() {
        System.out.println("getProcedureID");
        String expResult = "proc01";
        String result = instance.getProcedureID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFileSMP method, of class MaintanceActivity.
     */
    @Test
    public void testGetFileSMP() {
        System.out.println("getFileSMP");
        String expResult = "smp1";
        String result = instance.getFileSMP();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setProcedureID method, of class MaintanceActivity.
     */
    @Test
    public void testSetProcedureID() {
        System.out.println("setProcedureID");
        String procedureID = "procedura2";
        instance.setProcedureID(procedureID);
        assertEquals(procedureID, instance.getProcedureID());
    }

    /**
     * Test of setFileSMP method, of class MaintanceActivity.
     */
    @Test
    public void testSetFileSMP() {
        System.out.println("setFileSMP");
        String fileSMP = "smp3";
        instance.setFileSMP(fileSMP);
        assertEquals(fileSMP, instance.getFileSMP());
    }

    /**
     * Test of getMaintainerID method, of class MaintanceActivity.
     */
    @Test
    public void testGetMaintainerID() {
        System.out.println("getMaintainerID");
        String expResult = "manttest";
        String result = instance.getMaintainerID();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setMaintainerID method, of class MaintanceActivity.
     */
    @Test
    public void testSetMaintainerID() {
        System.out.println("setMaintainerID");
        String maintainerID = "mant01";
        instance.setMaintainerID(maintainerID);
        assertEquals(maintainerID, instance.getMaintainerID());
    }

    /**
     * Test of setWorkspacenotes method, of class MaintanceActivity.
     */
    @Test
    public void testSetWorkspacenotes() {
        System.out.println("setWorkspacenotes");
        String workspacenotes = "workspac";
        instance.setWorkspacenotes(workspacenotes);
        assertEquals(workspacenotes, instance.getWorkspacenotes());
    }

    public class MaintanceActivityImpl extends MaintanceActivity {   

        public MaintanceActivityImpl(MaintanceActivityFactory.Category category, String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible,int week, String procedureID, String fileSMP, String maintainerID, String workspace) {
            super(category, id, site, typology, activityDescription, intervationTime, interruptible,week, procedureID, fileSMP, maintainerID, workspace);
        }
    }
    
}
