/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Personne;

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

}
