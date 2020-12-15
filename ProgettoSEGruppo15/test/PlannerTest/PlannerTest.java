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
    public void insertNewMaintenanceActivity(String id,String site, String type) throws SQLException{
         stm.executeUpdate("insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n" +
"								interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID, plannedActivity)\n" +
"values ('"+id+"','descrizioneAttivita',120,true,22,'"+type+"','"+site+"','proc01',true);");
    }
    private void insertSite(String id) throws SQLException{
        stm.executeUpdate("insert into Site values ('"+id+"','factory1','area1')");
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
    private void insertTypology(String name) throws SQLException{
        stm.executeUpdate("insert into Typology values('"+name+"');");
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
       Planner p=new Planner();
       assertTrue(p.getActivityList().size()==0 && p.getSiteList().size()==0);
    }
    //Test: creazione di un Planner con il database non vuoto in particolare con una tipologia
    //      un sito e un'attività già presenti nel database
    //Risultato atteso: Le liste dei siti e delle attività inserite devono essere riempiti con l'attività e il sito inserito. 
    @Test
    public void testCreatePlannerWithData() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.getActivityList().get(0).getId().equals("act005") && p.getSiteList().get(0).getId().equals("site01"));
    }
    //Test: Inserimento di un attività con il database vuoto
    //Risultato atteso: createActivity deve segnale con il suo valore di ritorno a false la non riusciuta
    //                  dell'operazione per via della mancaza di tipo e sito
    @Test
    public void testInsertActvityNoData(){
       Planner p=new Planner();
       assertFalse(p.createActivity("act005", new Site("site01","Fisciano","Molding"), "Eletrical", "descrizioneAttività" , 120, true, 5, "Informazioni aggiuntive"));
    }
    //Test:  Inserimento di un'attività nel database e nella lista mantenuta dal planner
    //       quando sono già presenti il sito e la tipologia.
    //Risultato atteso: Inserimento nel database dell'attività creata.
    @Test
    public void testInsertActivityWithDataSucces() throws SQLException{
        this.insertSite("site01");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertTypology("Eletrical");
        Planner p=new Planner();
        p.createActivity("act005", new Site("site01", "Factory", "area"), "Eletrical", "Descrizione", 120, true, 20, "Aggiuntivo");
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
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertTypology("Eletrical");
        Planner p=new Planner();
        assertFalse(p.createActivity("act005", new Site("site02", "Factory", "area"), "Eletrical", "Descrizione", 120, true, 20, "Aggiuntivo"));
    }
    //Test: Inserimento di un attività con il sito non presente nel db
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testInsertActivityWithDataTypeMissing() throws SQLException{
        this.insertSite("site01");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertTypology("Eletrical");
        Planner p=new Planner();
        assertFalse(p.createActivity("act005", new Site("site01", "Factory", "area"), "mechanical", "Descrizione", 120, true, 20, "Aggiuntivo"));
    }
    //Test: Aggiornamento di un attività di manutenzione con il database vuoto
    //Risultato atteso: Fallimento dell'operazione(in questo caso il metodo restituisce false).
    @Test
    public void testUpdateActvityNoData(){
        Planner p=new Planner();
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
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01", "eletrical");
        Planner p=new Planner();
        p.updateActivity(0, "act005", "site02", "mechanical", "Modifica", 60, false, 50);
        ResultSet rst=stm.executeQuery("select * from MaintenanceActivity where activityid='act005'");
        rst.next();
        String site= this.getActivitySite(rst);
        String type = this.getActivityType(rst);
        String description = this.getActivityDescription(rst);
        Integer time = this.getActivityTime(rst);
        Integer week = this.getActivityWeek(rst);
        boolean isInterrupible = this.getActivityInterrupible(rst);
        assertTrue(type.equals("mechanical") && time==60 && week==50 && description.equals("Modifica") && site.equals("site02") && !isInterrupible);
    }
    //Test: Aggiornamento multicampo di un' attività cambiando il sito ad esso associato con uno non presente nel database
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testUpdateActivityWithDataSiteMissing() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertTypology("mechanical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01", "eletrical");
        Planner p=new Planner();
        assertFalse(p.updateActivity(0, "act005", "site02", "mechanical", "Modifica", 60, false, 50));
    }
    //Test: Aggiornamento multicampo di un' attività cambiando il sito ad esso associato con uno non presente nel database
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testUpdateActivityWithDataTypeMissing() throws SQLException{
        this.insertSite("site01");
        this.insertSite("site02");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01", "eletrical");
        Planner p=new Planner();
        assertFalse(p.updateActivity(0, "act005", "site02", "mechanical", "Modifica", 60, false, 50));
    }
    //Test: Cancellazione di un'attività con il database vuoto
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testDeleteActivityNoData(){
        Planner p=new Planner();
        assertFalse(p.deleteActivity("act005", 0));
    }
    //Test: Cancellazione dell'unica attività presente nel database
    //Risultato atteso: Riusciuta dell'operazione
    @Test
    public void testDeleteActivityWithDataOneActvity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.deleteActivity("act005", 0));
    }
    //Test: Cancellazione di un' attività che non è l'unica presente nel database
    //Risultato atteso: Riusciuta dell'operazione
    @Test
    public void testDeleteActivityWithDataMoreActvity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.deleteActivity("act005", 0));
    }
    
    //Test: Ricerca in base all'id di un'attività mantenuta nella lista del planner Planner
    //Risultato atteso: Fallimento
    @Test
    public void testGetMaintanceActivityNoData(){
        Planner p=new Planner();
        assertNull(p.getMaintanceActivity("act005"));
    }
    //Test: Ricerca di un'attvità presente in base all'id in una lista che contiene solo quell'attività.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testGetMaintanceActivityWithOneActivity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.getMaintanceActivity("act005").getId().equals("act005"));
    }
    //Test: Ricerca di un'attvità non presente in base all'id in una lista non vuota.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testGetMaintanceActivityWithDataFailure() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertNull(p.getMaintanceActivity("act010"));
    }
    
    //Test: Ricerca di un'attvità presente nella prima posizione in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testGetMaintanceActivityWithDataSuccessFirstPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.getMaintanceActivity("act005").getId().equals("act005"));
    }
    
    //Test: Ricerca di un'attvità presente nell'ultima posizione in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testGetMaintanceActivityWithDataSuccessLastPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.getMaintanceActivity("act008").getId().equals("act008"));
    }
    
    //Test: Ricerca di un'attvità presente in una posizione intermedia in base all'id in una lista non vuota.
    //Risultato atteso: L'id della'attività recuperata deve essere uguale a quella che si è ricercato.
    @Test
    public void testGetMaintanceActivityWithDataSuccessMiddlePosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.getMaintanceActivity("act007").getId().equals("act007"));
    }
    //Test: Ricerca di un sito in base all'id in una lista do siti vuota.
    //Risultato atteso: Fallimento dell'operazione
    @Test
    public void testFindSiteInListNoData(){
        Planner p=new Planner();
        assertNull(p.findSiteInList("site01",p.getSiteList()));
    }
    
    //Test: Ricerca di un sito presente in base all'id in una lista che contiene solo quel sito.
    //Risultato atteso: L'id del sito recuperato deve coincidere con quello ricercato.
    @Test
    public void testFindSiteInListOneActivity() throws SQLException{
        this.insertSite("site01");
        Planner p=new Planner();
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
        Planner p=new Planner();
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
        Planner p=new Planner();
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
        Planner p=new Planner();
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
        Planner p=new Planner();
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
        Planner p=new Planner();
        assertNull(p.findSiteInList("site05", p.getSiteList()));
    }
    //Test: verifica se un'attività con un certo id è presente in una lista di attività vuota
    //Risultato atteso: Attività non trovata
    @Test
    public void testIdControlNoData(){
        Planner p=new Planner();
        assertFalse(p.idControl("act005"));
    }
    //Test: verifica se un'attività con un certo id è presente in una lista con una sola attività
    //Risultato atteso: Attività non trovata
    @Test
    public void testIdControlWithOneActivity() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.idControl("act005"));
    }
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che non la contiene
    //Risultato atteso: Attività non trovata
    @Test
    public void testIdControlWithDataFailure() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertFalse(p.idControl("act001"));
    }
   
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che la contiene nella sua prima posizione
    //Risultato atteso: Attività trovata
    @Test
    public void testIdControlWithDataSuccessFirstPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.idControl("act005"));
    }
    
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che la contiene nella sua ultima posizione
    //Risultato atteso: Attività trovata
    @Test
    public void testIdControlWithDataSuccessLastPosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.idControl("act008"));
    }
    
    //Test: Verifica se un'attività con un certo id è presente in una lista non vuota che la contiene nella in una sua posizione intermedia
    //Risultato atteso: Attività trovata
    @Test
    public void testIdControlWithDataSuccessMiddlePosition() throws SQLException{
        this.insertSite("site01");
        this.insertTypology("eletrical");
        this.insertCompetence();
        this.insertProcedure();
        this.insertCompetenceToProcedure();
        this.insertNewMaintenanceActivity("act005","site01","eletrical");
        this.insertNewMaintenanceActivity("act006","site01","eletrical");
        this.insertNewMaintenanceActivity("act007","site01","eletrical");
        this.insertNewMaintenanceActivity("act008","site01","eletrical");
        Planner p=new Planner();
        assertTrue(p.idControl("act006"));
    }
    //Test: Selezionare le attività assegnabili dal planner in un dato giorno da una lista di attività vuota
    //Risultato atteso: matrice con una sola righe di stringhe 
    @Test
    public void testGetSelectionableActvityNoData(){
        Planner p=new Planner();
        Object[][] mat=p.getSelectionableActvity("40");
        assertTrue(mat.length==0 && mat[0][0]=="" && mat[0][1]=="" && mat[0][2]=="" && mat[0][3]=="" && mat[0][4]=="");
    }
    
    
    
    /*
    
    //Test:
    //Risultato atteso:
    @Test
    public void testGetCompotencesActivityNoData(){
        Planner p=new Planner();
        assertEquals(p.getCompetencesList("act005").size(),0);
    }
    
    
   
    
    //Test:
    //Risultato atteso:
    @Test
    public void testGetStringInFileWithErrorPath(){
        Planner p= new Planner();
        //String []c= p.getStringInFile(new File("\\src\\Tickets"));
        assertNull(p.getStringInFile(new File("\\src\\Ticke")));
    }
    //Test:
    //Risultato atteso:
    public void testGetStringInFileWithNoErrorPath(){
        Planner p= new Planner();
        //String []c= p.getStringInFile(new File("\\src\\Tickets"));
        assertTrue((p.getStringInFile(new File("\\src\\Tickets"))).length >0);
    }
    
    
    
    
    
    
    
    
    //Test:
    //Risultato atteso:
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
    */
    
    
}
