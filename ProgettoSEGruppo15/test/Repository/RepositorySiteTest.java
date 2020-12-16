/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Enrico
 */
public class RepositorySiteTest extends RepositoryTestBase{
    
    RepositorySiteInterface instance;
    
    @Before
    public void setUp() {
        instance = new RepositorySite();
        setUpForTest();
    }
    
    @After
    public void tearDown() {
        deleteTestTables();
    }

    /**
     * Test of getSiteID method, of class RepositorySite.
     */
    @Test
    public void testGetSiteID() {
        
        String expResult = "test02";
        String result = instance.getSiteID(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getSiteID method, of class RepositorySite.
     */
    @Test
    public void testEmptyGetSiteID() {
        assertNull(instance.getSiteID(rstEmpty));
    }

    /**
     * Test of getFactorySite method, of class RepositorySite.
     */
    @Test
    public void testGetFactorySite() {
        
        String expResult = "factory1";
        String result = instance.getFactorySite(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getFactorySite method, of class RepositorySite.
     */
    @Test
    public void testEmptyGetFactorySite() {
        assertNull(instance.getFactorySite(rstEmpty));
    }

    /**
     * Test of getAreaSite method, of class RepositorySite.
     */
    @Test
    public void testGetAreaSite() {
        
        String expResult = "area1";
        String result = instance.getAreaSite(rstFull);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getAreaSite method, of class RepositorySite.
     */
    @Test
    public void testEmptyGetAreaSite() {
        assertNull(instance.getAreaSite(rstEmpty));
    }

    /**
     * Test of getSiteTable method, of class RepositorySite.
     */
    @Test
    public void testGetSiteTable() {
        assertNotNull(instance.getSiteTable());
    }
    
}
