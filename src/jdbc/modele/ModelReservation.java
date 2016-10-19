/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.modele;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.interBD.InterBD;
import jdbc.metier.Adherent;
import jdbc.metier.Representation;
import jdbc.metier.Reservation;
import jdbc.observer.*;
import jdbc.observer.WhatChanged.type_message;

/**
 *
 * @author cflagollet
 */

public class ModelReservation extends AbstractModel{

    public ModelReservation(InterBD interbd) {
        super(interbd);
        hashAdherent = selectLstAdherent();
        hashRepresentation = selectLstRepresentation();   
    }

    public void initModelReservation() {
        reset();
        notifyObserver(new WhatChanged(type_message.MSG_INIT, 1,selectLstAdherent(),selectLstRepresentation(),this.total));
    }
    
    public void setReservation(int numAdherent, int numRepresentation, int nbPersonnes,Date dateJour )
    {
        this.numAdherent = numAdherent;
        this.numRepresentation = numRepresentation;
        this.nbPers = nbPersonnes;
    }
    
    public void setMaxPers(int maxPers) {
      this.nbPersMax= maxPers;
      
    }
    
    public void setPersRes(int nbPersRes) {
      this.nbPersRes= nbPersRes;
    }
    
  /**
   * Réinitialise tout
   */
    @Override
  public void reset(){
    System.out.println("remise à 0 des champs");  
    this.total = 0;
    this.nbPers = 1;
    this.cboAdherentIndexSelected = -1;
    this.cboRepresentationIndexSelected=-1;
    this.nbPersMax=-1;
    this.numAdherent =0;
    this.numRepresentation =0;
    this.nbPersRes = -1;
    hashRepresentation = selectLstRepresentation(); //on recharge la combo pour MAJ le  nombre de places dispos!
  
  }

  //Calcul
  public void setTotal(){    
    this.total =  this.nbPers*this.tarif; //*cout
      
    //On lance aussi la mise à jour !
    notifyObserver(new WhatChanged(type_message.MSG_TOTAL,this.total));
  }


    @Override
    public void setAdherent(int indexCboAdherent) {
        if (indexCboAdherent!=-1)
            this.numAdherent = (int)hashAdherent.keySet().toArray()[indexCboAdherent];
    }

    @Override
    public void setRepresentation(int indexCbçoRepresentation) {
        if (indexCbçoRepresentation != -1) {
            this.numRepresentation = (int)hashRepresentation.keySet().toArray()[indexCbçoRepresentation];
            this.tarif = hashRepresentation.get(this.numRepresentation).getTarif();
            this.nbPersMax = hashRepresentation.get(this.numRepresentation).getNbPersMax();
            this.nbPersRes = hashRepresentation.get(this.numRepresentation).getNbPersRes();
            setTotal();
            notifyObserver(new WhatChanged(type_message.MSG_CBO_REPRESENTATION,this.total));
           
        Boolean bOk = controlNbPlaces();    
        }
    }

    @Override
    public void setNbPersonnes(int nbPers) {
        Boolean bOk = true;
        if (this.getPersMax() != -1) {
            this.nbPers=nbPers;
            bOk = controlNbPlaces();
        }
             
        setTotal();
        notifyObserver(new WhatChanged(type_message.MSG_NBPERS,this.nbPers,this.total));
       
    }
    
    public  int getPersMax() {
        return nbPersMax;
    }
    
    public int getPersRes() {
        return nbPersRes;
    }

    public void setPersRep(int nbPersRep) {
        this.nbPersRes = nbPersRes;
    }
    
    public void setPersMax(int nbPersMax) {
        this.nbPersMax = nbPersMax;
    }
    
    /** sélection des adhérents 
     * 
     * @return : renvoie une Linked HashMap (donc qui respecte l'ordre d'insertion)
     */
    public LinkedHashMap<Integer,Adherent> selectLstAdherent () {
        LinkedHashMap<Integer,Adherent> hash = new LinkedHashMap<Integer,Adherent>();
        String req = "select numAdherent, TRIM(nomAdherent), TRIM(prenomAdherent) from adherent";
        
        ResultSet rs = interbd.exec(req);
        try {
            while (rs.next()) {
                Adherent a = new Adherent(rs.getInt(1),rs.getString(2), rs.getString(3));                     
                hash.put( rs.getInt(1),a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
           
    }
    /**
     * 
     * selection des représentions, du nombre de de places dans la salle, et du nombre de réservations déjà effectuées
     * @return : renvoie une Linked HashMap (donc qui respecte l'ordre d'insertion)
     */        
    public LinkedHashMap<Integer,Representation>  selectLstRepresentation() {
        java.util.Date dateRep=null;
        String req =    "select r.numRepresentation,r.numSpectacle,r.numSalle,r.dateRepresentation,r.tarif,trim(s.nomspectacle), \n" +
                        "sa.nbplaces,sum(rs.nbpersonnes) \n" +
                        "from representation r join spectacle s on r.numspectacle = s.numspectacle \n" +
                        "join salle sa on r.numsalle = sa.numSalle \n" +
                        "left join reservation rs on rs.numrepresentation = r.numrepresentation \n" +
                        "group by r.numRepresentation,r.numSpectacle,r.numSalle,r.dateRepresentation,r.tarif,trim(s.nomspectacle), \n" +
                        "sa.nbplaces order by r.DATEREPRESENTATION asc, r.NUMREPRESENTATION asc";

        LinkedHashMap<Integer,Representation> hash = new LinkedHashMap<Integer,Representation>();
         ResultSet rs = interbd.exec(req);
                try {
            while (rs.next()) {
              
                Representation r = new Representation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),
                                                      rs.getDouble(5),rs.getString(6),rs.getInt(7),rs.getInt(8));                                    
                hash.put( rs.getInt(1),r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return hash;
    }
    
    /** insertion de la réservation 
     * Le numéro de réservation est valorisé automatiquement dans la base (sequence + trigger : auto-incrément
     */
    public void insertReservation() {
         Reservation r = new Reservation(numRepresentation, numAdherent, nbPers);
         
         String req =  "insert into RESERVATION (NUMREPRESENTATION, NUMADHERENT, NBPERSONNES,dateresa) VALUES ("+
                        r.getNumRepresentation()+","+r.getNumAdherent()+","+r.getNbPersonnes()+",sysdate)";
         
         System.out.println("req : "+req);
         int ret = interbd.update(req);
         if (ret==1) 
             interbd.update("commit");
    }

    /**
     * Femeture de la base
     */
    public void closeDB() {
        interbd.close();
    }

    /** 
     * contrôle des données: tout doit être renseigné et le nombre de places inférieur ou égal au disponible
     * @return boolean qui indique si contrôle ok ou pas
     */
    public boolean control() {
        boolean bOk = true;
        
        if ((this.numAdherent == 0) || (this.numRepresentation == 0) || (this.nbPers == 0)){
            bOk = false;
            notifyObserver(new WhatChanged(type_message.MSG_ERREUR,"Veuillez renseigner tous les champs"));
        } else 
            bOk = controlNbPlaces();
       
        return bOk;
    }
    
    private boolean controlNbPlaces() {
        Boolean bOk = false;
        int nbPlacesRestantes = this.getPersMax()-this.getPersRes();
        
        if (nbPlacesRestantes == 0)
            notifyObserver(new WhatChanged(type_message.MSG_ERREUR,"Cette représentation affiche complet"));
        else if (this.nbPers>nbPlacesRestantes) 
            notifyObserver(new WhatChanged(type_message.MSG_ERREUR,"Il ne reste que "+nbPlacesRestantes+" places pour cette représentation"));
        else if (this.nbPers==0)
            notifyObserver(new WhatChanged(type_message.MSG_ERREUR,"Veuillez saisir un nombre de places"));
        else {
            notifyObserver(new WhatChanged(type_message.MSG_ERREUR,""));
            bOk = true; 
        }
        
        return bOk;
    }
           
}
