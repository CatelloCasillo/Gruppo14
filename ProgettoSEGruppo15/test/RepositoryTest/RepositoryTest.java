package RepositoryTest;

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

/**
 *
 * @author Enrico
 */
public class RepositoryTest {
    private Repository repo;
    private ResultSet rstFull;
    private ResultSet rstEmpty;
   
    private Connection conn;
    private Statement stm;

   
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        String user = "utente_progetto_se";
        String pwd = "password";
        repo= new Repository();
        //rst = repo.getInformationOfMaintenanceActivity();
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            stm = this.conn.createStatement();
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
            this.stm.executeUpdate(query);
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
            this.stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initTestResultSet(){
          try {
            String query = "select* from \n" +
                            "MaintenanceActivity as ma \n" +
                            " full join Procedure as p on (ma.procedureID=p.procedureID )\n" +
                            " full join Site as s on (ma.siteID=s.siteID )\n"+
                            " where ma.activityid='test06' " +
                            "order by ma.activityID; ";
                   
            rstFull = this.stm.executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void initTestResultSetEmpty(){
      try {
         //query utilizzata per ottenere 0 righe
        String query = "select* from MaintenanceActivity where activityWeekNumber<0;";
        rstEmpty = this.stm.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void insertActivity(){
        try {
            String query = "insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
                "interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannerID,MaintainerID,activityMaterials,activityWorkspaceNotes)\n" +
                "values ('test99','descrizioneAttivita',120,true,22,'electrical','test02','test03','test01','test05','meterialsTest','notes');";
                   
            this.stm.executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void deleteActivity(){
        try {
            String query = " delete from MaintenanceActivity where activityid = 'test99';";
                   
            this.stm.executeQuery(query);
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSetRepositoryConnection() {
        String url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        String user = "utente_progetto_se";
        String pwd = "password";
        assertEquals(true,repo.setRepositoryConnection(url,user,pwd));
    }
    @Test
    public void testWrongURLRepositoryConnection() {
        String url = "jdbc:postgresql://localhost/fail";
        String user = "utente_progetto_se";
        String pwd = "password";
        assertEquals(false,repo.setRepositoryConnection(url,user,pwd));
    }
    @Test
    public void testWrongUserRepositoryConnection() {
        String url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        String user = "fail";
        String pwd = "password";
        assertEquals(false,repo.setRepositoryConnection(url,user,pwd));
    }
    @Test
    public void testWrongPasswordRepositoryConnection() {
        String url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        String user = "utente_progetto_se";
        String pwd = "fail";
        assertEquals(false,repo.setRepositoryConnection(url,user,pwd));
    }
    
    @Test
    public void testGetInformationOfMaintenanceActivity() {
        assertNotNull(repo.getInformationOfMaintenanceActivity());
    }
    @Test
    public void testGetActivityID() {
       assertNotNull(repo.getActivityID(rstFull));
    }
    @Test
    public void testEmptyGetActivityID() {
       assertNull(repo.getActivityID(rstEmpty));
    }
    @Test
    public void testGetActivityDescription() {
        assertNotNull(repo.getActivityDescription(rstFull));
    }
    @Test
    public void testEmptyGetActivityDescription() {
       assertNull(repo.getActivityDescription(rstEmpty));
    }
    @Test
    public void testGetActivityInterventionTime() {
        assertNotNull(repo.getActivityInterventionTime(rstFull));
    }
    @Test
    public void testEmptyGetActivityInterventionTime() {
        assertEquals(0,repo.getActivityInterventionTime(rstEmpty));
    }
    @Test
    public void testIsInterruptibleActivity() {
        assertNotNull(repo.isInterruptibleActivity(rstFull));
    }
    @Test
    public void testEmptyIsInterruptibleActivity() {
        assertEquals(false,repo.isInterruptibleActivity(rstEmpty));
    }
    @Test
    public void testGetActivityWeekNumber() {
        assertNotNull(repo.getActivityWeekNumber(rstFull));
    }
    @Test
    public void testEmptyGetActivityWeekNumber() {
        assertEquals(0,repo.getActivityWeekNumber(rstEmpty));
    }
    @Test
    public void testGetActivityTypology() {
        assertNotNull(repo.getActivityTypology(rstFull));
    }
    @Test
    public void testEmptyGetActivityTypology() {
        assertNull(repo.getActivityTypology(rstEmpty));
    }
    @Test
    public void testGetSiteID() {
        assertNotNull(repo.getSiteID(rstFull));
    }
    @Test
    public void testEmptyGetSiteID() {
        assertNull(repo.getSiteID(rstEmpty));
    }
    @Test
    public void testGetFactorySite() {
        assertNotNull(repo.getFactorySite(rstFull));
    }
    @Test
    public void testEmptyGetFactorySite() {
        assertNull(repo.getFactorySite(rstEmpty));
    }
    @Test
    public void testGetAreaSite() {
        assertNotNull(repo.getAreaSite(rstFull));
    }
    @Test
    public void testEmptyGetAreaSite() {
        assertNull(repo.getAreaSite(rstEmpty));
    }
    @Test
    public void testGetProcedureID() {
        assertNotNull(repo.getProcedureID(rstFull));
    }
    @Test
    public void testEmptyGetProcedureID() {
        assertNull(repo.getProcedureID(rstEmpty));
    }
    @Test
    public void testGetProcedureName() {
        assertNotNull(repo.getProcedureName(rstFull));
    }
    @Test
    public void testEmptyGetProcedureName() {
        assertNull(repo.getProcedureName(rstEmpty));
    }
    @Test
    public void testGetProcedureDescription() {
        assertNotNull(repo.getProcedureDescription(rstFull));
    }
    @Test
    public void testEmptyGetProcedureDescription() {
        assertNull(repo.getProcedureDescription(rstEmpty));
    }
    @Test
    public void testGetFileSMP() {
        assertNotNull(repo.getFileSMP(rstFull));
    }
    @Test
    public void testEmptyGetFileSMP() {
        assertNull(repo.getFileSMP(rstEmpty));
    }
    @Test
    public void testGetSiteTable() {
        assertNotNull(repo.getSiteTable());
    }
    @Test
    public void testViewMaintenanceActivityTable() {
        assertEquals(true,repo.viewMaintenanceActivityTable());
    }
    @Test
    public void testInsertActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        assertEquals(true,repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "typology"));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testFailInsertActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "typology");
        assertEquals(false,repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "typology"));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testUpdateActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        assertEquals(true,repo.updateMaintenanceActivity("test99","test02", "changed typology", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
        
    }
    @Test
    public void testFailUpdateActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        assertEquals(false,repo.updateMaintenanceActivity("test99","fail99", "changed typology", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testDeleteActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        assertEquals(true,repo.deleteMaintenanceActivity("test99"));
    }
    @Test
    public void testGetCompetencesOfActivity() {
        //test su activityID='test06'
        assertNotNull(repo.getCompetencesOfActivity("test06"));
        
    }
    
}
