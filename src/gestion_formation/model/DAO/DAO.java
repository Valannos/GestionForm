/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model.DAO;

import java.util.List;

/**
 *
 * @author vanel
 * @param <T>
 */
public  interface DAO<T> {
    
    public  List<T> findAll();
   
    
    
}
