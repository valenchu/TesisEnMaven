/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import coneccion.ConexionDB;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultaControl {

    DefaultTableModel ModeloTabla;
    ConexionDB cc = new ConexionDB();

    //Busqueda de Control para mostrar usuarios en tabla
    public void buscar1(String filtro, String valor, JTable a) {
        try {
            //Cantidad de columnas mas encabezado
            String[] columnas = new String[]{"ID", "Usuario", "Contraseña", "Edad", "Sexo", "Tipo de usuario"};
            //Cargo otro array para la cantidad de registros en columna
            String[] Reg = new String[7];
            out.println("Pase reg");
            //Creo objeto tabla donde le paso cantidad de columnas
            ModeloTabla = new DefaultTableModel(null, columnas) {
                public boolean isCellEditable(int row, int column) {

                    return false;
                }

            };

            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Me conecto a la base de datos

            out.println("SQL String  pase");
            //Este es el filtro que recibo de busqueda en el select del frame
            switch (filtro) {
                case "User":
                    SSQL
                            = "SELECT IDU,user, pass, Edad, Sexo, tipodeuser.privilegio FROM usuarios,tipodeuser WHERE (User LIKE  '" + valor + "%')"
                            + "AND(tipodeuser.IDtipou=usuarios.IDtipou) LIMIT 5 ";
                    break;
                case "todo":
                    SSQL = "SELECT * FROM usuarios LIMIT 5";
                    break;
                case "Sexo":
                    SSQL = "SELECT user, pass, Edad, Sexo, tipodeuser.privilegio FROM usuarios,tipodeuser WHERE Sexo LIKE '" + valor + "%' LIMIT 5 ";
                    break;
                default:
                    break;
            }

            //Me conecto a la BD
            Connection conect = cc.getConnection();
            out.println("Pase select");
            //Preparo la secuencia SQL

            PreparedStatement st = conect.prepareStatement(SSQL);

            // Ejecuto la consulta en un ResultSet
            ResultSet rs = st.executeQuery();
            out.println("Pase resulset");

            //pasamos nombre de las tablas base de datos al reg
            while (rs.next()) {
                // for(int i = 0; i < 6; i++){
                Reg[0] = rs.getString("IDU");
                Reg[1] = rs.getString("user");
                Reg[2] = rs.getString("pass");
                Reg[3] = rs.getString("Edad");
                Reg[4] = rs.getString("Sexo");
                Reg[5] = rs.getString("tipodeuser.privilegio");
                ModeloTabla.addRow(Reg);

                //asignamos datos a la tabla
            }

            out.println("Pase netx");
        } catch (SQLException ex) {
            getLogger(ConsultaControl.class.getName()).log(SEVERE, null, ex);
        }

        //pasamos la tabla a el metodo
        a.setModel(ModeloTabla);

        a.getTableHeader().setReorderingAllowed(false);

    }

    public boolean verificarUserBD(String user) {
        String usuario = "";
        boolean es = false;
        try {
            ConexionDB cc = new ConexionDB();
            Connection con = cc.getConnection();
            String sqlñ = "SELECT user FROM usuarios WHERE user = '" + user + "'";
            PreparedStatement ps = con.prepareStatement(sqlñ);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuario = rs.getString("user");
            }
            if (!"".equals(usuario)) {
                es = true;
            } else {
                es = false;
            }
        } catch (SQLException ex) {
            getLogger(ConsultaControl.class.getName()).log(SEVERE, null, ex);
        }

        return es;
    }
    //Insercion de usuarios nuevos creados en la parte de Control

    public void InsertarDatos(String user, String pass, int edad, String sex, int TipU) {

        try {
            ConexionDB cc = new ConexionDB();

            try (Statement st = cc.getConnection().createStatement()) {
                String ssql = "INSERT INTO usuarios  VALUES (NULL, '" + user + "', '" + pass + "', " + edad + ", '" + sex + "', " + TipU + ")";
                st.executeUpdate(ssql);
                showMessageDialog(null, "Se ha registrado Exitosamente", "Información", INFORMATION_MESSAGE);
            }
            cc.desconectar();
        } catch (SQLException ex) {
            showMessageDialog(null, "No se registro", "Error", ERROR_MESSAGE);
        }

    }

    //Modifica los datos creados en la parte de Control
    public void modificardatos(String usser, String passw, String Edad, String Sexo, String id) {
        int confirmar = showConfirmDialog(null, "Esta seguro que desea modificar campos");
        if (confirmar == YES_OPTION) {

            try {
                // Generaci�n String SQL para la base de datos
                try (Statement estatuto = cc.getConnection().createStatement()) {
                    // Generaci�n String SQL para la base de datos
                    String sql = "UPDATE usuarios SET user= '" + usser + "' , pass= '" + passw + "',Edad= " + Edad + ",Sexo= '" + Sexo + "'WHERE IDU = " + id + "";
                    // Muestra la SQL por consola
                    out.println(sql);
                    // Ejecuta la SQL
                    estatuto.executeUpdate(sql);
                    // Muestra en un di�logo como fue la registracion
                    showMessageDialog(null, "Se ha MODIFICADO Exitosamente", "Información", INFORMATION_MESSAGE);
                }
                cc.desconectar();

            } catch (SQLException e) {
                out.println(e.getMessage());
                showMessageDialog(null, "No se Registro la persona");
            }

        } else {
            showMessageDialog(null, "Gracias por entrar");
        }

    }
    // Borro toda la tabla usuarios de control y reestablezco de cero con un nuevo nombre de usuario y contraseña.

    public void reestablecer_tabla() {
        try {
            try ( //Conecto base de datos
                    Connection con = cc.getConnection(); Statement st = cc.getConnection().createStatement()) {
                String nuevoUsuario = "admin";
                int nuevaContraseña = 1234;
                int edad = 1;
                String sesso = "Masculino";
                int tiu = 1;
                String sql = "";
                String sqla = "";
                String sqlb = "";
                out.println("Antes de la consulta");
                sqlb = "TRUNCATE TABLE usuarios";
                sql = "ALTER TABLE usuarios AUTO_INCREMENT=1";
                sqla = "INSERT INTO usuarios VALUES (NULL, '" + nuevoUsuario + "','" + nuevaContraseña + "','" + edad + "','" + sesso + "','" + tiu + "')";
                out.println("Antes del query ");
                st.executeUpdate(sqlb);
                st.executeUpdate(sql);
                st.executeUpdate(sqla);
                showMessageDialog(null, "Registros borrados satisfactoriamente");
            }
            cc.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultaControl.class.getName()).log(SEVERE, null, ex);
        }
    }
    //Elimina Usuarios de la parte control

    public void Eliminar(int idelim) {
        int confirm = showConfirmDialog(null, "Esta seguro de querer eliminar este "
                + "registro por completo ? ", "Error", YES_NO_CANCEL_OPTION);
        if (confirm == YES_OPTION) {
            try {
                //Conecta base de datos
                Connection con = cc.getConnection();
                // Ejecuta sintaxys sql para eliminar registro de la base de datos que
                // Le pasamos por el parametro idelim
                String sqll = ("DELETE FROM usuarios WHERE IDU= '" + idelim + "'");
                //Prepara la sentencia
                Statement st = con.prepareStatement(sqll);
                // La imprime por pantalla para verificar si hasta aca a llegado.
                out.println(sqll);
                //Ejecuta la sentencia sqll enviada a la base de datos
                int x = st.executeUpdate(sqll);
                //Manamos mensaje fila eliminada con exito
                showMessageDialog(null, "Se han eliminado con exito: " + x + " filas de la tabla usuarios", "Bien", INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                getLogger(ConsultaControl.class.getName()).log(SEVERE, null, ex);
            }

        }
    }
}
