/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.metier;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author cflagollet
 */
public class Reservation {
    private int numresa;
    private int numRepresentation;
    private int numAdherent;
    private int nbPersonnes;
    private Date dateResa;  

    
    /** Constructeur
     *  
     * @param numRepresentation
     * @param numAdherent
     * @param nbPersonnes 
     * La date de réservation n'est pas valorisée ici, mais directement lors de l'insert (utilisation de sysdate d'oracle)
     * Le numéro de réservation n'est pas non plus valorisé car utilisation d'un auto-incrément dans la base
     */
    public Reservation(int numRepresentation, int numAdherent, int nbPersonnes) {
        this.numRepresentation = numRepresentation;
        this.numAdherent = numAdherent;
        this.nbPersonnes = nbPersonnes;      
    }

    public int getNumRepresentation() {
        return numRepresentation;
    }

    public void setNumRepresentation(int numRepresentation) {
        this.numRepresentation = numRepresentation;
    }

    public int getNumAdherent() {
        return numAdherent;
    }

    public void setNumAdherent(int numAdherent) {
        this.numAdherent = numAdherent;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }
}
