/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositoryMaintainer extends RepositoryBase implements RepositoryMaintainerInterface {

    public RepositoryMaintainer() {
        super();
    }

    @Override
    public ResultSet getMaintainerTable() {
        try {
            connect();
            String query = "select maintainerId, maintainerName from Maintainer; ";

            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ResultSet getMaintainer(String id) {
        try {
            connect();
            String query = "select maintainerId, maintainerName from Maintainer where maintainerId='" + id + "';";

            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getMaintainerName(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("MaintainerName");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getMaintainerID(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("maintainerID");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ResultSet getCompetencesOfMaintainer(String maintainerID) {
        StringBuilder temp = new StringBuilder();
        temp.append("select c.competenceID , c.competenceName from \n"
                + "competenceToMaintainer as cm \n"
                + "join competence as c on (c.competenceID=cm.competenceID )\n"
                + "where cm.MaintainerID = '").append(maintainerID).append("' ;");
        try {
            connect();
            ResultSet rst = stm.executeQuery(temp.toString());
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     ///-----------Funzioni inserimento nuovo maintainer con creazione della disponibilità ----------------------------
    
    @Override
    public boolean insertNewMaintainer(String id, String name, String password){
        if(id.length()>6){
            System.err.println("Mainteiner id ERROR: length exceeded");
            return false;
        }
        String days[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        StringBuilder temp = new StringBuilder();
        temp.append("insert into Maintainer\n" +
                        "values ('").append(id).append("' , '").append(name).append("' , '").append(password).append("' );");
        try {
            connect();
            //SQL transaction
            conn.setAutoCommit(false);
            stm.executeUpdate(temp.toString());
            for(int i=0;i<7;i++){
                insertNewMaintainerAvailability(id,days[i]);
            }
            conn.commit();
            conn.setAutoCommit(true);
            closeConnection();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    private boolean insertNewMaintainerAvailability(String maintainerID, String day) {
      
        StringBuilder temp = new StringBuilder();
        temp.append("insert into MaintainerAvailabilityCurrentWeek(maintainerID,Day, \n" +
                    " TimeSlot1,TimeSlot2,TimeSlot3,TimeSlot4,TimeSlot5,TimeSlot6,TimeSlot7,TimeSlot8)\n" +
                    " values('").append(maintainerID).append("','").append(day).append("',").append("60,60,60,60,60,60,60,60);");
        try {
            
            stm.executeUpdate(temp.toString());
            
            return true;
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    //------------------------------------------------------------------------------------------------

}
