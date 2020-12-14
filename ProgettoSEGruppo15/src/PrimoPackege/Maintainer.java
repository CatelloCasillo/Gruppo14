/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrimoPackege;

import Repository.Repository;
import java.sql.ResultSet;

/**
 *
 * @author Enrico
 */
public class Maintainer {
    private String id;
    private String name;
    private Repository rep;

    public Maintainer(String id, String name) {
        this.id = id;
        this.name = name;
        this.rep=new Repository();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getDayAvailability(String day){
        ResultSet rst= rep.getDayAvailability(id, day);
        int TotalFreeMinutes=0;
        final int TotalWorkInDay= 60*8;
        int [] timeSlots = rep.getTimeslots(rst);
        for(int time : timeSlots){
            TotalFreeMinutes+=time;
        }
        
        int percentage = ((TotalFreeMinutes*100)/TotalWorkInDay);
        //System.out.println(TotalFreeMinutes+" - "+percentage);
        return percentage+"%";
    }
    public Object[] getSlotsAvailability(String day){
        System.out.println(day);
        ResultSet rst= rep.getDayAvailability(id, day);
        final int slotsNumber = 8;
        int [] timeSlots = rep.getTimeslots(rst);
        Object[] formattedSlots = new Object[slotsNumber];
        for(int i=0; i<slotsNumber; i++){
            formattedSlots[i] = timeSlots[i]+" min";
        }
        return formattedSlots;
    }
    
}
