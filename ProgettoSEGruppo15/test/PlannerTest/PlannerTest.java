package PlannerTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import PrimoPackege.Planner;
import PrimoPackege.Site;
import Repository.Repository;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Catello
 */
//Testing che presuppongo il datbase vuoto.
public class PlannerTest {
    private Connection conn;
    private Statement stm;
    
    public PlannerTest(){
        try {
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost/Progetto_SE_gruppo14", "utente_progetto_se", "password");
            this.stm = this.conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    @After
    public void deleteAll(){
        try {
            stm.executeUpdate(  "delete from competencetoprocedure;\n" +
                                "delete from MaintenanceActivity;\n" +
                                "delete from Procedure;\n" +
                                "delete from Competence;\n" +
                                "delete from Site;");
            
        } catch (SQLException ex) {
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void insertNewMaintenanceActivity() throws SQLException{
         stm.executeUpdate("insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
"								interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID)\n" +
"values ('act005','descrizioneAttivita',120,true,22,'electrical','site01','proc01');");
    }
    private void insertSite() throws SQLException{
        stm.executeUpdate("insert into Site values ('site01','factory1','area1')");
    }
    private void insertProcedure() throws SQLException{
        stm.executeUpdate("insert into Procedure values ('proc01','proceduraTest','testoProcedura');");
    }
    private void insertCompetence() throws SQLException{
        stm.executeUpdate("insert into Competence values ('comp01','nomeCompetenza','descrizioneCompetenza');");
    }
    private void insertCompetenceToProcedure() throws SQLException{
        stm.executeUpdate("insert into competenceToProcedure values('proc01','comp01');");
    }
    private String selectActivityId() throws SQLException{
        ResultSet rst=stm.executeQuery("select activityid from MaintenanceActivity where activityid='act005'");
        rst.next();
        return rst.getString("activityid");
    }
    
    
    @Test
    public void testplannerCreate(){
       Planner p=new Planner();
       assertTrue(p.getActivityList().size()==0 && p.getSiteList().size()==0);
    }
    /*
    @Test
    public void testInsertActvityNoData(){
       Planner p=new Planner();
       assertFalse(p.createActivity("act005", new Site("site01","Fisciano","Molding"), "Eletrical", "descrizioneAttività" , 120, true, 5, "Informazioni aggiuntive"));
    }*/
    @Test
    public void testUpdateActvityNoData(){
        Planner p=new Planner();
        assertFalse(p.updateActivity(0,"act005", "site01", "Eletrical", "descrizioneAttività" , 120, true, 5));
    }
    @Test
    public void testDeleteActivityNoData(){
        Planner p=new Planner();
        assertFalse(p.deleteActivity("act005", 0));
    }
    @Test
    public void testGetCompotencesActivityNoData(){
        Planner p=new Planner();
        assertEquals(p.getCompetencesList("act005").size(),0);
        
    }
    @Test
    public void testGetSelectionableAttributeNoData(){
        Planner p=new Planner();
        Object[][] mat=p.getSelectionableAttribute("40");
        assertEquals(mat.length,0);
    }
    @Test
    public void testGetMaintanceActivityNoData(){
        Planner p=new Planner();
        assertNull(p.getMaintanceActivity("act005"));
    }
    @Test
    public void testFindSiteInListNoData(){
        Planner p=new Planner();
        assertNull(p.findSiteInList("site01",p.getSiteList()));
    }
    @Test
    public void testIdControlNoData(){
        Planner p=new Planner();
        assertFalse(p.idControl("act005"));
    }
    
    @Test
    public void testCreatePlannerWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        assertTrue(p.getActivityList().size()==1 && p.getSiteList().size()==1);
    }
    @Test
    public void testGetStringInFileWithErrorPath(){
        Planner p= new Planner();
        //String []c= p.getStringInFile(new File("\\src\\Tickets"));
        assertNull(p.getStringInFile(new File("src\\Ticke")));
    }
    @Test
    public void testGetStringInFileWithNoErrorPath(){
        Planner p= new Planner();
        //String []c= p.getStringInFile(new File("\\src\\Tickets"));
        assertTrue((p.getStringInFile(new File("src\\Tickets\\ticket1.txt"))).length >0);
    }
    @Test
    public void testGetStringInFile(){
        Planner p= new Planner();
        assertEquals("EWO001", p.getStringInFile(new File("src\\Tickets\\ticket1.txt"))[0]);
    }
    /*
    @Test
    public void testInsertActivityWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        Planner p=new Planner();
        p.createActivity("act005", new Site("site01", "Factory", "area"), "Eletrical", "Descrizione", 120, true, 20, "Aggiuntivo");
        String id=this.selectActivityId();
        assertTrue(p.getActivityList().size()==1 && id.equals("act005"));
    }*/
    
    
    @Test
    public void testUpdateActivityWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        p.updateActivity(0, "act005", "site01", "Mech", "Descrizione due", 200, false, 2);
        ResultSet rst=stm.executeQuery("select * from MaintenanceActivity where activityid='act005'");
        rst.next();
        String type=rst.getString("activityTypology").trim();
        String time=rst.getString("activityInterventionTime").trim();
        assertTrue(type.equals("Mech") && time.equals("200"));
    }
    @Test(expected=SQLException.class)
    public void testDeleteActivityWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        p.deleteActivity("act005", 0);
        this.selectActivityId();
    }
    @Test
    public void testGetSelectionableAttributeWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        Object[][] mat=p.getSelectionableAttribute("22");
        String id=(String) mat[0][0];
        assertTrue(id.equals("act005"));
    }
    @Test
    public void testGetMaintanceActivityWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        assertTrue(p.getMaintanceActivity("act005").getId().equals("act005"));
    }
    @Test
    public void testFinfInSiteWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        assertTrue(p.findSiteInList("site01",p.getSiteList()).getId().equals("site01"));
    }
    @Test
    public void testIdControlWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        assertTrue(p.idControl("act005"));
    }
    
    @Test
    public void testGetCompetenceWithData() throws SQLException{
        this.insertSite();
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity();
        Planner p=new Planner();
        System.out.println("InizioStampa");
        System.out.println(p.getCompetencesList("act005").size());
        System.out.println("FineStampa");
        assertTrue(p.getCompetencesList("act005").get(0).equals("nomeCompetenza"));   
    }
    
    
    
}
