/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.controler;
import jdbc.modele.AbstractModel;
import jdbc.modele.ModelReservation;
/**
 *
 * @author cflagollet
 */
public class ControlerReservation extends AbstractControler {
    public ControlerReservation(ModelReservation modelReservation) {
    super(modelReservation);
  }

  public boolean control() {
    return  model.control();
  }
 
  public void setNbPersonnes(String sNbPers) {
    
    int nbPers;
    try {
           nbPers = Integer.valueOf(sNbPers);
           if (nbPers < 0) 
                   nbPers = 0;
      } catch (Exception e) {
            nbPers = 0;
      }
      System.out.println("max salle : "+model.getPersMax()+ " reservé : "+model.getPersRes()+" nbpers "+nbPers);
      
      model.setNbPersonnes(nbPers);
  }
  
  public void save() {
      if (model.control()) {
          model.insertReservation();
          model.initModelReservation();
      }
          
  }
  public void reset() {
    this.model.reset();
  }
  
  public void quit() {
      model.closeDB();
      System.exit(0);   
  }
      
  public void setAdherent(int numAdherent){
      model.setAdherent(numAdherent);
  }
  
  public void setRepresentation(int numRepresentation){ 
    model.setRepresentation(numRepresentation);
  }
  
}
