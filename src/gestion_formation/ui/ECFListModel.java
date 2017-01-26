/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.ui;

import gestion_formation.model.DAO.ECFDAO;
import gestion_formation.model.ECF;
import gestion_formation.model.Formation;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author vanel
 */
public class ECFListModel extends DefaultListModel {

    List<ECF> ECFFromForm;

    public ECFListModel() {
        super();
        this.ECFFromForm = ECFDAO.findAll();
    }

    public ECFListModel(Formation form) {

        this.ECFFromForm = ECFDAO.findAllinFormation(form);
    }

    @Override
    public void addElement(Object element) {
        super.addElement(element); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty(); //To change body of generated methods, choose Tools | Templates.
    }

}
