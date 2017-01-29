/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model;


import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author vanel
 */
public class Formation implements Serializable {

    private List<Stagiaire> listeStagiaire = new ArrayList<>();
    private String nom;
    private List<ECF> listECF;
    private int id;
    private Date start;
    private Date end;

    public Formation(String nom, Date start, Date end) {
        this.nom = nom;
        this.start = start;
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<Stagiaire> getListeStagiaire() {
        return listeStagiaire;
    }

    public Formation(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }

    public Formation(String nom) {

        this.nom = nom;
    }

    public List<ECF> getListECF() {
        return listECF;
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

  
    public Formation(String nom, List<ECF> listECF) {
        this.nom = nom;
        this.listECF = listECF;
    }

    

    @Override
    public String toString() {
        return nom ;
    }
    
    

}
