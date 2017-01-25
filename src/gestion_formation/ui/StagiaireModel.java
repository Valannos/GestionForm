/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.ui;

import gestion_formation.model.DAO.StagiaireDAO;
import gestion_formation.model.Stagiaire;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author vanel
 */
public class StagiaireModel extends AbstractTableModel {

    private final String[] cols = {"Code", "Nom", "Prénom", "Formation"};
    private List<Stagiaire> allStg;

    public StagiaireModel() {
        super();
        allStg = StagiaireDAO.findAll();

    }

    @Override
    public int getRowCount() {
        return allStg.size();
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return cols[columnIndex];
    }

    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return allStg.get(rowIndex).getCodeStagiaire();
            case 1:
                return allStg.get(rowIndex).getNom();
            case 2:
                return allStg.get(rowIndex).getPrenom();
            case 3: 
                return allStg.get(rowIndex).getForm().getNom();
            default:
                return null;
        }

    }
    
    @Override
        public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
        
    }
        
        public Stagiaire getStagiaire(int rowIndex) {
           
           return  allStg.get(rowIndex);
            
            
        }
    
    

   

}
