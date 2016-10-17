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
public class Reservation {
    private int numRepresentation;
    private int numAdherent;
    private int nbPersonnes;

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
