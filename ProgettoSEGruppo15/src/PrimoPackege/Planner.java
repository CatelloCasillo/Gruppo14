package PrimoPackege;


import PrimoPackege.MaintanceActivity;
import Repository.Repository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriella
 */
public class Planner {
  MaintanceActivity activity;
  Repository repository;
  ArrayList<MaintanceActivity> activityList;
  ArrayList<Site> siteList;
  File f [];
  ArrayList<Maintainer> maintainers;
  
 

    public Planner() {
        this.repository= new Repository();
         //inizializzazione delle strutture dati che contengono i dati presi dal DB
        this.initSiteList();
        this.initActivityList();
        this.f = new File("C:\\Users\\User\\Tickets").listFiles();
    }
    
    //permette di creare un'attività, di aggiungerla alla lista e al database 
    public boolean createActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String workspacenotes ){
       MaintanceActivity a= new PlannedActivity(id, site, typology, activityDescription, intervationTime, interruptible, week, workspacenotes);
       if(repository.insertNewMaintenanceActivity(a.getId(), a.getSite().getId(), a.getActivityDescription(), a.getIntervationTime(), a.isInterruptible(), a.getWeekNumber(), a.getWorkspacenotes(), a.getTypology(), a.getCategory().toString())){
            activityList.add(a);
            return true;
       }
       return false;
    }
    
    private void initActivityList(){
        this.activityList = new ArrayList<>();
        try {
            ResultSet rst = repository.getInformationOfMaintenanceActivity();
            while (rst.next()) {
                String id = repository.getActivityID(rst);
                
                String siteID = repository.getSiteID(rst);
                //trovare il site nella lista
                //System.out.println("siteId = "+siteID);
                Site site = this.findSiteInList(siteID,this.siteList);
                //--
                
                String procedureID = repository.getProcedureID(rst);
                String fileSMP = repository.getFileSMP(rst);
                
                int weekNumber = repository.getActivityWeekNumber(rst);
                int intervationTimeNumber = repository.getActivityInterventionTime(rst);
                String typology = repository.getActivityTypology(rst);
                String activityDescription = repository.getActivityDescription(rst);
                boolean interruptible = repository.isInterruptibleActivity(rst);
                String maintainerID=repository.getActivityMaintainerID(rst);
                MaintanceActivity mainActivity= new PlannedActivity(id, site, typology, activityDescription, intervationTimeNumber, interruptible, weekNumber, procedureID, fileSMP, maintainerID);
                this.activityList.add(mainActivity);
           } 
        } catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    
    // restituisce la maintance activity del rispettivo id
    public MaintanceActivity getMaintanceActivity (String idActivity){
        for(MaintanceActivity m : activityList){
            if(m.getId().equals(idActivity))
                return m;
        }
      return null;
    }
    
    private void initSiteList(){
      this.siteList = new ArrayList<>();
      try {
        ResultSet rst = repository.getSiteTable();
        while (rst.next()) {
          String id = repository.getSiteID(rst);
          String factory = repository.getFactorySite(rst);
          String area = repository.getAreaSite(rst);
          Site site = new Site(id, factory, area);
          this.siteList.add(site);
        } 
      } catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }
    
    public ArrayList<String> getTypology(){
        ArrayList<String> a = new ArrayList<>();
        try {
            ResultSet rst = repository.getTypologyTable();
            while (rst.next()) {
            String typology = repository.getActivityTypology(rst);
            a.add(typology);
            //System.out.println(typology);
            }return a;
        }catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
    }
    
    // metodo che controlla se l'id dell'attività è già presente
    public boolean idControl(String id){
        for(MaintanceActivity m : activityList){
            if(m.getId().equals(id)){
                return true;
            }
    }    return false;
    }
    
    public Site findSiteInList(String idSite, ArrayList<Site> siteList) {
        String [] id= idSite.split(":");
          for (Site s : siteList) {
              if (s.getId().trim().equals(id[0].trim())) {
                  return s;
              }
          }
          return null;
    }
    //Trova l'attività di manutenzione con il week number minore da più tempo nella lista delle attività e la restituisce
    private MaintanceActivity getActivityWithMinimumWeek(ArrayList<MaintanceActivity> maintanceList){
        int numRighe=maintanceList.size();
        MaintanceActivity actMin=maintanceList.get(0);
        int min=actMin.getWeekNumber();
        int indexMin=0;
        for (int i=1; i<numRighe; i++){
            MaintanceActivity act = maintanceList.get(i);
            if(act.getWeekNumber() < min){
                min =act.getWeekNumber();
                actMin = act;
                indexMin=i;
            }
        }
        return actMin;
    }
    private int getNumberActivityInWeek(int weekNumber){
        int numAct=0;
        for (int i=0; i<this.activityList.size(); i++){
            PlannedActivity act=(PlannedActivity) this.activityList.get(i);
            if(act.getWeekNumber() == weekNumber && act.getMaintainerID() == null)
                numAct++;
        }
        return numAct;
    }
    
    //metodo che converte la lista delle attività di manutenzione in una matrice di oggetti
    //per permettere al planner di scegliere l'attività vuole assegnare. Le righe sono inserite
    //in ordine crescente di numero di settimana
    
    public Object[][] getSelectionableAttribute(String currentWeek){
        int currentWeekNumber=parseInt(currentWeek.trim());
        int numRighe=this.getNumberActivityInWeek(currentWeekNumber);
        System.out.println(numRighe);
        final int numAttr=4;
        Object attrTable[][]= new Object[numRighe][];
        int j=0;
        boolean empty=true;
        for (int i=0; i<this.activityList.size();i++){
            PlannedActivity act= (PlannedActivity)this.activityList.get(i);
            if(act.getWeekNumber() == currentWeekNumber && act.getMaintainerID() == null){
                
                String id= act.getId();
                Site site= act.getSite();
                String area= site.getArea();
                String factory =site.getFactory();
                String type= act.getTypology();
                String time= ""+act.getIntervationTime();
                attrTable[j++]=new Object[]{id,area+" "+factory,type,time,new JButton("Select")};
                empty=false;
            }
        }
        for(int k=0;k<2;k++){
            for(int q=0;q<5;q++)
                System.out.print(attrTable[k][q].toString()+", ");
            System.out.println("");
        }
        if(!empty)
        return attrTable;
        else{
            Object ris [][]={{}};
            return ris;
        }
        
    }
    
    //permette di cancellare un'attività dalla lista e dal database
    public boolean deleteActivity(String idActivity, int row){
        if(repository.deleteMaintenanceActivity(idActivity)){
            activityList.remove(row);
            return true;
        }
        return false;
    }
    
    //permette di modificare un'attività dalla lista e dal database
    public boolean updateActivity(int row,String id, String site, String typology,String description, int time, boolean inter, int week ){ 
        Site s= this.findSiteInList(site, siteList);
        if(s != null && repository.updateMaintenanceActivity(id, s.getId() ,typology, description, time, inter, week)){
            activityList.get(row).setSite(s);
            activityList.get(row).setTypology(typology);
            activityList.get(row).setActivityDescription(description);
            activityList.get(row).setIntervationTime(time);
            activityList.get(row).setInterruptible(inter);
            activityList.get(row).setWeek(week);
            return true;
        }
        return false;
    }

    public ArrayList<MaintanceActivity> getActivityList() {
        return activityList;
    }

    public ArrayList<Site> getSiteList() {
        return siteList;
    }
    public ArrayList<String> getCompetencesList(String activityId){
        ResultSet rst = repository.getCompetencesOfActivity(activityId);
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
    
    public ArrayList<String> getCompetenceTypology(String typology){
        ResultSet rst= repository.getCompetenceOfTypology(typology);
        ArrayList<String> competenceTypology= new ArrayList<>();
        try {  
          while(rst.next()){
            competenceTypology.add(rst.getString(1));  
          }
          return competenceTypology;
        }catch (SQLException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
        return null;
        }
    }

    public File[] getF() {
        return f;
    }
   
    public String[] getStringInFile(int i) {
        BufferedReader b;
        String s[];
        Reader r; 
      try {
          r= new FileReader(f[i]);
          b=new BufferedReader(r);
          String f=b.readLine();
          if(f == null){
              return null;
          }
          else{
              s= f.split(", ");
          }
      } catch (IOException ex) {
          Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
          return null;
      }
        return s;
    }
    
    private String skillCompliance(String selectedActivityId, String selectedMaintainerId){
        ResultSet rst1 = repository.getCompetencesOfActivity(selectedActivityId.trim());
        ResultSet rst2 = repository.getCompetencesOfMaintainer(selectedMaintainerId);
        ArrayList<String> maintainerCompetences = new ArrayList<>();
        ArrayList<String> activityCompetences = new ArrayList<>();
        try {
            while(rst1.next()){
                activityCompetences.add(repository.getCompetenceID(rst1));
            }
            while(rst2.next()){
                maintainerCompetences.add(repository.getCompetenceID(rst2));
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
    
    
    public Object[][] getMaintainerWeekCalendar(String selectedActvityId){
        maintainers = new ArrayList<>();
        ResultSet rst = repository.getMaintainerTable();
        int maintainersNumber=0;
        try {
            while(rst.next()){
                String maintainerId=repository.getMaintainerID(rst);
                String maintainerName=repository.getMaintainerName(rst);
                maintainers.add(new Maintainer(maintainerId,maintainerName));
                maintainersNumber++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        Object[][] AvailabilityWeekTable= new Object[maintainersNumber][];
        ArrayList<String>WeekAvailability = new ArrayList<>();
        for(int i=0;i<maintainersNumber;i++){
            Maintainer m=maintainers.get(i);
            for(String day : days)
                WeekAvailability.add(m.getDayAvailability(day));
            AvailabilityWeekTable[i] = new Object[]{m.getName(),skillCompliance(selectedActvityId, m.getId()), WeekAvailability.get(0), WeekAvailability.get(1), WeekAvailability.get(2), WeekAvailability.get(3), WeekAvailability.get(4), WeekAvailability.get(5), WeekAvailability.get(6)};
        }
        return AvailabilityWeekTable;
        
    }
    public Object[] getMaintainerDailyAvailability(String selectedMaintainerId, String selectedDay){
        ResultSet rst= repository.getMaintainer(selectedMaintainerId);
        try {
            rst.next();
        } catch (SQLException ex) {
            Logger.getLogger(Planner.class.getName()).log(Level.SEVERE, null, ex);
        }
        Maintainer m = new Maintainer(repository.getMaintainerID(rst),repository.getMaintainerName(rst));
        return m.getSlotsAvailability(selectedDay);
    }
    public Maintainer getSelectedMaintainer(int selectedIndex){
        return maintainers.get(selectedIndex);
    }
    
    public boolean updateMaintainerAvailability(String maintainerID, String day, ArrayList<Integer> updatedtimeSlots){
        if(repository.updateMaintainerAvailabilityCurrentWeek(maintainerID, day, updatedtimeSlots.get(0), updatedtimeSlots.get(1), updatedtimeSlots.get(2),updatedtimeSlots.get(3), updatedtimeSlots.get(4), updatedtimeSlots.get(5),updatedtimeSlots.get(6),updatedtimeSlots.get(7)))
            return true;
        return false;
    }
    public boolean assignActivityFraction(String activityID, String maintainerID, String day, int weekNumber,  int [] fractions){
        if(repository.assignActivity(activityID, maintainerID, day,weekNumber, fractions[0], fractions[1], fractions[2], fractions[3], fractions[4], fractions[5], fractions[6], fractions[7]))
            return true;
        return false;
                    
    }
    
    public boolean updateNotes(String activityId, String notes){
        MaintanceActivity act = this.getMaintanceActivity(activityId);
        if(repository.updateWorkspceNotes(activityId, notes)){
            act.setWorkspacenotes(notes);
            return true;
        }
        return false;
    }
    public void updateActivityToMaintainer(String activityId, String maintainerId){
        PlannedActivity act =(PlannedActivity)this.getMaintanceActivity(activityId);
        act.setMaintainerID(maintainerId);
    }
}

