package PrimoPackege;


import PrimoPackege.MaintanceActivity;
import Repository.Repository;
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
    
    //this method create a maintance activity 
    public void createActivity(String id, Site site, String typology, String activityDescription, int intervationTime, boolean interruptible, int week, String workspacenotes ){
       MaintanceActivity a= new MaintanceActivity(id, site, typology, activityDescription, intervationTime, interruptible, week, workspacenotes);
       //activityList.add(a);
       System.out.println(a.id+' '+ a.getSite().id.trim()+' '+ a.activityDescription+' '+ a.intervationTime+' '+ a.interruptible+ ' '+ a.weekNumber+' '+ a.workspacenotes+' '+ a.typology );
       repository.insertNewMaintenanceActivity(a.id, a.getSite().id, a.activityDescription, a.intervationTime, a.interruptible, a.weekNumber, a.workspacenotes, a.typology);
       activityList.add(a);
    }
    
    public void initActivityList(){
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
    
    
    public void initSiteList(){
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
    public Site findSiteInList(String idSite, ArrayList<Site> siteList) {
        String [] id= idSite.split(":");
          for (Site s : siteList) {
              //System.out.println(id.concat(s.getId()));
              if (s.getId().trim().equals(id[0].trim())) {
                  System.out.println(id);
                  return s;
              }
          }
          return null;
    }
    private MaintanceActivity getActivityWithMinimumWeek(ArrayList<MaintanceActivity> maintanceList){
        int numRighe=maintanceList.size();
        MaintanceActivity actMin=maintanceList.get(0);
        int min=actMin.weekNumber;
        int indexMin=0;
        for (int i=1; i<numRighe; i++){
            MaintanceActivity act = maintanceList.get(i);
            if(act.weekNumber < min){
                min =act.weekNumber;
                actMin = act;
                indexMin=i;
            }
        }
        maintanceList.remove(indexMin);
        return actMin;
    }
    
    public Object[][] getSelectionableAttribute(){
        int numRighe=this.activityList.size();
        final int numAttr=4;
        ArrayList<MaintanceActivity> appoggio = (ArrayList<MaintanceActivity>)this.activityList.clone();
        Object attrTable[][]= new Object[numRighe][];
        for (int i=0; i<numRighe; i++){
            MaintanceActivity act= getActivityWithMinimumWeek(appoggio);
            String id= act.getId();
            Site site= act.getSite();
            String area= site.area;
            String factory =site.factory;
            String type= act.typology;
            String time= ""+act.intervationTime;
            attrTable[i]=new Object[]{id,area+" "+factory,type,time,new JButton("Select")};
            System.out.println(act.weekNumber);
        }
        
        return attrTable;
    }
    
    public void deleteActivity(String idActivity, int row){
        repository.deleteMaintenanceActivity(idActivity);
        activityList.remove(row);
    }
    
    public MaintanceActivity getActivity() {
        return activity;
    }


    public void setActivity(MaintanceActivity activity) {
        this.activity = activity;
    }

    public void setListmaintance(ArrayList<MaintanceActivity> listmaintance) {
        this.activityList = listmaintance;
    }

    public ArrayList<MaintanceActivity> getActivityList() {
        return activityList;
    }

    public ArrayList<Site> getSiteList() {
        return siteList;
    }
}
