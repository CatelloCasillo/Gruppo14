/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

public interface RepositoryMaintainerInterface {
    /**
     * 
     * @return A ResultSet that correspond to the Maintainer table present in the Database.
     */
    public ResultSet getMaintainerTable();
    
    /**
     * 
     * @param id
     * @return A ResultSet that cointains all the parameter of the specified Maintainer.
     */
    public ResultSet getMaintainer(String id);
    
    /**
     * 
     * @param rst
     * @return The name of the Maintainer present in the ResultSet.
     */
    public String getMaintainerName(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The ID of the Maintainer present in the ResultSet.
     */
    public String getMaintainerID(ResultSet rst);
    
    /**
     * 
     * @param maintainerID
     * @return A ResultSet that cointains all the competences of the specified Maintainer.
     */
    public ResultSet getCompetencesOfMaintainer(String maintainerID);
    
    /**
     * 
     * Insert a new Maintainer into the Database including the craation of this availability in the current week.
     * @param id ID of the Maintainer to insert
     * @param name Name of the Maintainer 
     * @param password Password associated to the Maintainer
     * @return True is a Maintainer i correctly insert in the Database, False otherwise.
     */
    public boolean insertNewMaintainer(String id, String name, String password);
}
