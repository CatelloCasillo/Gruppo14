/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

public interface RepositorySiteInterface {
    /**
     * 
     * @param rst
     * @return The ID of the Site present in the ResultSet.
     */
    public String getSiteID(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The Factory of the Site present in the ResultSet.
     */
    public String getFactorySite(ResultSet rst);
    
    /**
     * 
     * @param rst
     * @return The Area of the Site present in the ResultSet.
     */
    public String getAreaSite(ResultSet rst);
    
    /**
     * 
     * @return A ResultSet that correspond to the Site table present in the Database
     */
    public ResultSet getSiteTable();
}
