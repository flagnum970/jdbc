/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.modele;

import jdbc.observer.Observable;
import jdbc.observer.Observer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
  protected double total = 0;
  protected int nbPersMax = 0;
  protected int nbPersRes = 0;
  protected int cboAdherentIndexSelected = -1;
  protected int cboRepresentationIndexSelected = -1;
  protected double tarif;
  protected LinkedHashMap<Integer, Adherent> hashAdherent ;
  protected LinkedHashMap<Integer,Representation> hashRepresentation;
  protected String sErreur = "";
  protected static InterBD interbd;
  private ArrayList<Observer> listObserver = new ArrayList<Observer>();   
  
  public AbstractModel(InterBD interbd) {     
      AbstractModel.interbd = interbd;
  }

  public abstract LinkedHashMap<Integer, Adherent> selectLstAdherent(); 
  public abstract LinkedHashMap<Integer, Representation> selectLstRepresentation(); 
  
  public abstract void setPersMax(int maxPers); 
  public abstract void setPersRes(int nbPersRep); 
  public abstract int getPersMax(); 
  public abstract int getPersRes();
  public abstract void initModelReservation();
  public abstract void reset();
  public abstract void setTotal();
  public abstract void setAdherent(int IndexAdherent) ;
  public abstract void setRepresentation(int numRepresentation);
  public abstract void setNbPersonnes(int nbPers);
  public abstract void insertReservation();
  public abstract boolean control();
  
  public abstract void closeDB();
  
  /**
   * Implémentation du pattern observer
   */
  @Override
  public void addObserver(Observer obs) {
    this.listObserver.add(obs);
  }

  public void notifyObserver(WhatChanged wc) {

    for(Observer obs : listObserver)
      obs.update(wc);
  }

  public void removeObserver() {
    listObserver = new ArrayList<Observer>();
  }   
}
