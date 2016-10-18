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
 * Classe abstract du contrôleur
 * @author cflagollet
 */
public abstract class AbstractControler {
  protected AbstractModel model; // le controleur ne connait que le model 
  
  /** constructeur
   * 
   * @param model 
   */
  public AbstractControler(AbstractModel model){
    this.model = model; 
   }
  
  public abstract void quit();
  
  public abstract void setAdherent(int numAdherent);
  
  public abstract void setNbPersonnes(String sNbPers);
    
  public abstract void setRepresentation(int numRepresentation);
  
  public abstract void reset(); 
   
  public abstract void save();
  
  abstract boolean control();
}
