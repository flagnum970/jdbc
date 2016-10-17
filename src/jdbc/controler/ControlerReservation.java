/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.controler;
import jdbc.modele.AbstractModel;
/**
 *
 * @author cflagollet
 */
public class ControlerReservation extends AbstractControler {
    public ControlerReservation(AbstractModel modelReservation) {
    super(modelReservation);
  }

  public boolean control() {
      return  true;
  }
 
  public void setNbPersonnes(String sNbPers) {
    
    int nbPers;
    try {
           nbPers = Integer.valueOf(sNbPers);
           if (nbPers < 1) 
                   nbPers = 1;
      } catch (Exception e) {
            nbPers = 1;
      }
      System.out.println("max salle : "+model.getPersMax()+ " reservé : "+model.getPersRes()+" nbpers "+nbPers);
      
      int nbPlacesRestantes = model.getPersMax()-model.getPersRes();
      if (nbPers>nbPlacesRestantes) 
          nbPers = nbPlacesRestantes;
      
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
  
  public void closeDB() {
      model.closeDB();
  }
      
}
