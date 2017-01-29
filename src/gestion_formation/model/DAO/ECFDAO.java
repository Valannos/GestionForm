/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestion_formation.model.ECF;
import gestion_formation.model.Formation;

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
    
    public static boolean addECFToFormation(ECF ecf) {
        
        Connection connection = DBConnect.gettingConnected();
        String sql = "INSERT INTO ECF (id_formation, nom_ecf, description) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ecf.getFormation().getId());
            ps.setString(2, ecf.getNom());
            ps.setString(3, ecf.getDescription());
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
    
    
    public static List<ECF> findAllinFormation(Formation form) {
        
        Connection connect = DBConnect.gettingConnected();
        List<ECF> listECF = new ArrayList<>();
        
        PreparedStatement ps = null;
        try {
            
            
            
            String sql = ("SELECT ecf.id, ecf.nom_ecf, ecf.id_formation FROM ECF ecf WHERE ecf.id_formation = ?");
            ps = connect.prepareStatement(sql);
            ps.setInt(1, form.getId());
            ResultSet res = ps.executeQuery();
            
            while (res.next()) {
                
                
                
                ECF ecf = new ECF(res.getInt("id"), res.getString("nom_ecf"), form);
                
                listECF.add(ecf);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            if (connect != null) {
                
                try {
                    connect.close();
                    ps.close();
                } catch (SQLException e) {
                    
                    e.printStackTrace();
                    
                }
                
            }
            
        }
        
        return listECF;
        
    }
    
    
    
}
