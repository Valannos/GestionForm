/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_formation;


import gestion_formation.ui.MainWindow;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author vanel
 */
public class Gestion_formation {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName()
            );

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Gestion_formation.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainWindow win = new MainWindow();
        win.setVisible(true);

    }

}
