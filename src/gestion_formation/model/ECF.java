/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model;

/**
 *
 * @author vanel
 */
public class ECF {
    
    private int id;
    private String nom;
    private Formation formation;
   

    public ECF(int id, String nom, Formation formation) {
        this.id = id;
        this.nom = nom;
        this.formation = formation;
     
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

   
    
    
    
    
    
    
}
