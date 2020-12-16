/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import RepositoryTest.RepositoryTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico
 */
public abstract class RepositoryTestBase {

    
    protected ResultSet rstFull;
    protected ResultSet rstFull2;
    protected ResultSet rstEmpty;

    protected Connection conn;
    protected Statement stm;

    protected void initTestTables() {
        String query;
        query = "insert into Planner\n"
                + "values ('test01','1234');\n"
                + "\n"
                + "insert into Site\n"
                + "values ('test02','factory1','area1');\n"
                + "\n"
                + "insert into Procedure\n"
                + "values ('test03','proceduraTest','testoProcedura','ulrFileSMP');\n"
                + "\n"
                + "insert into Competence\n"
                + "values ('test04','nomeCompetenza','descrizioneCompetenza');\n"
                + "\n"
                + "insert into competenceToProcedure\n"
                + "values('test03','test04');\n"
                + "\n insert into Typology values('Typology1');\n"
                + " insert into Typology values('Typology2'); "
                + " insert into TypologyToCompetence values('test04', 'Typology1'); "
                + " insert into CompetenceToMaintainer values('test04', 'test05'); "
                + //"insert into Maintainer\n" +
                //"values ('test05','testMaintainer','1234');"+
                "insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n"
                + "interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannerID,MaintainerID,activityMaterials,activityWorkspaceNotes,plannedActivity)\n"
                + "values ('test06','descrizioneAttivita',120,true,22,'Typology1','test02','test03','test01','test05','meterialsTest','notes','PLANNED');";
        try {
            insertNewMaintainer("test05", "testMaintainer1", "1234");
            insertNewMaintainer("test07", "testMaintainer2", "1234");
            this.stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void deleteTestTables() {
        String query;
        query = "delete from competencetoprocedure where procedureid ='test03';"
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
                + " delete from Maintainer where Maintainerid = 'test07';";

        try {
            this.stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void initTestResultSet() {
        try {
            String query = "select* from \n"
                    + "MaintenanceActivity as ma \n"
                    + " full join Procedure as p on (ma.procedureID=p.procedureID )\n"
                    + " full join Site as s on (ma.siteID=s.siteID )\n"
                    + " where ma.activityid='test06' "
                    + "order by ma.activityID; ";

            rstFull = this.stm.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void initTestResultSet2() {
        try {
            String query = "select* from maintainer as m\n"
                    + "join CompetenceToMaintainer as cm on(m.maintainerid=cm.maintainerid)\n"
                    + "join Competence as c on(c.competenceID=cm.competenceid)\n"
                    + "join MaintainerAvailabilityCurrentWeek as week on(m.maintainerID = week.maintainerID)\n"
                    + "join typologytocompetence as t on(t.competenceid=c.competenceid)"
                    + " where m.maintainerID='test05' or m.maintainerID='test07' ; ";

            rstFull2 = this.stm.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void initTestResultSetEmpty() {
        try {
            //query utilizzata per ottenere 0 righe
            String query = "select* from MaintenanceActivity where activityWeekNumber<0;";
            rstEmpty = this.stm.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void insertActivity() {
        try {
            String query = "insert into MaintenanceActivity(activityID,activityDescription,activityInterventionTime,\n"
                    + "interruptibleActivity,activityWeekNumber,activityTypology,siteID,procedureID,plannerID,MaintainerID,activityMaterials,activityWorkspaceNotes,plannedActivity)\n"
                    + "values ('test99','descrizioneAttivita',120,true,22,'Typology1','test02','test03','test01','test05','meterialsTest','notes','PLANNED');";

            this.stm.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void deleteActivity() {
        try {
            String query = " delete from MaintenanceActivity where activityid = 'test99';";

            this.stm.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Funzioni per l'inserimento di un maintainer con annessa disponibilità
    protected boolean insertNewMaintainer(String id, String name, String password) {
        if (id.length() > 6) {
            System.err.println("Mainteiner id ERROR: length exceeded");
            return false;
        }
        String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Maintainer\n"
                + "values ('").append(id).append("' , '").append(name).append("' , '").append(password).append("' );");
        try {

            //SQL transaction
            conn.setAutoCommit(false);
            stm.executeUpdate(temp.toString());
            for (int i = 0; i < 7; i++) {
                insertNewMaintainerAvailability(id, days[i]);
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
        temp.append("insert into MaintainerAvailabilityCurrentWeek(maintainerID,Day, \n"
                + " TimeSlot1,TimeSlot2,TimeSlot3,TimeSlot4,TimeSlot5,TimeSlot6,TimeSlot7,TimeSlot8)\n"
                + " values('").append(maintainerID).append("','").append(day).append("',").append("60,60,60,60,60,60,60,60);");
        try {

            stm.executeUpdate(temp.toString());

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    protected boolean deleteActivityTimeDivision(String activityID) {

        String temp;
        temp = String.format("delete from ActivityTimeDivision where activityid = '%s';", activityID);
        try {

            stm.executeUpdate(temp);

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    ///-----------------------------------------------------------------------------------
    protected void setUpForTest(){
        String url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        String user = "utente_progetto_se";
        String pwd = "password";
        
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
}
