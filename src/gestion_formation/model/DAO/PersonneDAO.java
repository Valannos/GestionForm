/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestion_formation.model.Personne;
import java.sql.PreparedStatement;

/**
 *
 * @author vanel
 */
public class PersonneDAO {

    public static List<Personne> findAll() {
        Connection connect = DBConnect.gettingConnected();
        List<Personne> listPers = new ArrayList<>();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `Personne`");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {

                Personne pers = new Personne(res.getString("nom"), res.getString("prenom"), res.getInt("id"));
                listPers.add(pers);

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

        return listPers;
    }

    public static List<Personne> findAllNonStagiaire() {
        Connection connect = DBConnect.gettingConnected();
        List<Personne> listPers = new ArrayList<>();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `Personne` WHERE isStagiaire = 0");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {

                Personne pers = new Personne(res.getString("nom"), res.getString("prenom"), res.getInt("id"));
                listPers.add(pers);

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

        return listPers;
    }

    public static int addPersonne(Personne p) {

        Connection connect = DBConnect.gettingConnected();
        int generatedId = 0;

        String sql = "INSERT INTO Personne (nom, prenom, isStagiaire) VALUES (?, ?, 1)";
        try {
            PreparedStatement ps = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            generatedId = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(StagiaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect != null) {

                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        return generatedId;
    }

    public static boolean deletePersonneById(int id) {

        boolean confirm = false;

         Connection connect = DBConnect.gettingConnected();

        try {

            String query = "DELETE FROM Personne WHERE id = ?";

            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, id);

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
        
        
        return confirm;
    }

}
