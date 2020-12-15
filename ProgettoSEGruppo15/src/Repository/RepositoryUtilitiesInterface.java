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
public interface RepositoryUtilitiesInterface {
    public ResultSet getTypologyTable();
    public String getCompetenceID(ResultSet rst);
    //public String getCompetenceName(ResultSet rst);
    public ResultSet getCompetenceOfTypology(String typology);
    public String getProcedureID(ResultSet rst);
    public String getFileSMP(ResultSet rst);
    
}
