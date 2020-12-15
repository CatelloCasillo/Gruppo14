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
public interface RepositoryMaintainerInterface {
    public ResultSet getMaintainerTable();
    public ResultSet getMaintainer(String id);
    public String getMaintainerName(ResultSet rst);
    public String getMaintainerID(ResultSet rst);
    public ResultSet getCompetencesOfMaintainer(String maintainerID);
}
