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

import model.Resultat;
import model.Stagiaire;

/**
 *
 * @author vanel
 */
public class ResultatDAO {

    public static List<Resultat> findAll() {
  
        List<Resultat> listRslt = new ArrayList<>();

        Connection connect = DBConnect.gettingConnected();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT r.validation, p.nom, p.prenom, s.code, f.nom_formation, f.id, ecf.id, ecf.nom_ecf FROM Resultat r INNER JOIN Stagiaire s ON s.code = r.id_stagiaire INNER JOIN ECF ecf ON ecf.id = r.id_ecf INNER JOIN Personne p ON p.id = s.id_personne INNER JOIN Formation f ON f.id = ecf.id_formation ");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Formation form = new Formation(res.getString("f.nom_formation"), res.getInt("f.id"));

                ECF ecf = new ECF(res.getInt("ecf.id"), res.getString("ecf.nom_ecf"), form);

                Stagiaire stg = new Stagiaire(res.getInt("s.code"), res.getString("p.nom"), res.getString("p.prenom"));

                Resultat rslt;

                if (res.getInt("r.validation") == 0) {
                    rslt = new Resultat(false, ecf, stg);
                    listRslt.add(rslt);
                } else {

                    rslt = new Resultat(true, ecf, stg);
                    listRslt.add(rslt);
                }

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

        return listRslt;
    }

    public static List<Resultat> getAllResultsInFormation(Formation form) {

        List<Resultat> listRslt = new ArrayList<>();

       
     
        Connection connect = DBConnect.gettingConnected();

       
        try {

            String sql = ("SELECT r.validation, ecf.id, ecf.nom_ecf, s.code, p.nom, p.prenom FROM Resultat r INNER JOIN ECF ecf ON r.id_ecf = ecf.id INNER JOIN Formation f ON f.id = ecf.id_formation INNER JOIN Stagiaire s ON r.id_stagiaire = s.code INNER JOIN Personne p ON p.id = s.id_personne WHERE f.id = ? ");

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, form.getId());

            ResultSet res = ps.executeQuery();

            while (res.next()) {
                
              

                ECF ecf = new ECF(res.getInt("ecf.id"), res.getString("ecf.nom_ecf"), form);

                Stagiaire stg = new Stagiaire(res.getInt("s.code"), res.getString("p.nom"), res.getString("p.prenom"));

                Resultat rslt;
                  if (res.getInt("r.validation") == 0) {

                   
                    rslt = new Resultat(false, ecf, stg);
                    listRslt.add(rslt);
                } else {

                    rslt = new Resultat(true, ecf, stg);
                    listRslt.add(rslt);
                }

                

            }

        } catch (SQLException ex) {
            Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect != null) {

                try {
                    connect.close();

                } catch (SQLException e) {

                    e.printStackTrace();

                }

            }

        }

        return listRslt;

    }

}
