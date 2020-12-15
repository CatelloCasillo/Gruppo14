/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

/**
 *
 * @author Gabriella
 */
public interface RepositoryAvailabilityInterface {
    public ResultSet getAllMaintainersCurrentWeekDayAvailability(String day);
    public ResultSet getDayAvailability(String mainteinerID, String day);
    public int[] getTimeslots(ResultSet rst);
    public ResultSet getWeekAvailability(String mainteinerID);
    public boolean updateMaintainerAvailabilityCurrentWeek(String maintainerID, String day,
                    int timeSlot1,int timeSlot2,int timeSlot3,int timeSlot4,int timeSlot5,int timeSlot6,int timeSlot7,int timeSlot8 );
    public boolean assignActivity(String activityID, String maintainerID, String day, int weekNumber, 
                        int SlotAssigned1, int SlotAssigned2, int SlotAssigned3, int SlotAssigned4, int SlotAssigned5, 
                        int SlotAssigned6, int SlotAssigned7, int SlotAssigned8);    
//èp+6pèppp6public boolean resetMaintainerAvailabilityCurrentWeek(String maintainerID, String day);
   /* public String getTimeSlot1(ResultSet rst);
    public String getTimeSlot2(ResultSet rst);
    public String getTimeSlot3(ResultSet rst);
    public String getTimeSlot4(ResultSet rst);
    public String getTimeSlot5(ResultSet rst);
    public String getTimeSlot6(ResultSet rst);
    public String getTimeSlot7(ResultSet rst);
    public String getTimeSlot8(ResultSet rst);
    public String getDay(ResultSet rst);*/
    
}
