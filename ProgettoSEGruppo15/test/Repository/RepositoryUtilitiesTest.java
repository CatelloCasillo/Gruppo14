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
public class RepositoryUtilitiesTest extends RepositoryTestBase{
    
    RepositoryUtilitiesInterface instance;
    
    @Before
    public void setUp() {
        instance = new RepositoryUtilities();
        setUpForTest();
    }
    
    @After
    public void tearDown() {
        deleteTestTables();
    }

    /**
     * Test of getTypologyTable method, of class RepositoryUtilities.
     */
    @Test
    public void testGetTypologyTable() {
        assertNotNull(instance.getTypologyTable());
    }

    /**
     * Test of getCompetenceID method, of class RepositoryUtilities.
     */
    @Test
    public void testGetCompetenceID() {
        //test with competenceId=test04
        String expResult = "test04";
        String result = instance.getCompetenceID(rstFull2);
        assertEquals(expResult, result);
      
    }
    /**
     * Test of getCompetenceID method, of class RepositoryUtilities.
     */
    @Test
    public void testEmptyGetCompetenceID() {
       assertNull(instance.getCompetenceID(rstEmpty));
    }

    /**
     * Test of getCompetenceOfTypology method, of class RepositoryUtilities.
     */
    @Test
    public void testGetCompetenceOfTypology() throws SQLException {
        //test with typology=Typology1
       ResultSet rst=instance.getCompetenceOfTypology("Typology1");
       assertTrue(rst.next());
    }
    /**
     * Test of getCompetenceOfTypology method, of class RepositoryUtilities.
     */
    @Test
    public void testFailGetCompetenceOfTypology() throws SQLException {
        //test with typology=Typology1
       ResultSet rst=instance.getCompetenceOfTypology("fail");
       assertFalse(rst.next());
    }

    /**
     * Test of getProcedureID method, of class RepositoryUtilities.
     */
    @Test
    public void testGetProcedureID() {
        //test with procedureID=test03
        String expResult = "test03";
        String result = instance.getProcedureID(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getProcedureID method, of class RepositoryUtilities.
     */
    @Test
    public void testEmptyGetProcedureID() {
        assertNull(instance.getProcedureID(rstEmpty));
    }

    /**
     * Test of getFileSMP method, of class RepositoryUtilities.
     */
    @Test
    public void testGetFileSMP() {
        
        String expResult = "ulrFileSMP";
        String result = instance.getFileSMP(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getFileSMP method, of class RepositoryUtilities.
     */
    @Test
    public void testEmptyGetFileSMP() {
        assertNull(instance.getFileSMP(rstEmpty));
    }
    
}
