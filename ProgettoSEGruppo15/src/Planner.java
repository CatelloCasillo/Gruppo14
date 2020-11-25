
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



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
  ArrayList<MaintanceActivity> listmaintance = new ArrayList<>();
 

    public Planner() {
        this.repository= new Repository();
    }
    
    //this method create a maintance activity 
    public void createActivity(String id, String site, String typology, String activityDescription, String intervationTime, boolean interruptible, String week ){
       MaintanceActivity a= new MaintanceActivity(id, site, typology, activityDescription, intervationTime, interruptible, week);
       listmaintance.add(a);
       
    }
   
    //this method create a maintance list from maintance activity table retrive from database 
    public ArrayList<MaintanceActivity> getActivityTable(){
        ArrayList<MaintanceActivity> list= new ArrayList<>();
        return list;
    } 
}
