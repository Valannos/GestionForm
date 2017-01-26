/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model;

import gestion_formation.Gestion_formation;

import java.util.List;

/**
 *
 * @author vanel
 */
public class Stagiaire extends Personne {

    private int codeStagiaire;
    private List<ECF> listECF;
    private Formation form;

    /**
     * 
     * @param codeStagiaire
     * @param nom
     * @param prenom 
     */
    
    public Stagiaire(int codeStagiaire, String nom, String prenom) {
        super(nom, prenom);
        this.codeStagiaire = codeStagiaire;
    }

    public Stagiaire(Formation form, String nom, String prenom) {
        super(nom, prenom);
        this.form = form;
    }
    
    
    
  
    
    
    public Formation getForm() {
        return form;
    }

    public void setForm(Formation form) {
        this.form = form;
    }

    

    

    public int getCodeStagiaire() {
        return codeStagiaire;
    }

    public void setCodeStagiaire(int codeStagiaire) {

        while (codeStagiaire <= 0) {

            System.out.print("Numéro stagiaire invalide, nouvelle tentative : ");
            codeStagiaire = Gestion_formation.sc.nextInt();

        }

        this.codeStagiaire = codeStagiaire;
    }

    public List<ECF> getListECF() {
        return listECF;
    }

    public void setListECF(List<ECF> listECF) {
        this.listECF = listECF;
    }

}
