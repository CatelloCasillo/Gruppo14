package PlannerTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MaintenanceActivity.MaintanceActivityFactory.Category;
import Planner.PlannerConcrete;
import Planner.PlannerInterface;
import MaintenanceActivity.Site;
import Repository.RepositoryMaintainer;
import Repository.RepositoryMaintainerInterface;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

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
                                "delete from Maintainer;\n" +
                                "delete from Typology;\n" +
                                "delete from TypologyToCompetence;\n" +
                                "delete from CompetenceToMaintainer;\n" +
                                "delete from MaintainerAvailabilityCurrentWeek ;\n" +
                                "delete from ActivityTimeDivision ;\n" +
                                "delete from MaintenanceActivity;\n" +
                                "delete from Procedure;\n" +
                                "delete from Competence;\n" +
                                "delete from Site;");
            
        } catch (SQLException ex) {
            Logger.getLogger(PlannerTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void insertNewMaintenanceActivity(String id,String site, String type,String proc) throws SQLException{
        if(proc==null)
            stm.executeUpdate("insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
"								interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID, plannedActivity)\n" +
"values ('"+id+"','descrizioneAttivita',120,true,22,'"+type+"','"+site+"',null,'PLANNED');");
        else
         stm.executeUpdate("insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
"								interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID, plannedActivity)\n" +
"values ('"+id+"','descrizioneAttivita',120,true,22,'"+type+"','"+site+"','"+proc+"','PLANNED');");
    }
    public void insertNewEWO(String id,String site, String type) throws SQLException{
         stm.executeUpdate("insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
"								interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID, plannedActivity)\n" +
"values ('"+id+"','descrizioneAttivita',120,true,22,'"+type+"','"+site+"',null,'EWO');");
    }
    private void insertSite(String id) throws SQLException{
        stm.executeUpdate("insert into Site values ('"+id+"','factory1','area1')");
    }
    private void insertProcedure(String proc) throws SQLException{
        stm.executeUpdate("insert into Procedure values ('"+proc+"','proceduraTest','testoProcedura');");
    }
    private void insertCompetence(String comp, String name) throws SQLException{
        stm.executeUpdate("insert into Competence values ('"+comp+"','"+name+"','descrizioneCompetenza');");
    }
    private void insertCompetenceToProcedure(String proc, String comp) throws SQLException{
        stm.executeUpdate("insert into competenceToProcedure values('"+proc+"','"+comp+"');");
    }
     private void insertCompetenceToMaintainer(String mantainer, String comp) throws SQLException{
        stm.executeUpdate("insert into CompetenceToMaintainer values('"+comp+"', '"+mantainer+"');");
    }
    private void insertCompetenceToTypology(String type, String comp) throws SQLException{
        stm.executeUpdate("insert into TypologyToCompetence values('"+comp+"', '"+type+"');");
    }
    private void insertTypology(String name) throws SQLException{
        stm.executeUpdate("insert into Typology values('"+name+"');");
    }
    private void insertMaintainer(String id, String name){
        RepositoryMaintainerInterface r= new RepositoryMaintainer();
        r.insertNewMaintainer(id, name, "1234");
    }
    private String getActivityId(ResultSet rst) throws SQLException{
        return rst.getString("activityid");
    }
    private String getActivityDescription(ResultSet rst) throws SQLException{
        return rst.getString("activityDescription");
    }
    private int getActivityTime(ResultSet rst) throws SQLException{
        return rst.getInt("activityInterventionTime");
    }
    private boolean getActivityInterrupible(ResultSet rst) throws SQLException{
        return rst.getBoolean("interruptibleActivity");
    }
    private int getActivityWeek(ResultSet rst) throws SQLException{
        return rst.getInt("activityWeekNumber");
    }
    private String getActivityType(ResultSet rst) throws SQLException{
        return rst.getString("activityTypology");
    }
    private String getActivitySite(ResultSet rst) throws SQLException{
        return rst.getString("siteID");
    }
    //Test:
    //Risultato atteso:
    
    
    //Test: creazione di un Planner con il database vuoto.
    //Risultato atteso: La lista dei siti e delle attività mantenute dal planner deve essere vuota
    @Test
    public void testplannerCreate(){
       PlannerInterface p=new PlannerConcrete();
       assertTrue(p.getActivityList().size()==0 && p.getSiteList().size()==0);
    }
    //Test: creazione di un Planner con il database non vuoto in particolare con una tipologia
    //      un sito e un'attività già presenti nel database
    //Risultato atteso: Le liste dei siti e delle attività inserite devono essere riempiti con l'attività e il sito inserito. 
    @Test
    public void testCreatePlannerWithData() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.getActivityList().get(0).getId().equals("act005") && p.getSiteList().get(0).getId().equals("site01"));
    }
    //Test: Inserimento di un attività con il database vuoto
    //Risultato atteso: createActivity deve segnale con il suo valore di ritorno a false la non riusciuta
    //                  dell'operazione per via della mancaza di tipo e sito
    @Test
    public void testInsertActvityNoData(){
       PlannerInterface p=new PlannerConcrete();
       assertFalse(p.createActivity(Category.PLANNED,"act005", new Site("site01","Fisciano","Molding"), "Eletrical", "descrizioneAttività" , 120, true, 5, "Informazioni aggiuntive"));
    }
    //Test:  Inserimento di un'attività nel database e nella lista mantenuta dal planner
    //       quando sono già presenti il sito e la tipologia.
    //Risultato atteso: Inserimento nel database dell'attività creata.
    @Test
    public void testInsertActivityWithDataSucces() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("Eletrical");
        PlannerInterface p=new PlannerConcrete();
        p.createActivity(Category.PLANNED,"act005", new Site("site01", "Factory", "area"), "Eletrical", "Descrizione", 120, true, 20, "Aggiuntivo");
        ResultSet rst=stm.executeQuery("select activityid from MaintenanceActivity where activityid='act005'");
        rst.next();
        String id = this.getActivityId(rst);
        assertTrue(p.getActivityList().size()==1 && id.equals("act005"));
    }
    //Test: Inserimento di un attività con il sito non presente nel db
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testInsertActivityWithDataSiteMissing() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("Eletrical");
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.createActivity(Category.PLANNED,"act005", new Site("site02", "Factory", "area"), "Eletrical", "Descrizione", 120, true, 20, "Aggiuntivo"));
    }
    //Test: Inserimento di un attività con il sito non presente nel db
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testInsertActivityWithDataTypeMissing() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("Eletrical");
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.createActivity(Category.PLANNED,"act005", new Site("site01", "Factory", "area"), "mechanical", "Descrizione", 120, true, 20, "Aggiuntivo"));
    }
    //Test: Aggiornamento di un attività di manutenzione con il database vuoto
    //Risultato atteso: Fallimento dell'operazione(in questo caso il metodo restituisce false).
    @Test
    public void testUpdateActvityNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.updateActivity(0,"act005", "site01", "Eletrical", "descrizioneAttività" , 120, true, 5));
    }
    //Test: Aggiornamento di un'attività gia presente nel database per tutti i suoi campi selezionando una tipologia 
    //      e un sito già presente nel database.
    //Risultato atteso: L'operazione termina con successo e i campi sul database vengono effetivamente aggiornati
    @Test
    public void testUpdateActivityWithDataSucces() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertTypology("eletrical");
        this.insertTypology("mechanical");
        this.insertNewMaintenanceActivity("act005","site01", "eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        p.updateActivity(0, "act005", "site02", "mechanical", "Modifica", 60, false, 50);
        ResultSet rst=stm.executeQuery("select * from MaintenanceActivity where activityid='act005'");
        rst.next();
        String site= this.getActivitySite(rst);
        String type = this.getActivityType(rst);
        String description = this.getActivityDescription(rst);
        Integer time = this.getActivityTime(rst);
        Integer week = this.getActivityWeek(rst);
        boolean isInterrupible = this.getActivityInterrupible(rst);
        System.out.println(""+site+" "+ type+" "+description+" "+time+" "+week+" "+isInterrupible+" ");
        assertTrue(type.equals("mechanical") && time==60 && week==50 && description.equals("Modifica") && site.equals("site02") && !isInterrupible);
    }
    //Test: Aggiornamento multicampo di un' attività cambiando il sito ad esso associato con uno non presente nel database
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testUpdateActivityWithDataSiteMissing() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertTypology("mechanical");
        this.insertNewMaintenanceActivity("act005","site01", "eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.updateActivity(0, "act005", "site02", "mechanical", "Modifica", 60, false, 50));
    }
    //Test: Aggiornamento multicampo di un' attività cambiando il sito ad esso associato con uno non presente nel database
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testUpdateActivityWithDataTypeMissing() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01", "eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.updateActivity(0, "act005", "site02", "mec", "Modifica", 60, false, 50));
    }
    //Test: Cancellazione di un'attività con il database vuoto
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testDeleteActivityNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.deleteActivity("act005", 0));
    }
    //Test: Cancellazione dell'unica attività presente nel database
    //Risultato atteso: Riusciuta dell'operazione
    @Test
    public void testDeleteActivityWithDataOneActvity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.deleteActivity("act005", 0));
    }
    //Test: Cancellazione di un' attività che non è l'unica presente nel database
    //Risultato atteso: Riusciuta dell'operazione
    @Test
    public void testDeleteActivityWithDataMoreActvity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.deleteActivity("act005", 0));
    }
    
    //Test: Ricerca in base all'id di un'attività mantenuta nella lista del planner Planner
    //Risultato atteso: Fallimento
    @Test
    public void testGetMaintanceActivityNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertNull(p.getMaintanceActivity("act005"));
    }
    //Test: Ricerca di un'attvità presente in base all'id in una lista che contiene solo quell'attività.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testGetMaintanceActivityWithOneActivity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.getMaintanceActivity("act005").getId().equals("act005"));
    }
    //Test: Ricerca di un'attvità non presente in base all'id in una lista non vuota.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testGetMaintanceActivityWithDataFailure() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertNull(p.getMaintanceActivity("act010"));
    }
    
    //Test: Ricerca di un'attvità presente nella prima posizione in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testGetMaintanceActivityWithDataSuccessFirstPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.getMaintanceActivity("act005").getId().equals("act005"));
    }
    
    //Test: Ricerca di un'attvità presente nell'ultima posizione in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testGetMaintanceActivityWithDataSuccessLastPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.getMaintanceActivity("act008").getId().equals("act008"));
    }
    
    //Test: Ricerca di un'attvità presente in una posizione intermedia in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testGetMaintanceActivityWithDataSuccessMiddlePosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.getMaintanceActivity("act007").getId().equals("act007"));
    }
    //Test: Ricerca di un sito in base all'id in una lista do siti vuota.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testFindSiteInListNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertNull(p.findSiteInList("site01",p.getSiteList()));
    }
    
    //Test: Ricerca di un sito presente in base all'id in una lista che contiene solo quel sito.
    //Risultato atteso: L'id del sito recuperato deve coincidere con quello ricercato.
    @Test
    public void testFindSiteInListOneActivity() throws SQLException{
        this.insertSite("site01");
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.findSiteInList("site01:area01", p.getSiteList()).getId().equals("site01"));
    }
    //Test: Ricerca di un sito non presente in base all'id in una lista non vuota.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testindSiteInListWithDataFailure() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertSite("site03");
        this.insertSite("site04");        
        PlannerInterface p=new PlannerConcrete();
        assertNull(p.findSiteInList("site05:area01", p.getSiteList()));
    }
    
    //Test: Ricerca di un'sito presente nella prima posizione in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testfindSiteInListWithDataSuccessFirstPosition() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertSite("site03");
        this.insertSite("site04");        
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.findSiteInList("site01:area01", p.getSiteList()).getId().equals("site01"));
    }
    
    //Test: Ricerca di un sito presente nell'ultima posizione in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testfindSiteInListWithDataWithDataSuccessLastPosition() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertSite("site03");
        this.insertSite("site04");        
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.findSiteInList("site04:area04", p.getSiteList()).getId().equals("site04"));
    }
    
    //Test: Ricerca di un sito presente in una posizione intermedia in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testFindSiteInListWithDataWithDataSuccessMiddlePosition() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertSite("site03");
        this.insertSite("site04");        
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.findSiteInList("site02:area02", p.getSiteList()).getId().equals("site02"));
    }
    //Test: Ricerca di un sito nella lista passandoo al metodo una stringa formattata in modo errato
    //Risultato atteso: Fallimento dell'operazione.
    @Test
    public void testFindSiteInListWithDataWithDataFailureWrongFormatted() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertSite("site03");
        this.insertSite("site04");        
        PlannerInterface p=new PlannerConcrete();
        assertNull(p.findSiteInList("site05", p.getSiteList()));
    }
    //Test: verifica se un'attività con un certo id è presente in una lista di attività vuota
    //Risultato atteso: Attività non trovata
    @Test
    public void testIdControlNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.idControl("act005"));
    }
    //Test: verifica se un'attività con un certo id è presente in una lista con una sola attività
    //Risultato atteso: Attività non trovata
    @Test
    public void testIdControlWithOneActivity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.idControl("act005"));
    }
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che non la contiene
    //Risultato atteso: Attività non trovata
    @Test
    public void testIdControlWithDataFailure() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.idControl("act001"));
    }
   
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che la contiene nella sua prima posizione
    //Risultato atteso: Attività trovata
    @Test
    public void testIdControlWithDataSuccessFirstPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.idControl("act005"));
    }
    
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che la contiene nella sua ultima posizione
    //Risultato atteso: Attività trovata
    @Test
    public void testIdControlWithDataSuccessLastPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.idControl("act008"));
    }
    
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che la contiene nella in una sua posizione intermedia
    //Risultato atteso: Attività trovata
    @Test
    public void testIdControlWithDataSuccessMiddlePosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act008","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.idControl("act006"));
    }
    //Test: Selezionare le attività assegnabili dal planner in un dato giorno da una lista di attività vuota
    //Risultato atteso: matrice con una sola riga di stringhe 
    @Test
    public void testGetSelectionableActvityNoData(){
        PlannerInterface p=new PlannerConcrete();
        Object[][] mat=p.getSelectionableActvity("40");
        assertTrue(mat.length==1 && mat[0][0].toString().equals("") && mat[0][1].toString().equals("") && mat[0][2].toString().equals("") && mat[0][3].toString().equals("") && mat[0][4].toString().equals(""));
    }
    //Test: Selezionare le attività assegnabili dal planner in una data settimana da una lista di attività dove sono presenti solo EWO
    //Risultato atteso: matrice con una sola riga di stringhe vuote (perchè gli EWO non sono assgnabili come una planned anche se la settimana è corretta)
    @Test
    public void testGetSelectionableActvityAllEWO() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewEWO("ewo005","site01","eletrical");
        this.insertNewEWO("ewo006","site01","eletrical");
        this.insertNewEWO("ewo007","site01","eletrical");
        PlannerInterface p=new PlannerConcrete();
        Object[][] mat=p.getSelectionableActvity("22");
        assertTrue(mat.length==1 && mat[0][0].toString().equals("") && mat[0][1].toString().equals("") && mat[0][2].toString().equals("") && mat[0][3].toString().equals("") && mat[0][4].toString().equals(""));
    }
    //Test: Selezionare le attività assegnabili dal planner in una data settimana da una lista di attività dove sono presenti solo attività in altre settimane
    //Risultato atteso: matrice con una sola riga di stringhe vuote 

     @Test
    public void testGetSelectionableActvityWrongWeek() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewEWO("ewo005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        Object[][] mat=p.getSelectionableActvity("30");
        assertTrue(mat.length==1 && mat[0][0].toString().equals("") && mat[0][1].toString().equals("") && mat[0][2].toString().equals("") && mat[0][3].toString().equals("") && mat[0][4].toString().equals(""));
    }
    //Test: Selezionare le attività assegnabili dal planner in una data settimana da una lista di attività dove sono presenti solo varie attività
    //Risultato atteso: seleziona  solo quelle nella settimana corrente che non sono EWO 
    @Test
    public void testGetSelectionableActvitySucces() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewEWO("ewo005","site01","eletrical");
        this.insertNewEWO("ewo006","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act007","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        Object[][] mat=p.getSelectionableActvity("22");
        System.out.println(mat[0][0].toString()+" "+mat[1][0].toString()+" "+mat.length);
        assertTrue(mat.length==2  && mat[0][0].toString().equals("act006") && mat[1][0].toString().equals("act007"));
    }
    //Test: Selezionare le competenze relative ad un certa attività con la lista delle attività vuote 
    //Risultato atteso: La lista delle competenze restituita deve essere vuota
    @Test
    public void testGetCompetencesActivityNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertEquals(p.getCompetencesList("act005").size(),0);
    }
    //Test: Selezionare la lista di compentenze di una attività da un lista che contiene più attività
    //Risultato atteso: La lista deve contenere le due competenze relative ad act007
    @Test
    public void testGetCompetenceWithDataSucces() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence("comp01","svitare");
        this.insertCompetence("comp02","aggiustare");
        this.insertCompetence("comp03", "riscaldare");
        this.insertProcedure("proc01");
        this.insertProcedure("proc02");
        this.insertCompetenceToProcedure("proc01","comp01");
        this.insertCompetenceToProcedure("proc01","comp02");
        this.insertCompetenceToProcedure("proc02","comp02");
        this.insertCompetenceToProcedure("proc02","comp03");
        this.insertNewMaintenanceActivity("act008","site01","eletrical","proc02");
        this.insertNewMaintenanceActivity("act007","site01","eletrical","proc01");
        PlannerInterface p=new PlannerConcrete();
        ArrayList<String> competences = p.getCompetencesList("act007");
        System.out.println(competences.size()+" "+competences.get(0)+" " +competences.get(1));
        assertTrue(competences.size()==2 && competences.get(0).equals("comp01") && competences.get(1).equals("comp02"));   
    }
    //Test: Selezionare la lista di compentenze di una attività da un lista che contiene che no contiene l'attività desiderata
    //Risultato atteso: La lista delle compotenze deve essere vuota
    @Test
    public void testGetCompetenceWithDataFailure() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence("comp01","svitare");
        this.insertCompetence("comp02","aggiustare");
        this.insertCompetence("comp03", "riscaldare");
        this.insertProcedure("proc01");
        this.insertProcedure("proc02");
        this.insertCompetenceToProcedure("proc01","comp01");
        this.insertCompetenceToProcedure("proc01","comp02");
        this.insertCompetenceToProcedure("proc02","comp02");
        this.insertCompetenceToProcedure("proc02","comp03");
        this.insertNewMaintenanceActivity("act008","site01","eletrical","proc02");
        this.insertNewMaintenanceActivity("act007","site01","eletrical","proc01");
        PlannerInterface p=new PlannerConcrete();
        assertEquals(p.getCompetencesList("act001").size(),0);
    }
    //Test: Selezionare la lista di compentenze relativa ad un certa tipologia con il database vuoto
    //Risultato atteso: La lista delle competenze deve essere vuota
    @Test
    public void testGetCompetenceTypologyNoData(){
        PlannerInterface p=new PlannerConcrete();
        assertEquals(p.getCompetenceTypology("eletrical").size(),0);
    }
    //Test: Selezionare la lista di compentenze associate ad una certa tipologia che non è presente nel database
    //Risultato atteso: La lista delle competenze restituita deve essere vuota
    @Test
    public void testGetCompetenceTypologyWithDataFailure() throws SQLException{
        this.insertTypology("eletrical");
        this.insertTypology("mechanical");
        this.insertCompetence("comp01","svitare");
        this.insertCompetence("comp02","aggiustare");
        this.insertCompetence("comp03", "riscaldare");
        this.insertCompetenceToTypology("eletrical", "comp01");
        this.insertCompetenceToTypology("eletrical", "comp02");
        this.insertCompetenceToTypology("mechanical", "comp03");
        PlannerInterface p=new PlannerConcrete();
        assertEquals(p.getCompetenceTypology("hydraulic").size(),0);
    }
    //Test: Selezionare la lista di compentenze associate ad una certa tipologia che non è presente nel database
    //Risultato atteso: La lista delle competenze restituita deve essere vuota
    @Test
    public void testGetCompetenceTypologyWithDataSucces() throws SQLException{
        this.insertTypology("eletrical");
        this.insertTypology("mechanical");
        this.insertCompetence("comp01","svitare");
        this.insertCompetence("comp02","aggiustare");
        this.insertCompetence("comp03", "riscaldare");
        this.insertCompetenceToTypology("eletrical", "comp01");
        this.insertCompetenceToTypology("eletrical", "comp02");
        this.insertCompetenceToTypology("mechanical", "comp03");
        PlannerInterface p=new PlannerConcrete();
        ArrayList<String> competences = p.getCompetenceTypology("eletrical");
        assertTrue(competences.size()==2 && competences.get(0).equals("svitare") && competences.get(1).equals("aggiustare"));
    }
    //Test: creazione del calendario della disponibilità settimanale di tutti i maintainer con il database vuoto
    //Risultato atteso: Il calendario deve essere vuoto
    @Test
    public void testGetMaintainerWeekCalendarNoData() {
        PlannerInterface p=new PlannerConcrete();
        assertEquals(p.getMaintainerWeekCalendar("act005").length,0);
    }
    //Test: creazione del calendario della disponibilità settimanale di tutti i maintainer con il database che ne contiene solo 1
    //Risultato atteso: La prima riga del calendario deve contenere il nome dell'unico manutentore 
    //                  e la sua skill compliance rispetto l'attività selezionata
    
    @Test
    public void testGetMaintainerWeekCalendarOneMaitainer() throws SQLException{
        
        this.insertMaintainer("mant01", "Pippo");
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence("comp01","svitare");
        this.insertCompetence("comp02","aggiustare");
        this.insertProcedure("proc01");
        this.insertCompetenceToProcedure("proc01","comp01");
        this.insertCompetenceToProcedure("proc01","comp02");
        this.insertCompetenceToMaintainer("mant01", "comp01");
        this.insertNewMaintenanceActivity("act007","site01","eletrical","proc01");
        PlannerInterface p=new PlannerConcrete();
        Object[][] calendar=p.getMaintainerWeekCalendar("act007");
        System.out.println(calendar.length +" "+ calendar[0][0].toString() +" "+ calendar[0][1].toString());
        assertTrue(calendar.length==1 && calendar[0][0].toString().equals("Pippo") && calendar[0][1].toString().equals("1/2"));
    }
    
    //Test: creazione del calendario della disponibilità settimanale di tutti i maintainer con il database che ne contiene più di uno
    //Risultato atteso: La prima riga del calendario deve contenere il nome del primo manutentore 
    //                  mentre e la sua skill compliance rispetto l'attività selezionata.
    //                  Stesse informazioni per la seconda riga ma rispetto al secondo manutentore
    
    @Test
    public void testGetMaintainerWeekCalendarMoreMaitainer() throws SQLException{
        
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence("comp01","svitare");
        this.insertCompetence("comp02","aggiustare");
        this.insertProcedure("proc01");
        this.insertCompetenceToProcedure("proc01","comp01");
        this.insertCompetenceToProcedure("proc01","comp02");
        this.insertCompetenceToMaintainer("mant01", "comp01");
        this.insertNewMaintenanceActivity("act007","site01","eletrical","proc01");
        PlannerInterface p=new PlannerConcrete();
        Object[][] calendar=p.getMaintainerWeekCalendar("act007");
        boolean bln1=calendar[0][0].toString().equals("Pippo") && calendar[0][1].toString().equals("1/2");
        boolean bln2=calendar[1][0].toString().equals("Pluto") && calendar[1][1].toString().equals("0/2");
        System.out.println(calendar.length +" "+ calendar[1][0].toString() +" "+ calendar[1][1].toString());
        assertTrue(calendar.length==2 && bln1 && bln2);
    }
    //Test: Ottieni la dispibalità giornaliera del maintaier per ogni fascia oraria con il database vuoto
    //Risultato attesso: Il vettore restituito conterrà tutti null
    @Test
    public void testGetMaintainerDailyAvailabilityNoData() {
       PlannerInterface p=new PlannerConcrete();
        Object[] disp=p.getMaintainerDailyAvailability("mant01","Monday");
        assertNull(disp[0]);
    }
    //Test: Ottieni la disponibilità giornaliera del matunetore per ogni fascia oraria di un manutentore non presente del database
    //Risultato attesso: Il vettore restituito conterrà tutti null
    @Test
    public void testGetMaintainerDailyAvailabilityWrongMaintainer() {
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        PlannerInterface p=new PlannerConcrete();
        Object[] disp=p.getMaintainerDailyAvailability("mant03","Monday");
        assertNull(disp[0]);
    }
    //Test: Ottieni la disponibilità giornaliera del manutentore per ogni fascia oraria, di un manutentore presente del database
    //Risultato attesso: Il vettore rstituito deve contenere tutti "60 min"( visto che la sua disponibilità non è stata modificata
    @Test
    public void testGetMaintainerDailyAvailabilitySucces() {
        
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertMaintainer("mant03", "Paperino");
        PlannerInterface p=new PlannerConcrete();
        Object[] disp=p.getMaintainerDailyAvailability("mant03","Friday");
        boolean disp0=disp[0].toString().equals("60 min");
        boolean disp1=disp[1].toString().equals("60 min");
        boolean disp2=disp[2].toString().equals("60 min");
        boolean disp3=disp[3].toString().equals("60 min");
        boolean disp4=disp[4].toString().equals("60 min");
        boolean disp5=disp[5].toString().equals("60 min");
        boolean disp6=disp[6].toString().equals("60 min");
        boolean disp7=disp[7].toString().equals("60 min");
        assertTrue(disp0 && disp1 && disp2 && disp3 && disp4 && disp5 && disp6 && disp7);
    }
    //Test: Ottieni il manutentore presente che occupa una certa posizione in una lista quando il database è vuoto
    //Risultato attesso: Viene restituio null (visto che non esiste proprio la lista di manutentori)
    @Test
    public void testGetSelectedMaintainerNoData() {
        PlannerInterface p=new PlannerConcrete();
        assertNull(p.getSelectedMaintainer(0));
    }
    //Test: Ottieni il manutentore presente che occupa una certa posizione in una lista quando il database è non vuoto 
    //      ma viene specificato un'indice negativo 
    //Risultato attesso: Viene restituio null (l'indicizzazione della lista parte da 0)
    @Test
    public void testGetSelectedMaintainerNegativeIndex() {
     
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertMaintainer("mant03", "Paperino");
        PlannerInterface p=new PlannerConcrete();
        p.getMaintainerWeekCalendar("");
        assertNull(p.getSelectedMaintainer(-1));
    }
    //Test: Ottieni il manutentore presente che occupa una certa posizione in una lista quando il database è non vuoto 
    //      ma viene specificato un indice maggiore del numero dei manutentori memorizzati
    //Risultato attesso: Viene restituio null (l'indicizzazione della lista non oltre il numero dei manutentori meno 1)
    @Test
    public void testGetSelectedMaintainerOutRangeIndex() {
        
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertMaintainer("mant03", "Paperino");
        PlannerInterface p=new PlannerConcrete();
        p.getMaintainerWeekCalendar("");
        assertNull(p.getSelectedMaintainer(3));
    }
    //Test: Ottieni il manutentore presente che occupa una certa posizione in una lista quando il database è non vuoto 
    //      e viene specificato un indice corrispondente al primo manutentore memorizzato
    //Risultato atteso: manutentore presente nella prima posizione della lista
    @Test
    public void testGetSelectedMaintainerSuccesFirstPosition() {
        
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertMaintainer("mant03", "Paperino");
        PlannerInterface p=new PlannerConcrete();
        p.getMaintainerWeekCalendar("");
        assertTrue(p.getSelectedMaintainer(0).getId().equals("mant01"));
    }
   //Test: Ottieni il manutentore presente che occupa una certa posizione in una lista quando il database è non vuoto 
    //      e viene specificato un indice corrispondente all'ultimo manutentore memorizzato
    //Risultato atteso: manutentore presente nell'ultima posizione della lista
    @Test
    public void testGetSelectedMaintainerSuccesMiddlePosition() {
        
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertMaintainer("mant03", "Paperino");
        PlannerInterface p=new PlannerConcrete();
        p.getMaintainerWeekCalendar("");
        assertTrue(p.getSelectedMaintainer(1).getId().equals("mant02"));
    }
    //Test: Ottieni il manutentore presente che occupa una certa posizione in una lista quando il database è non vuoto 
    //      e viene specificato un indice corrispondente nè al primo nè all'ultimo manutentore memorizzato
    //Risultato atteso: manutentore presente nell' ultima posizione della lista
    @Test
    public void testGetSelectedMaintainerSuccesLastPosition() {
        
      
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pluto");
        this.insertMaintainer("mant03", "Paperino");
        PlannerInterface p=new PlannerConcrete();
        p.getMaintainerWeekCalendar("");
        assertTrue(p.getSelectedMaintainer(2).getId().equals("mant03"));
    }
    //Test: Aggiornamento della disponibilità di un manutentore con il database vuoto
    //Risultato atteso: Fallimento dell'operazione(Non si può aggiornare la disponibilità di un maitainer che non esiste)
    @Test
    public void testUpdateMaintainerAvailabilityNoData() {
        PlannerInterface p=new PlannerConcrete();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<8;i++)
            list.add(i, 40);
        assertFalse(p.updateMaintainerAvailability("mant01","Monday",list));
    }
    //Test: Aggiornamento della disponibilità di un manutentore non esistente con il database non vuoto
    //Risultato atteso: Fallimento dell'operazione(Non si può aggiornare la disponibilità di un maitainer che non esiste)
    @Test
    public void testUpdateMaintainerAvailabilityWrongMaitainer() {
        
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<8;i++)
            list.add(i, 40);
        assertFalse(p.updateMaintainerAvailability("mant02","Monday",list));
    }
    //Test: Aggiornamento della disponibilità di un manutentore esistente con il database non vuoto ma con un valore per le fasce orarie troppo grande
    //Risultato atteso: Fallimento dell'operazione(Le fasce orarie possono essere al massimo di 60 min)
    @Test
    public void testUpdateMaintainerAvailabilityAvailabilityOutLimit() {
        
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<8;i++)
            list.add(i, 80);
        assertFalse(p.updateMaintainerAvailability("mant01","Monday",list));
    }
    //Test: Aggiornamento della disponibilità di un manutentore esistente con il database non vuoto ma con un valore per le fasce orarie negativo
    //Risultato atteso: Fallimento dell'operazione(Le fasce orarie non possono durare meno di 0 minuti)
    @Test
    public void testupdateMaintainerAvailabilityNegativeAvailability() {
        
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<8;i++)
            list.add(i, -40);
        assertFalse(p.updateMaintainerAvailability("mant01","Monday",list));
    }
    //Test: Aggiornamento della disponibilità di un manutentore esistente con il database non vuoto ma con un valore corretto per le fasce orarie
    //Risultato atteso: Successo dell'operazione
    @Test
    public void testupdateMaintainerAvailabilitySucces() {
 
        this.insertMaintainer("mant01", "Pippo");
        this.insertMaintainer("mant02", "Pippo");
        this.insertMaintainer("mant03", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<8;i++)
            list.add(i, 40);
        assertTrue(p.updateMaintainerAvailability("mant01","Monday",list));
    }
    //Test: Assegnare un'attivita a un maintainer con il database vuoto
    //Risultato attesso: Fallimento dell'operazione( Non esiste nè l'attività da assegnare nè il manutentore al quale assegnarla)
    @Test
    public void testAssignActivityFractionNoData() {
        PlannerInterface p=new PlannerConcrete();
        int [] fractions = new int[8];
        for(int i=0;i<8;i++)
            fractions[i] = 40;
        assertFalse(p.assignActivityFraction("act001","mant01","Monday",50,fractions));
    }
    //Test: Assegnare un'attivita non esistente a un maintainer 
    //Risultato attesso: Fallimento dell'operazione( Non esiste l'attività da assegnare quindi non è assegnabile)
    @Test
    public void testAssignActivityFractionWrongActivity() throws SQLException {
  
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        int [] fractions = new int[8];
        for(int i=0;i<8;i++)
            fractions[i] = 40;
        assertFalse(p.assignActivityFraction("act001","mant01","Monday",50,fractions));
    }
    //Test: Assegnare un'attivita esistente a un maintainer non esistente
    //Risultato attesso: Fallimento dell'operazione( Non esiste il manutentore a cui assegnare l'attività quindi non è assegnabile)
    @Test
    public void testAssignActivityFractionWrongMaintainer() throws SQLException {
    
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        int [] fractions = new int[8];
        for(int i=0;i<8;i++)
            fractions[i] = 40;
        assertFalse(p.assignActivityFraction("act001","mant02","Monday",50,fractions));
    }
    //Test: Assegnare un'attivita a un maintainer in un numero di settimana che non esiste nel caleario gregoriano
    //Risultato attesso: Fallimento dell'operazione( Non esiste la settimane durante la quale il manutentore dovrebbe svolgere l'attività
    //                   l'attività quindi non è assegnabile)
    @Test
    public void testAssignActivityFractionWrongWeekNumber() throws SQLException {
      
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        int [] fractions = new int[8];
        for(int i=0;i<8;i++)
            fractions[i] = 40;
        boolean bln1 = p.assignActivityFraction("act001","mant02","Monday",60,fractions);
        boolean bln2 = p.assignActivityFraction("act001","mant02","Monday",-20,fractions);
        assertFalse(bln1 || bln2);
    }
    //Test: Assegnare un'attivita a un maintainer inn un giorno della settimana formattato in modo non previsto
    //Risultato attesso: Fallimento dell'operazione( Il database supporta solo determinate formattazioni per i giorni della settimana)
    @Test
    public void testAssignActivityFractionWrongDayWeek() throws SQLException {
     
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        int [] fractions = new int[8];
        for(int i=0;i<8;i++)
            fractions[i] = 40;
        assertFalse(p.assignActivityFraction("act001","mant01","MONDAY",50,fractions));
    }
    //Test: Assegnare un'attivita esistente a un maintainer esistente ma l'aggiornamento delle frazioni di ora associate ad ogni attività sono errate
    //Risultato attesso: Fallimento dell'operazione(Le frazioni di attivatà devono durare tra gli 0 e i 60 minuti)
    @Test
    public void testAssignActivityFractionWrongFractions() throws SQLException {
        
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act005","site01","eletrical",null);
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        int [] fractions1 = new int[8];
        int [] fractions2 = new int[8];
        for(int i=0;i<8;i++)
            fractions1[i] = 80;
        for(int i=0;i<8;i++)
            fractions2[i] = -20;
        boolean bln1 = p.assignActivityFraction("act001","mant02","Monday",50,fractions1);
        boolean bln2 = p.assignActivityFraction("act001","mant02","Monday",50,fractions2);
        assertFalse(bln1 || bln2);
    }
    //Test: assegnazione di un'attività esistente a un manutentore esistente con tutti i paramentri specificati correttamente
    //Risultato attesso: Operatorzione riusciuta.
    @Test
    public void testAssignActivitySuccess() throws SQLException {
       
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act001","site01","eletrical",null);
        this.insertMaintainer("mant01", "Pippo");
        PlannerInterface p=new PlannerConcrete();
        int [] fractions = new int[8];
        for(int i=0;i<8;i++)
            fractions[i] = 40;
        assertTrue(p.assignActivityFraction("act001","mant01","Monday",50,fractions));
    }
    //Test: Aggiornamento delle note di un'attività con un dato id con il database vuoto
    //Risultato atteso: Fallimento dell'operazione( Se l'attività non esiste non possono essere aggiornate le sue note)
    @Test
    public void testUpdateNotesNoData() {
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.updateNotes("act005","NuoveNote"));
    }
    //Test: Aggiornamento delle note di un'attività che non esiste
    //Risultato atteso: Fallimento dell'operazione( Se l'attività non esiste non possono essere aggiornate le sue note)
    @Test
    public void testUpdateNotesWrongActivity() throws SQLException{
   
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act001","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertFalse(p.updateNotes("act005","NuoveNote"));
    }
    //Test: Aggiornamento delle note di un'attività che esistente
    //Risultato atteso: Successo dell'operazione
 
    @Test
    public void testUpdateNotesSucces() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertNewMaintenanceActivity("act001","site01","eletrical",null);
        this.insertNewMaintenanceActivity("act002","site01","eletrical",null);
        PlannerInterface p=new PlannerConcrete();
        assertTrue(p.updateNotes("act001","NuoveNote"));
    }
    
    //Test:
    //Risultato atteso:
    @Test
    public void testGetStringInFileWithErrorPath(){
        PlannerInterface p= new PlannerConcrete();
        //String []c= p.getStringInFile(new File("\\src\\Tickets"));
        assertNull(p.getStringInFile(new File("src\\Ticke")));
    }
    //Test:
    //Risultato atteso:
    public void testGetStringInFileWithNoErrorPath(){
        PlannerInterface p= new PlannerConcrete();
        //String []c= p.getStringInFile(new File("\\src\\Tickets"));
        assertTrue((p.getStringInFile(new File("src\\Tickets\\ticket1.txt"))).length >0);
    }
    
    @Test
    public void testGetStringInFile(){
        PlannerInterface p= new PlannerConcrete();
        assertEquals("EWO001", p.getStringInFile(new File("src\\Tickets\\ticket1.txt"))[0]);
    }
   
}
