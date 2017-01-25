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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestion_formation.model.Formation;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author vanel
 */
public class FormationDAO {

    /**
     *
     * @return List
     */
    public static List<Formation> findAll() {

        Connection connect = DBConnect.gettingConnected();
        List<Formation> listForm = new ArrayList<>();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `Formation`");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {

                Formation form = new Formation(res.getString("nom_formation"));
                form.setId(res.getInt("id"));
                form.setStart(res.getDate("debut"));
                form.setEnd(res.getDate("fin"));
                listForm.add(form);

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

        return listForm;
    }

    /**
     *
     * @param form
     * @return (int) id of formation received in parameters
     */
    public static int getFormationId(Formation form) {

        int id = 0;
        Connection connect = DBConnect.gettingConnected();

        try {

            String sql = ("SELECT id FROM `Formation` WHERE nom_formation = ?");

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, form.getNom());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.isLast()) {
                    id = rs.getInt("id");
                }

            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect != null) {

                try {

                    connect.close();

                } catch (SQLException ex) {
                    Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

        return id;
    }

    public static void addFormation(Formation form) {

        Connection connect = DBConnect.gettingConnected();

        String sql = "INSERT INTO Formation (nom_formation, debut, fin) VALUES (?, ?, ?)";

        try {

            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, form.getNom());

            ps.setTimestamp(3, new Timestamp(form.getEnd().getTime()));
            ps.setTimestamp(2, new Timestamp(form.getStart().getTime()));

            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (connect != null) {
                try {
                    connect.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FormationDAO.class.getName()).log(Level.SEVERE, null, ex);

                }
            }

        }

    }

}
