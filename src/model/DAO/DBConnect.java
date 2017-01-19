/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;

//import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vanel
 */
public class DBConnect {

    final static private String url = "jdbc:mysql://localhost/Gestion_Formation";
    final static private String login = "root";
    final static String password = "admin";

    public static Connection gettingConnected() {

        Connection connect = null;
       

        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            
            connect = DriverManager.getConnection(url, login, password);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connect;
    }

}
