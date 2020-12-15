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

/**
 *
 * @author Gabriella
 */
public class RepositoryMaintainer extends RepositoryBase implements RepositoryMaintainerInterface {

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
            String query = "select maintainerId, maintainerName from Maintainer where maintainerId='"+id+"';";
                   
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
        }}

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
        }}

    @Override
    public ResultSet getCompetencesOfMaintainer(String maintainerID) {
    StringBuilder temp = new StringBuilder();
        temp.append("select c.competenceID , c.competenceName from \n" +
                    "competenceToMaintainer as cm \n" +
                    "join competence as c on (c.competenceID=cm.competenceID )\n" +
                    "where cm.MaintainerID = '").append(maintainerID).append("' ;");
        try {
            connect();
            ResultSet rst = stm.executeQuery(temp.toString());
            closeConnection();
            return rst;
           
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }}
    
}
