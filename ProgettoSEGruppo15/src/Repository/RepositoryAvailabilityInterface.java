/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

public interface RepositoryAvailabilityInterface {
    /**
     * Get the availability of all maintainer in the given day and divided in the time slots that repesent the hours of the work day.
     * ResulSet composed by :[maintainerID,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot5,timeSlot6,timeSlot7,timeSlot8] 
     * @param day A day of week. It may be: "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday".
     * @return A ResultSet of Availability of all maintainers in the current week on the specified day.
     */
    public ResultSet getAllMaintainersCurrentWeekDayAvailability(String day);
    
    /**
     * Get the availability of a maintainer specified by the given ID in the given day and divided in the time slots that repesent the hours of the work day.
     * ResulSet composed by :[maintainerID,day,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot5,timeSlot6,timeSlot7,timeSlot8] 
     * @param mainteinerID
     * @param day A day of week. It may be: "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday".
     * @return A ResultSet of Availability of the Maintainer that correspond to the ID in the current week on the specified day.
     */
    public ResultSet getDayAvailability(String mainteinerID, String day);
    
    /**
     * Get an array of the time slots that represent the hours of the work day that specified the availability of a maintainer.
     * The value at a specified index of the array represent the timeSlot of the maintainer's availability.
     * @param rst A ResultSet that has to contain the timeSlot parameter of a maintainer
     * @return An array that contains the Availability of a maintainer. 
     * 
     */
    public int[] getTimeslots(ResultSet rst);
    
    /**
     * Get the availability of a maintainer specified by the given ID in the current week and divided in the time slots that repesent the hours of the work day.
     * ResulSet composed by :[maintainerID,day,timeSlot1,timeSlot2,timeSlot3,timeSlot4,timeSlot5,timeSlot6,timeSlot7,timeSlot8] 
     * @param mainteinerID The ID of the Maintainer
     * @return A resultSet that represent the availability of a maintainer of the week.
     * 
     */
    public ResultSet getWeekAvailability(String mainteinerID);
    
    /**
     * Update the availability of a Maintainer in the Database.
     * @param maintainerID The ID of the Maintainer
     * @param day A day of week. It may be: "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday".
     * @param timeSlot1 1st hour of the work day of a Maintainer.
     * @param timeSlot2 2nd hour of the work day of a Maintainer.
     * @param timeSlot3 3rd hour of the work day of a Maintainer.
     * @param timeSlot4 4th hour of the work day of a Maintainer.
     * @param timeSlot5 5th hour of the work day of a Maintainer.
     * @param timeSlot6 6th hour of the work day of a Maintainer.
     * @param timeSlot7 7th hour of the work day of a Maintainer.
     * @param timeSlot8 8th hour of the work day of a Maintainer.
     * @return True, if the update is executed. False otherwise.
     */
    public boolean updateMaintainerAvailabilityCurrentWeek(String maintainerID, String day,
            int timeSlot1, int timeSlot2, int timeSlot3, int timeSlot4, int timeSlot5, int timeSlot6, int timeSlot7, int timeSlot8);
    
    /**
     * Assign an Activity to a Maintainer and divide the time assigned of the Activity in the correspond table on Database.
     * @param activityID The ID of Activity to assign.
     * @param maintainerID The Maintainer to wich the Activity has to be assigned.
     * @param day A day of week. It may be: "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday".
     * @param weekNumber The number of the week in the current year. It can be between 1 and 52 both incluted.
     * @param SlotAssigned1 How much time of the Activity is assigned to the 1st hour of the work day of a Maintainer.
     * @param SlotAssigned2 How much time of the Activity is assigned to the 2nd hour of the work day of a Maintainer.
     * @param SlotAssigned3 How much time of the Activity is assigned to the 3rd hour of the work day of a Maintainer.
     * @param SlotAssigned4 How much time of the Activity is assigned to the 4th hour of the work day of a Maintainer.
     * @param SlotAssigned5 How much time of the Activity is assigned to the 5th hour of the work day of a Maintainer.
     * @param SlotAssigned6 How much time of the Activity is assigned to the 6th hour of the work day of a Maintainer.
     * @param SlotAssigned7 How much time of the Activity is assigned to the 7th hour of the work day of a Maintainer.
     * @param SlotAssigned8 How much time of the Activity is assigned to the 8th hour of the work day of a Maintainer.
     * @return True, if the activity is essigned. False otherwise.
     */
    public boolean assignActivity(String activityID, String maintainerID, String day, int weekNumber,
            int SlotAssigned1, int SlotAssigned2, int SlotAssigned3, int SlotAssigned4, int SlotAssigned5,
            int SlotAssigned6, int SlotAssigned7, int SlotAssigned8);
    /**
     * Reset to default the availability of a Maintainer by given ID an day of week.
     * @param maintainerID The maintainer ID 
     * @param day A day of week. It may be: "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday".
     * @return True, if the availability of the maintainer is set to default values. False otherwise.
     */
    boolean resetMaintainerAvailabilityCurrentWeek(String maintainerID, String day);

    /**
     * Get an array of the time slots that represent how the time of a assigned Activity is divided into the hours of the work day.
     * The value at a specified index of the array represent how much time of the Activity is assigned to the time slot.
     * @param activityID The Activity ID 
     * @return An array that represent how the time required by activity is divided between the time slots of the day.
     * Any index of array refers to the time slot of the day.
     */
    public int[] getAssignedTimeslotsOfActivity(String activityID);
    /**
     * Delete an Activity that is assigned to a Maintainer, moreover retore the availability of the Maintainer.
     * @param activityID The ID of the Activity to delete
     * @return True, if an assigned activity is correctly deleted. False otherwise.
     */
    public boolean deleteAssignedActivity(String activityID);
    
    /**
     * Get a ResultSet that cointains all the ActivityIDs that have been assigned to that time slot(specified by the given index) at the specified Maintainer.
     * @param maintainerId The ID of the Maintainer wich has some assigned Activities.
     * @param day A day of week. It may be: "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday".
     * @param weeknumber The number of the week in the current year. It can be between 1 and 52 both incluted.
     * @param timeSlotIndex The index to specify what time slot of the day want to select. It can be between 1 and 8 incluted.
     * @return A resultSet with all the id of the activities present in that time slot of the day.
     */
    public ResultSet getActivitiesOfTimeSlot(String maintainerId, String day, int weeknumber, int timeSlotIndex);

}
