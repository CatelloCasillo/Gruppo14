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


public class RepositoryAvailability extends RepositoryBase implements RepositoryAvailabilityInterface {

    public RepositoryAvailability() {
        super();
    }

    @Override
    public ResultSet getAllMaintainersCurrentWeekDayAvailability(String day) {
        //check day string
        String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i > 7; i++) {
            if (!days[i].equals(day)) {
                System.err.println("ERROR: day does not exist, try one of these -> \"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\",\"Sunday\"");
                return null;
            }
        }

        StringBuilder temp = new StringBuilder();
        temp.append("select m.maintainerID,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8\n"
                + " from MaintainerAvailabilityCurrentWeek as ma\n"
                + " full join Maintainer as m on (m.maintainerID=ma.maintainerID )\n"
                + " where ma.day='").append(day).append("' ");
        temp.append(" order by m.maintainerID; ");

        try {
            connect();
            ResultSet rst = stm.executeQuery(temp.toString());
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ResultSet getDayAvailability(String mainteinerID, String day) {
        //check day string
        String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i > 7; i++) {
            if (!days[i].equals(day)) {
                System.err.println("ERROR: day does not exist, try one of these -> \"Monday\",\"Tuesday\",\"Wednesday\",\"Thursday\",\"Friday\",\"Saturday\",\"Sunday\"");
                return null;
            }
        }

        StringBuilder temp = new StringBuilder();
        temp.append("select ma.maintainerID,ma.day,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8\n"
                + " from MaintainerAvailabilityCurrentWeek as ma\n"
                + " where ma.day= '").append(day).append("' and ma.maintainerID= '").append(mainteinerID).append("';");
        try {
            connect();
            ResultSet rst = stm.executeQuery(temp.toString());
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int[] getTimeslots(ResultSet rst) {
        String base = "timeslot";
        int timeVector[] = new int[8];
        try {
            rst.next();
            for (int i = 1; i <= 8; i++) {
                timeVector[i - 1] = rst.getInt("" + base + i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return new int[8];
        }
        return timeVector;
    }

    @Override
    public ResultSet getWeekAvailability(String mainteinerID) {
        StringBuilder temp = new StringBuilder();
        temp.append("select ma.maintainerID,ma.day,ma.TimeSlot1,ma.TimeSlot2,ma.TimeSlot3,ma.TimeSlot4,ma.TimeSlot5,ma.TimeSlot6,ma.TimeSlot7,ma.TimeSlot8\n"
                + " from MaintainerAvailabilityCurrentWeek as ma\n"
                + " where ma.maintainerID= '").append(mainteinerID).append("';");
        try {
            connect();
            ResultSet rst = stm.executeQuery(temp.toString());
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean updateMaintainerAvailabilityCurrentWeek(String maintainerID, String day, int timeSlot1, int timeSlot2, int timeSlot3, int timeSlot4, int timeSlot5, int timeSlot6, int timeSlot7, int timeSlot8) {
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
            connect();
            if (stm.executeUpdate(temp.toString()) != 0) {
                closeConnection();
                return true;
            }
            closeConnection();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean assignActivity(String activityID, String maintainerID, String day, int weekNumber, int SlotAssigned1, int SlotAssigned2, int SlotAssigned3, int SlotAssigned4, int SlotAssigned5, int SlotAssigned6, int SlotAssigned7, int SlotAssigned8) {
        //update of maintainerId in activity table
        StringBuilder temp1 = new StringBuilder();
        temp1.append("update MaintenanceActivity"
                + " set maintainerID = ");
        temp1.append(" '").append(maintainerID).append("'");
        temp1.append(" where activityid = ").append(" '").append(activityID).append("';");

        // store the time division of the activity
        StringBuilder temp2 = new StringBuilder();
        temp2.append("insert into ActivityTimeDivision\n"
                + " values( ");
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
            connect();
            //SQL transaction
            conn.setAutoCommit(false);
            stm.executeUpdate(temp1.toString());
            stm.executeUpdate(temp2.toString());
            conn.commit();
            conn.setAutoCommit(true);
            closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean resetMaintainerAvailabilityCurrentWeek(String maintainerID, String day) {
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
            connect();
            if (stm.executeUpdate(temp.toString()) != 0) {
                closeConnection();
                return true;
            }
            closeConnection();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //funzioni che ritornano il tempo distribuito nei vari intervalli temporali di un'attivita assegnata ad un maintainer
    private ResultSet getAssignedTimeSlotOfActivity(String activityID) {
        String query;
        query = String.format("select SlotAssigned1, SlotAssigned2, SlotAssigned3, SlotAssigned4, SlotAssigned5, SlotAssigned6, SlotAssigned7, SlotAssigned8\n"
                + "from ActivityTimeDivision \n"
                + "where activityId='%s';", activityID);

        try {
            connect();
            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int[] getAssignedTimeslotsOfActivity(String activityID) {
        ResultSet rst = getAssignedTimeSlotOfActivity(activityID);
        String base = "SlotAssigned";
        int timeVector[] = new int[8];
        try {
            if (rst.next()) {
                for (int i = 1; i <= 8; i++) {
                    timeVector[i - 1] = rst.getInt("" + base + i);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return new int[8];
        }
        return timeVector;
    }
    //----------------------------------------------------------------

    @Override
    public ResultSet getActivitiesOfTimeSlot(String maintainerId, String day, int weeknumber, int timeSlotIndex) {
        if (timeSlotIndex > 9 || timeSlotIndex < 0) {
            return null;
        }
        if (weeknumber > 53 || weeknumber < 0) {
            return null;
        }
        String query;
        query = String.format("select activityId from ActivityTimeDivision\n"
                + "where maintainerid='%s'and day='%s' and weekNumber=%d and slotassigned%d>0;", maintainerId, day, weeknumber, timeSlotIndex);

        try {
            connect();
            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private ResultSet getActivityTimeDivision(String activityID) {
        String query;
        query = String.format("select * from ActivityTimeDivision\n"
                + " where activityId='%s';", activityID);

        try {
            connect();
            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean deleteAssignedActivity(String activityID) {
        try {

            //array contenente il tempo assegnato all'attivita nei vari slot temporali
            int timeActivity[] = getAssignedTimeslotsOfActivity(activityID);

            ResultSet rst1 = getActivityTimeDivision(activityID);
            if (!rst1.next()) {
                return false;
            }
            String day = rst1.getString("day");
            String maintainerID = rst1.getString("maintainerID");

            ResultSet rst2 = getDayAvailability(maintainerID, day);
            if (rst2 == null) {
                return false;
            }

            //indica la disponiblita del maintainer in uno specifico giorno separando le fascie orarie tramite l'indice dell'array
            int timeMaintainer[] = getTimeslots(rst2);

            int[] newMaintainerAvailability = new int[8];
            //calcolo della nuova disponibilita del maintainer tenendo conto che un'attivita a lui assegnata è stata rimossa
            for (int i = 0; i < 8; i++) {
                newMaintainerAvailability[i] = timeActivity[i] + timeMaintainer[i];
            }

            String query1 = String.format(" delete from MaintenanceActivity"
                    + " where activityid = '%s' ;", activityID);
            StringBuilder query2 = new StringBuilder();
            query2.append("update MaintainerAvailabilityCurrentWeek"
                    + " set");
            query2.append(" timeSlot1 =").append(newMaintainerAvailability[0]).append(",");
            query2.append(" timeSlot2 =").append(newMaintainerAvailability[1]).append(",");
            query2.append(" timeSlot3 =").append(newMaintainerAvailability[2]).append(",");
            query2.append(" timeSlot4 =").append(newMaintainerAvailability[3]).append(",");
            query2.append(" timeSlot5 =").append(newMaintainerAvailability[4]).append(",");
            query2.append(" timeSlot6 =").append(newMaintainerAvailability[5]).append(",");
            query2.append(" timeSlot7 =").append(newMaintainerAvailability[6]).append(",");
            query2.append(" timeSlot8 =").append(newMaintainerAvailability[7]).append(" ");
            query2.append(" where maintainerID = '").append(maintainerID).append("' and day= '").append(day).append("' ;");

            connect();
            //SQL transaction
            conn.setAutoCommit(false);
            stm.executeUpdate(query1);
            stm.executeUpdate(query2.toString());
            conn.commit();
            conn.setAutoCommit(true);
            closeConnection();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(RepositoryAvailability.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
