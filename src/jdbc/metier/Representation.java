/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.metier;

import java.sql.Date;

/**
 *
 * @author cflagollet
 */
public class Representation {
    private int numRepresentation;
    private int numSpectales;
    private int numSalle;
    private Date dateRepresentation;
    private double tarif;
    private String nomSpectacle;
    private int nbPersRes;
    private int nbPersMax;

    /**
     * Constructeur d'une représentation
     * @param numRepresentation
     * @param numSpectales
     * @param numSalle
     * @param dateRepresentation
     * @param tarif
     * @param nomSpectacle
     * @param nbPersMax
     * @param nbPersRes 
     */
    public Representation(int numRepresentation, int numSpectales, int numSalle, Date dateRepresentation, double tarif,String nomSpectacle,int nbPersMax, int nbPersRes) {
        this.numRepresentation = numRepresentation;
        this.numSpectales = numSpectales;
        this.numSalle = numSalle;
        this.dateRepresentation = dateRepresentation;
        this.tarif = tarif;
        this.nbPersRes = nbPersRes;
        this.nbPersMax = nbPersMax;
        this.nomSpectacle = nomSpectacle;
    }

    public int getNumRepresentation() {
        return numRepresentation;
    }

    public int getNumSpectales() {
        return numSpectales;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public Date getDateRepresentation() {
        return dateRepresentation;
    }

    public double getTarif() {
        return tarif;
    }

    public String getNomSpectacle() {
        return nomSpectacle;
    }

    public int getNbPersRes() {
        return nbPersRes;
    }

    public int getNbPersMax() {
        return nbPersMax;
    }
    
}
