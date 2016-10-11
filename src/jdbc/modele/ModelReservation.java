/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.modele;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.interBD.InterBD;
import jdbc.metier.Adherent;
import jdbc.metier.Representation;
import jdbc.observer.*;
import jdbc.observer.WhatChanged.type_message;

/**
 *
 * @author cflagollet
 */

public class ModelReservation extends AbstractModel{

    public ModelReservation(InterBD interbd) {
        super(interbd);
        hashAdherent = selectLstAdherent();//= new HashMap<Integer, Adherent>();
        //hashRepresentation = new HashMap<Integer,Representation>();
        
    }

    public void initModelReservation() {
        notifyObserver(new WhatChanged(type_message.MSG_INIT, 1));
    }
    
    public void setReservation(int numAdherent, int numRepresentation, int nbPersonnes,Date dateJour )
    {
        this.numAdherent = numAdherent;
        this.numRepresentation = numRepresentation;
        this.nbPers = nbPersonnes;
    }
    
  //Force le calcul
    @Override
  public void getTotal() {
    calculTotal();
  }

  //Réinitialise tout
    @Override
  public void reset(){
    this.total = 0;
    this.nbPers = 1;
    this.cboAdherentIndexSelected = -1;
    this.cboRepresentationIndexSelected=-1;
    
    //Mise à jour !
    notifyObserver(new WhatChanged(type_message.MSG_RESET,this.cboAdherentIndexSelected,this.cboRepresentationIndexSelected,this.nbPers,this.total));
  }

  //Calcul
  public void calculTotal(){    
    this.total =  this.nbPers; //*cout
      
    //On lance aussi la mise à jour !
    notifyObserver(new WhatChanged(type_message.MSG_TOTAL,String.valueOf(this.total)));
  }

    @Override
    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public void setAdherent(int numAdherent) {
        this.numAdherent = numAdherent;
    }

    @Override
    public void setRepresentation(int numRepresentation) {
        this.numRepresentation = numRepresentation;
        
     //   notifyObserver(new WhatChangedString.valueOf(this.numRepresentation));
    }

    @Override
    public void setNbPersonnes(int nbPers) {
        this.nbPers=nbPers;
    }
    
    public HashMap<Integer,Adherent> selectLstAdherent () {
        HashMap<Integer,Adherent> hash = new HashMap<Integer,Adherent>();
        String req = "select numAdherent, TRIM(nomAdherent), TRIM(prenomAdherent) from adherent";
        
        ResultSet rs = interbd.exec(req);
                try {
            while (rs.next()) {
                
                Adherent a = new Adherent(  rs.getInt(1),
                                            rs.getString(2),                                            
                                            rs.getString(3));
                                                    
               hash.put( rs.getInt(1),a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
           
    }
            
    public HashMap<Integer,Representation>  selectLstRepresentation() {
        String req =  "select r.numRepresentation,nom from representation";
        
        
        HashMap<Integer,Representation> hash = new HashMap<Integer,Representation>();
        
        return hash;
    }
    
    public void InsertReservation() {
        
    }
}
