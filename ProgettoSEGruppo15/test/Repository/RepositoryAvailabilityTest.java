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
public class RepositoryAvailabilityTest extends RepositoryTestBase{
    RepositoryAvailabilityInterface instance;
    
    @Before
    public void setUp() {
        instance= new RepositoryAvailability();
        setUpForTest();
    }
    
    @After
    public void tearDown() {
        deleteTestTables();
    }

    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability1() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Monday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability2() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Tuesday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability3() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Wednesday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability4() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Thursday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability5() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Friday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability6() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Saturday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability7() {
        assertNotNull(instance.getAllMaintainersCurrentWeekDayAvailability("Sunday"));
    }
    /**
     * Test of getAllMaintainersCurrentWeekDayAvailability method, of class RepositoryAvailability.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFailGetAllMaintainersCurrentWeekDayAvailability() throws SQLException {
        ResultSet rst=instance.getAllMaintainersCurrentWeekDayAvailability("fail");
        assertFalse(rst.next());
    }

    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability1() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Monday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability2() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Tuesday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability3() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Wednesday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability4() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Thursday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability5() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Friday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability6() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Saturday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetDayAvailability7() {
        //test with maintainerId=mant05
        assertNotNull(instance.getDayAvailability("mant05","Sunday"));
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFailGetDayAvailability1() throws SQLException {
        //wrong maintainerID
        ResultSet rst=instance.getDayAvailability("fail","Sunday");
        assertFalse(rst.next());
    }
    /**
     * Test of getDayAvailability method, of class RepositoryAvailability.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFailGetDayAvailability2() throws SQLException {
        //wrong day
        ResultSet rst=instance.getDayAvailability("mant05","fail");
        assertFalse(rst.next());
    }

    /**
     * Test of getTimeslots method, of class RepositoryAvailability.
     */
    @Test
    public void testGetTimeslots() {
        //mantainer id= test07
        // rstFull2 contains maintainer parameters
        
        int[] expResult = {60,60,60,60,60,60,60,60};
        int[] result = instance.getTimeslots(rstFull2);
        assertArrayEquals(expResult, result);
        
    }
    /**
     * Test of getTimeslots method, of class RepositoryAvailability.
     */
    @Test
    public void testFailGetTimeslots() {
        //mantainer id= test07
        // rstFull2 contains maintainer parameters
        
        int[] expResult = new int[8];
        int[] result = instance.getTimeslots(rstEmpty);
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of getWeekAvailability method, of class RepositoryAvailability.
     */
    @Test
    public void testGetWeekAvailability() {
        //test with maintainerId=mant05
        assertNotNull(instance.getWeekAvailability("mant05"));
    }
    /**
     * Test of getWeekAvailability method, of class RepositoryAvailability.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFailGetWeekAvailability() throws SQLException {
        ResultSet rst=instance.getWeekAvailability("fail");
        assertFalse(rst.next());
    }

    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
   @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek1() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Monday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Tuesday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek3() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Wednesday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek4() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Thursday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek5() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Friday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek6() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Saturday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek7() {
        //test with maintainerId=mant05
        assertTrue(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Sunday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testFailUpdateMaintainerAvailabilityCurrentWeek1() {
        //wrong maintainerId
        assertFalse(instance.updateMaintainerAvailabilityCurrentWeek("fail","Sunday",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testFailUpdateMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        //wrong day
        assertFalse(instance.updateMaintainerAvailabilityCurrentWeek("mant05","fail",30,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testFailUpdateMaintainerAvailabilityCurrentWeek3() {
        //test with maintainerId=mant05
        //wrong time number
        assertFalse(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Sunday",100,30,30,30,30,30,30,30));
    }
    /**
     * Test of updateMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    public void testFailUpdateMaintainerAvailabilityCurrentWeek4() {
        //test with maintainerId=mant05
        //wrong time number
        assertFalse(instance.updateMaintainerAvailabilityCurrentWeek("mant05","Sunday",-1,30,30,30,30,30,30,30));
    }

    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testAssignActivity() {
        //test with activityId=test06, maintainedId=test05
       assertTrue(instance.assignActivity("test06","test05","Monday",22,0,0,0,0,30,30,0,0));
       this.deleteActivityTimeDivision("test06");
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity1() {
        //test with activityId=test06, maintainedId=test05
        //wrong activityId
       assertFalse(instance.assignActivity("fail","test05","Monday",22,0,0,0,0,30,30,0,0));
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity2() {
        //test with activityId=test06, maintainedId=test05
        //wrong maintainerId
       assertFalse(instance.assignActivity("test06","fail","Monday",22,0,0,0,0,30,30,0,0));
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity3() {
        //test with activityId=test06, maintainedId=test05
        //wrong day
       assertFalse(instance.assignActivity("test06","test05","fail",22,0,0,0,0,30,30,0,0));
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity4() {
        //test with activityId=test06, maintainedId=test05
        //wrong weekNumber
       assertFalse(instance.assignActivity("test06","test05","Monday",-1,0,0,0,0,30,30,0,0));
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity5() {
        //test with activityId=test06, maintainedId=test05
        //wrong weekNumber
       assertFalse(instance.assignActivity("test06","test05","Monday",100,0,0,0,0,30,30,0,0));
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity6() {
        //test with activityId=test06, maintainedId=test05
        //wrong AssignedSlot number
       assertFalse(instance.assignActivity("test06","test05","Monday",22,0,0,100,0,30,30,0,0));
    }
    /**
     * Test of assignActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testFailAssignActivity7() {
        //test with activityId=test06, maintainedId=test05
        //wrong AssignedSlot number
       assertFalse(instance.assignActivity("test06","test05","Monday",22,0,0,-1,0,30,30,0,0));
    }

    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek1() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Monday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Tuesday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek3() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Wednesday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek4() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Thursday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek5() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Friday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek6() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Saturday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek7() {
        //test with maintainerId=mant05
        assertTrue(instance.resetMaintainerAvailabilityCurrentWeek("mant05","Sunday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testFailResetMaintainerAvailabilityCurrentWeek1() {
        //wrong maintainerId
        assertFalse(instance.resetMaintainerAvailabilityCurrentWeek("fail","Sunday"));
    }
    /**
     * Test of resetMaintainerAvailabilityCurrentWeek method, of class RepositoryAvailability.
     */
    @Test
    public void testFailResetMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        //wrong day
        assertFalse(instance.resetMaintainerAvailabilityCurrentWeek("mant05","fail"));
    }

    /**
     * Test of getAssignedTimeslotsOfActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testGetAssignedTimeslotsOfActivity() {
        System.out.println("getAssignedTimeslotsOfActivity");
        String activityID = "";
        RepositoryAvailability instance = new RepositoryAvailability();
        int[] expResult = null;
        int[] result = instance.getAssignedTimeslotsOfActivity(activityID);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivitiesOfTimeSlot method, of class RepositoryAvailability.
     */
    @Test
    public void testGetActivitiesOfTimeSlot() {
        System.out.println("getActivitiesOfTimeSlot");
        String maintainerId = "";
        String day = "";
        int weeknumber = 0;
        int timeSlotIndex = 0;
        RepositoryAvailability instance = new RepositoryAvailability();
        ResultSet expResult = null;
        ResultSet result = instance.getActivitiesOfTimeSlot(maintainerId, day, weeknumber, timeSlotIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAssignedActivity method, of class RepositoryAvailability.
     */
    @Test
    public void testDeleteAssignedActivity() {
        System.out.println("deleteAssignedActivity");
        String activityID = "";
        RepositoryAvailability instance = new RepositoryAvailability();
        boolean expResult = false;
        boolean result = instance.deleteAssignedActivity(activityID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
    }
    
}
