/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.controler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import jdbc.modele.AbstractModel;

/**
 *
 * @author cflagollet
 */
public abstract class AbstractControler {
  protected AbstractModel model;
  
  public AbstractControler(AbstractModel model){
    this.model = model; 
   }
  
  public abstract void closeDB();
  
  public void setAdherent(int numAdherent){
      model.setAdherent(numAdherent);
  }
  
  public abstract void setNbPersonnes(String sNbPers);
    
  public void setRepresentation(int numRepresentation){ 
    model.setRepresentation(numRepresentation);
  }
   
  //Efface
  public abstract void reset(); 
   
  public abstract void save();
  
  //Méthode de contrôle
  abstract boolean control();
}
