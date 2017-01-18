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

/**
 *
 * @author vanel
 */
public class ECFDAO implements DAO {

    /**
     * 
     * @return A list of ECF objects
     */
    
    @Override
    public List<ECF> findAll() {

        Connection connect = DBConnect.gettingConnected();
        List<ECF> listECF = new ArrayList<>();
        FormationDAO formDAO = new FormationDAO();
        List<Formation> listForm = formDAO.findAll();

        Statement state = null;
        try {

            state = connect.createStatement();

            String sql = ("SELECT * FROM `ECF`");

            ResultSet res = state.executeQuery(sql);

            while (res.next()) {

                for (int i = 0; i < listForm.size(); i++) {

                    if (res.getInt("id_formation") == listForm.get(i).getId()) {

                        ECF ecf = new ECF(res.getInt("id"), res.getString("nom_ecf"), listForm.get(i));
                    
                        listECF.add(ecf);
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

        return listECF;

    }

}
