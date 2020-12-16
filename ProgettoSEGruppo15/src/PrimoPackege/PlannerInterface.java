/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Gabriella
 */
public interface PlannerInterface {
    public boolean createActivity(MaintanceActivityFactory.Category category, String id, Site site, String typology, String activityDescription, int intervationTime,
                    boolean interruptible, int week, String workspacenotes);
    public MaintanceActivity getMaintanceActivity (String idActivity);
    public ArrayList<String> getTypology();
    public boolean idControl(String id);
    public Site findSiteInList(String idSite, ArrayList<Site> siteList);
    public Object[][] getSelectionableAttribute(String currentWeek);
    public boolean deleteActivity(String idActivity, int row);
    public boolean updateActivity(int row,String id, String site, String typology,String description, int time, boolean inter, int week );
    public ArrayList<MaintanceActivity> getActivityList();
    public ArrayList<Site> getSiteList();
    public ArrayList<String> getCompetencesList(String activityId);
    public ArrayList<String> getCompetenceTypology(String typology);
    public String[] getStringInFile(File f);
    public Object[][] getMaintainerWeekCalendar(String selectedActvityId);
    public Object[] getMaintainerDailyAvailability(String selectedMaintainerId, String selectedDay);
    public Maintainer getSelectedMaintainer(int selectedIndex);
    public boolean updateMaintainerAvailability(String maintainerID, String day, ArrayList<Integer> updatedtimeSlots);
    public boolean assignActivityFraction(String activityID, String maintainerID, String day, int weekNumber,  int [] fractions);
    public boolean updateNotes(String activityId, String notes);
    public void updateActivityToMaintainer(String activityId, String maintainerId);
}