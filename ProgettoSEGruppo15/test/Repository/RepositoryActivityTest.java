/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Enrico
 */
public class RepositoryActivityTest extends RepositoryTestBase{
    RepositoryActivityInterface instance;
    

    @Before
    public void setUp() {
        instance= new RepositoryActivity();
        setUpForTest();
    }
    
    @After
    public void tearDown() {
        deleteTestTables();
    }

    /*
    @Test
    public void testGetInformationOfMaintenanceActivity() {
        System.out.println("getInformationOfMaintenanceActivity");
        RepositoryActivity instance = new RepositoryActivity();
        ResultSet expResult = null;
        ResultSet result = instance.getInformationOfMaintenanceActivity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    /**
     * Test of getInformationOfMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testGetInformationOfMaintenanceActivity() {
        assertNotNull(instance.getInformationOfMaintenanceActivity());
    }
    
    /**
     * Test of getActivityID method, of class RepositoryActivity.
     */
    @Test
    public void testGetActivityID() {
        
        String expResult = "test06";
        String result = instance.getActivityID(rstFull);
        assertEquals(expResult, result);
      
    }
    /**
     * Test of getActivityID method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyGetActivityID() {
       assertNull(instance.getActivityID(rstEmpty));
    }

    /**
     * Test of getActivityDescription method, of class RepositoryActivity.
     */
    @Test
    public void testGetActivityDescription() {
        
        String expResult = "descrizioneAttivita";
        String result = instance.getActivityDescription(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getActivityDescription method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyGetActivityDescription() {
       assertNull(instance.getActivityDescription(rstEmpty));
    }

    /**
     * Test of getActivityInterventionTime method, of class RepositoryActivity.
     */
    @Test
    public void testGetActivityInterventionTime() {
        
        int expResult = 120;
        int result = instance.getActivityInterventionTime(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getActivityInterventionTime method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyGetActivityInterventionTime() {
        assertEquals(0,instance.getActivityInterventionTime(rstEmpty));
    }

    /**
     * Test of isInterruptibleActivity method, of class RepositoryActivity.
     */
    @Test
    public void testIsInterruptibleActivity() {
        
        boolean expResult = true;
        boolean result = instance.isInterruptibleActivity(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of isInterruptibleActivity method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyIsInterruptibleActivity() {
        assertEquals(false,instance.isInterruptibleActivity(rstEmpty));
    }

    /**
     * Test of getActivityWeekNumber method, of class RepositoryActivity.
     */
    @Test
    public void testGetActivityWeekNumber() {
        
        int expResult = 22;
        int result = instance.getActivityWeekNumber(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getActivityWeekNumber method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyGetActivityWeekNumber() {
        assertEquals(0,instance.getActivityWeekNumber(rstEmpty));
    }

    /**
     * Test of getActivityTypology method, of class RepositoryActivity.
     */
    @Test
    public void testGetActivityTypology() {
        
        String expResult = "Typology1";
        String result = instance.getActivityTypology(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getActivityTypology method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyGetActivityTypology() {
        assertNull(instance.getActivityTypology(rstEmpty));
    }

    /**
     * Test of getWorkSpacenotes method, of class RepositoryActivity.
     */
    @Test
    public void testGetWorkSpacenotes() {
        
        String expResult = "notes";
        String result = instance.getWorkSpacenotes(rstFull);
        assertEquals(expResult, result);
       
    }
    /**
     * Test of getWorkSpacenotes method, of class RepositoryActivity.
     */
    @Test
    public void testEmptyGetWorkSpacenotes() {
        assertNull(instance.getWorkSpacenotes(rstEmpty));
    }

    /**
     * Test of getPlannedActivity method, of class RepositoryActivity.
     */
    @Test
    public void testGetPlannedActivity() {
        
        String expResult = "PLANNED";
        String result = instance.getPlannedActivity(rstFull);
        assertEquals(expResult, result);
        
    }
    @Test
    public void testEmptyGetPlannedActivity() {
        assertNull(instance.getPlannedActivity(rstEmpty));
    }

    /**
     * Test of insertNewMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testInsertNewMaintenanceActivity() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        assertEquals(true,instance.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    /**
     * Test of insertNewMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testFailInsertActivityTable1() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        //inserimento di un'attività già presente
        instance.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED");
        assertEquals(false,instance.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    /**
     * Test of insertNewMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testFailInsertActivityTable2() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        // wrong typology
        assertEquals(false,instance.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "fail","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    /**
     * Test of insertNewMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testFailInsertActivityTable3() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        // wrong siteID
        assertEquals(false,instance.insertNewMaintenanceActivity("test99", "fail", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    /**
     * Test of deleteMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testDeleteMaintenanceActivity() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        assertEquals(true,instance.deleteMaintenanceActivity("test99"));
    }
    /**
     * Test of deleteMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testFailDeleteActivityTable() {
       
        assertEquals(false,instance.deleteMaintenanceActivity("fail"));
    }

    /**
     * Test of updateMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testUpdateMaintenanceActivity() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        assertEquals(true,instance.updateMaintenanceActivity("test99","test02", "Typology1", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
       
    }
    /**
     * Test of updateMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testFailUpdateActivityTable1() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        //wrong SiteID
        assertEquals(false,instance.updateMaintenanceActivity("test99","fail99", "Typology1", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
    }
    /**
     * Test of updateMaintenanceActivity method, of class RepositoryActivity.
     */
    @Test
    public void testFailUpdateActivityTable2() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        //wrong Typology
        assertEquals(false,instance.updateMaintenanceActivity("test99","test02", "fail", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
    }

    /**
     * Test of getCompetencesOfActivity method, of class RepositoryActivity.
     */
    @Test
    public void testGetCompetencesOfActivity() {
        //test su activityID='test06'
        assertNotNull(instance.getCompetencesOfActivity("test06"));
     
    }
    /**
     * Test of getCompetencesOfActivity method, of class RepositoryActivity.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFailGetCompetencesOfActivity() throws SQLException {
        ResultSet rst = instance.getCompetencesOfActivity("fail");
        assertFalse(rst.next());
    }

    /**
     * Test of updateWorkspceNotes method, of class RepositoryActivity.
     */
    @Test
    public void testUpdateWorkspceNotes() {
       //test with activityId=test06
       insertActivity();
       assertTrue(instance.updateWorkspceNotes("test99","notesTest"));
       deleteActivity();
    }
    /**
     * Test of updateWorkspceNotes method, of class RepositoryActivity.
     */
    @Test
    public void testFailUpdateWorkspceNotes() {
        //test with activityId=test06
       assertFalse(instance.updateWorkspceNotes("fail","notes"));
    }
    
}
