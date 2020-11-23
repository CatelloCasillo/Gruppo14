
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
 

    public Planner() {
        this.repository= new Repository();
    }
    
    //this method create a maintance activity 
    public void createActivity(String id, String site, String typology, String activityDescription, String intervationTime, boolean interruptible, String week ){
       MaintanceActivity a= new MaintanceActivity(id, site, typology, activityDescription, intervationTime, interruptible, week);
       
    }
    
    //this method create a maintance list from maintance activity table retrive from database 
    public List<MaintanceActivity> getActivityTable(){
        List<MaintanceActivity> list= new LinkedList<>();
        return list;
    }
}
