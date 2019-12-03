package CargaDescarga;

import FNC.Funciones;
import Stock.ModificacionJtable;
import coneccion.*;
import static java.lang.Integer.valueOf;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Valentin
 */
public class ConsultasCargaPedido {

    //vvariable statica que guarda altabajapedido para poder instanciar consultas en bd
    private static ArrayList<Integer> lista = new ArrayList<>();

    public ArrayList<Integer> getLista() {
        return lista;

    }

    //Carga el jtable de pedido
    /**
     * @wbp.parser.entryPoint
     */
    public void setLista(ArrayList<Integer> lista) {
        lista = lista;
    }

    public void pedidoCarga(JTable tb, String list) {
        ModificacionJtable modi = new ModificacionJtable();
        modi.limpiarTabla(tb);//Limpio la tabla
        DefaultTableModel md = (DefaultTableModel) tb.getModel();//Cargo tabla para modificala a gusto
        TableRowSorter<TableModel> ordenador = new TableRowSorter<>(md);//Creo para ordenar por cabezera
        tb.setRowSorter(ordenador);//Instancio ordenador en la cabezera
        Object[] ob = new Object[3];//Objeto que llenare con datos de la BD para pasarlos al JTable

        Date a = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        boolean n = false;
        //Me conecto a la BD
        ConexionDB db = new ConexionDB();
        Connection conec = db.getConnection();
        //Creo sentencia SQL
        String sql = "SELECT IDPedido, fechaPedido, altaBajaPedido FROM pedido ORDER BY fechaPedido DESC LIMIT " + list + "  ";
        try {
            //Preparo la sentencia
            PreparedStatement pt = conec.prepareStatement(sql);
            //Ejecuto el resulset para recorrer los registros y reasignarlos al objeto creado anteriormente !
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                //Asigno al objeto ob todos los datos de la BD
                ob[0] = rs.getInt("IDPedido");
                a = rs.getDate("fechaPedido");
                ob[1] = format.format(a);
                int r = rs.getInt("altaBajaPedido");
                if (r == 1) {
                    n = true;

                } else {
                    n = false;
                }
                ob[2] = n;
                //Paso fila a fila a la tabla.
                md.addRow(ob);
            }
        } catch (SQLException ex) {
            getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
        }
        tb.setModel(md);

    }
    //Metodo para busqueda por fecha de los pedidos

    public void busqedaPedidosPorFecha(Date fechaDesde, Date fechaHasta, JTable pedidoss) {
        try {
            //Obtengo el modelo del jtable pedidos
            DefaultTableModel md = (DefaultTableModel) pedidoss.getModel();
            //limpio tabla pedidos
            ModificacionJtable mj = new ModificacionJtable();
            mj.limpiarTabla(pedidoss);
            //creo variables
            Integer id = null;
            boolean aB = false;
            String fechaBD = null;
            String fd = null, fh = null; //Variables fechas de pedidos
            //Genero una mascara para transformar las fechas y poder buscarlas en la BD
            SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Genero mascara para obtener fecha de BD
            SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            //Formateo la fecha
            fd = formatt.format(fechaDesde);
            fh = formatt.format(fechaHasta);

            //me conecto a la BD
            ConexionDB conDB = new ConexionDB();
            Connection cc = conDB.getConnection();
            //Creo consulta sql
            String sqll = "SELECT IDPedido, fechaPedido, altaBajaPedido FROM pedido "
                    + "WHERE fechaPedido BETWEEN '" + fd + "' AND '" + fh + "'";
            //ejecuto la consulta
            try ( //preparo la consulta
                    PreparedStatement ps = cc.prepareStatement(sqll)) {
                //ejecuto la consulta
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("IDPedido");
                    if (rs.getInt("altaBajaPedido") == 0) {
                        aB = false;
                    } else {
                        aB = true;
                    }
                    fechaBD = ft.format(rs.getDate("fechaPedido"));
                    md.addRow(new Object[]{id, fechaBD, aB});
                }
            }
            conDB.desconectar();
            //inserto la nueva tabla pedidos de la busqueda!
            pedidoss.setModel(md);

        } catch (SQLException ex) {
            getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
        }
    }

    //Carga de proveedor
    public void cargaDatosProveedor(boolean altaBaja, int idPedido, JList datosProveedor) {
        String nombre = null, apellido = null, email = null, dire = null;
        Integer dni = null;
        Long tel = null;
        DefaultListModel lm = new DefaultListModel();
        datosProveedor.removeAll();
        //Me conecto a la BD
        ConexionDB conec = new ConexionDB();
        Connection cc = conec.getConnection();
        //Preparo SQL
        String sqll = "SELECT proveedor.Nombre, proveedor.Apellido, proveedor.DNI, proveedor.Tel, proveedor.Email, proveedor.dirección "
                + "FROM proveedor, pedido "
                + "WHERE pedido.IDProveedor = proveedor.IDProveedor "
                + "AND pedido.IDPedido = '" + idPedido + "'";
        try {
            //preparo la sentencia
            PreparedStatement ps = cc.prepareStatement(sqll);
            //Ejecuto el resultset
            ResultSet rs = ps.executeQuery();
            //agrego todos los datos a la lista 
            if (altaBaja == false) {
                while (rs.next()) {

                    nombre = rs.getString("proveedor.Nombre");
                    apellido = rs.getString("proveedor.Apellido");
                    email = rs.getString("proveedor.Email");
                    dire = rs.getString("proveedor.dirección");
                    dni = rs.getInt("proveedor.DNI");
                    tel = rs.getLong("proveedor.Tel");
                    lm.addElement("Nombre: " + nombre);
                    lm.addElement("Apellido: " + apellido);
                    lm.addElement("D.N.I: " + dni);
                    lm.addElement("Dirección: " + dire);
                    lm.addElement("EMAIL: " + email);
                    lm.addElement("Tel: " + tel);
                }
                //instancio el modelo creado a la lista original
                datosProveedor.setModel(lm);
            } else {
                lm.addElement("Este pedido ya ha sido CARGADO");
                datosProveedor.setModel(lm);
            }

        } catch (SQLException ex) {
            getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
        }

    }

    //Cargar detalle del pedido tercer jtable de cargar pedido
    public void cargarDetallePedido(int idPedido, JTable detalle, int[] limitador, boolean altaBaja) {
        //Limpio la tabla
        ArrayList a = new ArrayList();

        CargaDePedido cd = new CargaDePedido();
        ModificacionJtable mod = new ModificacionJtable();
        mod.limpiarTabla(detalle);
        DefaultTableModel md = (DefaultTableModel) detalle.getModel();//Adquiero el modelo del detalle   
        TableRowSorter<TableModel> ordenador = new TableRowSorter<>(md);//Creo para ordenar por cabezera
        detalle.setRowSorter(ordenador);//Instancio ordenador en la cabezera
        //Variable del jtabla para pasar datos
        long idPro = 0, cant = 0;
        int d = 0;
        String descProd = "";

        //Conecto bd
        ConexionDB db = new ConexionDB();
        //obtengo coneccion
        Connection cc = db.getConnection();
        //instancio sql
        String sqll = "SELECT * FROM detallepedido WHERE IDPedido = '" + idPedido + "' "
                + "LIMIT " + limitador[0] + "," + limitador[1] + "";
        ModificacionJtable mdd = new ModificacionJtable();
        try {
            //preparo la sentencia y la ejecuto 
            PreparedStatement st = cc.prepareStatement(sqll);
            ResultSet rs = st.executeQuery();
            //recorro la sentencia con result set
            if (altaBaja == true) {
                mdd.limpiarTabla(detalle);
                md.addRow(new Object[]{"Pedidos", " ya cargados en la BD"});

            } else {
                while (rs.next()) {
                    //Paso los datos de la bd a las variables
                    idPro = rs.getInt("IDproducto");
                    cant = rs.getInt("cantPedido");
                    descProd = rs.getString("descPedido");
                    d = rs.getInt("altabajadetalle");
                    //Creo objeto y lo instancio en el modelo de la tabla
                    Object[] ob = {idPro, descProd, cant};
                    md.addRow(ob);
                    a.add(d);
                }
            }
        } catch (SQLException ex) {
            getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
        }

        //Paso el modelo de la tabla al jtable
        detalle.setModel(md);

        setLista(a);
        out.println(getLista());

    }
    //Tomo cantidad de pedido y lo actualizo en la base de datos

    public void actualizarStock(JTable tbPedido, JTable tbDetallePedido) {
        
        
        try {
            
            ConexionDB cc = new ConexionDB();
            Funciones fn = new Funciones();
            Statement st = cc.getConnection().createStatement();
            int a = tbPedido.getSelectedRow(); //tomo a ver si se selecciono una fila
            int b = tbPedido.getRowCount();//devuelvo umero de filas
            int c = tbDetallePedido.getRowCount();//cantidad filas detalle
            int idPedido = 0;//variable que contendra idpedido
            Long idProducto = null, cant = null;
            Integer altaBajap = null;
            boolean altaBaja = false, test = false;
            int obt = 0, od = 0, sod = 0;
            int[] obtener = new int[200];
            int[] obtene = new int[200];
            ArrayList<String> ub = new ArrayList<>();
            boolean selected = false;//Variable verificar seleccion de fila
            String sqll = "", sql2 = "", sql3 = "", sql4 = "", sql5 = "", sql6 = "";//Variable sql

            //Verifico si esta seleccionada la fila de pedido
            if (a == -1) {
                showMessageDialog(null, "Debe seleccionar una sola fila de la tabla pedidos para poder proseguir", "ERROR", ERROR_MESSAGE);
            } else {
                for (int i = 0; i < b; i++) {
                    selected = tbPedido.isRowSelected(i);//Devuelve true la fila que fue seleccionada

                    if (selected == true) {

                        //Tomo alta baja para saber si el pedido ya esta cargado o no
                        altaBaja = (boolean) tbPedido.getValueAt(i, 2);
                        idPedido = (int) tbPedido.getValueAt(i, 0);
                        out.println("AltaBaja = " + altaBaja);

                        if (altaBaja == false) {
                            //Recorro todas las filas de detalle pedido para actualiza la cantiad en la BD
                            for (int x = 0; x < c; x++) {
                                idProducto = parseLong(tbDetallePedido.getValueAt(x, 0).toString());//Tomo la columna 0 para sacar id del producto
                                cant = parseLong(tbDetallePedido.getValueAt(x, 2).toString());                                                                                               
                                sql4 = "UPDATE detallepedido SET altabajadetalle = 0 WHERE IDPedido = '" + idPedido + "'";
                                st.executeUpdate(sql4);      
                                //llama al metodo ts para ver si da de baja o no al producto !
                                     String as =ts(idProducto);                                                                
                                //Busco en la bd todos los productos que posee el id de este pedido
                                sqll = "UPDATE productos SET Cantidad = Cantidad + '" + cant + "' WHERE IDproducto = '" + idProducto + "' ";
                                st.executeUpdate(sqll);
                                try {
                                    //Actualizo alta baj de pedidos
                                    sql2 = "UPDATE pedido SET altaBajaPedido = 1 WHERE IDPedido = '" + idPedido + "'";
                                    altaBajap = x;
                                    st.executeUpdate(sql2);
                                    out.println(sql2);
                                    out.println(altaBajap);
                                } catch (SQLException ex) {
                                    getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
                                }
                            }
                        } else {
                            showMessageDialog(null, "Este pedido ya ha sido cargado, por favor seleccione otro", "INFORMACION", INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
        }
    }
//Metodo que verifica el alta baja de productos en comparacion con detallepedido
    @SuppressWarnings("empty-statement")
    public String ts(Long idProduc) {
        
        ArrayList<String> ar = new ArrayList<>();
          ArrayList<String> ar2 =new ArrayList<>();
           ArrayList<String> definitivo = new ArrayList<>();
            Long vec = null;//Variable del producto
            String mensaje = "";//variable mensaje
        try {
            //Conexion
            ConexionDB cc = new ConexionDB();
            Statement st = cc.getConnection().createStatement();
            ResultSet rs;
            String sql1, sql2;
            int idPro = 0, idDetalle = 0, cont1 = 0, cont2 = 0, cont3 = 0;
           

            //Consulta que me trae todos los id de productos de pedidos activos aun       
            sql1 = "SELECT IDproducto FROM detallepedido WHERE altabajadetalle = 1";
            rs = st.executeQuery(sql1);
            while (rs.next()) {
                ar.add(rs.getString("IDproducto"));
                out.println("idPro = " + idDetalle);
                cont1++;
                
            }

            //Consulta que trae todos los id de producto pero de la tabla productos
            sql2 = "SELECT DISTINCT productos.IDproducto FROM productos,detallepedido WHERE productos.IDproducto = detallepedido.IDproducto";
            rs = st.executeQuery(sql2);
            while (rs.next()) {
                ar2.add(rs.getString("IDproducto"));

               
            }
            out.println(valueOf(ar2));
            for(String a : ar){
                out.println(a);
            }
            //ar.retainAll(ar2); Compara los dos arrays y devuelve las igualdades nomas !
          ar.retainAll(ar2);
            out.println(ar);
         for(String a : definitivo){
                out.println(a);
            }
         
         for(int ini = 0 ; ini< ar.size(); ini++){
             int arra= valueOf(ar.get(ini)); 
             if( arra == idProduc){
                 vec = idProduc;
             }
         }
            int contar = 0;
           
            //Si el producto existe como up en detalle pedido no se da de alta en producto si existe como dado de baja en detalle
            //se le da de baja en producto. Estos if son los encargados de ejecutarlo
            //Primer if si el id prod es != null se suma si no se resta si el contar final es positivo no se da de baja si no si
                                     if(vec != null ){
                                         contar++;
                                     } else {
                                         contar--;
                                     }
                                     if(contar > 0){
                                         out.println("Es mayor q cero");
                                         mensaje = "El producto"+idProduc+"no se dio de baja";
                                     }else{
                                         out.println("Imprimime el producto en la bd "+idProduc);
                                        sql1 = "UPDATE productos SET altaBajaPedidos = 0 WHERE  IDproducto = '" + idProduc + "'";
                                        st.executeUpdate(sql1);
                                        
                                        mensaje = "El producto VACIO se dio de baja";
                                        
                                     }
        

            out.println("finish");
        } catch (SQLException ex) {
            getLogger(ConsultasCargaPedido.class.getName()).log(SEVERE, null, ex);
        }
       
      
return mensaje;
    }

}
