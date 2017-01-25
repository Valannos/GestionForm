/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation.model;

import static gestion_formation.Gestion_formation.sc;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import gestion_formation.model.DAO.FormationDAO;
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

    /* public void serializeFormation() {

        ObjectOutputStream oos = null;

        try {

            FileOutputStream file = new FileOutputStream("data.ser");
            oos = new ObjectOutputStream(file);
            oos.writeObject(this);
            oos.flush();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException a) {
            a.printStackTrace();
        } finally {

            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();

                }

            } catch (IOException a) {
                a.printStackTrace();

            }

        }

    }

    public static Formation unserializeFormation() {

        Formation form = null;

        ObjectInputStream ois = null;

        try {

            FileInputStream file = new FileInputStream("data.ser");
            ois = new ObjectInputStream(file);
            form = (Formation) ois.readObject();

        } catch (FileNotFoundException fnfe) {

            fnfe.printStackTrace();

        } catch (IOException | ClassNotFoundException ioe) {

            ioe.printStackTrace();

        } finally {

            try {
                if (ois != null) {

                    ois.close();

                }
            } catch (IOException ioe) {

                ioe.printStackTrace();

            }

        }
        
        return form;
    }*/
    public Formation(String nom, List<ECF> listECF) {
        this.nom = nom;
        this.listECF = listECF;
    }

    public static String chooseFormation() {
        FormationDAO formDAO = new FormationDAO();

        List<Formation> listForm = formDAO.findAll();
        System.out.println(listForm.size());

        String choice;

        for (int i = 0; i < listForm.size(); i++) {

            System.out.println(i + "-" + listForm.get(i).getNom());

        }

        System.out.println("Chosisez une formation");
        choice = sc.next();

        return choice;
    }

}
