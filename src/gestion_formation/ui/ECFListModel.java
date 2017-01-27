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
     
    }

    public ECFListModel(Formation form) {

        this.ECFFromForm = ECFDAO.findAllinFormation(form);
        for (int i = 0; i < ECFFromForm.size(); i++) {
            
            this.addElement(ECFFromForm.get(i));
            
        }
    }

    public void addECFList(List<ECF> listECF) {
       
        for (int i = 0; i < listECF.size(); i++) {
            
            this.addElement(listECF.get(i));
            
        }
        
    }

    


    @Override
    public boolean isEmpty() {
        return super.isEmpty(); //To change body of generated methods, choose Tools | Templates.
    }
    


}
