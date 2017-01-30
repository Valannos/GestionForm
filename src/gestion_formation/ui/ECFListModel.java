/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.ui;

import gestion_formation.model.DAO.ECFDAO;
import gestion_formation.model.ECF;
import gestion_formation.model.Formation;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author vanel
 */
public class ECFListModel extends DefaultListModel {

    List<ECF> ECFList;

    public ECFListModel() {
        super();

    }

    @Override
    public void addElement(Object element) {
        super.addElement(element); //To change body of generated methods, choose Tools | Templates.
    }

    public ECFListModel(Formation form) {

        this.ECFList = ECFDAO.findAllinFormation(form);
        for (int i = 0; i < ECFList.size(); i++) {

            this.addElement(ECFList.get(i));

        }
    }

    public void addECFList(List<ECF> listECF) {

        ECFList = new ArrayList<>();

        for (int i = 0; i < listECF.size(); i++) {

            this.addElement(listECF.get(i));
            this.ECFList.add(listECF.get(i));
            System.out.println(ECFList.get(i).getDescription());

        }

    }

    public void addECF(ECF ecf) {

        this.addElement(ecf);
        this.fireContentsChanged(ecf, 0, 0);

    }

    @Override
    public void removeElementAt(int index) {
        super.removeElementAt(index); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty(); //To change body of generated methods, choose Tools | Templates.
    }

    public ECF getECF(int index) {
        return ECFList.get(index);
    }

    @Override
    public Object remove(int index) {
        
        ECFList.remove(index);
        return super.remove(index); 
        
    }



}
