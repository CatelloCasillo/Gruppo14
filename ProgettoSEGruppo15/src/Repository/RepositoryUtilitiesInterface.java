/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.ResultSet;


public interface RepositoryUtilitiesInterface {
    /**
     * 
     * @return A ResultSet that correspond to the Typology table present in the Database
     */
    public ResultSet getTypologyTable();
    
    /**
     * 
     * @param rst A ResultSet that has to contain the ID of a Competence.
     * @return The ID of the Competence present in the ResultSet.
     */
    public String getCompetenceID(ResultSet rst);

    /**
     * Get a ResultSet that cointains all the competences required for the specified Typology.
     * @param typology
     * @return A ResultSet that cointains all the competences required for the specified Typology.
     */
    public ResultSet getCompetenceOfTypology(String typology);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the ID of a Procedure.
     * @return The ID of the Procedure present in the ResultSet.
     */
    public String getProcedureID(ResultSet rst);
    
    /**
     * 
     * @param rst A ResultSet that has to contain the parameter:"fileSMP" of a Procedure.
     * @return The fileSMP path present in the ResultSet.
     */
    public String getFileSMP(ResultSet rst);

}
