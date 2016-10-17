/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.modele;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        hashAdherent = selectLstAdherent();//= new LinkedHashMap<Integer, Adherent>();
        hashRepresentation = selectLstRepresentation();
        
    }

    public void initModelReservation() {
        reset();
        notifyObserver(new WhatChanged(type_message.MSG_INIT, 1,selectLstAdherent(),selectLstRepresentation(),this.nbPers,this.total));
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
        }
    }

    @Override
    public void setNbPersonnes(int nbPers) {
        this.nbPers=nbPers;
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
            
    public LinkedHashMap<Integer,Representation>  selectLstRepresentation() {
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
    
    public void insertReservation() {
         Reservation r = new Reservation(numRepresentation, numAdherent, nbPers);
         
         String req =  "insert into RESERVATION (NUMREPRESENTATION, NUMADHERENT, NBPERSONNES) VALUES ("+
                        r.getNumRepresentation()+","+r.getNumAdherent()+","+r.getNbPersonnes()+")";
         
         System.out.println("req : "+req);
         int ret = interbd.update(req);
    }

    public void closeDB() {
        interbd.close();
    }

    public boolean control() {
        boolean bOk = true;
        
        if (this.numAdherent == 0) {
            bOk = false;
        }
            
        if (this.numRepresentation == 0)
            bOk = false;
        
        if (this.nbPers == 0)
            bOk= false;
        
        return bOk;
    }
            
}
