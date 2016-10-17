/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.application;

import jdbc.controler.AbstractControler;
import jdbc.controler.ControlerReservation;
import jdbc.ihmMPT.VueReservation;
import jdbc.interBD.InterBD;
import jdbc.modele.AbstractModel;
import jdbc.modele.ModelReservation;
import jdbc.observer.Observer;

/**
 *
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
        } else System.err.println("erreur connexion");
        
         AbstractModel mr = new ModelReservation(interbd);
         AbstractControler cr = new ControlerReservation(mr);
         VueReservation vr = new VueReservation(cr,mr);
        
         mr.addObserver(vr);
         mr.initModelReservation();
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
                vr.setVisible(true);
            }
        });
    }

}
