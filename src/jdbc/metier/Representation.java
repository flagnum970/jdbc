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

    public Representation(int numRepresentation, int numSpectales, int numSalle, Date dateRepresentation, double tarif) {
        this.numRepresentation = numRepresentation;
        this.numSpectales = numSpectales;
        this.numSalle = numSalle;
        this.dateRepresentation = dateRepresentation;
        this.tarif = tarif;
    }
    
}
