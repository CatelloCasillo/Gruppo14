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
public class RepositoryMaintainerTest extends RepositoryTestBase{
    RepositoryMaintainerInterface instance;
    
    @Before
    public void setUp() {
        instance = new RepositoryMaintainer();
        setUpForTest();
    }
    
    @After
    public void tearDown() {
        deleteTestTables();
    }

    /**
     * Test of getMaintainerTable method, of class RepositoryMaintainer.
     */
    @Test
    public void testGetMaintainerTable() {
        assertNotNull(instance.getMaintainerTable());
    }

    /**
     * Test of getMaintainer method, of class RepositoryMaintainer.
     */
    @Test
    public void testGetMaintainer() {
        //test with maintainerid=test05
        ResultSet result = instance.getMaintainer("test05");
        assertNotNull(result);
        
    }

    /**
     * Test of getMaintainerName method, of class RepositoryMaintainer.
     */
    @Test
    public void testGetMaintainerName() {
        //test with maintainerName=testMaintainer1
        //rstFull cointains all maintainer parameters
        String expResult = "testMaintainer1";
        String result = instance.getMaintainerName(rstFull2);
        assertEquals(expResult, result);
    }
    /**
     * Test of getMaintainerName method, of class RepositoryMaintainer.
     */
    @Test
    public void testEmptyGetMaintainerName() {
       assertNull(instance.getMaintainerName(rstEmpty));
    }

    /**
     * Test of getMaintainerID method, of class RepositoryMaintainer.
     */
    @Test
    public void testGetMaintainerID() {
        //test with maintainerid=test05
        //rstFull cointains all maintainer parameters
        String expResult = "test05";
        String result = instance.getMaintainerID(rstFull2);
        assertEquals(expResult, result);
    }
    /**
     * Test of getMaintainerID method, of class RepositoryMaintainer.
     */
    @Test
    public void testEmptyGetMaintainerID() {
       assertNull(instance.getMaintainerID(rstEmpty));
    }

    /**
     * Test of getCompetencesOfMaintainer method, of class RepositoryMaintainer.
     */
    @Test
    public void testGetCompetencesOfMaintainer() {
        //test with maintainerId=mant05 that have some competences
       assertNotNull(instance.getCompetencesOfMaintainer("test05"));
    }
    /**
     * Test of getCompetencesOfMaintainer method, of class RepositoryMaintainer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testGetCompetencesOfMaintainer2() throws SQLException {
        //test with maintainerId=mant05 that have no competences
       ResultSet rst=instance.getCompetencesOfMaintainer("test07");
       assertFalse(rst.next());
    }
    /**
     * Test of getCompetencesOfMaintainer method, of class RepositoryMaintainer.
     * @throws java.sql.SQLException
     */
    @Test
    public void testFailGetCompetencesOfMaintainer() throws SQLException {
        //wrong mintainerid
       ResultSet rst=instance.getCompetencesOfMaintainer("fail");
       assertFalse(rst.next());
    }
    
}
