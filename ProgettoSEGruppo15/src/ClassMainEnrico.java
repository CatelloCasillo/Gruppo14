
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enrico
 */
public class ClassMainEnrico {
    public static void main(String[] args) {
        Repository r= new Repository();
       Planner p=new Planner();
        //System.out.println(p.getActivityList().toString());
        ArrayList<MaintanceActivity> list = p.getActivityList();
        ArrayList<Site> sList = p.getSiteList();
        Site aID = list.get(0).getSite();
        System.out.println(aID);
        System.out.println(sList);
        p.findSiteInList("site01", sList);
    }
}
