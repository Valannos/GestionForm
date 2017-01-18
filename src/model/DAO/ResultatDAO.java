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
import model.ECF;
import model.Formation;

import model.Resultat;
import model.Stagiaire;

/**
 *
 * @author vanel
 */
public class ResultatDAO implements DAO {

    @Override
    public List<Resultat> findAll() {

        StagiaireDAO stgDAO = new StagiaireDAO();
        ECFDAO ecfDAO = new ECFDAO();

        List<ECF> listECF = ecfDAO.findAll();
        System.out.println(listECF);
        List<Stagiaire> listStg = stgDAO.findAll();
        System.out.println(listStg);
        List<Resultat> listRslt = new ArrayList<>();

        Connection connect = DBConnect.gettingConnected();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `Resultat`");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {

                for (int i = 0; i < listStg.size(); i++) {

                    if (listStg.get(i).getCodeStagiaire() == res.getInt("id_stagiaire")) {

                        for (int j = 0; j < listECF.size(); j++) {

                            if (listECF.get(j).getId() == res.getInt("id_ecf")) {

                                if (res.getInt("validation") == 0) {
                                    Resultat rslt = new Resultat(false, listECF.get(j), listStg.get(i));
                                    listRslt.add(rslt);
                                } else {

                                    Resultat rslt = new Resultat(true, listECF.get(j), listStg.get(i));
                                    listRslt.add(rslt);
                                }

                            }

                        }
                    }

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
        StagiaireDAO stgDAO = new StagiaireDAO();
        ECFDAO ecfDAO = new ECFDAO();

        List<ECF> listECF = ecfDAO.findAll();
        System.out.println(listECF);
        List<Stagiaire> listStg = stgDAO.findAll();
        System.out.println(listStg);

        Connection connect = DBConnect.gettingConnected();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `Resultat`");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {

                for (int i = 0; i < listStg.size(); i++) {

                    if (listStg.get(i).getCodeStagiaire() == res.getInt("id_stagiaire") && listStg.get(i).getForm().getId() == form.getId()) {

                        for (int j = 0; j < listECF.size(); j++) {

                            if (listECF.get(j).getId() == res.getInt("id_ecf")) {

                                if (res.getInt("validation") == 0) {
                                    Resultat rslt = new Resultat(false, listECF.get(j), listStg.get(i));
                                    listRslt.add(rslt);
                                } else {

                                    Resultat rslt = new Resultat(true, listECF.get(j), listStg.get(i));
                                    listRslt.add(rslt);
                                }

                            }

                        }
                    }

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

}
