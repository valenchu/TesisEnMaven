/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Valentin
 */
public class HiloLector extends Thread {

    private InputStream leer;
    private String dato;

    public HiloLector(InputStream leer, String dato) {
        this.leer = leer;
        this.dato = dato;

    }

    @Override
    //Hilo que se encarga de ir tirando dato de lo que sucede cuando se genera el back up de la BD
    public void run() {
        try {
            byte[] buffer = new byte[100];
            int leido = leer.read(buffer);
            while (leido > 0) {
                String texto = new String(buffer, 0, leido);
                System.out.print(texto);
                leido = leer.read(buffer);
            }
            if(dato.equals("backUP")){
                 JOptionPane.showMessageDialog(null,"BACKUP, COMPLETADO REVISE SU CARPETA PRINCIPAL","INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            }else{
            JOptionPane.showMessageDialog(null, "SE RECUPERO LA BD EXITOSAMENTE","INFORMACIÓN",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"ERROR NO HUBO CAMBIOS","ERROR", JOptionPane.ERROR);
        }
    }

}
