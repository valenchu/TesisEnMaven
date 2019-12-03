package Bienvenido;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;

public class Tesis {

    public static void main(String[] args) throws ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        for (javax.swing.UIManager.LookAndFeelInfo info : getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    setLookAndFeel(info.getClassName());
                } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    getLogger(Tesis.class.getName()).log(SEVERE, null, ex);
                }
                break;
            }
        }
        //</editor-fold>
        /* Create and display the form */
        invokeLater(() -> {
            new Inicio_Sesion().setVisible(true);
        });

    }

}
