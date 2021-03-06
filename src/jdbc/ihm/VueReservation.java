/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.ihm;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import jdbc.observer.Observer;
import jdbc.controler.AbstractControler;
import jdbc.controler.ControlerReservation;
import jdbc.metier.Adherent;
import jdbc.metier.Representation;
import jdbc.modele.AbstractModel;
import jdbc.modele.ModelReservation;
import jdbc.observer.WhatChanged;

/**
 *
 * @author cflagollet
 */
public class VueReservation extends javax.swing.JFrame implements Observer {

  //L'instance de notre objet contrôleur
  private ControlerReservation controler;
  
    /**
     * Creates new form VueReservations
     */
    public VueReservation(ControlerReservation controler) {
        initComponents();
        
        //centrer la fenetre
        this.pack();
        this.setLocationRelativeTo(null);
        
        this.controler= controler;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblAdherent = new javax.swing.JLabel();
        jCboAdherent = new javax.swing.JComboBox<>();
        jLblRepresentation = new javax.swing.JLabel();
        jCboRepresentation = new javax.swing.JComboBox<>();
        jLblNbPersonnes = new javax.swing.JLabel();
        jTxtNbPers = new javax.swing.JTextField();
        jLblTotal = new javax.swing.JLabel();
        jTxtTotal = new javax.swing.JTextField();
        jBtnValider = new javax.swing.JButton();
        jBtnQuitter = new javax.swing.JButton();
        jLblErreur = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nouvelle réservation");
        setName("frmReservation"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.GridLayout(5, 1));

        jLblAdherent.setText("Adhérent :");
        jPanel1.add(jLblAdherent);
        validate();

        jPanel1.add(jCboAdherent);
        validate();

        jLblRepresentation.setText("Représentation :");
        jPanel1.add(jLblRepresentation);
        validate();

        jPanel1.add(jCboRepresentation);
        validate();

        jLblNbPersonnes.setText("Nombre de personnes");
        jPanel1.add(jLblNbPersonnes);
        validate();

        jTxtNbPers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtNbPersFocusLost(evt);
            }
        });
        jTxtNbPers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNbPersActionPerformed(evt);
            }
        });
        jPanel1.add(jTxtNbPers);

        jLblTotal.setText("Total :");
        jPanel1.add(jLblTotal);

        jTxtTotal.setEditable(false);
        jTxtTotal.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.add(jTxtTotal);

        jBtnValider.setText("Valider");
        jBtnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnValiderActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnValider);

        jBtnQuitter.setText("Quitter");
        jBtnQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnQuitterActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnQuitter);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 23, 450, 160));

        jLblErreur.setForeground(new java.awt.Color(255, 0, 51));
        jLblErreur.setPreferredSize(new java.awt.Dimension(52, 14));
        getContentPane().add(jLblErreur, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 420, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /** Clic sur le bouton 'quitter'
     * Appel au controler pour 
     * @param evt 
     */
    private void jBtnQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnQuitterActionPerformed
        controler.quit();              
    }//GEN-LAST:event_jBtnQuitterActionPerformed

    /** 'Entrée' sur la zone de texte nombre de personnes
     * appel au controler pour maj nbpersonnes
     * @param evt 
     */
    private void jTxtNbPersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNbPersActionPerformed
        controler.setNbPersonnes(jTxtNbPers.getText().trim());
    }//GEN-LAST:event_jTxtNbPersActionPerformed

    /** Perte du focus sur la zone de texte nombre de personnes
     * appel au controler pour maj nbpersonnes
     * @param evt 
     */
    private void jTxtNbPersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtNbPersFocusLost
        controler.setNbPersonnes(jTxtNbPers.getText().trim());
    }//GEN-LAST:event_jTxtNbPersFocusLost

    /** clic sur le bouton valider 
     * 
     * @param evt 
     */
    private void jBtnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnValiderActionPerformed
        controler.save();
    }//GEN-LAST:event_jBtnValiderActionPerformed

    /** selection d'une représentation 
    * 
    * @param evt 
    */
    private void jCboRepresentationActionPerformed(java.awt.event.ActionEvent evt) {                                             
        controler.setRepresentation(jCboRepresentation.getSelectedIndex()); 
        controler.setNbPersonnes(jTxtNbPers.getText().trim());
    }   
    
    /** Sélection d'un adhérent
     * 
     * @param evt 
     */
    private void jCboAdherentActionPerformed(java.awt.event.ActionEvent evt) {                                             
        controler.setAdherent(jCboAdherent.getSelectedIndex());    
    }   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnQuitter;
    private javax.swing.JButton jBtnValider;
    private javax.swing.JComboBox<String> jCboAdherent;
    private javax.swing.JComboBox<String> jCboRepresentation;
    private javax.swing.JLabel jLblAdherent;
    private javax.swing.JLabel jLblErreur;
    private javax.swing.JLabel jLblNbPersonnes;
    private javax.swing.JLabel jLblRepresentation;
    private javax.swing.JLabel jLblTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtNbPers;
    private javax.swing.JTextField jTxtTotal;
    // End of variables declaration//GEN-END:variables
 
    /** mise à jour de la vue
     * Envoyé par le model
     * Utilisation de la classe WhatChanged pour savoir quelles sont les données à mettre à jour
     * @param wc 
     * MSG_INIT :   Message Envoyé Après chaque enregistrement, et au début de l'appli
     *              O1 Integer                                  Nombre de personnes
     *              O2 LinkedHashMap<Integer,Adherent>          HashMap des adhérents
     *              O3 LinkedHashMap<Integer,Representation>    HashMap des représentations
     *              04 Double                                   Prix total à payer
     * MSG_TOTAL    Message envoyé lorsque le nombre de personnes, ou la représentation sélectionnée change
     *              01 Double                                   Prix total à payer
     * MSG_NBPERS   Message envoyé quand la chaine saisie  dans nb personnnes n'est pas un nombre : mise à 0 du nb de personnes, et du prix (0.0)
     *              01 Integer                                  Nombre de personnes
     *              02 Double                                   Prix total à payer
     * MSG_ERREUR   Message envoyé lors d'une erreur
     *              01 String                                   Message à afficher
     */
    @Override
    public void update(WhatChanged wc) {
        
          switch (wc.getMess()) {
              case MSG_INIT     : 
                                this.jTxtNbPers.setText(String.valueOf((Integer)wc.getO1()));
                                LinkedHashMap<Integer,Adherent> hash =(LinkedHashMap<Integer,Adherent>) wc.getO2();
                                jCboAdherent.removeAllItems();
                                for (Adherent a : hash.values()) {
                                    jCboAdherent.addItem(a.getNomAdhrent()+" "+a.getPrenomAdherent());
                                }  
                                jCboAdherent.setSelectedIndex(-1);
                                jCboRepresentation.removeAll();
                                LinkedHashMap<Integer,Representation> hashR =(LinkedHashMap<Integer,Representation>) wc.getO3();
                                for (Representation r : hashR.values()) {
                                    jCboRepresentation.addItem(new SimpleDateFormat("dd/MM/yyyy").format(r.getDateRepresentation())+" "+r.getNomSpectacle());
                                }  
                                jCboRepresentation.setSelectedIndex(-1);
                                jCboRepresentation.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            jCboRepresentationActionPerformed(evt);
                                        }
                                });
                                jCboAdherent.addActionListener(new java.awt.event.ActionListener() {
                                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                            jCboAdherentActionPerformed(evt);
                                        }
                                });
                                jTxtTotal.setText(String.valueOf((double)wc.getO4()));
                                break;
              case MSG_TOTAL    : 
                                jTxtTotal.setText(String.valueOf((double)wc.getO1()));
                                break;
              case MSG_NBPERS   :
                                jTxtNbPers.setText(String.valueOf((int)wc.getO1()));
                                jTxtTotal.setText(String.valueOf((double)wc.getO2()));
                                break;
              case MSG_ERREUR   :
                                jLblErreur.setText(wc.getErreur());
              default           : break;
          }

    }
}
