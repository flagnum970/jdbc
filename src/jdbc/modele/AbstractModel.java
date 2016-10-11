/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.modele;

import jdbc.observer.Observable;
import jdbc.observer.Observer;
import java.util.ArrayList;
import java.util.HashMap;
import jdbc.interBD.InterBD;
import jdbc.metier.Adherent;
import jdbc.metier.Representation;
import jdbc.observer.WhatChanged;

/**
 *
 * @author cflagollet
 */
public abstract class AbstractModel implements Observable {
  protected int numAdherent = 0;   
  protected int numRepresentation = 0;   
  protected int nbPers = 0;   
  protected int total = 0;
  protected int maxPers = 0;
  protected int cboAdherentIndexSelected = -1;
  protected int cboRepresentationIndexSelected = -1;
  protected HashMap<Integer, Adherent> hashAdherent ;
  protected HashMap<Integer,Representation> hashRepresentation;
 
  protected static InterBD interbd;
  private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
  
  public AbstractModel(InterBD interbd) {     
      AbstractModel.interbd = interbd;
    
      
  }

  public abstract HashMap<Integer, Adherent> selectLstAdherent(); 
  public abstract HashMap<Integer, Representation> selectLstRepresentation(); 
  
  public void setMaxPers(int maxPers) {
      this.maxPers = maxPers;
      
  }
    
  
  //Efface 
  public abstract void reset();

  //Effectue le calcul
  public abstract void calculTotal();

  //Affichage forcé du résultat
  public abstract void getTotal();

  //Définit l'opérateur de l'opération
  public abstract void setTotal(int total);

  //Définit le nombre à utiliser pour l'opération
  public abstract void setAdherent(int numAdherent) ;

  public abstract void setRepresentation(int numRepresentation);
  
  public abstract void setNbPersonnes(int nbPers);
  
  public abstract void initModelReservation();
          
  //Implémentation du pattern observer
  @Override
  public void addObserver(Observer obs) {
    this.listObserver.add(obs);
  }

  public void notifyObserver(WhatChanged wc) {
   // if(str.matches("^0[0-9]+"))
   //   str = str.substring(1, str.length());

    for(Observer obs : listObserver)
      obs.update(wc);
  }

  public void removeObserver() {
    listObserver = new ArrayList<Observer>();
  }   
}
