package PrimoPackage;


import PrimoPackage.MaintanceActivity;
import Repository.Repository;
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
  
 

    public Planner() {
        this.repository= new Repository();
         //inizializzazione delle strutture dati che contengono i dati presi dal DB
        this.initSiteList();
        this.initActivityList();
    }
    
    //permette di creare un'attività, di aggiungerla alla lista e al database 
    public boolean createActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String workspacenotes ){
       MaintanceActivity a= new MaintanceActivity(id, site, typology, activityDescription, intervationTime, interruptible, week, workspacenotes);
       if(repository.insertNewMaintenanceActivity(a.getId(), a.getSite().getId(), a.getActivityDescription(), a.getIntervationTime(), a.isInterruptible(), a.getWeekNumber(), a.getWorkspacenotes(), a.getTypology())){
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
                MaintanceActivity mainActivity= new MaintanceActivity(id, site, typology, activityDescription, intervationTimeNumber, interruptible, weekNumber, procedureID, fileSMP);
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
            MaintanceActivity act= this.activityList.get(i);
            if(act.getWeekNumber() == weekNumber)
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
        for (int i=0; i<this.activityList.size(); i++){
            MaintanceActivity act= this.activityList.get(i);
            if(act.getWeekNumber() == currentWeekNumber){
                String id= act.getId();
                Site site= act.getSite();
                String area= site.getArea();
                String factory =site.getFactory();
                String type= act.getTypology();
                String time= ""+act.getIntervationTime();
                attrTable[j++]=new Object[]{id,area+" "+factory,type,time,new JButton("Select")};
            }
        }
        return attrTable;
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
}

