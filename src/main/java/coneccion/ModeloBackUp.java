/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import javax.swing.JFileChooser;

/**
 *
 * @author Valentin
 */
public class ModeloBackUp {

    private static String urlGuardado ;// url para guardar la bd
    private static JFileChooser urlguar;
    private static String urlRecuperar ;//url para recuperar bd
    private static String usuario = "";// usuario bd
    private static String contra="";// pass bd
    private static String nomBD="";// nombre de la BD a guardar
    private static String nomRecuperarBD;

    public ModeloBackUp(String urlGuardado, String urlRecuperar, String usuario, String contra, String nomBD, String nomRecuperarBD) {
        this.urlGuardado = urlGuardado;
        this.usuario = usuario;
        this.contra = contra;
        this.nomBD = nomBD;
        this.nomRecuperarBD = nomRecuperarBD;
        
        this.urlRecuperar = urlRecuperar;
    }

    public static String getNomRecuperarBD() {
        return nomRecuperarBD;
    }

    public static void setNomRecuperarBD(String nomRecuperarBD) {
        ModeloBackUp.nomRecuperarBD = nomRecuperarBD;
    }

    public ModeloBackUp() {
    }

    public  String getUrlGuardado() {
        return urlGuardado;
    }

    public void setUrlGuardado(String urlGuardado) {
        this.urlGuardado = urlGuardado;
    }

    public String getUrlRecuperar() {
        return urlRecuperar;
    }

    public void setUrlRecuperar(String urlRecuperar) {
        this.urlRecuperar = urlRecuperar;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNomBD() {
        return nomBD;
    }

    public void setNomBD(String nomBD) {
        this.nomBD = nomBD;
        System.out.println(nomBD);
    }
    
    public void devolver (JFileChooser uG){
        this.urlguar= uG;
    }

}
