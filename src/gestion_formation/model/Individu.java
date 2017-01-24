/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;

/**
 *
 * @author vanel
 */
public class Individu implements java.io.Serializable {

    private String nom = "";
    private String prenom = "";
    private int taille = 0;

    public Individu(final String nom, final String prenom, final int taille) {

        this.nom = nom;
        this.prenom = prenom;
        this.taille = taille;

    }

    public static void SerializeIndividu() {

        final Individu ind = new Individu("Jean", "Dupont", 177);
        ObjectOutputStream oos = null;
        try {

            final FileOutputStream file = new FileOutputStream("indiv.ser");
            oos = new ObjectOutputStream(file);
            oos.writeObject(ind);
            oos.writeLong(2652458);
            oos.writeUTF("UTF-8 ma gueule !");
            oos.flush();

        } catch (final java.io.IOException e) {

            e.printStackTrace();

        } finally {

            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }

            } catch (final java.io.IOException e) {
                e.printStackTrace();
            }

        }

    }
    
    public static void UnSerializeIndividu() {
        
        
        ObjectInputStream ois = null;
        
        try {
            
            final FileInputStream file = new FileInputStream("indiv.ser");
            ois = new ObjectInputStream(file);
            
            final Individu ind = (Individu) ois.readObject();
            System.out.println("Individu :");
            System.out.println("Nom :" + ind.getNom());
             System.out.println("PréNom :" + ind.getPrenom());
              System.out.println("Nom :" + Integer.toString(ind.getTaille()));
            
            
            
        } catch (java.io.IOException e) {
            
            e.printStackTrace();
            
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(final int taille) {
        this.taille = taille;
    }

}
