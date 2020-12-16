/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositoryActivity extends RepositoryBase implements RepositoryActivityInterface {

    public RepositoryActivity() {
        super();
    }

    @Override
    public ResultSet getInformationOfMaintenanceActivity() {
        try {
            connect();
            String query = "select* from "
                    + "MaintenanceActivity as ma "
                    + "left join Procedure as p on (ma.procedureID=p.procedureID )"
                    + "join Site as s on (ma.siteID=s.siteID )"
                    + "order by ma.activityWeekNumber;";
            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getActivityID(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("activityID");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getActivityDescription(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("activityDescription");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int getActivityInterventionTime(ResultSet rst) {
        try {
            connect();
            int i = rst.getInt("activityInterventionTime");
            closeConnection();
            return i;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public boolean isInterruptibleActivity(ResultSet rst) {
        try {
            connect();
            boolean b = rst.getBoolean("interruptibleActivity");
            closeConnection();
            return b;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public int getActivityWeekNumber(ResultSet rst) {
        try {
            connect();
            int i = rst.getInt("activityWeekNumber");
            closeConnection();
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public String getActivityTypology(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("activityTypology");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getWorkSpacenotes(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("activityWorkspaceNotes");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getPlannedActivity(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("PlannedActivity");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean insertNewMaintenanceActivity(String activityID, String siteID, String activityDescription, int activityInterventionTime, boolean interruptibleActivity, int activityWeekNumber, String activityWorkspaceNotes, String activityTypology, String typeactivity) {
        StringBuilder temp = new StringBuilder();
        temp.append("insert into MaintenanceActivity(activityID, "
                + "siteID,"
                + "activityDescription,activityInterventionTime,"
                + "interruptibleActivity,activityWeekNumber,"
                + "activityWorkspaceNotes,activityTypology,plannedactivity )");
        temp.append("values(");
        temp.append("'").append(activityID).append("',");
        temp.append("'").append(siteID).append("',");
        temp.append("'").append(activityDescription).append("',");
        temp.append(" ").append(activityInterventionTime).append(",");
        temp.append(" ").append(interruptibleActivity).append(" ,");
        temp.append(" ").append(activityWeekNumber).append(" ,");
        temp.append("'").append(activityWorkspaceNotes).append("',");
        temp.append("'").append(activityTypology).append("' ,");
        temp.append(" '").append(typeactivity).append("' ");
        temp.append(");");

        try {
            connect();
            stm.executeUpdate(temp.toString());
            closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean deleteMaintenanceActivity(String activityID) {
        StringBuilder temp = new StringBuilder();
        temp.append("delete from MaintenanceActivity"
                + " where activityid = ");
        temp.append(" '").append(activityID).append("' ;");
        try {
            connect();
            if (stm.executeUpdate(temp.toString()) != 0) {
                closeConnection();
                return true;
            }
            closeConnection();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean updateMaintenanceActivity(String id, String site, String typology, String description, int time, boolean inter, int week) {
        StringBuilder temp = new StringBuilder();
        temp.append("update MaintenanceActivity"
                + " set activityDescription = ");
        temp.append(" '").append(description).append("',");
        temp.append(" siteID = ").append(" '").append(site).append("',");
        temp.append(" activityInterventionTime = ").append("  ").append(time).append(" ,");
        temp.append(" activityTypology = ").append("'").append(typology).append("',");
        temp.append(" interruptibleActivity = ").append("  ").append(inter).append(" ,");
        temp.append(" activityWeekNumber = ").append("  ").append(week).append(" ");
        temp.append(" where activityid = ").append(" '").append(id).append("';");
        try {
            connect();
            if(stm.executeUpdate(temp.toString()) != 0){
                closeConnection();
                return true;
            }
            closeConnection();
            return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ResultSet getCompetencesOfActivity(String activityID) {
        StringBuilder temp = new StringBuilder();
        temp.append("select c.competenceID,c.competenceName from \n"
                + "MaintenanceActivity as ma \n"
                + "join competenceToProcedure as cp on (ma.procedureID=cp.procedureID )\n"
                + "join competence as c on (c.competenceID=cp.competenceID ) ");
        temp.append(" where ma.activityID= '").append(activityID).append("' ;");
        try {
            connect();
            ResultSet rst = stm.executeQuery(temp.toString());
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean updateWorkspceNotes(String activityID, String activityWorkspaceNotes) {
        StringBuilder temp1 = new StringBuilder();
        temp1.append("update MaintenanceActivity"
                + " set activityWorkspaceNotes = ");
        temp1.append(" '").append(activityWorkspaceNotes).append("'");
        temp1.append(" where activityid = ").append(" '").append(activityID).append("';");
        try {
            connect();
            if(stm.executeUpdate(temp1.toString())!= 0){
                closeConnection();
                return true;
            }
            closeConnection();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
