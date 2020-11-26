package PrimoPackege;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enrico
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {
    String url;
    String user;
    String pwd;
    Connection conn;
    Statement stm;
    public Repository() {
        this.url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        this.user = "utente_progetto_se";
        this.pwd = "password";
        this.setRepositoryConnection(url,user,pwd);
    }
    
    public void setRepositoryConnection(String url,String user, String pwd){
         try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, pwd);
            this.stm = this.conn.createStatement();
            //System.out.println("Connessione Database effettuata");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void getMaintenanceActivity(){
        try {
            
            String query = "select* from MaintenanceActivity";
            ResultSet rst = stm.executeQuery(query);
   
            while (rst.next()) {
                String activityID = rst.getString("activityID");
                String activityDescription = rst.getString("activityDescription");
                int activityInterventionTime = rst.getInt("activityInterventionTime");
                boolean interruptibleActivity = rst.getBoolean("interruptibleActivity");
                int activityWeekNumber = rst.getInt("activityWeekNumber");
                String activityTypology = rst.getString("activityTypology");
                
                System.out.println(activityID+"\t"+activityDescription+"\t"+activityInterventionTime+"\t"+interruptibleActivity+
                        "\t"+activityWeekNumber+"\t"+activityTypology);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
   
}
