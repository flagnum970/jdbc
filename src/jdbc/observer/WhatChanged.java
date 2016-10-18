/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.observer;

/**
 * Classe pour la communication entre le modele et la vue : 
 * Le model envoie le message qui correspond au données à mettre à jour à l'écran par la vue
 * @author cflagollet
 * 
 */
public class  WhatChanged {

// messages indiquant la nature du changement
public enum type_message {MSG_CBO_ADHERENT,MSG_CBO_REPRESENTATION,MSG_TOTAL,MSG_RESET,MSG_INIT,MSG_NBPERS,MSG_ERREUR};

// Variables d’instance
  public  type_message mess;
  public Object O1,O2,O3,O4,O5;  // 03,04,… si nécessaire !
  public String erreur;
  
// Constructeurs utiles
  public WhatChanged(type_message mess) {
    this.mess = mess;  
  } // O1 et 02 sont nuls
  
  public WhatChanged(type_message mess, String s) {
      this.mess= mess;
      this.erreur = s;
  }
  
  public WhatChanged(type_message mess,Object O1) {
    this.mess = mess;
    this.O1 = O1;  
  } // 02 est nul
  
  public WhatChanged(type_message mess, Object O1 ,Object O2) {
    this.mess = mess;
    this.O1 = O1;
    this.O2 = O2;  
  }
  
  public WhatChanged(type_message mess, Object O1 ,Object O2, Object O3) {
    this.mess = mess;
    this.O1 = O1;
    this.O2 = O2;  
    this.O3 = O3;
  }
  
  public WhatChanged(type_message mess, Object O1 ,Object O2,Object O3, Object O4) {
    this.mess = mess;
    this.O1 = O1;
    this.O2 = O2;
    this.O3 = O3;
    this.O4 = O4;
  }
  
    public WhatChanged(type_message mess, Object O1 ,Object O2,Object O3, Object O4,Object O5) {
    this.mess = mess;
    this.O1 = O1;
    this.O2 = O2;
    this.O3 = O3;
    this.O4 = O4;
    this.O5 = O5;
  }
  
}
