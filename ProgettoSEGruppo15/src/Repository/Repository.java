package Repository;

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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {
    private String url;
    private String user;
    private String pwd;
    private Connection conn;
    private Statement stm;
    
    public Repository() {
        this.url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        this.user = "utente_progetto_se";
        this.pwd = "password";
        this.setRepositoryConnection(url,user,pwd);
    }
    
    public boolean setRepositoryConnection(String url,String user, String pwd){
         try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, pwd);
            this.stm = this.conn.createStatement();
            //System.out.println("Connessione Database effettuata");
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
    public ResultSet getInformationOfMaintenanceActivity(){
       
        try {
            String query = "select* from "
                    + "MaintenanceActivity as ma "
                    + "left join Procedure as p on (ma.procedureID=p.procedureID )"
                    + "join Site as s on (ma.siteID=s.siteID )"
                    + "order by ma.activityID;";
            ResultSet rst = stm.executeQuery(query);
            return rst;
       
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public String getActivityID(ResultSet rst){
        try {
            return rst.getString("activityID");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
    }
    public String getActivityDescription(ResultSet rst){
        try {
            return rst.getString("activityDescription");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public int getActivityInterventionTime(ResultSet rst){
        try {
            return rst.getInt("activityInterventionTime");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
      
    }
    public boolean isInterruptibleActivity(ResultSet rst){
        try {
            return rst.getBoolean("interruptibleActivity");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public int getActivityWeekNumber(ResultSet rst){
        try {
            return rst.getInt("activityWeekNumber");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public String getActivityTypology(ResultSet rst){
        try {
            return rst.getString("activityTypology");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getSiteID(ResultSet rst){
        try {
            return rst.getString("siteID");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getFactorySite(ResultSet rst){
        try {
            return rst.getString("FactorySite");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public String getAreaSite(ResultSet rst){
        try {
            return rst.getString("AreaSite");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public String getProcedureID(ResultSet rst){
        try {
            return rst.getString("ProcedureID");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public String getProcedureName(ResultSet rst){
        try {
            return rst.getString("ProcedureName");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }
    public String getProcedureDescription(ResultSet rst){
        try {
            return rst.getString("ProcedureDescription");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
      public String getFileSMP(ResultSet rst){
        try {
            return rst.getString("FileSMP");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    public boolean insertNewMaintenanceActivity(String activityID,
               // String plannerID, String maintainerID, String procedureID,
                String siteID,
                String activityDescription,int activityInterventionTime,
                boolean interruptibleActivity,
                //String activityMaterials,
                int activityWeekNumber,
                String activityWorkspaceNotes,String activityTypology){
 
        StringBuilder temp = new StringBuilder();
        temp.append("insert into MaintenanceActivity(activityID, "
                //+ "plannerID,maintainerID,procedureID,"
                +"siteID,"
                + "activityDescription,activityInterventionTime,"
                + "interruptibleActivity,activityWeekNumber,"
                + "activityWorkspaceNotes,activityTypology )");
        temp.append("values(");
        temp.append("'").append(activityID).append("',");
        
       // temp.append("'").append(plannerID).append("',");
        //temp.append("'").append(maintainerID).append("',");
        temp.append("'").append(siteID).append("',");
       // temp.append("'").append(procedureID).append("',");
        
        temp.append("'").append(activityDescription).append("',");
        temp.append(" ").append(activityInterventionTime).append(",");
        temp.append(" ").append(interruptibleActivity).append(" ,");
        //temp.append("' ").append(activityMaterials).append("' ,");
        temp.append(" ").append(activityWeekNumber).append(" ,");
        temp.append("'").append(activityWorkspaceNotes).append("',");
        temp.append("'").append(activityTypology).append("' ");
        temp.append(");");
        
        //viewMaintenanceActivityTable();
        try {
            stm.executeUpdate(temp.toString());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean deleteMaintenanceActivity(String activityID){
        StringBuilder temp = new StringBuilder();
        //System.out.println(activityID);
        temp.append("delete from MaintenanceActivity"
                + " where activityid = ");
        temp.append(" '").append(activityID).append("' ;");
        //System.out.println(temp);
        
                
        try {
            stm.executeUpdate(temp.toString());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public boolean updateMaintenanceActivity(String id,String site,String typology, String description, int time, boolean inter, int week){
        StringBuilder temp = new StringBuilder();
        temp.append("update MaintenanceActivity"
        + " set activityDescription = ");
        temp.append(" '").append(description).append("',");
        temp.append(" siteID = ").append(" '").append(site).append(" ',");
        temp.append(" activityInterventionTime = ").append("  ").append(time).append(" ,");
        temp.append(" activityTypology = ").append("' ").append(typology).append(" ',");
        temp.append(" interruptibleActivity = ").append("  ").append(inter).append(" ,");
        temp.append(" activityWeekNumber = ").append("  ").append(week).append(" ");
        temp.append(" where activityid = ").append(" '").append(id).append(" ';");
        
         try {
            stm.executeUpdate(temp.toString());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean viewMaintenanceActivityTable(){
            
        try {
            String query = "select* from "
                    + "MaintenanceActivity ";
            ResultSet rst = stm.executeQuery(query);
            
            System.out.println("activityID"+"\t "+ "siteID"+"\t"+"activityDescription"+"\t"+"activityInterventionTime"+"\t"+"interruptibleActivity"+
            "\t"+"activityWeekNumber"+"\t"+"activityTypology");
            while (rst.next()) {
            String activityID = rst.getString("activityID");
            String siteID = rst.getString("siteID");
            String activityDescription = rst.getString("activityDescription");
            int activityInterventionTime = rst.getInt("activityInterventionTime");
            boolean interruptibleActivity = rst.getBoolean("interruptibleActivity");
            int activityWeekNumber = rst.getInt("activityWeekNumber");
            String activityTypology = rst.getString("activityTypology");
            
            System.out.println(activityID+"\t"+siteID+"\t"+activityDescription+"\t"+activityInterventionTime+"\t"+interruptibleActivity+
            "\t"+activityWeekNumber+"\t"+activityTypology);
            } 
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public ResultSet getSiteTable(){
         try {
            String query = "select* from Site ";
                   
            ResultSet rst = stm.executeQuery(query);
            return rst;
        
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ResultSet getCompetencesOfActivity(String activityID){
      
        StringBuilder temp = new StringBuilder();
        temp.append("select c.competenceName from \n" +
                    "MaintenanceActivity as ma \n" +
                    "join competenceToProcedure as cp on (ma.procedureID=cp.procedureID )\n" +
                    "join competence as c on (c.competenceID=cp.competenceID ) ");
        temp.append(" where ma.activityID= '").append(activityID).append("' ;");
        try {
            return stm.executeQuery(temp.toString());
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
