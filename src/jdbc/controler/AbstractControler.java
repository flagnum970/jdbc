/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.controler;
import java.util.ArrayList;
import jdbc.modele.AbstractModel;

/**
 *
 * @author cflagollet
 */
public abstract class AbstractControler {
  protected AbstractModel res;
  
  protected int numAdherent = 0, numRepresentation = 0,nbPers = 0,total =0,maxPers;
  protected ArrayList<String> listOperateur = new ArrayList<String>();

  public AbstractControler(AbstractModel res){
    this.res = res;
    
   }
   
  //Définit l'opérateur
  public void setAdherent(int numAdherent){
    
      this.numAdherent = numAdherent;
    
  }
   
  //Définit le nombre
  public void setnumRepresentation(int numRepresentation){
    this.numRepresentation = numRepresentation;
    
  }
   
  //Efface
  public void reset(){
    this.res.reset();
  }
   
  //Méthode de contrôle
  abstract void control();
}
