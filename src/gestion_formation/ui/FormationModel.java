/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.ui;

import gestion_formation.model.DAO.FormationDAO;
import gestion_formation.model.Formation;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vanel
 */
public class FormationModel extends AbstractTableModel {

    private List<Formation> allForms;
    private final String[] cols = {"N°", "Nom Formation", "Date Début", "Date Fin"};

    public FormationModel() {
        super();
        allForms = FormationDAO.findAll();

    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }

    @Override
    public int getRowCount() {
        return allForms.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return allForms.get(rowIndex).getId();

            case 1:

                return allForms.get(rowIndex).getNom();

            case 2:
                return allForms.get(rowIndex).getStart();

            case 3:
                return allForms.get(rowIndex).getEnd();
            default:
                return null;

        }

    }
    
    public Formation getFormation(int index) {
        
        
        return allForms.get(index);
    }
}
