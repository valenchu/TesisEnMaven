/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import FNC.Funciones;
import coneccion.ConexionDB;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.JComboBox;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class ConsultaProveedor {

    DefaultTableModel ModeloTabla;
    ConexionDB cc = new ConexionDB();

    //Busca proveedores por filtro
    public void buscarProvee(String fil, String val, JTable b) {

        JComboBox o = new JComboBox();

        try {
            //Cantidad de columnas mas encabezado
            String[] columnas = {"ID", "DNI", "Nombre", "Apellido", "Teléfono", "Email", "Dirección"};
            //Inserto el objeto columnas para modificarlas con setColumnIdentifiers;
            ModeloTabla = (DefaultTableModel) b.getModel();
            ModeloTabla.setColumnIdentifiers(columnas);
            ModificacionJtable mod = new ModificacionJtable();
            mod.limpiarTabla(b);
            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Me conecto a la base de datos
            //Este es el filtro que recibo de busqueda en el select del frame
            if (fil.equals("DNI")) {
                SSQL = "SELECT IDProveedor, Nombre, Apellido, DNI, Tel, Email, dirección "
                        + " FROM proveedor WHERE (DNI LIKE  '" + val + "%')ORDER BY IDProveedor ASC LIMIT 50";
            } else if (fil.equals("Apellido")) {
                SSQL = "SELECT IDProveedor, Nombre, Apellido, DNI, Tel, Email, dirección "
                        + " FROM proveedor WHERE (Apellido LIKE  '" + val + "%')ORDER BY IDProveedor ASC LIMIT 50";
            }

            //Me conecto a la BD
            Connection conect = cc.getConnection();
            //Preparo la secuencia SQL

            PreparedStatement st = conect.prepareStatement(SSQL);

            // Ejecuto la consulta en un ResultSet
            ResultSet rs = st.executeQuery();
            o.removeAllItems();
            //Tomo los datos de la BD los asigno a variables para despues agregarlos como filas
            while (rs.next()) {
                int id = rs.getInt("IDProveedor");
                int dni = rs.getInt("DNI");
                String nomb = rs.getString("Nombre");
                String ape = rs.getString("Apellido");
                long tel = rs.getLong("Tel");
                String email = rs.getString("Email");
                String dire = rs.getString("dirección");
                ModeloTabla.addRow(new Object[]{id, dni, nomb, ape, tel, email, dire});
                //asignamos los datos a la tabla

            }

        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }
        //pasamos la tabla a el metodo
        //pasamos la tabla a el metodo
        b.setModel(ModeloTabla);
        //Doy tamaño a las columna
        b.getColumnModel().getColumn(0).setPreferredWidth(20);
        b.getColumnModel().getColumn(1).setPreferredWidth(40);
        b.getColumnModel().getColumn(2).setPreferredWidth(40);
        b.getColumnModel().getColumn(3).setPreferredWidth(40);
        b.getColumnModel().getColumn(4).setPreferredWidth(40);

    }
    // Agrega proveedor a la BD

    public void agregarProveedor(JTable agregar) {
       Funciones fun = new Funciones();
        try {
            DefaultTableModel df = (DefaultTableModel) agregar.getModel();

            try (Statement st = cc.getConnection().createStatement()) {
                int filas = agregar.getRowCount();
                out.println("Cantidad de filas " + filas);
                int entre = 0;
                int com = 0;
                Integer dni;
                String nomb;
                String ape;
                Long tel;
                String email;
                String dire;
                //Verifico que la fila no sea null . Si es null no entra en el while para ingresar datos
                while (com < filas) {
                    dni = (Integer) agregar.getValueAt(com, 0);
                    out.println(dni);
                    nomb = (String) agregar.getValueAt(com, 1);
                    out.println(nomb);
                    ape = (String) agregar.getValueAt(com, 2);
                    out.println(ape);
                    tel = (Long) agregar.getValueAt(com, 3);
                    out.println(tel);
                    email = (String) agregar.getValueAt(com, 4);
                    out.println(email);
                    dire = (String) agregar.getValueAt(com, 5);
                    //if((desc != null) && (precioC != null) && (precioS != null) && (cant != null) && (offe != null) ){
                  
                    if (dni == null) {
                    } else if (nomb == null) {
                    } else if (ape == null) {       
                    } else if (tel == null) {
                    } else if (email == null) {
                    } else if (dire == null) {
                    }else if((fun.validarCorreo(email)==true) && nomb != null && ape != null && tel != null && dire != null && dni != null){                   
                    
                        String sql = "";
                        sql = "INSERT INTO proveedor VALUES (NULL, '" + nomb + "', '" + ape + "','" + dni + "','" + tel + "','" + email + "','" + dire + "')";
                        st.executeUpdate(sql);
                        out.println("Fila " + nomb + " " + ape + " Registrada correctamente");
                        entre = 1;
                    }
                    com++;                     
                }
               
                //Verifica que una fila aunque sea este escrita . Si no lo esta salta el cartel de que debe llenar
                // datos para guardar productos.
                if (entre == 0) {
                    showMessageDialog(null, "Debe llenar una fila completa, para que se registren los datos", "Error", ERROR_MESSAGE);
                } else {
                    showMessageDialog(null, "Se agrego el proveedor o los proveedores correctamente", "Información", INFORMATION_MESSAGE);
                    //Si una de las filas aunque sea fue llenada . al apretar el boton agregar producto
                    //Borra las filas del Jtable y lo recetea a cero
                    for (int m = 0; m < df.getRowCount(); m++) {
                        df.removeRow(m);
                        m -= 1;
                    }   //Despues de remover las filas agrega automaticamente 4 de nuevo.Vacias 
                    int evolucionar = 0;
                    while (evolucionar < 4) {
                        df.addRow(new Object[]{null, null, null, null, null, null});
                        evolucionar++;
                        agregar.setModel(df);
                    }
                }
            }
            cc.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }
    }
    //   Busca proveedores apenas entra en la pestaña

    public void buscarProveeInstantaneamente(JTable b) {

        JComboBox o = new JComboBox();
        //Cantidad de columnas mas encabezado
        String[] columnas = {"ID", "DNI", "Nombre", "Apellido", "Teléfono", "Email", "Dirección"};
        //Inserto el objeto columnas para modificarlas con setColumnIdentifiers;
        DefaultTableModel model = (DefaultTableModel) b.getModel();
        model.setColumnIdentifiers(columnas);
        try {

            ModiTblProveedor mod = new ModiTblProveedor();
            mod.limpiarTabla(b);
            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Me conecto a la base de datos
            out.println("SQL String  pase");
            //Este es el filtro que recibo de busqueda en el select del frame

            SSQL = "SELECT *"
                    + " FROM proveedor ORDER BY IDProveedor ASC LIMIT 50";

            //Me conecto a la BD
            Connection conect = cc.getConnection();
            out.println("Pase select");
            //Preparo la secuencia SQL

            PreparedStatement st = conect.prepareStatement(SSQL);

            // Ejecuto la consulta en un ResultSet
            ResultSet rs = st.executeQuery();
            out.println("Pase resulset");
            o.removeAllItems();
            //Tomo los datos de la BD los asigno a variables para despues agregarlos como filas
            while (rs.next()) {
                int id = rs.getInt("IDProveedor");
                int dni = rs.getInt("DNI");
                String nomb = rs.getString("Nombre");
                String ape = rs.getString("Apellido");
                long tel = rs.getLong("Tel");
                String email = rs.getString("Email");
                String dire = rs.getString("dirección");
                model.addRow(new Object[]{id, dni, nomb, ape, tel, email, dire});
                //asignamos los datos a la tabla

            }

            out.println("Pase netx");
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }
        //pasamos la tabla a el metodo
        //pasamos la tabla a el metodo
        b.setModel(model);
        //Doy tamaño a las columna
        b.getColumnModel().getColumn(0).setPreferredWidth(10);
        b.getColumnModel().getColumn(1).setPreferredWidth(25);
        b.getColumnModel().getColumn(2).setPreferredWidth(30);
        b.getColumnModel().getColumn(3).setPreferredWidth(30);
        b.getColumnModel().getColumn(4).setPreferredWidth(30);
    }

    //Reestablece de cero la tabla proveedor
    public void borrarRegistroProvee() throws SQLException {
        try (Statement st = cc.getConnection().createStatement()) {
            String sql;
            sql = "TRUNCATE TABLE proveedor";
        }
        cc.desconectar();
        showMessageDialog(null, "Los registros de proveedores han sido borrados"
                + "completamente", "Información", INFORMATION_MESSAGE);

    }
//Elimina proveedores

    public void eliminarProvee(JTable bl1, int id) {
        ModeloTabla = (DefaultTableModel) bl1.getModel();
        try {
            try (Statement st = cc.getConnection().createStatement()) {
                String sql = "";
                out.println("Lllegue ejecutare el sql");
                sql = "DELETE FROM proveedor WHERE IDProveedor = '" + id + "' ";
                //Codigo para refrescar la tabbla//
                bl1.setVisible(false);
                bl1.setModel(ModeloTabla);
                ModeloTabla.fireTableDataChanged();
                bl1.setVisible(true);
                /////////////////////////
                st.executeUpdate(sql);
            }
            cc.desconectar();
            showMessageDialog(null, "Registro eliminado con exito!", "Informacion", INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }

    }
//Modifico proveedores

    public void modProvee(int id, int dni, String nom, String ape, long tel, String email, String dire) {

        try {
            try (Statement st = cc.getConnection().createStatement()) {
                String sql = "";
                sql = "UPDATE proveedor SET Nombre = '" + nom + "', Apellido = '" + ape + "', DNI = '" + dni + "'"
                        + ", Tel = '" + tel + "', Email = '" + email + "',direccion = '" + dire + "' WHERE IDProveedor = '" + id + "'";
                st.executeUpdate(sql);
            }
            cc.desconectar();

        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }

    }

}
