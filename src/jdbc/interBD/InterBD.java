/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.interBD;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'accès à la base de données : connexion, fermeture, select, mise à jour
 * @author cflagollet
 */
public class InterBD {
    private String dbURL = "";
    private String user = "";
    private String password = "";
    private java.sql.Connection dbConnect = null;
    private java.sql.Statement dbStatement = null;
 
    /**
     * Constructeur
     * @param url
     * @param user
     * @param password
     */
    public InterBD(String url, String user, String password) {
        this.dbURL = url;
        this.user = user;
        this.password = password;
    }
 
    /**
     * Connecter à la base de donnée
     * @return false en cas d'échec
     */
    public Boolean connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            //établissement de la connexion au lien ODBC 'maBDOracle'
            this.dbConnect = DriverManager.getConnection("jdbc:oracle:thin:@" + this.dbURL, this.user, this.password);
            this.dbStatement = this.dbConnect.createStatement();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
 
    /**
     * Execution une requete SQL de sélection
     * @param sql
     * @return resultset de la requete
     */
    public ResultSet exec(String sql) {
        try {
            ResultSet rs = this.dbStatement.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Execution une requete SQL d'insertion/mise à jour (insert, update)
     * @param sql
     * @return nombre de rows mises à jour
     */
    public int update(String sql) {
        try {
            int nbMaj = this.dbStatement.executeUpdate(sql);
            return nbMaj;
        } catch (SQLException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
 
    /**
     * Fermeture la connexion au serveur de DB
     */
    public void close() {
        try {
            this.dbStatement.close();
            this.dbConnect.close();
        } catch (SQLException ex) {
            Logger.getLogger(InterBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
