/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coneccion;

import static java.lang.Double.parseDouble;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author Valentin
 */
public class ConsultasGenerales {
    //Cuenta registros tabla

    public double contarRegSql(String countSql) {
        //Creo objeto bd
        ConexionDB con = new ConexionDB();
        double numReg = 0;
        try {
            //Conecto bd
            Connection cn = con.getConnection();
            //Prearo el sql
            PreparedStatement st = cn.prepareStatement(countSql);
            //Asigno el sql a resulset para trabajar con el 
            ResultSet rs = st.executeQuery();
            //repaso los registros con next()
            if (rs.next()) {
                //Asigno numero de reg
                numReg = parseDouble(rs.getString("total"));//sql ejemplo SELECT count(*) AS total FROM tabla
            }                                                    //Tengo que poner AS total si o si

            out.println("Numero de registros " + numReg);
        } catch (SQLException ex) {
            getLogger(ConexionDB.class.getName()).log(SEVERE, null, ex);
        }
        //Retorno la cantidad de reg
        return numReg;
    }
}
