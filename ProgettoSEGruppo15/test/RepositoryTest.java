/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Repository.Repository;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.postgresql.core.v3.QueryExecutorImpl;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Enrico
 */
public class RepositoryTest {
    private Repository repo;
    private ResultSet rstFull;
    private ResultSet rstEmpty;
   

   
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        repo= new Repository();
        //rst = repo.getInformationOfMaintenanceActivity();
        try {
            initTestTables();
            //creazione di un result set riempito per il testing
            initTestResultSet();
            rstFull.next();
            //creazione di un result set vuoto per il testing
            initTestResultSetEmpty();
            rstEmpty.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
       deleteTestTables();
    }
    public void initTestTables(){
        String query;
        query="insert into Planner\n" +
                "values ('test01','1234');\n" +
                "\n" +
                "insert into Site\n" +
                "values ('test02','factory1','area1');\n" +
                "\n" +
                "insert into Procedure\n" +
                "values ('test03','proceduraTest','testoProcedura','ulrFileSMP');\n" +
                "\n" +
                "insert into Competence\n" +
                "values ('test04','nomeCompetenza','descrizioneCompetenza');\n" +
                "\n" +
                "insert into competenceToProcedure\n" +
                "values('test03','test04');\n" +
                "\n" +
                "insert into Maintainer\n" +
                "values ('test05','pippo','1234');"
                + "insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
                "interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannerID,MaintainerID,activityMaterials,activityWorkspaceNotes)\n" +
                "values ('test06','descrizioneAttivita',120,true,22,'electrical','test02','test03','test01','test05','meterialsTest','notes');";
        try {
            repo.getStm().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteTestTables(){
        String query;
        query="delete from competencetoprocedure where procedureid ='test03';"
                + " delete from MaintenanceActivity where activityid = 'test06';"
                + " delete from Planner where plannerid = 'test01';"
                + " delete from Site where siteid = 'test02';"
                + " delete from Procedure where Procedureid = 'test03';"
                + " delete from Competence where Competenceid = 'test04';"
                + " delete from Maintainer where Maintainerid = 'test05';"
               ;
                
        try {
            repo.getStm().executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void initTestResultSet(){
          try {
            String query = "select* from \n" +
                            "MaintenanceActivity as ma \n" +
                            " full join Procedure as p on (ma.procedureID=p.procedureID )\n" +
                            " full join Site as s on (ma.siteID=s.siteID )\n"+
                            " where ma.activityid='test06' " +
                            "order by ma.activityID; ";
                   
            rstFull = repo.getStm().executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void initTestResultSetEmpty(){
      try {
         //query utilizzata per ottenere 0 righe
        String query = "select* from MaintenanceActivity where activityWeekNumber<0;";
               /* "select* from \n" +
                            "MaintenanceActivity as ma \n" +
                            " full join Procedure as p on (ma.procedureID=p.procedureID )\n" +
                            " full join Site as s on (ma.siteID=s.siteID )\n"+
                            " where ma.activityWeekNumber<0 " +
                            "order by ma.activityID; ";
                  */      

        rstEmpty = repo.getStm().executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testSetRepositoryConnection() {
        String url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        String user = "utente_progetto_se";
        String pwd = "password";
        repo.setRepositoryConnection(url,user,pwd);
    }
    /*
    @Test(expected = SQLException.class)
    public void testWrongURLRepositoryConnection() {
        String url = "jdbc:postgresql://localhost/fail";
        String user = "utente_progetto_se";
        String pwd = "password";
        repo.setRepositoryConnection(url,user,pwd);
    }
    */
    
    @Test
    public void testGetInformationOfMaintenanceActivity() {
        assertNotNull(repo.getInformationOfMaintenanceActivity());
    }
    @Test
    public void testGetActivityID() {
       assertNotNull(repo.getActivityID(rstFull));
       assertNull(repo.getActivityID(rstEmpty));
    }
    @Test
    public void testGetActivityDescription() {
        assertNotNull(repo.getActivityDescription(rstFull));
       // assertNull(repo.getActivityDescription(rstEmpty));
    }
    @Test
    public void testGetActivityInterventionTime() {
        assertNotNull(repo.getActivityInterventionTime(rstFull));
        //assertEquals(0,repo.getActivityInterventionTime(rstEmpty));
    }
    @Test
    public void testIsInterruptibleActivity() {
        assertNotNull(repo.isInterruptibleActivity(rstFull));
        //assertEquals(false,repo.isInterruptibleActivity(rstEmpty));
    }
    @Test
    public void testGetActivityWeekNumber() {
        assertNotNull(repo.getActivityWeekNumber(rstFull));
        //assertEquals(0,repo.getActivityWeekNumber(rstEmpty));
    }
    @Test
    public void testGetActivityTypology() {
        assertNotNull(repo.getActivityTypology(rstFull));
        //assertNull(repo.getActivityTypology(rstEmpty));
    }
    @Test
    public void testGetSiteID() {
        assertNotNull(repo.getSiteID(rstFull));
        //assertNull(repo.getSiteID(rstEmpty));
    }
    @Test
    public void testGetFactorySite() {
        assertNotNull(repo.getFactorySite(rstFull));
        //assertNull(repo.getFactorySite(rstEmpty));
    }
    @Test
    public void testGetAreaSite() {
        assertNotNull(repo.getAreaSite(rstFull));
        //assertNull(repo.getAreaSite(rstEmpty));
    }
    @Test
    public void testGetProcedureID() {
        assertNotNull(repo.getProcedureID(rstFull));
        //assertNull(repo.getProcedureID(rstEmpty));
    }
    @Test
    public void testGetProcedureName() {
        assertNotNull(repo.getProcedureName(rstFull));
        //assertNull(repo.getProcedureName(rstEmpty));
    }
    @Test
    public void testGetProcedureDescription() {
        assertNotNull(repo.getProcedureDescription(rstFull));
        //assertNull(repo.getProcedureDescription(rstEmpty));
    }
    @Test
    public void testGetFileSMP() {
        assertNotNull(repo.getFileSMP(rstFull));
        //assertNull(repo.getFileSMP(rstEmpty));
    }
    @Test
    public void testGetSiteTable() {
        assertNotNull(repo.getSiteTable());
    }
    @Test
    public void testInsertUpdateDeleteActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "typology");
        repo.updateMaintenanceActivity("test99","test02", "changed typology", "changed description", 70, false, 11);
        repo.deleteMaintenanceActivity("test99");
        
    }
}
