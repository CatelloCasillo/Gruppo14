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


public class RepositorySite extends RepositoryBase implements RepositorySiteInterface {

    public RepositorySite() {
        super();
    }

    @Override
    public String getSiteID(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("siteID");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getFactorySite(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("FactorySite");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getAreaSite(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("AreaSite");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public ResultSet getSiteTable() {
        try {
            connect();
            String query = "select* from Site ";

            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
