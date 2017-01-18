/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author vanel
 */
public class Resultat {
    
    private boolean valide;
    private ECF ecf;
    private Stagiaire stg;

    public Resultat(boolean valide, ECF ecf, Stagiaire stg) {
        this.valide = valide;
        this.ecf = ecf;
        this.stg = stg;
    }

    public Resultat(boolean valide) {
        this.valide = valide;
    }
    
    



    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public ECF getEcf() {
        return ecf;
    }

    public void setEcf(ECF ecf) {
        this.ecf = ecf;
    }

    public Stagiaire getStg() {
        return stg;
    }

    public void setStg(Stagiaire stg) {
        this.stg = stg;
    }




    
    


    
    
    
    
}
