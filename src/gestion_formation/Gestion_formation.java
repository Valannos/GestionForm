/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation;

import java.util.Scanner;
import model.Formation;

import model.Stagiaire;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.util.List;
import javax.swing.JFrame;

import model.DAO.FormationDAO;
import model.DAO.PersonneDAO;
import model.DAO.ResultatDAO;
import model.DAO.StagiaireDAO;
import model.Personne;
import model.Resultat;

/**
 *
 * @author vanel
 */
public class Gestion_formation {

    public static Scanner sc = new Scanner(System.in);

    /**
     *
     */
    public static void displayWindow() {

        JFrame win = new JFrame();
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        win.setVisible(true);

    }



/**
 *
 * @param listForm
 * @param choixForm
 */
public static void displaysMainMenu(List<Formation> listForm, int choixForm) {

        System.out.println(Color.ANSI_GREEN + "******FORMATION " + listForm.get(choixForm).getNom() + "******" + Color.ANSI_RESET);

        System.out.println(Color.ANSI_YELLOW + "1-Ajouter un stagiaire" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLUE + "2-Afficher la liste courante" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + "3-Quitter" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + "4-Choisir une autre formation" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + "5-Voir tous les stagiaires" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_RED + "6-Retirer un stagiaire de la formation" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_BLUE + "7-Gérer les résultats" + Color.ANSI_RESET);
        System.out.println(Color.ANSI_GREEN + "************" + Color.ANSI_RESET);

    }

    /**
     *
     * @param listStg
     */
    public static void displaysStagiaireList(List<Stagiaire> listStg) {

        for (int i = 0; i < listStg.size(); i++) {

            System.out.println("************");
            System.out.println("Nom : " + listStg.get(i).getNom());
            System.out.println("Prénom : " + listStg.get(i).getPrenom());
            System.out.println("N° : " + Integer.toString(listStg.get(i).getCodeStagiaire()));
            if (listStg.get(i).getForm() != null) {
                System.out.println("Formation : " + listStg.get(i).getForm().getNom() + ".");
            }

            System.out.println("************");

        }

    }

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args)  {

        List<Formation> listForm = FormationDAO.findAll();
        List<Personne> listPers;
        List<Stagiaire> listStg;
        displayWindow();
        String choice;
        int choixForm = 0;
        Boolean quit = false;
        try {
            choixForm = Integer.parseInt(Formation.chooseFormation());
        } catch (IndexOutOfBoundsException e) {

            System.out.println("Saisie incorrecte");

        }

        while (!quit) {

            displaysMainMenu(listForm, choixForm);
            choice = sc.next();

            switch (choice) {

                case "3":

                    quit = true;
                    break;

                case "1":
                    listPers = PersonneDAO.findAllNonStagiaire();

                    if (listPers.isEmpty()) {

                        System.out.println("Aucune personne ne peut être inscrite.");

                    } else {
                        System.out.println("****Choisisez une personne à inscrire****");
                        for (int i = 0; i < listPers.size(); i++) {
                            System.out.println("************");
                            System.out.println("Nom : " + listPers.get(i).getNom());
                            System.out.println("Prénom : " + listPers.get(i).getPrenom());
                            System.out.println("N°Personne : " + Integer.toString(listPers.get(i).getId()));
                            System.out.println("************");

                        }
                        System.out.println("Numéro : ");
                        String chx = sc.next();

                        for (int i = 0; i < listPers.size(); i++) {
                            if (Integer.parseInt(chx) == listPers.get(i).getId()) {
                                Personne pers = new Personne(listPers.get(i).getNom(), listPers.get(i).getPrenom());
                                pers.setId(listPers.get(i).getId());
                                StagiaireDAO.addStagiaireToFormation(pers, listForm.get(choixForm));

                                System.out.println("Ajout confirmé.");

                                break;
                            }
                        }

                    }

                    break;
                case "2":
                    listStg = StagiaireDAO.getStgByFormation(listForm.get(choixForm));
                    displaysStagiaireList(listStg);

                    break;

                case "4":

                    choixForm = Integer.parseInt(Formation.chooseFormation());

                    break;
                case "5":

                    List<Stagiaire> allStg = StagiaireDAO.findAll();
                    displaysStagiaireList(allStg);

                    break;
                case "6":
                    System.out.println("Saisir le code du stagiaire à retirer de la formatrion " + listForm.get(choixForm).getNom() + ".");
                    listStg = StagiaireDAO.getStgByFormation(listForm.get(choixForm));

                    displaysStagiaireList(listStg);
                    System.out.println("Saisir 0 pour quitter");

                    boolean idValide;
                    int chxStg = 0;
                    do {

                        try {

                            chxStg = sc.nextInt();
                            idValide = true;

                        } catch (NumberFormatException e) {

                            e.printStackTrace();
                            System.out.println("Saisie invalide");
                            idValide = false;

                        }

                    } while (!idValide);

                    if (chxStg == 0) {

                        System.out.println("Aucun stagiaire n'a été retiré de la formation");

                    } else {
                        for (int i = 0; i < listStg.size(); i++) {

                            if (listStg.get(i).getCodeStagiaire() == chxStg) {

                                StagiaireDAO.RemoveStagiaire(listStg.get(i));
                                listStg.remove(listStg.get(i));
                                System.out.println("Stagiaire retiré de la formation");

                            }

                        }
                    }

                    break;

                case "7":

                    List<Resultat> listRslt = ResultatDAO.getAllResultsInFormation(listForm.get(choixForm));

                    if (listRslt.isEmpty()) {

                        System.out.println("Aucun stagiaire actuellement dans la formation");

                    } else {

                        for (int i = 0; i < listRslt.size(); i++) {
                            System.out.println("******************");
                            System.out.println("Prénom/Nom : " + listRslt.get(i).getStg().getPrenom() + " " + listRslt.get(i).getStg().getNom());
                            System.out.println("ECF : " + listRslt.get(i).getEcf().getNom());

                            if (listRslt.get(i).isValide()) {

                                System.out.println("ECF Validé");

                            } else {
                                System.out.println("ECF NON Validé");
                            }
                            System.out.println("******************");
                        }
                    }
                    break;

                default:
                    System.out.println(Color.ANSI_RED + "Saisie incorrecte" + Color.ANSI_RESET);
            }
        }
        System.out.println(Color.ANSI_GREEN + "Kenavo !" + Color.ANSI_RESET);
    }

    /* public static void readFile() {

        File f = new File("test.txt");

        System.out.println("Chemin : " + f.getAbsolutePath());
        System.out.println("Nom : " + f.getName());
        System.out.println("Existe ? : " + f.exists());
        System.out.println("Dossier ? : " + f.isDirectory());
        System.out.println("Fichier ? : " + f.isFile());

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {

            fis = new FileInputStream(f);
            fos = new FileOutputStream(new File("File2.txt"));

            byte[] buf = new byte[8];

            int n = 0;

            while ((n = fis.read(buf)) >= 0) {

                fos.write(buf);

                for (byte bit : buf) {
                    System.out.print("\t" + bit + "(" + (char) bit + ")");
                }
                buf = new byte[8];

            }
            System.out.println("Copie Terminée");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {

                if (fis != null) {
                    fis.close();
                }
            } catch (IOException iOException) {

                iOException.printStackTrace();
            }

            try {

                if (fos != null) {
                    fos.close();
                }
            } catch (IOException iOException) {

                iOException.printStackTrace();
            }

        }

    }*/
}
