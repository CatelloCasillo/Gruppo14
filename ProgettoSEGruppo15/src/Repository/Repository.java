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
            if(stm.executeUpdate(temp.toString())!=0)
                return true;
            return false;
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

    //Sprint 2
  public ResultSet getMaintainerTable(){
         try {
            String query = "select maintainerId, maintainerName from Maintainer; ";
                   
            ResultSet rst = stm.executeQuery(query);
            return rst;
        
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ResultSet getAllMaintainersCurrentWeekDayAvailability(String day){
        //check day string
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        for(int i=0;i>7;i++){
            if(!days[i].equals(day)){
                System.err.println("ERROR: day does not exist, try one of these -> \"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\",\"Sunday\"");
                return null;
            }
        }
      
        StringBuilder temp = new StringBuilder();
        temp.append("select m.maintainerID,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8\n" +
                    " from MaintainerAvailability as ma\n" +
                    " full join Maintainer as m on (m.maintainerID=ma.maintainerID )\n" +
                    " where (ma.day='").append(day).append("' ");
	temp.append(" order by m.maintainerID; ");
        
        try {
            return stm.executeQuery(temp.toString());
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ResultSet getDayAvailability(String mainteinerID, String day){
        //check day string
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        for(int i=0;i>7;i++){
            if(!days[i].equals(day)){
                System.err.println("ERROR: day does not exist, try one of these -> \"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\",\"Sunday\"");
                return null;
            }
        }
      
        StringBuilder temp = new StringBuilder();
        temp.append("sselect ma.maintainerID,ma.day,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8\n" +
                    " from MaintainerAvailabilityCurrentWeek as ma\n" +
                    " where ma.day= '").append(day).append("' and ma.maintainerID= '").append(mainteinerID).append("';");
        try {
           return stm.executeQuery(temp.toString());
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public ResultSet getWeekAvailability(String mainteinerID){
        StringBuilder temp = new StringBuilder();
        temp.append("sselect ma.maintainerID,ma.day,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8\n" +
                    " from MaintainerAvailabilityCurrentWeek as ma\n" +
                    " where ma.maintainerID= '").append(mainteinerID).append("';");
        try {
            return stm.executeQuery(temp.toString());
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public boolean updateMaintainerAvailabilityCurrentWeek(String maintainerID, String day,
                    int timeSlot1,int timeSlot2,int timeSlot3,int timeSlot4,int timeSlot5,int timeSlot6,int timeSlot7,int timeSlot8 ){
        StringBuilder temp = new StringBuilder();
        temp.append("update MaintainerAvailabilityCurrentWeek"
        + " set");
        temp.append(" timeSlot1 =").append(timeSlot1).append(",");
        temp.append(" timeSlot2 =").append(timeSlot2).append(",");
        temp.append(" timeSlot3 =").append(timeSlot3).append(",");
        temp.append(" timeSlot4 =").append(timeSlot4).append(",");
        temp.append(" timeSlot5 =").append(timeSlot5).append(",");
        temp.append(" timeSlot6 =").append(timeSlot6).append(",");
        temp.append(" timeSlot7 =").append(timeSlot7).append(",");
        temp.append(" timeSlot8 =").append(timeSlot8).append(" ");
        temp.append(" where maintainerID = '").append(maintainerID).append("' and day= '").append(day).append("' ;");
        
         try {
            stm.executeUpdate(temp.toString());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public boolean resetMaintainerAvailabilityCurrentWeek(String maintainerID, String day){
        StringBuilder temp = new StringBuilder();
        temp.append("update MaintainerAvailabilityCurrentWeek"
        + " set");
        temp.append(" timeSlot1 = 60,");
        temp.append(" timeSlot2 = 60,");
        temp.append(" timeSlot3 = 60,");
        temp.append(" timeSlot4 = 60,");
        temp.append(" timeSlot5 = 60,");
        temp.append(" timeSlot6 = 60,");
        temp.append(" timeSlot7 = 60,");
        temp.append(" timeSlot8 = 60 ");
        temp.append(" where maintainerID = '").append(maintainerID).append("' and day= '").append(day).append("' ;");
        
         try {
            stm.executeUpdate(temp.toString());
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    public ResultSet getCompetencesOfMaintainer(String maintainerID){
      
        StringBuilder temp = new StringBuilder();
        temp.append("select c. competenceID , c.competenceName from \n" +
                    "competenceToMaintainer as cm \n" +
                    "join competence as c on (c.competenceID=cm.competenceID )\n" +
                    "where cm.MaintainerID = '").append(maintainerID).append("' ;");
        try {
            return stm.executeQuery(temp.toString());
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getCompetenceID(ResultSet rst){
        try {
            return rst.getString("CompetenceID");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getCompetenceName(ResultSet rst){
        try {
            return rst.getString("CompetenceName");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String getMaintainerID(ResultSet rst){
        try {
            return rst.getString("MaintainerID");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getMaintainerName(ResultSet rst){
        try {
            return rst.getString("MaintainerName");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot1(ResultSet rst){
        try {
            return rst.getString("TimeSlot1");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot2(ResultSet rst){
        try {
            return rst.getString("TimeSlot2");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot3(ResultSet rst){
        try {
            return rst.getString("TimeSlot3");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot4(ResultSet rst){
        try {
            return rst.getString("TimeSlot4");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot5(ResultSet rst){
        try {
            return rst.getString("TimeSlot5");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot6(ResultSet rst){
        try {
            return rst.getString("TimeSlot6");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot7(ResultSet rst){
        try {
            return rst.getString("TimeSlot7");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getTimeSlot8(ResultSet rst){
        try {
            return rst.getString("TimeSlot8");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public String getDay(ResultSet rst){
        try {
            return rst.getString("day");
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    /*
    public boolean isInitializedInMaintainerAvailability(String maintainerID, String day, int weekNumber){
        StringBuilder temp = new StringBuilder();
        temp.append("select * from MaintainerAvailability\n" +
                    "where maintainerID='").append(maintainerID).append("' and day='").append(day).append("' and weekNumber= ").append(weekNumber).append(" ;");
                
        try {
            ResultSet rst = stm.executeQuery(temp.toString());
           //return false se il result set è vuoto
            return rst.next();
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    */
    
    ///-----------Funzioni inserimento nuovo maintainer con creazione della disponibilità ----------------------------
    
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
    //------------------------------------------------------------------------------------------------
    
    public boolean assignActivity(String activityID, String maintainerID, String day, int weekNumber, 
                        int SlotAssigned1, int SlotAssigned2, int SlotAssigned3, int SlotAssigned4, int SlotAssigned5, int SlotAssigned6, int SlotAssigned7, int SlotAssigned8){
        //update of maintainerId in activity table
        StringBuilder temp1 = new StringBuilder();
        temp1.append("update MaintenanceActivity"
        + " set maintainerID = ");
        temp1.append(" '").append(maintainerID).append("'");
        temp1.append(" where activityid = ").append(" '").append(activityID).append(" ';");
        
        // store the time division of the activity
        StringBuilder temp2 = new StringBuilder();
        temp2.append("insert into ActivityTimeDivision\n" +
                     " values( ");
        temp2.append(" '").append(activityID).append("', ");
        temp2.append(" '").append(maintainerID).append("', ");
        temp2.append(" '").append(day).append("', ");
        temp2.append(weekNumber).append(", ");
        temp2.append(SlotAssigned1).append(", ");
        temp2.append(SlotAssigned2).append(", ");
        temp2.append(SlotAssigned3).append(", ");
        temp2.append(SlotAssigned4).append(", ");
        temp2.append(SlotAssigned5).append(", ");
        temp2.append(SlotAssigned6).append(", ");
        temp2.append(SlotAssigned7).append(", ");
        temp2.append(SlotAssigned8).append(" ");
        temp2.append(" );");
         try {
            //SQL transaction
            conn.setAutoCommit(false);
            stm.executeUpdate(temp1.toString());
            stm.executeUpdate(temp2.toString());
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }
}
