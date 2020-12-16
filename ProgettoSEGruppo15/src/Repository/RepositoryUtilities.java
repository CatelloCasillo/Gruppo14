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


public class RepositoryUtilities extends RepositoryBase implements RepositoryUtilitiesInterface {

    public RepositoryUtilities() {
        super();
    }

    @Override
    public ResultSet getTypologyTable() {
        try {
            connect();
            String query = "select* from Typology ";

            ResultSet rst = stm.executeQuery(query);
            closeConnection();
            return rst;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getCompetenceID(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("CompetenceID");
            closeConnection();
            return s;

        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /*
    @Override
    public String getCompetenceName(ResultSet rst) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    @Override
    public ResultSet getCompetenceOfTypology(String typology) {
        StringBuilder temp = new StringBuilder();
        temp.append("select c.competenceName from \n"
                + "TypologyToCompetence as tc \n"
                + "join competence as c on (c.competenceID=tc.competenceID ) ");
        temp.append(" where tc.activityTypology=  '").append(typology).append("' ;");
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

    @Override
    public String getProcedureID(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("ProcedureID");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String getFileSMP(ResultSet rst) {
        try {
            connect();
            String s = rst.getString("FileSMP");
            closeConnection();
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
