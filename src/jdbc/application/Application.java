/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.application;

import jdbc.controler.AbstractControler;
import jdbc.controler.ControlerReservation;
import jdbc.ihm.VueRepresentations;
import jdbc.ihm.VueReservation;
import jdbc.interBD.InterBD;
import jdbc.modele.AbstractModel;
import jdbc.modele.ModelReservation;

/**
 * Application Test pour la mise en place du pattern MVC
 * Gestion de resérvation de places de spectacles
 * Base Oracle
 * Contrôles effectués pour la réservation : 
 * -  places restantes
 * Toutes les représentations sont affichées (pas de controle de date
 * @author cflagollet
 */
public class Application {
        /**
     * @param args the command line arguments
     */
     
    public static void main(String args[]) {
        
        InterBD interbd = new InterBD("//localhost/XE", "chris_jdbc", "root");
        if (interbd.connect()) {
        System.err.println("Connexion OK");
        } else {
            System.err.println("erreur connexion");
            System.exit(0);
        }
        
        ModelReservation mr = new ModelReservation(interbd);
        ControlerReservation cr = new ControlerReservation(mr);
        VueRepresentations vrs = new VueRepresentations(cr);
        VueReservation vr = new VueReservation(cr);
        
        //On ajoute les observers : VueRéservation et VueRepresentations
         mr.addObserver(vr); 
         mr.addObserver(vrs);
         
         // TODO Modif pour que ce soit le controleur qui envoie
         cr.initModelReservation();
         
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VueReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VueReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VueReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VueReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vrs.setVisible(true);
                vr.setVisible(true);
            }
        });
    }

}
