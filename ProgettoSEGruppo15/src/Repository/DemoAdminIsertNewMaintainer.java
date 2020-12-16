/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;


public class DemoAdminIsertNewMaintainer {
    
    /**
     * Main used to populate the Database with some Maintainers and their Availability
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       RepositoryMaintainerInterface r= new RepositoryMaintainer();
       //insert new maintainer as the example:
       //r.insertNewMaintainer("mant44", "pluto", "1234");
       
       r.insertNewMaintainer("mant01", "pluto", "1234");
       r.insertNewMaintainer("mant02", "pippo", "1234");
       r.insertNewMaintainer("mant03", "paperino", "1234");
       r.insertNewMaintainer("mant04", "topolino", "1234");
       r.insertNewMaintainer("mant05", "pietro", "1234");
       
       //r.deleteAssignedActivity("act005");
       //r.resetMaintainerAvailabilityCurrentWeek("mant01", "Monday");
       //r.resetMaintainerAvailabilityCurrentWeek("mant02", "Monday");


    }
   
}
