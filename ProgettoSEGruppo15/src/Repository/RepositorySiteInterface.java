/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;

/**
 *
 * @author Gabriella
 */
public interface RepositorySiteInterface {
    public String getSiteID(ResultSet rst);
    public String getFactorySite(ResultSet rst);
    public String getAreaSite(ResultSet rst);
    public ResultSet getSiteTable();
}
