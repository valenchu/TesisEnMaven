/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bienvenido;

import coneccion.ConexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class ConsultaInicioSesion {
    //Creo una variable de default mode table

    public static String t;
    public static String Usuario;
    DefaultTableModel ModeloTabla;

//Validacion de inicio de sesion recorro la base de datos para verificar usuarios
    //Este metodo no distingue de mayusculas y minusculas . Pasa todo a minuscula
    public boolean validarsesion(String Us, String Pas) {
        int resultado = 0;
        String User = "";
        String Pass = "";
        String A, B, C, D = "";

        String tipoDeUser;

        try {
            //objeto conecxion
            ConexionDB con = new ConexionDB();

            //Generacion de string sql base de datos
            try (Statement st = con.getConnection().createStatement()) {
                //Generacion de string sql base de datos
                String sql = ("SELECT * FROM usuarios");
                // ejecuto sql con resulset que trae toda la base de datos
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    User = rs.getString("user");
                    Pass = rs.getString("pass");
                    tipoDeUser = rs.getString("IDtipou");
                    //Paso todo a letras chicas para compara resultados
                    A = Us.toLowerCase();
                    B = Pas.toLowerCase();
                    C = User.toLowerCase();
                    D = Pass.toLowerCase();
                    if (C.equals(A) && D.equals(B)) {
                        resultado = 1;
                        t = tipoDeUser;
                        Usuario = C.toUpperCase();

                    }

                }
            }
            con.desconectar();
        } catch (SQLException ex) {
            showMessageDialog(null, "No se encuentra usuario en base de datos");
        }
        //Si el resultado es verdadero inicia sesion si no no !
        if (resultado == 1) {

            return true;

        } else {
            return false;
        }
    }
    //Metodo que guarda el tipo de usuario en la variable t.

    public String guardar() {
        return t;
    }

    // Metedo para obtener usuario
    public static String getUsuario() {
        return Usuario;
    }

}
