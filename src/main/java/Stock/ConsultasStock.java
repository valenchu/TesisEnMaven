/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import coneccion.ConexionDB;
import java.awt.Cursor;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import static java.util.logging.Level.SEVERE;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ConsultasStock {
    
    DefaultTableModel ModeloTabla;

    //Busqueda de stock para llenar tabla
    public void buscarStock(String fil, String val, JTable b, int[] limitador) {
        
        int id = -1;
        DefaultTableModel model = (DefaultTableModel) b.getModel();
        try {
            //Cantidad de columnas mas encabezado
            String[] columnas = new String[]{"IDProducto", "Descripción", "Cantidad", "PrecioS/IVA", "PrecioC/IVA", "Oferta", "Alta/Baja"};
            //Inserto el objeto columnas para modificarlas con setColumnIdentifiers;
            model = (DefaultTableModel) b.getModel();
            model.setColumnIdentifiers(columnas);
            //Ordena la tabla por los header
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
            b.setRowSorter(elQueOrdena);
            ModificacionJtable mod = new ModificacionJtable();
            mod.limpiarTabla(b);
            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Me conecto a la base de datos
            //Este es el filtro que recibo de busqueda en el select del frame

            switch (fil) {
                case "Descripción":
                    SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta, altabajaproductos "
                            + " FROM productos WHERE (Descripcio LIKE  '" + val + "%')ORDER BY IDProducto ASC"
                            + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                    out.println("entre en descripcion");
                    break;
                case "IDProducto":
                    SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta, altabajaproductos "
                            + " FROM productos WHERE (IDProducto LIKE  '" + val + "%')ORDER BY IDProducto ASC"
                            + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                    out.println("entre en idproducto");
                    break;
                case "PrecioSIVA":
                    SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta, altabajaproductos "
                            + " FROM productos WHERE (PrecioSinIVA LIKE  '" + val + "%')ORDER BY IDProducto ASC"
                            + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                    break;
                case "PrecioCIVA":
                    SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta, altabajaproductos "
                            + " FROM productos WHERE (PrecioConIVA LIKE  '" + val + "%')ORDER BY IDProducto ASC"
                            + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                    break;
                case "":
                    SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta, altabajaproductos "
                            + " FROM productos ORDER BY IDProducto ASC"
                            + " LIMIT " + limitador[0] + "," + limitador[1] + "";
                    break;
                default:
                    JOptionPane.showConfirmDialog(null, "Filtro erroneo", "ERROR", JOptionPane.ERROR);
                    break;
            }
            ConexionDB cc = new ConexionDB();
            //Me conecto a la BD
            Connection conect = cc.getConnection();
            //Preparo la secuencia SQL      
            PreparedStatement st = conect.prepareStatement(SSQL);
            try ( // Ejecuto la consulta en un ResultSet
                    ResultSet rs = st.executeQuery()) {

                //isBeforeFirst devuelve true si el cursor esta antes de la primera fila.
                //Tomo los datos de la BD los asigno a variables para despues agregarlos como filas
                while (rs.next()) {
                    
                    String c = "Alta";
                    int a = rs.getInt("altabajaproductos");
                    id = rs.getInt("IDProducto");
                    String desc = rs.getString("Descripcio");
                    int cant = rs.getInt("Cantidad");
                    Double preS = rs.getDouble("PrecioSinIVA");
                    Double preC = rs.getDouble("PrecioConIVA");
                    int offe = rs.getInt("Oferta");
                    if (a == 0) {
                        c = "Baja";
                    }
                    
                    model.addRow(new Object[]{id, desc, cant, preS, preC, offe, c});
                    //asignamos los datos a la tabla
                }
                if (model.getRowCount() == 0) {
                    System.out.println("No hay registros");
                }
            }
            //isBeforeFirst devuelve true si el cursor esta antes de la primera fila.
            cc.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }
        //pasamos la tabla a el metodo
        //pasamos la tabla a el metodo
        b.setModel(model);
        //Doy tamaño a las columnas
        b.getColumnModel().getColumn(0).setPreferredWidth(50);
        b.getColumnModel().getColumn(1).setPreferredWidth(150);
        b.getColumnModel().getColumn(2).setPreferredWidth(100);
        b.getColumnModel().getColumn(3).setPreferredWidth(100);
        b.getColumnModel().getColumn(4).setPreferredWidth(100);
        b.getColumnModel().getColumn(5).setPreferredWidth(100);
        b.getColumnModel().getColumn(6).setPreferredWidth(50);
        b.getTableHeader().setReorderingAllowed(false);
        
    }
    //Metodo para buscar por cantidad precioiva sin iva en tabla stock

    public void buscarPorCantidadPrecio(String filtro, int valA, int valB, JTable tabla, int[] limitador) {
        int id = -1;
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        try {

            //Cantidad de columnas mas encabezado
            String[] columnas = new String[]{"IDProducto", "Descripción", "Cantidad", "PrecioS/IVA", "PrecioC/IVA", "Oferta", "Alta/Baja"};
            //Inserto el objeto columnas para modificarlas con setColumnIdentifiers;

            model.setColumnIdentifiers(columnas);
            //Ordena la tabla por los header
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
            tabla.setRowSorter(elQueOrdena);
            ModificacionJtable mod = new ModificacionJtable();
            mod.limpiarTabla(tabla);
            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Me conecto a la base de datos
            //Este es el filtro que recibo de busqueda en el select del frame
            //Verifico por caracteres que los numeros ingresados sean correctos

            switch (filtro) {
                case "Cantidad":
                    SSQL = "SELECT  * FROM productos WHERE (Cantidad BETWEEN '" + valA + "' AND '" + valB + "') ORDER BY Cantidad ASC  LIMIT " + limitador[0] + "," + limitador[1] + " ";
                    
                    break;
                case "PrecioSIVA":
                    SSQL = "SELECT  *"
                            + "FROM productos WHERE (PrecioSinIVA BETWEEN '" + valA + "' AND '" + valB + "') ORDER BY PrecioSinIVA ASC LIMIT " + limitador[0] + "," + limitador[1] + "  ";
                    
                    break;
                case "PrecioCIVA":
                    SSQL = "SELECT  *"
                            + "FROM productos WHERE (PrecioConIVA BETWEEN '" + valA + "' AND '" + valB + "') ORDER BY PrecioConIVA ASC LIMIT " + limitador[0] + "," + limitador[1] + "  ";
                    
                    break;
                default:
                    JOptionPane.showConfirmDialog(null, "Filtro erroneo", "ERROR", JOptionPane.ERROR);
                    
                    break;
            }
            System.out.println("SQL= " + SSQL);
            //Conexion a la BD y busqueda por RS result set
            ConexionDB con = new ConexionDB();
            //conexion establecida
            Connection conect = con.getConnection();
            //Preparo la sentencia SQL
            PreparedStatement ps = conect.prepareStatement(SSQL);
            
            ResultSet rs = ps.executeQuery();
            //Tomo los datos de la BD los asigno a variables para despues agregarlos como filas
            while (rs.next()) {
                
                String c = "Alta";
                int a = rs.getInt("altabajaproductos");
                id = rs.getInt("IDProducto");
                String desc = rs.getString("Descripcio");
                int cant = rs.getInt("Cantidad");
                Double preS = rs.getDouble("PrecioSinIVA");
                Double preC = rs.getDouble("PrecioConIVA");
                int offe = rs.getInt("Oferta");
                if (a == 0) {
                    c = "Baja";
                }
                
                model.addRow(new Object[]{id, desc, cant, preS, preC, offe, c});
                //asignamos los datos a la tabla

            }
            
            System.out.println("Mostrame 1 fila" + model.getValueAt(2, 2).toString());
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, e);
        }

        //pasamos la tabla a el metodo
        tabla.setModel(model);
        //Doy tamaño a las columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        tabla.getTableHeader().setReorderingAllowed(false);
        
    }
//Metodo de la tabla agregar prducto

    public void agregarProducto(JTable agregar) {
        
        try {
            ConexionDB cc = new ConexionDB();
            ModeloTabla = (DefaultTableModel) agregar.getModel();
            ;
            try (Statement st = cc.getConnection().createStatement()) {
                int filas = agregar.getRowCount();
                int entre = 0;
                int com = 0;
                String desc = null;
                Integer cant;
                Double precioC;
                Double precioS;
                Integer offe;
                String categoria;

                //Verifico que la fila no sea null . Si es null no entra en el while para ingresar datos
                while (com < filas) {
                    desc = (String) agregar.getValueAt(com, 0);
                    out.println(desc);
                    precioS = (Double) agregar.getValueAt(com, 2);
                    out.println(precioS);
                    precioC = (Double) agregar.getValueAt(com, 3);
                    out.println(precioC);
                    cant = (Integer) agregar.getValueAt(com, 1);
                    out.println(cant);
                    offe = (Integer) agregar.getValueAt(com, 4);
                    out.println(offe);
                    categoria = (String) agregar.getValueAt(com, 5);
                    Integer id_categoria = compararCategoria(categoria);
                    System.out.println("mostrame categoría" + id_categoria);
                    //if((desc != null) && (precioC != null) && (precioS != null) && (cant != null) && (offe != null) ){
                    if (desc == null) {
                    } else if (cant == null) {
                    } else if (offe == null) {
                    } else if (precioC == null) {
                    } else if (precioS == null) {
                    } else if (id_categoria == null) {
                    } else {
                        String sql = "";
                        sql = "INSERT INTO productos VALUES (NULL, '" + desc + "', '" + precioS + "','" + precioC + "','" + cant + "','" + offe + "','1','0','" + id_categoria + "')";
                        st.executeUpdate(sql);
                        out.println("Fila " + desc + " Registrada correctamente");
                        entre = 1;
                        showMessageDialog(null, "Se agrego el producto correctamente", "Información", INFORMATION_MESSAGE);
                    }
                    com++;
                }
                //Verifica que una fila aunque sea este escrita . Si no lo esta salta el cartel de que debe llenar
                // datos para guardar productos.
                if (entre == 0) {
                    showMessageDialog(null, "Debe llenar una fila completa, para que se registren los datos", "Error", ERROR_MESSAGE);
                } else {
                    //Si una de las filas aunque sea fue llenada . al apretar el boton agregar producto
                    //Borra las filas del Jtable y lo resetea a cero
                    for (int m = 0; m < ModeloTabla.getRowCount(); m++) {
                        ModeloTabla.removeRow(m);
                        m -= 1;
                    }
                    //Despues de remover las filas agrega automaticamente 4 de nuevo.Vacias
                    int evolucionar = 0;
                    while (evolucionar < 4) {
                        ModeloTabla.addRow(new Object[]{null, null, null, null, null, null});
                        evolucionar++;
                        agregar.setModel(ModeloTabla);
                    }
                }
            }
            cc.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }
    }
//rellena la tabla stock apenas se entra a la sala

    public void rellenarStock(JTable tblauto, int[] limitador) {
        DefaultTableModel model = (DefaultTableModel) tblauto.getModel();
        try {
            ConexionDB cc = new ConexionDB();
            //Cantidad de columnas mas encabezado
            String[] columnas = new String[]{"IDProducto", "Descripción", "Cantidad", "PrecioS/IVA", "PrecioC/IVA", "Oferta", "Alta/Baja"};
            //Cargo otro array para la cantidad de registros en columna

            model.setColumnIdentifiers(columnas);
            //Ordena la tabla por sus cabeceras
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
            tblauto.setRowSorter(elQueOrdena);
            //Limpio la tabla
            ModificacionJtable mod = new ModificacionJtable();
            mod.limpiarTabla(tblauto);
            //Señalizo string sql para pasar buscar en la base de datos
            String SSQL = null;
            //Me conecto a la base de datos
            //Sql busqueda 
            SSQL = "SELECT IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta, altabajaproductos "
                    + " FROM productos ORDER BY IDProducto ASC LIMIT " + limitador[0] + "," + limitador[1] + "";
            //Me conecto a la BD
            Connection conect = cc.getConnection();
            //Preparo la secuencia SQL
            PreparedStatement st = conect.prepareStatement(SSQL);
            //pasamos nombre de las tablas base de datos al reg
            try ( // Ejecuto la consulta en un ResultSet
                    ResultSet rs = st.executeQuery()) {
                //pasamos nombre de las tablas base de datos al reg
                while (rs.next()) {
                    String c = "Alta";
                    int a = rs.getInt("altabajaproductos");
                    int id = rs.getInt("IDProducto");
                    String desc = rs.getString("Descripcio");
                    int cant = rs.getInt("Cantidad");
                    Double preS = rs.getDouble("PrecioSinIVA");
                    Double preC = rs.getDouble("PrecioConIVA");
                    int offe = rs.getInt("Oferta");
                    if (a == 0) {
                        c = "Baja";
                    }
                    model.addRow(new Object[]{id, desc, cant, preS, preC, offe, c});
                    //asignamos los datos a la tabla
                }
            }
            cc.desconectar();
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class
                    .getName()).log(SEVERE, null, ex);
        }
        //pasamos la tabla a el metodo
        //pasamos la tabla a el metodo
        tblauto.setModel(model);
        tblauto.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblauto.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblauto.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblauto.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblauto.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblauto.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblauto.getColumnModel().getColumn(6).setPreferredWidth(50);
        tblauto.getTableHeader().setReorderingAllowed(false);
    }

    //Cargo tabla de Stock bajo y Cantidad que queda en stock
    public void cargarTablaStockBajo(JTable stockBajo) {

        /////////////////////////////////////////////////////////
        try {//Try catch que ejecuta consulta para llenar bajo stock mercadería 
            //Indico columnas
            String[] calum = new String[]{"Bajo stock mercadería", "Cantidad"};
            int num = numero();//Lllamo a la consulta numero para obtener el numero asinado
            //por el usuario para el stockbajo
            out.println(num);
            //Creo array reg para pasarle los datos de las columnas obtenidas en basede datos
            String[] leg = new String[2];
            //Creo objeto tabla donde le paso cantidad de columnas
            DefaultTableModel model = new DefaultTableModel(null, calum) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; //To change body of generated methods, choose Tools | Templates.
                }
            };
            ConexionDB rx = new ConexionDB();
            //Instancio variables vacias para pasarles las consultas
            //Me conecto a BD
            Connection conect = rx.getConnection();
            //Creo consulta. En la consulta Ordeno los productos que tienen menos cantidad y que no estan dados de baja en el programa
            String sql = "SELECT Descripcio, Cantidad FROM productos WHERE (Cantidad <= '" + num + "') AND (altabajaproductos = 1) ORDER BY Cantidad ASC";
            //Preparo la consulta
            PreparedStatement st = conect.prepareStatement(sql);
            //Recorro el resulset buscando lo que deseo de la consulta, y lo paso a las variables
            //instanciadas vacias
            try ( //Ejecuto consulta en ResultSet
                    ResultSet rs = st.executeQuery()) {
                //Recorro el resulset buscando lo que deseo de la consulta, y lo paso a las variables
                //instanciadas vacias
                while (rs.next()) {
                    
                    leg[0] = rs.getString("Descripcio");
                    leg[1] = rs.getString("Cantidad");
                    //Cargo los elementos deseados a la tabla por defecto
                    model.addRow(leg);
                }   //pasamos la tabla a el metodo
                stockBajo.setModel(model);
                //Ordena la tabla por sus cabeceras
                TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
                stockBajo.setRowSorter(elQueOrdena);
                stockBajo.getTableHeader().setReorderingAllowed(false);
                //Doy tamaño a las columnas   stockBajo.getColumnModel().getColumn(0).setPreferredWidth(150);
                stockBajo.getColumnModel().getColumn(0).setPreferredWidth(150);
                rx.desconectar();
            }
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class
                    .getName()).log(SEVERE, null, ex);
        }
        
    }
//obtiene el numero de stock bajo

    public static int numero() {
        int numero = 0;
        try {
            ConexionDB con = new ConexionDB();
            Connection conec = con.getConnection();
            String sql2 = "SELECT stockbajo FROM configuracion WHERE idConfig = 1 ";
            PreparedStatement ps = conec.prepareStatement(sql2);
            try (ResultSet rs2 = ps.executeQuery()) {
                if (rs2 != null) {
                    while (rs2.next()) {
                        numero = rs2.getInt("stockbajo");
                    }
                }
                con.desconectar();
            }
            
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
        }
        return numero;
        
    }

    // Modifica Tabla stock las columnas 1,4,5,6,7
    public void modificarTablaStock(JTable tblStock) {
        int res = showConfirmDialog(null, "¿Esta seguro que desea modificar la tabla?", "Confirmación", WARNING_MESSAGE);
        if (res == YES_OPTION) {
            try {
                ConexionDB cc = new ConexionDB();
                try (Statement st = cc.getConnection().createStatement()) {
                    String sql = "";
                    DefaultTableModel model = (DefaultTableModel) tblStock.getModel();
                    //Ordena la tabla por sus cabeceras
                    TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
                    tblStock.setRowSorter(elQueOrdena);
                    int ro = model.getRowCount();
                    int j = 0;
                    int id;
                    String desc;
                    int cant;
                    Double prCiva;
                    Double prSiva;
                    int offe;
                    while (j < ro) {
                        id = (int) model.getValueAt(j, 0);//id del producto
                        desc = (String) model.getValueAt(j, 1);//Descripcion producto
                        cant = (int) model.getValueAt(j, 2);// Cantidad en unidades producto
                        prSiva = (Double) model.getValueAt(j, 3);// Precio sin el iva
                        prCiva = (Double) model.getValueAt(j, 4);// Precio con el iva
                        offe = (int) model.getValueAt(j, 5);// Oferta producto
                        //sql edita los productos
                        sql = "UPDATE productos SET descripcio= '" + desc + "' , Cantidad = " + cant
                                + ", PrecioSinIVA = " + prSiva + ",PrecioConIVA = " + prCiva + " , Oferta= " + offe + " WHERE IDProducto = " + id + "";
                        st.executeUpdate(sql);
                        j++;
                    }
                }
                cc.desconectar();
                showMessageDialog(null, "Datos modificados con exito", "Mensaje", INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
                getLogger(ConsultasStock.class
                        .getName()).log(SEVERE, null, ex);
            }
        } else {
            showMessageDialog(null, "No se modifico ningun dato", "Mensaje", INFORMATION_MESSAGE);
        }
    }

    //Modifico tabla stock alta baja
    public void modstock(JTable tbl2) {
        if (tbl2 != null) {
            
            try {
                ConexionDB cc = new ConexionDB();
                try (Statement st = cc.getConnection().createStatement()) {
                    String sql = "";
                    int numFilas = tbl2.getRowCount();
                    for (int l = 0; l < numFilas; l++) {
                        String id = tbl2.getValueAt(l, 0).toString();
                        
                        String altaBaja = (String) tbl2.getValueAt(l, 6);
                        
                        if (altaBaja.equals("Alta")) {
                            sql = "UPDATE productos SET altabajaproductos = 1 WHERE IDproducto = " + id + "";
                            st.executeUpdate(sql);
                        } else {
                            sql = "UPDATE productos SET altabajaproductos = 0 WHERE IDproducto = " + id + "";
                            st.executeUpdate(sql);
                            
                        }
                    }
                }
                cc.desconectar();
            } catch (SQLException ex) {
                getLogger(ConsultasStock.class
                        .getName()).log(SEVERE, null, ex.getMessage());
            }
        } else {
            showMessageDialog(null, "No posee datos completos en la tabla para modificar", "Error", ERROR_MESSAGE);
        }
    }

    //Configura el numero de stock bajo
    public void cfgNumStock(int valor) {
        try {
            ConexionDB con = new ConexionDB();
            try (Statement st = con.getConnection().createStatement()) {
                String sql2 = "UPDATE configuracion SET stockbajo = '" + valor + "' WHERE idConfig = '1'";
                st.executeUpdate(sql2);
                out.println("Datos insertados en tabla");
                con.desconectar();
            }
        } catch (SQLException ex) {
            getLogger(ConsultasStock.class.getName()).log(SEVERE, null, ex);
            out.println("Conexión fallida: " + ex);
        }
    }

    //Cargar cmb categorias
    public void cmbCargarCategorias(JList cat) {
        // Paso el modelo del cmb
        ArrayList array = new ArrayList();
        DefaultListModel def = new DefaultListModel();
        
        try {
            String sqll = ""; //Variable para la consulta

            //Borro los elementos del combobox
            cat.removeAll();

            //Agrego en el indice 1 seleccionar
            //cmbCategoria.addElement("Seleccionar:");//Instancio el indice 0 con seleccionar:
            //Genero objeto conexion DB
            ConexionDB cc = new ConexionDB();
            Connection con = cc.getConnection();
            sqll = "SELECT * FROM categoria_productos";
            PreparedStatement ps = con.prepareStatement(sqll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                def.addElement(rs.getString("categoria"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  def.addElement(array);
        cat.setModel(def);
        
    }

    //Cargar cmb categorias
    public void cmbCargarCategorias2(JComboBox cmbCat) {
        DefaultComboBoxModel cmbCategoria = (DefaultComboBoxModel) cmbCat.getModel();// Paso el modelo del cmb
        //Establece cursor
        cmbCat.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        try {
            String sqll = ""; //Variable para la consulta

            //Borro los elementos del combobox
            //Agrego en el indice 1 seleccionar
            //Genero objeto conexion DB
            ConexionDB cc = new ConexionDB();
            Connection con = cc.getConnection();
            sqll = "SELECT * FROM categoria_productos";
            PreparedStatement ps = con.prepareStatement(sqll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbCategoria.addElement(rs.getString("categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbCat.setModel(cmbCategoria);
    }

    //Metodo para comparar categorías se pasa la variable categoría y se compara para obtener su id
    public Integer compararCategoria(String categoria) {
        
        Integer catego = 0; //Variable categoria
        try {
            String sqll = ""; //Variable para la consulta

            //Borro los elementos del combobox
            //Agrego en el indice 1 seleccionar
            //Genero objeto conexion DB
            ConexionDB cc = new ConexionDB();
            Connection con = cc.getConnection();
            sqll = "SELECT * FROM categoria_productos WHERE categoria = '" + categoria + "'";
            PreparedStatement ps = con.prepareStatement(sqll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                catego = rs.getInt("id_categoria");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return catego;
    }

    //Metotodo para cargar jTable Categorías
    public void rellenarJtableCategoria(JTable tabla) {
        //Modelo tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        ModificacionJtable mod = new ModificacionJtable();
        //Creo elque ordena para ordenar por header la tabla
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
        tabla.setRowSorter(elQueOrdena);
        //eliminar filas me permite borrar todas las filas de la tabla y rellenarlas
        mod.limpiarTabla(tabla);
        try {
            int id_cat = 0, altaBaja = 0;
            String cat = "";
            //Conexion a la DB
            ConexionDB con = new ConexionDB();
            Connection conect = con.getConnection();
            //Consulta que paso a la DB
            String SQL = "SELECT * FROM categoria_productos";
            PreparedStatement ps = conect.prepareStatement(SQL);
            //Resulset el cual me permite recorrer en una lista los datos que se traen de la DB
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                rs.beforeFirst();
                
                while (rs.next()) {
                    id_cat = rs.getInt("id_categoria");
                    cat = rs.getString("categoria");
                    altaBaja = rs.getInt("alta_baja_categoria");
                    model.addRow(new Object[]{id_cat, cat, altaBaja});
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron registros", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabla.setModel(model);
    }

    //Metodo para modificar las categorias
    public void modificarCategoria(int idCategoria, JTextField categoria, JRadioButton a) {
        try {
            int altaBaja;
            String cat = categoria.getText();
            if (a.isSelected() == true) {
                altaBaja = 1;
                System.out.println("Entre en altaBaja 1");
            } else {
                altaBaja = 0;
                System.out.println("Entre en altaBaja 0");
            }

            //Me conecto a la BD
            ConexionDB cc = new ConexionDB();
            //Creo el statement
            Statement st = cc.getConnection().createStatement();
            //Creo query SQL
            String SQL = "UPDATE categoria_productos SET categoria = '" + cat + "', alta_baja_categoria = " + altaBaja + " WHERE id_categoria = " + idCategoria + "";
            System.out.println("SQL CATEGORÏA = " + SQL);
            int num = st.executeUpdate(SQL);
            if (num > 0) {
                
                JOptionPane.showMessageDialog(null, "La Categoría se modifico satisfactoriamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
            //Filtrar por categoría
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para agregar una nueva categoría
    public void agregarCategoria(String categoria) {
        try {
            //Me conecto a la BD
            ConexionDB cc = new ConexionDB();
            //Preparo la sentencia statement
            Statement st = cc.getConnection().createStatement();
            //Creo el query
            String SQL = "INSERT categoria_productos VALUES null,'" + categoria + "', '1'";
            //Ejecuto el query
            int verificacion = st.executeUpdate(SQL);
            if (verificacion > 0) {
                JOptionPane.showMessageDialog(null, "Se agrego una nueva categoría satisfactoriamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Creo metodo para modificar la categoría en Stock
    public void modificarCategoriaStork(JTable tabla, int categoria) {

        //Creo modelo tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        //Tomo la fila seleccionada
        int filaSeleccionada = tabla.getSelectedRow();
        int idProducto;
        int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar la categoría, en la fila seleccionada?", "WARNING", JOptionPane.YES_NO_OPTION);
        if (JOptionPane.YES_OPTION == opcion) {
            
            if (filaSeleccionada != -1) {
                try {
                    //Tomo id de producto de la tabla de la fila seleccionada
                    idProducto = (int) model.getValueAt(filaSeleccionada, 0);
                    //Me conecto a la BD
                    ConexionDB cc = new ConexionDB();
                    Statement st = cc.getConnection().createStatement();
                    //Creo query UPDATE
                    String SQL = "UPDATE productos SET id_categoria = " + categoria + " WHERE IDproducto = " + idProducto + "";
                    int verificar = st.executeUpdate(SQL);
                    if (verificar > 0) {
                        JOptionPane.showMessageDialog(null, "La categoría fue actualizada sattisfactoriamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La categoría no se modifico", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //Filtrar por categoría

    public void filtroCategoria(JList cate, JTable tabla, int[] limitador) {
        //Instancio modelos de tabla y cmb

        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        DefaultListModel modelo = new DefaultListModel();

        //Verifico que cmb sea distinto de cero
        //verificacion para tomar el item seleccionado
        try {

            //Borro las filas de la tabla
            ModificacionJtable mod = new ModificacionJtable();
            mod.limpiarTabla(tabla);
            //Ordena la tabla por sus cabeceras creo objeto model y despues paso la tabla original
            TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(model);
            tabla.setRowSorter(elQueOrdena);
            int categoria = cate.getSelectedIndex() + 1;
            System.out.println("categoría" + categoria);
            //Me conecto bd
            ConexionDB con = new ConexionDB();
            Connection cc = con.getConnection();
            //Preparo setencia SQL
            String SQLL = "SELECT DISTINCT * FROM productos INNER JOIN categoria_productos ON productos.id_categoria = categoria_productos.id_categoria WHERE productos.id_categoria ='" + categoria + "' LIMIT " + limitador[0] + "," + limitador[1] + "";
            System.out.println("SQL=== " + SQLL);
            PreparedStatement ps = cc.prepareStatement(SQLL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String c = "Alta";
                int a = rs.getInt("altabajaproductos");
                int id = rs.getInt("IDProducto");
                String desc = rs.getString("Descripcio");
                int cant = rs.getInt("Cantidad");
                Double preS = rs.getDouble("PrecioSinIVA");
                Double preC = rs.getDouble("PrecioConIVA");
                int offe = rs.getInt("Oferta");
                if (a == 0) {
                    c = "Baja";
                }
                model.addRow(new Object[]{id, desc, cant, preS, preC, offe, c});
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasStock.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabla.setModel(model);
        //Modifico el tamaño de las columnas
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(50);
        //hago que no se puedan mover las columnas
        tabla.getTableHeader().setReorderingAllowed(false);
        
    }
}
