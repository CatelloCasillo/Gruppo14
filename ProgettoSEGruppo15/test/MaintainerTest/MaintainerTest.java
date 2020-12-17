/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaintainerTest;

import PlannerTest.PlannerTest;
import Maintainer.Maintainer;
import Repository.RepositoryMaintainer;
import Repository.RepositoryMaintainerInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Catello
 */
public class MaintainerTest {
    private Connection conn;
    private Statement stm;
  
    
    public MaintainerTest() {
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
    private void insertMaintainer(String id, String name){
        RepositoryMaintainerInterface r= new RepositoryMaintainer();
        r.insertNewMaintainer(id, name, "1234");
    }
    // Test: Recupero dell'id di un certo manutentore da una sua istanza
    // Risultato attesso: l' id restituito deve coincidere con quello usato per creare l'istanza del manutentore
    @Test
    public void testGetId(){
        Maintainer m= new Maintainer("mant01","Pippo");
        assertEquals(m.getId(),"mant01");
    }
    // Test: Recupero del nome di un certo manutentore da una sua istanza
    // Risultato attesso: il nome restituito deve coincidere con quello usato per creare l'istanza del manutentore
    @Test
    public void testGetName(){
        Maintainer m= new Maintainer("mant01","Pippo");
        assertEquals(m.getName(),"Pippo");
    }
    // Test: Acquisizione della disponibilità percentuale di un manutentore non ancora memorizzato nel Database
    // Risultato attesso: Fallimento dell'Operazione (-1% indica ovviamnete un codice di errore)
    @Test
    public void testGetDayAvailabilityNoData(){
        Maintainer m= new Maintainer("mant01","Pippo");
        String disp = m.getDayAvailability("Monday");
        assertEquals(disp,"-1%");
    }
     // Test: Acquisizione della disponibilità percentuale di un manutentore memorizzato nel database 
     //       ma per il quale si specifica un giorno della settimana nel formato sbagliato
    // Risultato attesso: Fallimento dell'Operazione (-1% indica ovviamnete un codice di errore)
    @Test
    public void testGetDayAvailabilityWrongDayWeek(){
        Maintainer m= new Maintainer("mant01","Pippo");
        this.insertMaintainer(m.getId(), m.getName());
        String disp = m.getDayAvailability("MONDAY");
        assertEquals(disp,"-1%");
    }
    // Test: Acquisizione della disponibilità percentuale di un manutentore memorizzato nel database 
    // Risultato attesso: Successo dell'Operazione (inizialmente il manutentore ha una disponibilità del 100%)
    @Test
    public void testGetDayAvailabilitySucces(){
        Maintainer m= new Maintainer("mant01","Pippo");
        this.insertMaintainer(m.getId(), m.getName());
        String disp = m.getDayAvailability("Monday");
        assertEquals(disp,"100%");
    }
    // Test: Acquisizione della disponibilità per ogni fascia oraria (in minuti) 
    //       di un manutentore non ancora memorizzato nel Database
    // Risultato attesso: Fallimento dell'Operazione (-1 min indica ovviamnete un codice di errore)
    @Test
    public void testGetSlotsAvailabilityNoData(){
        Maintainer m= new Maintainer("mant01","Pippo");
        Object [] disp = m.getSlotsAvailability("Monday");
        boolean disp0=disp[0].toString().equals("-1 min");
        boolean disp1=disp[1].toString().equals("-1 min");
        boolean disp2=disp[2].toString().equals("-1 min");
        boolean disp3=disp[3].toString().equals("-1 min");
        boolean disp4=disp[4].toString().equals("-1 min");
        boolean disp5=disp[5].toString().equals("-1 min");
        boolean disp6=disp[6].toString().equals("-1 min");
        boolean disp7=disp[7].toString().equals("-1 min");
        assertTrue(disp0 && disp1 && disp2 && disp3 && disp4 && disp5 && disp6 && disp7);
    }
    // Test: Acquisizione della disponibilità per ogni fascia oraria (in minuti) 
    //       di un manutentore memorizzato nel Database ma per il quale si specifica un giorno della settimana in cui ottenrla in un formato errato
    // Risultato attesso: Fallimento dell'Operazione (-1 min indica ovviamnete un codice di errore)
    @Test
    public void testGetSlotsAvailabilityWrongDayWeek(){
        Maintainer m= new Maintainer("mant01","Pippo");
        this.insertMaintainer(m.getId(), m.getName());
        Object [] disp = m.getSlotsAvailability("MONDAY");
        boolean disp0=disp[0].toString().equals("-1 min");
        boolean disp1=disp[1].toString().equals("-1 min");
        boolean disp2=disp[2].toString().equals("-1 min");
        boolean disp3=disp[3].toString().equals("-1 min");
        boolean disp4=disp[4].toString().equals("-1 min");
        boolean disp5=disp[5].toString().equals("-1 min");
        boolean disp6=disp[6].toString().equals("-1 min");
        boolean disp7=disp[7].toString().equals("-1 min");
        assertTrue(disp0 && disp1 && disp2 && disp3 && disp4 && disp5 && disp6 && disp7);
    }
    // Test: Acquisizione della disponibilità per ogni fascia oraria (in minuti) 
    //       di un manutentore memorizzato nel Database 
    // Risultato attesso: Successp dell'Operazione (Inizialmente ogni manutentore ha un disponibilità di 60min per ogni fascia oraria)
    @Test
    public void testGetSlotsAvailabilitySucces(){
        Maintainer m= new Maintainer("mant01","Pippo");
        this.insertMaintainer(m.getId(), m.getName());
        Object [] disp = m.getSlotsAvailability("Monday");
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
    
    
}
