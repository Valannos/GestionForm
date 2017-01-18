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
        List<Stagiaire> listStg = stgDAO.findAll();
        List<Resultat> listRslt = null;

        Connection connect = DBConnect.gettingConnected();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `Resultat`");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {
                Resultat rstl;
                if (res.getInt("validation") == 0) {
                    rstl = new Resultat(false);
                } else {
                    rstl = new Resultat(true);
                }

                for (int i = 0; i < listStg.size(); i++) {

                    if (listStg.get(i).getId() == res.getInt("id_stagiaire")) {
                        rstl.setStg(listStg.get(i));
                        for (int j = 0; j < listECF.size(); j++) {

                            if (listECF.get(j).getId() == res.getInt("id_ecf")) {

                                rstl.setEcf(listECF.get(j));
                                listRslt.add(rstl);

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
