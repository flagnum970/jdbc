/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.metier;

/**
 *
 * @author cflagollet
 */
public class Adherent {
    private int numAdherent;
    private String nomAdhrent;
    private String prenomAdherent;
    
    /**
     * Constructeur d'un adhérent
     * @param num
     * @param nom
     * @param prenom 
     */
    public Adherent(int num,String nom, String prenom) {
        this.numAdherent = num;
        this.nomAdhrent = nom;
        this.prenomAdherent = prenom;
    }

    public int getNumAdherent() {
        return numAdherent;
    }

    public String getNomAdhrent() {
        return nomAdhrent;
    }

    public String getPrenomAdherent() {
        return prenomAdherent;
    }
    
    
}
