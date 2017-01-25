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
import gestion_formation.model.Formation;
import gestion_formation.model.Personne;
import gestion_formation.model.Stagiaire;

/**
 *
 * @author vanel
 */
public class StagiaireDAO {

    public static List<Stagiaire> findAll() {

        
        Connection connect = DBConnect.gettingConnected();
        List<Stagiaire> listStg = new ArrayList<>();
     

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT s.code, p.nom, p.prenom, f.id, f.nom_formation FROM Stagiaire s INNER JOIN Personne p ON p.id = s.id_personne INNER JOIN Formation f ON f.id = s.id_formation");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Stagiaire stg = new Stagiaire(res.getInt("s.code"), res.getString("p.nom"), res.getString("p.prenom"));
                Formation form = new Formation(res.getString("f.nom_formation"), res.getInt("f.id"));
                stg.setForm(form);
                listStg.add(stg);
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

        return listStg;

    }

    public static boolean addStagiaireToFormation(Personne pers, Formation form) {

        int id = 0;
        Connection connect = DBConnect.gettingConnected();
        boolean success = false;

        try {

            FormationDAO formDAO = new FormationDAO();
            id = formDAO.getFormationId(form);

            String sql = "INSERT INTO `Stagiaire` (id_personne, id_formation) VALUES ( ?, ?)";

            try (PreparedStatement ps = connect.prepareStatement(sql)) {
                ps.setInt(1, pers.getId());
                ps.setInt(2, form.getId());

                ps.execute();
            }

        } catch (SQLException ex) {
            Logger.getLogger(StagiaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StagiaireDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        return success;
    }

    public static List<Stagiaire> getStgByFormation(Formation form) {

        List<Stagiaire> listStg = new ArrayList<>();
        Connection connection = DBConnect.gettingConnected();
        int id = FormationDAO.getFormationId(form);
        //System.out.println(id);

        try {

            String query = ""
                    + "SELECT p.nom, p.prenom, s.code FROM Stagiaire s "
                    + "INNER JOIN Formation f "
                    + "ON s.id_formation = f.id "
                    + "INNER JOIN Personne p "
                    + "ON p.id = s.id_personne "
                    + "WHERE f.id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Stagiaire stg = new Stagiaire(rs.getInt("code"), rs.getString("nom"), rs.getString("prenom"));
                    System.out.println(stg);
                    listStg.add(stg);

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Stagiaire.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connection != null) {

                try {

                    connection.close();

                } catch (SQLException ex) {
                    Logger.getLogger(Stagiaire.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return listStg;
    }

    public static void RemoveStagiaire(Stagiaire stg) {

        Connection connect = DBConnect.gettingConnected();

        try {

            String query = "DELETE FROM Stagiaire WHERE code = ?";

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, stg.getCodeStagiaire());

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(StagiaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect != null) {
                try {

                    connect.close();

                } catch (SQLException ex) {
                    Logger.getLogger(StagiaireDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

}
