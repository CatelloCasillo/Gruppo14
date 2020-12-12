/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Enrico
 */
public abstract class RepositoryBase {
    private String url;
    private String user;
    private String pwd;
    protected Connection conn = null;
    protected Statement stm = null;
    
    public RepositoryBase() {
        this.url = "jdbc:postgresql://localhost/Progetto_SE_gruppo14";
        this.user = "utente_progetto_se";
        this.pwd = "password";
        
    }
    
    public boolean connect() throws SQLException{
         try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(url, user, pwd);
            this.stm = this.conn.createStatement();
            //System.out.println("Connessione Database effettuata");
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Repository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void closeConnection() throws SQLException{
        if(this.conn!=null) {
                this.conn.close();
        }
    }
    
}
