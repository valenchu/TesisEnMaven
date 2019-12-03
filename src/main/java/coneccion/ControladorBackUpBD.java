/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Runtime.getRuntime;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class ControladorBackUpBD extends ModeloBackUp {

    //Metodo para generar back up BD
    //código para hacer el backup
   
    public void backUP() {
        //llamo a back up para obterner los parametros
   
        

        try {
            String executeCmd = ""+getUrlGuardado()+" -u "+getUsuario()+" -p"+getContra()+" "+getNomBD()+" -R";
            
            System.out.println(""+getUrlGuardado() + " -u " + getUsuario() + " -p" + getContra() + " " + getNomBD()+"");
            //ejecuto proceso
            
            Process p = getRuntime()
                    //Paso url donde se Ubica mysqdump para generar back up
                    //UR el primero / segundo usuario de BD / tercero Pass BD / cuarto el nombre de la BD que se quiere
                    //hacer backUP
                    .exec(executeCmd);
            new HiloLector(p.getErrorStream(),"backUP").start(); 
            //habro ventana de guardado con fileoutput y guardo la BD
            InputStream leer = p.getInputStream();
    try (FileOutputStream fos = new FileOutputStream("backup_"+getNomBD()+".sql")) {
        byte[] buffer = new byte[100];
        
        int leido = leer.read(buffer);
        while (leido > 0) {
            fos.write(buffer, 0, leido);
            leido = leer.read(buffer);
        }
        fos.close();
    }
    
           
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error no se pudo completar la operación, por favor rellene todos los campos para hacer un backup", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    //Metodo para obtener backUpBD
    public void restore() {
        //llamo a back up para obterner los parametros
        
        try {
            String executeCmd = ""+getUrlRecuperar()+" -u "+getUsuario()+" -p"+getContra()+" "+getNomRecuperarBD()+"";
            System.out.println(""+getUrlRecuperar() + " -u " + getUsuario() + " -p" + getContra() + " " + getNomRecuperarBD()+"");
            Process p = Runtime
                    .getRuntime()
                    //Paso url donde se Ubica mysqdump para generar back up
                    //UR el primero / segundo usuario de BD / tercero Pass BD / cuarto el nombre de la BD que se quiere
                    //hacer backUP
                    .exec(executeCmd);
                   
            new HiloLector(p.getErrorStream(),"").start();  
            
             //habro la ventana para recupeara la bd
            OutputStream os = p.getOutputStream();

            FileInputStream fis = new FileInputStream("backup_"+getNomRecuperarBD()+".sql");
            byte[] buffer = new byte[100];

            int leido = fis.read(buffer);
            while (leido > 0) {
                os.write(buffer, 0, leido);
                leido = fis.read(buffer);
            }

            os.flush();
            os.close();
            fis.close();
            

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error no se pudo completar la operación, por favor rellene todos los campos correctamente para restaurar BD", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
