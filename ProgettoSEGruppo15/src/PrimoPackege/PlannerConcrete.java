/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import static java.lang.Integer.parseInt;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Gabriella
 */
public class PlannerConcrete extends PlannerAbstract{

    public PlannerConcrete() {
        super();
    }

    @Override
    public boolean createActivity(MaintanceActivityFactory.Category category, String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String workspacenotes) {
        MaintanceActivity a= MaintanceActivityFactory.make(category, id, site, typology, activityDescription, intervationTime, interruptible, week, null, null, null, workspacenotes);
       if(repoActivity.insertNewMaintenanceActivity(a.getId(), a.getSite().getId(), a.getActivityDescription(), a.getIntervationTime(), a.isInterruptible(), a.getWeekNumber(), a.getWorkspacenotes(), a.getTypology(),category.toString())){
            activityList.add(a);
            return true;
       }
       return false;}

    @Override
    public MaintanceActivity getMaintanceActivity(String idActivity) {
        for(MaintanceActivity m : activityList){
            if(m.getId().equals(idActivity))
                return m;
        }
      return null;}

    @Override
    public ArrayList<String> getTypology() {
       ArrayList<String> a = new ArrayList<>();
        try {
            ResultSet rst = repoUtilities.getTypologyTable();
            while (rst.next()) {
            String typology = repoActivity.getActivityTypology(rst);
            a.add(typology);
            //System.out.println(typology);
            }return a;
        }catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }}

    @Override
    public boolean idControl(String id) {
        for(MaintanceActivity m : activityList){
            if(m.getId().equals(id)){
                return true;
            }
    }    return false;}
    
    private int getNumberActivityInWeek(int weekNumber){ //
        int numAct=0;
        for (int i=0; i<this.activityList.size(); i++){
            MaintanceActivity act= this.activityList.get(i);
            if(act.getWeekNumber() == weekNumber && act.getMaintainerID() == null)
                numAct++;
        }
        return numAct;
    }

    @Override
    public Object[][] getSelectionableAttribute(String currentWeek) {
        int currentWeekNumber=parseInt(currentWeek.trim());
        int numRighe=this.getNumberActivityInWeek(currentWeekNumber);
        System.out.println(numRighe);
        final int numAttr=4;
        Object attrTable[][]= new Object[numRighe][];
        int j=0;
        for (int i=0; i<this.activityList.size();i++){
            MaintanceActivity act= this.activityList.get(i);
            if(act.getWeekNumber() == currentWeekNumber && act.getMaintainerID() == null ){
                    if(act.getCategory().equals(MaintanceActivityFactory.Category.PLANNED)){
               
                String id= act.getId();
                Site site= act.getSite();
                String area= site.getArea();
                String factory =site.getFactory();
                String type= act.getTypology();
                String time= ""+act.getIntervationTime();
                attrTable[j++]=new Object[]{id,area+" "+factory,type,time,new JButton("Select")};
                }}
            else {
            }
        }
        if(numRighe==0){
            attrTable=new Object[1][];
            attrTable[0] = new Object[]{"","","","",""};
        }
        return attrTable;}

    @Override
    public boolean deleteActivity(String idActivity, int row) {
         if(repoActivity.deleteMaintenanceActivity(idActivity)){
            activityList.remove(row);
            return true;
        }
        return false;}

    @Override
    public boolean updateActivity(int row, String id, String site, String typology, String description, int time, boolean inter, int week) {
       Site s= this.findSiteInList(site, siteList);
        if(s != null && repoActivity.updateMaintenanceActivity(id, s.getId() ,typology, description, time, inter, week)){
            activityList.get(row).setSite(s);
            activityList.get(row).setTypology(typology);
            activityList.get(row).setActivityDescription(description);
            activityList.get(row).setIntervationTime(time);
            activityList.get(row).setInterruptible(inter);
            activityList.get(row).setWeek(week);
            return true;
        }
        return false;}

    @Override
    public ArrayList<MaintanceActivity> getActivityList() {
        return activityList;
    }

    @Override
    public ArrayList<Site> getSiteList() {
        return siteList;
    }

    @Override
    public ArrayList<String> getCompetencesList(String activityId) {
        ResultSet rst = repoActivity.getCompetencesOfActivity(activityId);
        ArrayList<String> skillList = new ArrayList<>();
        try {  
          while(rst.next()){
          skillList.add(rst.getString(1));
          }
          return skillList;
        } catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          return skillList;
        }
    }

    @Override
    public ArrayList<String> getCompetenceTypology(String typology) {
        ResultSet rst= repoUtilities.getCompetenceOfTypology(typology);
        ArrayList<String> competenceTypology= new ArrayList<>();
        try {  
          while(rst.next()){
            competenceTypology.add(rst.getString(1));  
          }
          return competenceTypology;
        }catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }}

    @Override
    public String[] getStringInFile(File f) {
        BufferedReader b;
        String s[];
        Reader r; 
      try {
          r= new FileReader(f);
          b=new BufferedReader(r);
          String c=b.readLine();
          if(f == null){
              return null;
          }
          else{
              s= c.split(", ");
          }
      } catch (IOException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
        return s;}

    private String skillCompliance(String selectedActivityId, String selectedMaintainerId){
        ResultSet rst1 = repoActivity.getCompetencesOfActivity(selectedActivityId.trim());
        ResultSet rst2 = repoMaintainer.getCompetencesOfMaintainer(selectedMaintainerId);
        ArrayList<String> maintainerCompetences = new ArrayList<>();
        ArrayList<String> activityCompetences = new ArrayList<>();
        try {
            while(rst1.next()){
                activityCompetences.add(repoUtilities.getCompetenceID(rst1));
            }
            while(rst2.next()){
                maintainerCompetences.add(repoUtilities.getCompetenceID(rst2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
        }
        final int skillneeded = activityCompetences.size();
        int match=0;
        for(String maintainerCompetence : maintainerCompetences)        
            for(String activityCompetence : activityCompetences)
                if(maintainerCompetence.equals(activityCompetence))
                    match++;
        return match+"/"+skillneeded;
    }
    
    @Override
    public Object[][] getMaintainerWeekCalendar(String selectedActvityId) {
       maintainers = new ArrayList<>();
        ResultSet rst = repoMaintainer.getMaintainerTable();
        int maintainersNumber=0;
        try {
            while(rst.next()){
                String maintainerId=repoMaintainer.getMaintainerID(rst);
                String maintainerName=repoMaintainer.getMaintainerName(rst);
                maintainers.add(new Maintainer(maintainerId,maintainerName));
                maintainersNumber++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        Object[][] AvailabilityWeekTable= new Object[maintainersNumber][];
        
        for(int i=0;i<maintainersNumber;i++){
            Maintainer m=maintainers.get(i);
            ArrayList<String>WeekAvailability = new ArrayList<>();
            for(String day : days)
                WeekAvailability.add(m.getDayAvailability(day));
            System.out.println(m.getName()+": "+WeekAvailability);
            AvailabilityWeekTable[i] = new Object[]{m.getName(),skillCompliance(selectedActvityId, m.getId()), WeekAvailability.get(0), WeekAvailability.get(1), WeekAvailability.get(2), WeekAvailability.get(3), WeekAvailability.get(4), WeekAvailability.get(5), WeekAvailability.get(6)};
        }
        return AvailabilityWeekTable;}

    @Override
    public Object[] getMaintainerDailyAvailability(String selectedMaintainerId, String selectedDay) {
        ResultSet rst= repoMaintainer.getMaintainer(selectedMaintainerId);
        try {
            rst.next();
        } catch (SQLException ex) {
            Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
        }
        Maintainer m = new Maintainer(repoMaintainer.getMaintainerID(rst),repoMaintainer.getMaintainerName(rst));
        return m.getSlotsAvailability(selectedDay);}

    @Override
    public Maintainer getSelectedMaintainer(int selectedIndex) {
        return maintainers.get(selectedIndex);}

    @Override
    public boolean updateMaintainerAvailability(String maintainerID, String day, ArrayList<Integer> updatedtimeSlots) {
        if(repoAvailability.updateMaintainerAvailabilityCurrentWeek(maintainerID, day, updatedtimeSlots.get(0), updatedtimeSlots.get(1), updatedtimeSlots.get(2),updatedtimeSlots.get(3), updatedtimeSlots.get(4), updatedtimeSlots.get(5),updatedtimeSlots.get(6),updatedtimeSlots.get(7)))
            return true;
        return false;}

    @Override
    public boolean assignActivityFraction(String activityID, String maintainerID, String day, int weekNumber, int[] fractions) {
        if(repoAvailability.assignActivity(activityID, maintainerID, day,weekNumber, fractions[0], fractions[1], fractions[2], fractions[3], fractions[4], fractions[5], fractions[6], fractions[7]))
            return true;
        return false;}

    @Override
    public boolean updateNotes(String activityId, String notes) {
        MaintanceActivity act = this.getMaintanceActivity(activityId);
        if(repoActivity.updateWorkspceNotes(activityId, notes)){
            act.setWorkspacenotes(notes);
            return true;
        }
        return false;}

    @Override
    public void updateActivityToMaintainer(String activityId, String maintainerId) {
       MaintanceActivity act =this.getMaintanceActivity(activityId);
        act.setMaintainerID(maintainerId);}
    
}