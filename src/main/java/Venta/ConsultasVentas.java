/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venta;

import static Bienvenido.Inicio_Sesion.U;
import Stock.ConsultasStock;
import Stock.ModificacionJtable;
import coneccion.ConexionDB;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Math.round;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Valentin
 */
public class ConsultasVentas {

    DefaultTableModel ModeloTabla;
    ConexionDB cc = new ConexionDB();
    ModificacionJtable mod = new ModificacionJtable();

    //Busco productos para poder insertar despues en tabla ventas
    public void buscarProductos(String filtro, String dato, JTable tbl, int[] limitador) {
        Integer idProd;
        String desc;
        Double preSIVA;
        Double preCIVA;
        Integer cant;
        Integer ofe;
        ConexionDB con = new ConexionDB();
        try {
            //Agrego el modlde al modelo de la tabla
            ModeloTabla = (DefaultTableModel) tbl.getModel();
            String sql = null;
            mod.limpiarTabla(tbl);
            //Hago el select para traer por filtro descripción y id la consulta
            //Si utilizo switch puedo comparar si filtro es iguala null si no los traio por descripcion o id
            if (null == filtro) {//Si el filtro es nulo estraigo toda la bd hasta 30 lineas
                sql = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                        + " FROM productos WHERE altabajaproductos = 1 ORDER BY IDProducto ASC "
                        + " LIMIT " + limitador[0] + "," + limitador[1] + "";

            } else {
                switch (filtro) {
                    case "ID":
                        //Filtro por id la busqueda
                        sql = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                                + " FROM productos WHERE (IDProducto LIKE  '" + dato + "%') AND (altabajaproductos = 1)ORDER BY IDProducto ASC "
                                + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                        break;
                    case "Descripción":
                        //Filtro por descripcion la busqueda

                        sql = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                                + " FROM productos WHERE (Descripcio LIKE  '" + dato + "%') AND (altabajaproductos = 1)ORDER BY IDProducto ASC "
                                + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                        break;
                    default:
                        break;
                }
            }
            //Me conecto BD
            Connection conect = con.getConnection();
            //Preparo el resulset para recorrer BD
            try ( //Preparo la sentencia sql;
                    PreparedStatement st = conect.prepareStatement(sql)) {
                //Preparo el resulset para recorrer BD
                ResultSet rs = st.executeQuery();
                //Genero while para recorrer la BD con el Resulset
                while (rs.next()) {
                    idProd = rs.getInt("IDProducto");
                    desc = rs.getString("Descripcio");
                    preSIVA = rs.getDouble("PrecioSinIVA");
                    preCIVA = rs.getDouble("PrecioConIVA");
                    cant = rs.getInt("Cantidad");
                    ofe = rs.getInt("Oferta");
                    ModeloTabla.addRow(new Object[]{idProd, desc, cant, preSIVA, preCIVA, ofe});
                }
            }
            con.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
        }
        tbl.setModel(ModeloTabla);

    }
//Busqueda por fecha movimiento venta

    public void movimientoFecha(JTable tblMovimiento, Date fechDesde, Date fechHasta, int[] limitador) {
        ConexionDB con = new ConexionDB();
        String fechalHasta = null;
        String fechalDesde = null;
        if (fechDesde == null && fechHasta == null) {
            showMessageDialog(null, "Ingrese una fecha o usuario a buscar", "ERROR", ERROR_MESSAGE);

        } else {
            SimpleDateFormat formatear2 = new SimpleDateFormat("yyyy-MM-dd");
            fechalDesde = formatear2.format(fechDesde);
            fechalHasta = formatear2.format(fechHasta);

            Integer idVenta;
            Integer idProd;
            String u;
            String desc;
            Double preSIVA;
            Integer cant;
            Integer ofe;
            String fecha;
            Double importe;
            try {
                //Agrego el modlde al modelo de la tabla
                ModeloTabla = (DefaultTableModel) tblMovimiento.getModel();
                String sql = "";
                mod.limpiarTabla(tblMovimiento);
                //Hago el select para traer por filtro descripción y id la consulta        
                sql = "SELECT  venta.idVenta, venta.Usuario,detallevta.IDproducto, "
                        + "detallevta.Descripcion, detallevta.Fecha, detallevta.Cant, "
                        + "detallevta.PrecioSIVA, detallevta.oferta,detallevta.Importe "
                        + "FROM venta INNER JOIN  detallevta ON venta.idVenta = detallevta.idVenta"
                        + " WHERE (detallevta.Fecha BETWEEN '" + fechalDesde + "' AND '" + fechalHasta + "') "
                        + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                //Me conecto BD
                Connection conect = con.getConnection();
                //Preparo el resulset para recorrer BD
                try ( //Preparo la sentencia sql;
                        PreparedStatement st = conect.prepareStatement(sql)) {
                    //Preparo el resulset para recorrer BD
                    ResultSet rs = st.executeQuery();
                    //Genero while para recorrer la BD con el Resulset
                    while (rs.next()) {
                        idProd = rs.getInt("detallevta.IDproducto");
                        idVenta = rs.getInt("venta.idVenta");
                        u = rs.getString("venta.Usuario");
                        SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");
                        fecha = formatear.format(rs.getDate("detallevta.Fecha"));
                        importe = rs.getDouble("detallevta.Importe");
                        desc = rs.getString("detallevta.Descripcion");
                        preSIVA = rs.getDouble("detallevta.PrecioSIVA");
                        cant = rs.getInt("detallevta.Cant");
                        ofe = rs.getInt("detallevta.oferta");
                        ModeloTabla.addRow(new Object[]{idVenta, u, idProd, desc, fecha, cant, preSIVA, ofe, importe});
                    }
                }
                con.desconectar();
            } catch (SQLException ex) {
                getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
            }
            tblMovimiento.setModel(ModeloTabla);
        }
    }

    //Carga automaticamente la tabla de movimiento apenas entras
    public void cargaAutomatica(JTable tbl, int[] limitador) {//La variable limitador es un array donde
        //limitador[0] es la base donde toma los registro
        //limitador[1] es la cantidad de registros a devolver
        //obtengo fecha     
        ConexionDB con = new ConexionDB();
        Date date = new Date();

        //formato que le dare a fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechal = dateFormat.format(date);
        Integer idVenta;
        Integer idProd;
        String u;
        String desc;
        Double preSIVA;
        Integer cant;
        Integer ofe;
        String fecha;
        Double importe;

        try {
            //Agrego el modlde al modelo de la tabla
            ModeloTabla = (DefaultTableModel) tbl.getModel();
            // TableRowSorter Sirve para odenar las tablas por su header
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(ModeloTabla);
            tbl.setRowSorter(elQueOrdena);
            String sql = "";

            mod.limpiarTabla(tbl);
            //Hago el select para traer por filtro descripción y id la consulta

            sql = "SELECT  venta.idVenta, venta.Usuario,detallevta.IDproducto, "
                    + "detallevta.Descripcion, detallevta.Fecha, detallevta.Cant, "
                    + "detallevta.PrecioSIVA, detallevta.oferta,detallevta.Importe "
                    + "FROM venta INNER JOIN  detallevta ON venta.idVenta = detallevta.idVenta "
                    + " WHERE (detallevta.Fecha BETWEEN 1950-01-01 AND '" + fechal + "') ORDER BY detallevta.Fecha DESC "
                    + "LIMIT " + limitador[0] + "," + limitador[1] + " ";
            //Me conecto BD
            Connection conect = con.getConnection();
            //Preparo el resulset para recorrer BD
            try ( //Preparo la sentencia sql;
                    PreparedStatement st = conect.prepareStatement(sql)) {
                //Preparo el resulset para recorrer BD
                ResultSet rs = st.executeQuery();

                //Genero while para recorrer la BD con el Resulset
                while (rs.next()) {
                    idProd = rs.getInt("detallevta.IDproducto");
                    idVenta = rs.getInt("venta.idVenta");
                    u = rs.getString("venta.Usuario");
                    SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");
                    fecha = formatear.format(rs.getDate("detallevta.Fecha"));
                    importe = rs.getDouble("detallevta.Importe");
                    desc = rs.getString("detallevta.Descripcion");
                    preSIVA = rs.getDouble("detallevta.PrecioSIVA");
                    cant = rs.getInt("detallevta.Cant");
                    ofe = rs.getInt("detallevta.oferta");
                    //Cargo los datos al modelo de tabla
                    ModeloTabla.addRow(new Object[]{idVenta, u, idProd, desc, fecha, cant, preSIVA, ofe, importe});
                }
            }
            con.desconectar();

        } catch (SQLException ex) {
            getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
        }

        tbl.setModel(ModeloTabla);

    }
//Busqueda por user en la tabla movimiento venta

    public void movimientoUsuario(int[] limitador, JTable tblMovimiento, String user) {
        ConexionDB con = new ConexionDB();
        Integer idVenta;
        Integer idProd;
        String u;
        String desc;
        Double preSIVA;
        Integer cant;
        Integer ofe;
        String fecha;
        Double importe;
        //Agrego el modlde al modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tblMovimiento.getModel();
        try {
            //Agrego el modlde al modelo de la tabla
            String sql = "";
            mod.limpiarTabla(tblMovimiento);
            //Hago el select para traer por filtro descripción y id la consulta
            if (user != null && user != "") {
                System.out.println("El user es = " + user);
                sql = "SELECT DISTINCT venta.idVenta, venta.Usuario,detallevta.IDproducto, "
                        + "detallevta.Descripcion, detallevta.Fecha, detallevta.Cant, "
                        + "detallevta.PrecioSIVA, detallevta.oferta,detallevta.Importe "
                        + "FROM venta INNER JOIN  detallevta ON venta.idVenta = detallevta.idVenta "
                        + "WHERE (venta.Usuario LIKE '" + user + "%')"
                        + " LIMIT " + limitador[0] + "," + limitador[1] + "";
            } else {
                showMessageDialog(null, "Ingrese usuario a buscar", "ERROR", ERROR_MESSAGE);

            }
            //Me conecto BD
            Connection conect = con.getConnection();
            //Preparo el resulset para recorrer BD
            try ( //Preparo la sentencia sql;
                    PreparedStatement st = conect.prepareStatement(sql)) {
                //Preparo el resulset para recorrer BD
                ResultSet rs = st.executeQuery();
                //Genero while para recorrer la BD con el Resulset
                while (rs.next()) {
                    idProd = rs.getInt("detallevta.IDproducto");
                    idVenta = rs.getInt("venta.idVenta");
                    u = rs.getString("venta.Usuario");
                    SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");

                    fecha = formatear.format(rs.getDate("detallevta.Fecha"));

                    importe = rs.getDouble("detallevta.Importe");
                    desc = rs.getString("detallevta.Descripcion");
                    preSIVA = rs.getDouble("detallevta.PrecioSIVA");
                    cant = rs.getInt("detallevta.Cant");
                    ofe = rs.getInt("detallevta.oferta");

                    model.addRow(new Object[]{idVenta, u, idProd, desc, fecha, cant, preSIVA, ofe, importe});
                }
            }
            con.desconectar();

        } catch (SQLException ex) {
            getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
        }
        tblMovimiento.setModel(model);

    }

    //Buscar productos automaticamente 
    public void rellenarStock(JTable tblauto, int[] limitador) {
        Integer idProd;
        String desc;
        Double preSIVA;
        Double preCIVA;
        Integer cant;
        Integer ofe;
        DefaultTableModel model = (DefaultTableModel) tblauto.getModel();
        ConexionDB con = new ConexionDB();

        try {

            String sql = null;
            mod.limpiarTabla(tblauto);

            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Sql busqueda 
            SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                    + " FROM productos WHERE altabajaproductos = 1 ORDER BY IDProducto ASC LIMIT "
                    + "" + limitador[0] + "," + limitador[1] + "";

            //Me conecto a la BD
            Connection conect = con.getConnection();
            //Preparo el resulset para recorrer BD
            try ( //Preparo la sentencia sql;
                    PreparedStatement st = conect.prepareStatement(SSQL)) {
                //Preparo el resulset para recorrer BD
                ResultSet rs = st.executeQuery();
                //Genero while para recorrer la BD con el Resulset
                while (rs.next()) {
                    idProd = rs.getInt("IDProducto");
                    desc = rs.getString("Descripcio");
                    preSIVA = rs.getDouble("PrecioSinIVA");
                    preCIVA = rs.getDouble("PrecioConIVA");
                    cant = rs.getInt("Cantidad");
                    ofe = rs.getInt("Oferta");
                    model.addRow(new Object[]{idProd, desc, cant, preSIVA, preCIVA, ofe});
                }
            }
            con.desconectar();

        } catch (SQLException ex) {
            getLogger(ConsultasStock.class
                    .getName()).log(SEVERE, null, ex);
        }
        //pasamos la tabla a el metodo
        //pasamos la tabla a el metodo
        tblauto.setModel(model);

    }

    //modifico el stock con el jtable de venta. le resto la cantidad vendida
    public void movimientoStock(JTable vtta) {
        ConexionDB con = new ConexionDB();
        System.out.println("llegue hasta comienzo movimiento");
        if (vtta == null) {
            System.out.println("no contiene nada el jtable");
        } else {
            System.out.println("entre en el else");
            try {
                //obtengo la tabla con getmodel
                DefaultTableModel md = (DefaultTableModel) vtta.getModel();
                //Cuento las filas de la tabla para generar un for
                int cantFilas = md.getRowCount();
                Integer idProd = 0;
                Integer cant = 0;
                //Conecto BD
                String sqll = "";
                //ejecuto sql para recorrer BD
                Statement st;
                st = con.getConnection().createStatement();
                //ejecuto sql para recorrer BD
                //regorro las filas para extraer los datos y actualizar bd
                for (int i = 0; i < cantFilas; i++) {
                    idProd = parseInt(md.getValueAt(i, 0).toString());
                    cant = parseInt(md.getValueAt(i, 2).toString());
                    //Preparo el sqll
                    sqll = "UPDATE productos SET Cantidad = Cantidad - '" + cant + "' WHERE IDproducto = '" + idProd + "'";
                    st.executeUpdate(sqll);
                }
                //Cierro st y conec BD
                st.close();
                con.desconectar();
            } catch (SQLException ex) {
                getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
            }
        }

    }

//Envio el id ultimo de venta
    public void idVentas(JTextField idventa) {
        ConexionDB con = new ConexionDB();
        try {
            Integer vta = null;
            String sql;
            ResultSet resultado;
            //SQL que selecciona el ultimo id del registro y le agrega 1
            sql = "SELECT MAX(idVenta)+1 FROM venta";

            Connection conec = con.getConnection();
            try (PreparedStatement st = conec.prepareStatement(sql)) {
                resultado = st.executeQuery();
                if (resultado.next()) {
                    vta = resultado.getInt(1);
                }
                idventa.setText(Integer.toString(vta));
            }
            con.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
        }
    }
//Agrego venta a detalleVenta en bd

    public void agregarVenta(JTable tbl, JTextField fechaDia, JTextField fechaMes, JTextField fechaAnno,
            JTextField total, JTextField idVenta) {
        ConexionDB con = new ConexionDB();
        try {
            Statement st = con.getConnection().createStatement();
            String sql = "";
            String sql2 = "";
            Integer idPro = 0;
            String desc = "";
            Integer cant = 0;
            Double preSIVA = 0.0;
            Integer ofer = 0;
            Double importe = 0.0;
            Integer dia = 0;
            Integer mes = 0;
            Integer ano = 0;
            Double totall = 0.0;
            Integer idVta = 0;
            String userVenta = "";
            DefaultTableModel mo = (DefaultTableModel) tbl.getModel();
            int cantFilas = mo.getRowCount();
            dia = parseInt(fechaDia.getText()) + 1;
            mes = parseInt(fechaMes.getText());
            ano = parseInt(fechaAnno.getText());
            totall = parseDouble(total.getText());
            totall = (double) round(totall * 100) / 100;
            idVta = parseInt(idVenta.getText());
            userVenta = U;
            if (userVenta.isEmpty()) {
                userVenta = "Admin";
            }

            if (cantFilas > 0) {
                System.out.println("Usuario que registro la venta = " + userVenta);
                sql2 = "INSERT INTO venta VALUES ('" + idVta + "', '" + userVenta + "') ";
                System.out.println(idVta + " " + userVenta);
                st.executeUpdate(sql2);
                for (int i = 0; i < cantFilas; i++) {
                    idPro = (Integer) mo.getValueAt(i, 0);
                    desc = (String) mo.getValueAt(i, 1);
                    cant = (Integer) mo.getValueAt(i, 2);
                    preSIVA = (Double) mo.getValueAt(i, 3);
                    ofer = (Integer) mo.getValueAt(i, 4);
                    importe = (Double) mo.getValueAt(i, 5);
                    System.out.println(idPro + " " + desc + " " + cant
                            + " " + preSIVA + " " + ofer + " " + importe + " " + ano + " " + mes + " " + dia + " " + totall);
                    sql = "INSERT INTO detallevta VALUES(null,'" + idVta + "', '" + idPro + "',"
                            + " '" + desc + "', '" + cant + "', '" + preSIVA + "', '" + ofer + "','" + importe + "',"
                            + "'" + totall + "','" + ano + "-" + mes + "-" + dia + "' ) ";
                    st.executeUpdate(sql);
                }
                st.close();
                con.desconectar();

            } else {
                showMessageDialog(null, "La tabla no tiene filas a pasar", "Error", ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
        }

    }

    //Genera un truncate que elimina todos los movimientos de DetalleVTa y de Venta
    public void resetearTablaMov() {
        try {
            //Me conecto BD
            ConexionDB con = new ConexionDB();
            //Creo sqll
            String SQLL = "TRUNCATE TABLE detallevta", SQLL2 = "TRUNCATE TABLE venta";
            //Creo statement
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(SQLL);
            st.executeUpdate(SQLL2);
            showMessageDialog(null, "Se reestablecieron la tabla Venta y Detalle venta de 0", "INFORMACIÓN", INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            getLogger(ConsultasVentas.class.getName()).log(SEVERE, null, ex);
        }

    }
}
