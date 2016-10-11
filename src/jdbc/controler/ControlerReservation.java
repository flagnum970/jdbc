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

  public void control() {
    //On notifie le modèle d'une action si le contrôle est bon
    //--------------------------------------------------------
      
    //Si l'opérateur est dans la liste
    
        //Si l'opérateur est = 
      //if (this.numAdherent!=-1)
      //  this.res.getResultat(); //On ordonne au modèle d'afficher le résultat
      
      //si l'adhérent a été selectionné
      if (this.numAdherent !=-1)
        this.res.setAdherent(this.numAdherent);  // on met à jour le modele
    
      //si la représentation a été selectionné
      if (this.numRepresentation !=-1) {
        this.res.setRepresentation(this.numRepresentation); 
        this.res.setMaxPers(this.numRepresentation);
        this.res.getTotal(); //On ordonne au modele d'afficher le résultat
               
      }
      
      //Si le nb de personnes à été renseignné et est inférieur au max
      if ((this.nbPers >=0) && this.nbPers<=(this.maxPers - this.total))
          this.res.setNbPersonnes(this.nbPers);
          
      
      
  }
  
}
