/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bienvenido;

import coneccion.ConexionDB;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Valentin
 */
public class RedesSociales {

    String face = "https://www.facebook.com/";
    String insta = "https://www.instagram.com/";
    String twitter = "https://twitter.com/";

    //Tenes que agregar el boton de modificar redes sociales
    public void modRedes() {

    }
//Conecto a la BD para pasar el reseteo de las cuentas de redes sociales

    public void resetRedes() {
        try {
            //Conecto BD
            ConexionDB cn = new ConexionDB();
            //Genero el string sql que realiza la consulta UPDATE
            try ( //Creo el statement
                    Statement st = cn.getConnection().createStatement()) {
                //Genero el string sql que realiza la consulta UPDATE
                String sql = "UPDATE redsocial SET redFacebook = '" + this.face + "' ,redInstagram = '" + this.insta + "' ,"
                        + "redTwitter = '" + this.twitter + "'";
                //Ejecuto la conslta sql creada anteriormente
                st.executeUpdate(sql);
                //Cierro statement y conexion. Para no generar consumo enxtra en el equipo
            }
            cn.desconectar();
        } catch (SQLException ex) {
            getLogger(RedesSociales.class.getName()).log(SEVERE, null, ex);
        }

    }
//getFace devuelve resultado consulta a la BD de la red social facebook

    public static String getFace() {
        String f = "";
        try {
            //conecto bd
            ConexionDB cn = new ConexionDB();
            //obtengo conexion
            Connection cone = cn.getConnection();
            //Elaboro sql
            String sqll = "SELECT redFacebook FROM redsocial WHERE idRedSocial = 1";
            //preparo y lo ejecuto con result set
            PreparedStatement ps = cone.prepareStatement(sqll);
            ResultSet rs = ps.executeQuery();
            //recorro rs para obtener el dato de red social facebook
            while (rs.next()) {
                f = rs.getString("redFacebook");
            }
        } catch (SQLException ex) {
            getLogger(RedesSociales.class.getName()).log(SEVERE, null, ex);
        }
        //retorno los datos de la red social para abrir en el navegador predeterminado
        return f;
    }
//getInsta devuelve  red social instagram

    public static String getInsta() {
        String i = "";
        try {
            //conecto bd
            ConexionDB cn = new ConexionDB();
            //obtengo conexion
            Connection cone = cn.getConnection();
            //Elaboro sql
            String sqll = "SELECT redInstagram FROM redsocial WHERE idRedSocial = 1";
            //preparo y lo ejecuto con result set
            PreparedStatement ps = cone.prepareStatement(sqll);
            ResultSet rs = ps.executeQuery();
            //recorro rs para obtener el dato de red social instagram
            while (rs.next()) {
                i = rs.getString("redInstagram");
            }
        } catch (SQLException ex) {
            getLogger(RedesSociales.class.getName()).log(SEVERE, null, ex);
        }
        //retorno los datos de la red social para abrir en el navegador predeterminado
        return i;

    }
//getTwitt devuelve la red social twitter 

    public static String getTwitt() {
        String t = "";
        try {
            //conecto bd
            ConexionDB cn = new ConexionDB();
            //obtengo conexion
            Connection cone = cn.getConnection();
            //Elaboro sql
            String sqll = "SELECT redTwitter FROM redsocial WHERE idRedSocial = 1";
            //preparo y lo ejecuto con result set
            PreparedStatement ps = cone.prepareStatement(sqll);
            ResultSet rs = ps.executeQuery();
            //recorro rs para obtener el dato de red social Twitter
            while (rs.next()) {
                t = rs.getString("redTwitter");
            }
        } catch (SQLException ex) {
            getLogger(RedesSociales.class.getName()).log(SEVERE, null, ex);
        }
        //retorno los datos de la red social para abrir en el navegador predeterminado
        return t;
    }

    //Modifico las redes sociles
    public void modRedes(String f, String i, String t) {
        try {

            ConexionDB cc = new ConexionDB();
            try (Statement st = cc.getConnection().createStatement()) {
                String sqlFace = "";
                String sqlInsta = "";
                String sqlTwitt = "";
                //Uso StringBuilder para concatenar los dos string tanto this.face como f
                String fache = (new StringBuilder()).append(this.face).append(f).toString();
                out.println(fache);
                String ins = (new StringBuilder()).append(this.insta).append(i).toString();
                out.println(ins);
                String tw = (new StringBuilder()).append(this.twitter).append(t).toString();
                out.println(tw);
                //Si todas las redes son distintas de su base y distintas de null se modifican todas juntas,
                //con el boton Modificar todas las redes
                String[] vec = new String[3];
                String[] vec2 = new String[3];
                //Creo las verificaciones si entra en el else modifica las redes sociales
                if (f.isEmpty() || f.equals("NombreDeTuPagina")) {
                    vec2[0] = "La red de Facebook no se ha modificado";
                    
                } else {
                    vec[0] = "La red de Facebook se modifico correctamente";
                    sqlFace = "UPDATE redsocial SET redFacebook = '" + fache + "'";
                    st.executeUpdate(sqlFace);
                }   if (i.isEmpty() || i.equals("NombreDeTuPagina")) {
                    vec2[1] = "La red de Instagram no se ha modificado";
                    
                } else {
                    vec[1] = "La red de Instagram se modifico correctamente";
                    sqlInsta = "UPDATE redsocial SET redInstagram = '" + ins + "'";
                    st.executeUpdate(sqlInsta);
                }   if (t.isEmpty() || t.equals("NombreDeTuPagina")) {
                    vec2[2] = "La red de Twitter no se ha modificado";
                    
                } else {
                    vec[2] = "La red de Twitter se modifico correctamente";
                    sqlTwitt = "UPDATE redsocial SET redTwitter = '" + tw + "'";
                    st.executeUpdate(sqlTwitt);
                    
                }   String[] mostrar = new String[3];
                //Recorro con for los arrays, aquellos iguales a null no se mostraran , aquellos que contengan algo se
                //mostrara el mensaje correspondiente
                for (int j = 0; j < 3; j++) {
                    if (vec[j] == null || vec[j] == "") {
                        //Ejecuto codigo de estilo para mostrar con rojo lo no modificado y con verde los modificados
                        mostrar[j] = "<html><div style ='color:#FF3333;font-size: large;'>" + vec2[j] + "</div></html>";
                    } else {
                        mostrar[j] = "<html><div style = 'color:#10D00B;font-size: large;'>" + vec[j] + "</div></html>";
                    }
                }   //Al final de recorrer los arrays solamente se mostrara el mensaje de los arrays no nulls
                showMessageDialog(null, "\n\n" + mostrar[0] + "\n\n" + mostrar[1] + "\n\n" + mostrar[2] + "\n\n", "DATA", INFORMATION_MESSAGE);
            }
            cc.desconectar();
        } catch (SQLException ex) {
            getLogger(RedesSociales.class.getName()).log(SEVERE, null, ex);
        }

    }
}
