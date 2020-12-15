/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

/**
 *
 * @author Enrico
 */
public class DemoAdminIsertNewMaintainer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Repository r= new Repository();
       //insert new maintainer as the example:
       //r.insertNewMaintainer("mant44", "pluto", "1234");
       
       r.insertNewMaintainer("mant01", "pluto", "1234");
       r.insertNewMaintainer("mant02", "pippo", "1234");
       r.insertNewMaintainer("mant03", "paperino", "1234");
       r.insertNewMaintainer("mant04", "topolino", "1234");
       r.insertNewMaintainer("mant05", "pietro", "1234");
    }
   
}
