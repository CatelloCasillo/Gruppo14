/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

public interface RepositoryAvailabilityInterface {
    /**
     * 
     * @param day
     * @return A ResultSet of Availability of all maintainers in the current week on the specified day.
     * ResulSet composed by :[maintainerID,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot5,timeSlot6,timeSlot7,timeSlot8] 
     */
    public ResultSet getAllMaintainersCurrentWeekDayAvailability(String day);
    
    /**
     * 
     * @param mainteinerID
     * @param day
     * @return A ResultSet of Availability of the Maintainer that correspond to the ID in the current week on the specified day.
     * ResulSet composed by :[maintainerID,day,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot5,timeSlot6,timeSlot7,timeSlot8] 
     */
    public ResultSet getDayAvailability(String mainteinerID, String day);
    
    /**
     * 
     * @param rst
     * @return An array that contains the Availability of a maintainer. 
     * The value at a specified index of the array represent the timeSlot of the maintainer's availability.
     */
    public int[] getTimeslots(ResultSet rst);
    
    /**
     * 
     * @param mainteinerID
     * @return A resultSet that represent the availability of a maintainer of the week.
     *  ResulSet composed by :[maintainerID,day,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot5,timeSlot6,timeSlot7,timeSlot8] 
     */
    public ResultSet getWeekAvailability(String mainteinerID);
    
    /**
     * 
     * @param maintainerID
     * @param day
     * @param timeSlot1
     * @param timeSlot2
     * @param timeSlot3
     * @param timeSlot4
     * @param timeSlot5
     * @param timeSlot6
     * @param timeSlot7
     * @param timeSlot8
     * @return True, if the update is executed. False otherwise.
     */
    public boolean updateMaintainerAvailabilityCurrentWeek(String maintainerID, String day,
            int timeSlot1, int timeSlot2, int timeSlot3, int timeSlot4, int timeSlot5, int timeSlot6, int timeSlot7, int timeSlot8);
    
    /**
     * 
     * @param activityID
     * @param maintainerID
     * @param day
     * @param weekNumber
     * @param SlotAssigned1
     * @param SlotAssigned2
     * @param SlotAssigned3
     * @param SlotAssigned4
     * @param SlotAssigned5
     * @param SlotAssigned6
     * @param SlotAssigned7
     * @param SlotAssigned8
     * @return True, if the activity is essigned. False otherwise.
     */
    public boolean assignActivity(String activityID, String maintainerID, String day, int weekNumber,
            int SlotAssigned1, int SlotAssigned2, int SlotAssigned3, int SlotAssigned4, int SlotAssigned5,
            int SlotAssigned6, int SlotAssigned7, int SlotAssigned8);
    /**
     * 
     * @param maintainerID
     * @param day
     * @return True, if the availability of the maintainer is set to default values. False otherwise.
     */
    boolean resetMaintainerAvailabilityCurrentWeek(String maintainerID, String day);

    /**
     * 
     * @param activityID
     * @return An array that represent how the time required by activity is divided between the time slots of the day.
     * Any index of array refers to the time slot of the day.
     */
    public int[] getAssignedTimeslotsOfActivity(String activityID);
    /**
     * 
     * @param activityID
     * @return True, if an assigned activity is correctly deleted. False otherwise.
     */
    public boolean deleteAssignedActivity(String activityID);
    
    /**
     * 
     * @param maintainerId
     * @param day
     * @param weeknumber
     * @param timeSlotIndex
     * @return A resultSet with all the id of the activities present in that time slot of the day.
     */
    public ResultSet getActivitiesOfTimeSlot(String maintainerId, String day, int weeknumber, int timeSlotIndex);

}
