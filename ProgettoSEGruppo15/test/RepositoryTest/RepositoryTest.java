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
    private ResultSet rstFull2;
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
            initTestResultSet2();
            rstFull2.next();
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
                "\n insert into Typology values('Typology1');\n" +
                " insert into Typology values('Typology2'); " +
                
                " insert into TypologyToCompetence values('test04', 'Typology1'); " +
                " insert into CompetenceToMaintainer values('test04', 'test05'); " +
                
                
                //"insert into Maintainer\n" +
                //"values ('test05','testMaintainer','1234');"+
                "insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
                "interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannerID,MaintainerID,activityMaterials,activityWorkspaceNotes,plannedActivity)\n" +
                "values ('test06','descrizioneAttivita',120,true,22,'Typology1','test02','test03','test01','test05','meterialsTest','notes',true);";
        try {
            insertNewMaintainer("test05", "testMaintainer1", "1234");
            insertNewMaintainer("test07", "testMaintainer2", "1234");
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
                + " delete from TypologyToCompetence where activityTypology = 'Typology1';"
                + " delete from Typology where activityTypology = 'Typology1';"
                + " delete from Typology where activityTypology = 'Typology2';"
                + " delete from Site where siteid = 'test02';"
                + " delete from Procedure where Procedureid = 'test03';"
                + " delete from Competence where Competenceid = 'test04';"
                + " delete from MaintainerAvailabilityCurrentWeek where Maintainerid = 'test05';"
                + " delete from CompetenceToMaintainer where Maintainerid = 'test05';"
                + " delete from Maintainer where Maintainerid = 'test05';"
                + " delete from Maintainer where Maintainerid = 'test07';"
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
    private void initTestResultSet2(){
          try {
            String query = "select* from maintainer as m\n" +
                                    "join CompetenceToMaintainer as cm on(m.maintainerid=cm.maintainerid)\n" +
                                    "join Competence as c on(c.competenceID=cm.competenceid)\n" +
                                    "join MaintainerAvailabilityCurrentWeek as week on(m.maintainerID = week.maintainerID)\n" +
                                    "join typologytocompetence as t on(t.competenceid=c.competenceid); ";
                   
            rstFull2 = this.stm.executeQuery(query);
            
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
                "interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannerID,MaintainerID,activityMaterials,activityWorkspaceNotes,plannedActivity)\n" +
                "values ('test99','descrizioneAttivita',120,true,22,'Typology1','test02','test03','test01','test05','meterialsTest','notes',true);";
                   
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
    //Funzioni per l'inserimento di un maintainer con annessa disponibilità
    public boolean insertNewMaintainer(String id, String name, String password){
        if(id.length()>6){
            System.err.println("Mainteiner id ERROR: length exceeded");
            return false;
        }
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Maintainer\n" +
                        "values ('").append(id).append("' , '").append(name).append("' , '").append(password).append("' );");
        try {
           
            //SQL transaction
            conn.setAutoCommit(false);
            stm.executeUpdate(temp.toString());
            for(int i=0;i<7;i++){
                insertNewMaintainerAvailability(id,days[i]);
            }
            conn.commit();
            conn.setAutoCommit(true);
           
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private boolean insertNewMaintainerAvailability(String maintainerID, String day) {
      
        StringBuilder temp = new StringBuilder();
        temp.append("insert into MaintainerAvailabilityCurrentWeek(maintainerID,Day, \n" +
                    " TimeSlot1,TimeSlot2,TimeSlot3,TimeSlot4,TimeSlot5,TimeSlot6,TimeSlot7,TimeSlot8)\n" +
                    " values('").append(maintainerID).append("','").append(day).append("',").append("60,60,60,60,60,60,60,60);");
        try {
            
            stm.executeUpdate(temp.toString());
            
            return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private boolean deleteActivityTimeDivision(String activityID) {
      
        String temp;
        temp=String.format("delete from ActivityTimeDivision where activityid = '%s';",activityID);
        try {
            
            stm.executeUpdate(temp);
            
            return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    ///-----------------------------------------------------------------------------------
    /*
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
    */
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
        
        assertEquals(true,repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testFailInsertActivityTable1() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        //inserimento di un'attività già presente
        repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED");
        assertEquals(false,repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testFailInsertActivityTable2() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        // wrong typology
        assertEquals(false,repo.insertNewMaintenanceActivity("test99", "test02", "descriptionTest", 60, true, 10, "notes", "fail","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testFailInsertActivityTable3() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        
        // wrong siteID
        assertEquals(false,repo.insertNewMaintenanceActivity("test99", "fail", "descriptionTest", 60, true, 10, "notes", "Typology1","PLANNED"));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testUpdateActivityTable() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        assertEquals(true,repo.updateMaintenanceActivity("test99","test02", "Typology1", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
        
    }
    @Test
    public void testFailUpdateActivityTable1() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        //wrong SiteID
        assertEquals(false,repo.updateMaintenanceActivity("test99","fail99", "Typology1", "changed description", 70, false, 11));
        deleteActivity();//usa activityID='test99'
    }
    @Test
    public void testFailUpdateActivityTable2() {
        //siteID='test02' nella tabella di test
        //weekNumber deve essere compreso tra 1 e 52 inclusi
        insertActivity(); //usa activityID='test99'
        //wrong Typology
        assertEquals(false,repo.updateMaintenanceActivity("test99","test02", "fail", "changed description", 70, false, 11));
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
    public void testFailDeleteActivityTable() {
       
        assertEquals(false,repo.deleteMaintenanceActivity("fail"));
    }
    @Test
    public void testGetCompetencesOfActivity() {
        //test su activityID='test06'
        assertNotNull(repo.getCompetencesOfActivity("test06"));
        
    }
    @Test
    public void testFailGetCompetencesOfActivity() throws SQLException {
        ResultSet rst = repo.getCompetencesOfActivity("fail");
        assertFalse(rst.next());
    }
    @Test
    public void testGetTypologyTable() {
        assertNotNull(repo.getTypologyTable());
    }
    @Test
    public void testGetMaintainerTable() {
        assertNotNull(repo.getMaintainerTable());
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability1() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Monday"));
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability2() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Tuesday"));
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability3() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Wednesday"));
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability4() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Thursday"));
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability5() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Friday"));
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability6() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Saturday"));
    }
    @Test
    public void testGetAllMaintainersCurrentWeekDayAvailability7() {
        assertNotNull(repo.getAllMaintainersCurrentWeekDayAvailability("Sunday"));
    }
    @Test
    public void testFailGetAllMaintainersCurrentWeekDayAvailability() throws SQLException {
        ResultSet rst=repo.getAllMaintainersCurrentWeekDayAvailability("fail");
        assertFalse(rst.next());
    }
    @Test
    public void testGetWeekAvailability() {
        //test with maintainerId=mant05
        assertNotNull(repo.getWeekAvailability("mant05"));
    }
    @Test
    public void testFailGetWeekAvailability() throws SQLException {
        ResultSet rst=repo.getWeekAvailability("fail");
        assertFalse(rst.next());
    }
    @Test
    public void testGetDayAvailability1() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Monday"));
    }
    @Test
    public void testGetDayAvailability2() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Tuesday"));
    }
    @Test
    public void testGetDayAvailability3() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Wednesday"));
    }
    @Test
    public void testGetDayAvailability4() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Thursday"));
    }
    @Test
    public void testGetDayAvailability5() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Friday"));
    }
    @Test
    public void testGetDayAvailability6() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Saturday"));
    }
    @Test
    public void testGetDayAvailability7() {
        //test with maintainerId=mant05
        assertNotNull(repo.getDayAvailability("mant05","Sunday"));
    }
    @Test
    public void testFailGetDayAvailability1() throws SQLException {
        //wrong maintainerID
        ResultSet rst=repo.getDayAvailability("fail","Sunday");
        assertFalse(rst.next());
    }
    @Test
    public void testFailGetDayAvailability2() throws SQLException {
        //wrong day
        ResultSet rst=repo.getDayAvailability("mant05","fail");
        assertFalse(rst.next());
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek1() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Monday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Tuesday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek3() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Wednesday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek4() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Thursday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek5() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Friday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek6() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Saturday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testUpdateMaintainerAvailabilityCurrentWeek7() {
        //test with maintainerId=mant05
        assertTrue(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Sunday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testFailUpdateMaintainerAvailabilityCurrentWeek1() {
        //wrong maintainerId
        assertFalse(repo.updateMaintainerAvailabilityCurrentWeek("fail","Sunday",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testFailUpdateMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        //wrong day
        assertFalse(repo.updateMaintainerAvailabilityCurrentWeek("mant05","fail",30,30,30,30,30,30,30,30));
    }
    @Test
    public void testFailUpdateMaintainerAvailabilityCurrentWeek3() {
        //test with maintainerId=mant05
        //wrong time number
        assertFalse(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Sunday",100,30,30,30,30,30,30,30));
    }
    public void testFailUpdateMaintainerAvailabilityCurrentWeek4() {
        //test with maintainerId=mant05
        //wrong time number
        assertFalse(repo.updateMaintainerAvailabilityCurrentWeek("mant05","Sunday",-1,30,30,30,30,30,30,30));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek1() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Monday"));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Tuesday"));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek3() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Wednesday"));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek4() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Thursday"));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek5() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Friday"));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek6() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Saturday"));
    }
    @Test
    public void testResetMaintainerAvailabilityCurrentWeek7() {
        //test with maintainerId=mant05
        assertTrue(repo.resetMaintainerAvailabilityCurrentWeek("mant05","Sunday"));
    }
    @Test
    public void testFailResetMaintainerAvailabilityCurrentWeek1() {
        //wrong maintainerId
        assertFalse(repo.resetMaintainerAvailabilityCurrentWeek("fail","Sunday"));
    }
    @Test
    public void testFailResetMaintainerAvailabilityCurrentWeek2() {
        //test with maintainerId=mant05
        //wrong day
        assertFalse(repo.resetMaintainerAvailabilityCurrentWeek("mant05","fail"));
    }
    /*
    @Test
    public void testGetActivityID() {
       assertNotNull(repo.getActivityID(rstFull));
    }
    @Test
    public void testEmptyGetActivityID() {
       assertNull(repo.getActivityID(rstEmpty));
    }
*/
    @Test
    public void testGetCompetencesOfMaintainer() {
        //test with maintainerId=mant05 that have some competences
       assertNotNull(repo.getCompetencesOfMaintainer("test05"));
    }
    @Test
    public void testGetCompetencesOfMaintainer2() throws SQLException {
        //test with maintainerId=mant05 that have no competences
       ResultSet rst=repo.getCompetencesOfMaintainer("test07");
       assertFalse(rst.next());
    }
    @Test
    public void testFailGetCompetencesOfMaintainer() throws SQLException {
        //wrong mintainerid
       ResultSet rst=repo.getCompetencesOfMaintainer("fail");
       assertFalse(rst.next());
    }
    @Test
    public void testGetCompetenceID() {
       assertNotNull(repo.getCompetenceID(rstFull2));
    }
    @Test
    public void testEmptyGetCompetenceID() {
       assertNull(repo.getCompetenceID(rstEmpty));
    }
    @Test
    public void testGetCompetenceName() {
       assertNotNull(repo.getCompetenceName(rstFull2));
    }
    @Test
    public void testEmptyGetCompetenceName() {
       assertNull(repo.getCompetenceName(rstEmpty));
    }
    @Test
    public void testGetMaintainerID() {
       assertNotNull(repo.getMaintainerID(rstFull2));
    }
    @Test
    public void testEmptyGetMaintainerID() {
       assertNull(repo.getMaintainerID(rstEmpty));
    }
    @Test
    public void testGetMaintainerName() {
       assertNotNull(repo.getMaintainerName(rstFull2));
    }
    @Test
    public void testEmptyGetMaintainerName() {
       assertNull(repo.getMaintainerName(rstEmpty));
    }
    @Test
    public void testGetTimeSlot1() {
       assertNotNull(repo.getTimeSlot1(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot1() {
       assertNull(repo.getTimeSlot1(rstEmpty));
    }
    @Test
    public void testGetTimeSlot2() {
       assertNotNull(repo.getTimeSlot2(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot2() {
       assertNull(repo.getTimeSlot2(rstEmpty));
    }
    @Test
    public void testGetTimeSlot3() {
       assertNotNull(repo.getTimeSlot3(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot3() {
       assertNull(repo.getTimeSlot3(rstEmpty));
    }
    @Test
    public void testGetTimeSlot4() {
       assertNotNull(repo.getTimeSlot4(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot4() {
       assertNull(repo.getTimeSlot4(rstEmpty));
    }
    @Test
    public void testGetTimeSlot5() {
       assertNotNull(repo.getTimeSlot5(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot5() {
       assertNull(repo.getTimeSlot5(rstEmpty));
    }
    @Test
    public void testGetTimeSlot6() {
       assertNotNull(repo.getTimeSlot6(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot6() {
       assertNull(repo.getTimeSlot6(rstEmpty));
    }
    @Test
    public void testGetTimeSlot7() {
       assertNotNull(repo.getTimeSlot7(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot7() {
       assertNull(repo.getTimeSlot7(rstEmpty));
    }
    @Test
    public void testGetTimeSlot8() {
       assertNotNull(repo.getTimeSlot8(rstFull2));
    }
    @Test
    public void testEmptyGetTimeSlot8() {
       assertNull(repo.getTimeSlot8(rstEmpty));
    }
    @Test
    public void testGetDay() {
       assertNotNull(repo.getDay(rstFull2));
    }
    @Test
    public void testEmptyGetDay() {
       assertNull(repo.getDay(rstEmpty));
    }
    @Test
    public void testAssignActivity() {
        //test with activityId=test06, maintainedId=test05
       assertTrue(repo.assignActivity("test06","test05","Monday",22,0,0,0,0,30,30,0,0));
       this.deleteActivityTimeDivision("test06");
    }
    @Test
    public void testFailAssignActivity1() {
        //test with activityId=test06, maintainedId=test05
        //wrong activityId
       assertFalse(repo.assignActivity("fail","test05","Monday",22,0,0,0,0,30,30,0,0));
    }
    @Test
    public void testFailAssignActivity2() {
        //test with activityId=test06, maintainedId=test05
        //wrong maintainerId
       assertFalse(repo.assignActivity("test06","fail","Monday",22,0,0,0,0,30,30,0,0));
    }
    @Test
    public void testFailAssignActivity3() {
        //test with activityId=test06, maintainedId=test05
        //wrong day
       assertFalse(repo.assignActivity("test06","test05","fail",22,0,0,0,0,30,30,0,0));
    }
    @Test
    public void testFailAssignActivity4() {
        //test with activityId=test06, maintainedId=test05
        //wrong weekNumber
       assertFalse(repo.assignActivity("test06","test05","Monday",-1,0,0,0,0,30,30,0,0));
    }
    @Test
    public void testFailAssignActivity5() {
        //test with activityId=test06, maintainedId=test05
        //wrong weekNumber
       assertFalse(repo.assignActivity("test06","test05","Monday",100,0,0,0,0,30,30,0,0));
    }
    @Test
    public void testFailAssignActivity6() {
        //test with activityId=test06, maintainedId=test05
        //wrong AssignedSlot number
       assertFalse(repo.assignActivity("test06","test05","Monday",22,0,0,100,0,30,30,0,0));
    }
    @Test
    public void testFailAssignActivity7() {
        //test with activityId=test06, maintainedId=test05
        //wrong AssignedSlot number
       assertFalse(repo.assignActivity("test06","test05","Monday",22,0,0,-1,0,30,30,0,0));
    }
    @Test
    public void testGetCompetenceOfTypology() throws SQLException {
        //test with typology=Typology1
       ResultSet rst=repo.getCompetenceOfTypology("Typology1");
       assertTrue(rst.next());
    }
    @Test
    public void testFailGetCompetenceOfTypology() throws SQLException {
        //test with typology=Typology1
       ResultSet rst=repo.getCompetenceOfTypology("fail");
       assertFalse(rst.next());
    }
    @Test
    public void testUpdateWorkspceNotes() {
        //test with activityId=test06
       assertTrue(repo.updateWorkspceNotes("test06","notes"));
    }
    @Test
    public void testFailUpdateWorkspceNotes() {
        //test with activityId=test06
       assertFalse(repo.updateWorkspceNotes("fail","notes"));
    }
}
