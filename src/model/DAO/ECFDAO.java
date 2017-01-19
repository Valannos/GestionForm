/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ECF;
import model.Formation;

/**
 *
 * @author vanel
 */
public class ECFDAO {

    /**
     *
     * @return A list of ECF objects
     */
    public static List<ECF> findAll() {
        
        Connection connect = DBConnect.gettingConnected();
        List<ECF> listECF = new ArrayList<>();
        
        Statement state = null;
        try {
            
            state = connect.createStatement();
            
            String sql = ("SELECT ecf.id, ecf.nom_ecf, f.nom_formation, ecf.id_formation FROM ECF ecf INNER JOIN Formation f ON f.id = ecf.id_formation");
            
            ResultSet res = state.executeQuery(sql);
            
            while (res.next()) {
                
                Formation form = new Formation(res.getString("f.nom_formation"), res.getInt("ecf.id_formation"));
                
                ECF ecf = new ECF(res.getInt("id"), res.getString("nom_ecf"), form);
                
                listECF.add(ecf);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            if (connect != null) {
                
                try {
                    connect.close();
                    state.close();
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                    
                }
                
            }
            
        }
        
        return listECF;
        
    }
    
    public static boolean addECFToFormation(Formation form, String nom) {
        
        Connection connection = DBConnect.gettingConnected();
        String sql = "INSERT INTO ECF (id_formation, nom_ecf) VALUES (?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, form.getId());
            ps.setString(2, nom);
            ps.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ECFDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ECFDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return true;
    }
    
}
