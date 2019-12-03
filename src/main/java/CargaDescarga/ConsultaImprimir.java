/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDescarga;

import FNC.Funciones;
import static Stock.ConsultasStock.numero;
import Stock.ModificacionJtable;
import coneccion.ConexionDB;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class ConsultaImprimir {

    DefaultTableModel ModeloTabla;
    ConexionDB cc = new ConexionDB();
    //Cargo pedidos para imprimir

    public void cargarPedido(JTable pedidos) {
        try {
            int conteo = 0;//Variable que me va a contar si tiene pedidos pendientes
            int valorEstado = 0;//Variable que instanacia el estado del pedidoa 0 = - y si es 1 = pedido pendiente, que indica
            //que el producto esta en espera de un pedido ya realizado, pero esto no impide realizar pedido con el mismo producto
            //Indico columnas
            // String[] calum = new String[]{"ID", "Descripción pedido", "Estado", "Cantidad"};
            int num = numero();//Lllamo a la consulta numero para obtener el numero asinado
            //por el usuario para el stockbajo
            DefaultTableModel tabel = (DefaultTableModel) pedidos.getModel();
            //Limpio tabla
            ModificacionJtable mod = new ModificacionJtable();
            mod.limpiarTabla(pedidos);
            //Creo array reg para pasarle los datos de las columnas obtenidas en basede datos
            Object[] regg = new Object[4];
            //Creo objeto tabla donde le paso cantidad de columnas
            // ModeloTabla = new DefaultTableModel(null, calum);

            //Instancio variables vacias para pasarles las consultas
            //Me conecto a BD
            ConexionDB yo = new ConexionDB();
            Connection conect = yo.getConnection();
            //Creo consulta
            String sql = "SELECT IDproducto, Descripcio, Cantidad, altaBajaPedidos FROM productos WHERE"
                    + "(Cantidad <= '" + num + "') AND (altabajaproductos = 1) ORDER BY Cantidad ASC ";
            // NOT EXISTS(SELECT IDproducto FROM detallepedido WHERE productos.IDproducto = detallepedido.IDproducto)"
            //Preparo la consulta
            PreparedStatement st = conect.prepareStatement(sql);
            //Ejecuto consulta en ResultSet
            ResultSet rs = st.executeQuery();
            //Recorro el resulset buscando lo que deseo de la consulta, y lo paso a las variables
            //instanciadas vacias
            while (rs.next()) {

                regg[0] = (int) rs.getInt("IDproducto");
                regg[1] = rs.getString("Descripcio");
                valorEstado = rs.getInt("altaBajaPedidos");
                if (valorEstado == 0) {
                    regg[3] = "--";
                } else {
                    regg[3] = "Pedido pendiente";
                    conteo++;
                }

                //Cargo los elementos deseados a la tabla por defecto
                tabel.addRow(regg);

            }
            //Si conteo es > q cero se larga el mesaje ya que hay pedidos pendientes
            if (conteo > 0) {
                showMessageDialog(null, "Tiene pedidos pendientes agregados a la lista. Puede generar mas encargos respecto a los mismos,\npero cuando los des de alta se acumularan a las cantidades de stock", "ADVERTENCIA", INFORMATION_MESSAGE);
            }
            //Creo render de texto al medio para la tabla
            DefaultTableCellRenderer tblr = new DefaultTableCellRenderer();
            tblr.setHorizontalAlignment(CENTER);
            //con el siguiente codigo paso el render de el texto y se centra en el medio
            //getcolum paso el numero de columna que quiero centrar
            pedidos.getColumnModel().getColumn(3).setCellRenderer(tblr);
            //pasamos la tabla a el metodo
            pedidos.setModel(tabel);

        } catch (SQLException ex) {
            getLogger(ConsultaImprimir.class.getName()).log(SEVERE, null, ex);
        }

    }

    //LLeno jlist con los proveedores disponibles
    public void llenarJlistProveedores(JList prov, DefaultListModel list) {
        prov.removeAll();
        try {
            //Creo un modelo de lista para luego setearlo al creado para modificarlo
            String nomb, ape, email, dire;
            int dni, id;
            long tel;
            String sql = null;
            //Sentencia para llamado de proveedores
            sql = "SELECT * FROM Proveedor ORDER BY Apellido ASC";
            //me conecto a la BD
            Connection conect = cc.getConnection();
            //preparo la sentencia sql
            PreparedStatement ps = conect.prepareStatement(sql);
            //Ejecuto consulta con resultset para recorrerla
            ResultSet rs = ps.executeQuery();
            //Recorro los registros con un while rs.next() y le asigno a las variables el contenido
            while (rs.next()) {
                nomb = rs.getString("Nombre");
                ape = rs.getString("Apellido");
                email = rs.getString("Email");
                dni = rs.getInt("DNI");
                id = rs.getInt("IDProveedor");
                tel = rs.getLong("Tel");
                dire = rs.getString("dirección");
                Proveedores pv = new Proveedores(nomb, ape, email, dni, id, tel, dire);
                list.addElement(pv);
            }
            prov.setModel(list);
        } catch (SQLException ex) {
            getLogger(ConsultaImprimir.class.getName()).log(SEVERE, null, ex);
        }

    }

    //Creo la carga de pedidos a la BD
    public void cargarPedidoBD(JLabel encargo, JLabel numP, int idProve, JTable table) throws SQLException {

        int cont = 0;
        ConexionDB cc = new ConexionDB();//Me conecto a la BD
        try (Statement st = cc.getConnection().createStatement() //Creo statement
        ) {
            String sql = "", sql2 = "", sql3 = "";
            int id = 0;
            int cant = 0;//Variables de cant y ide pedido
            String desc = null; //Variable descripcion pedido
            long idAutoIncrement = 0;//Variable para el id autoincrement
            int altBajPedidos = 0;//Variable de alta o baja de pedidos  1 = True, 0 = False lo guardo como 0 para
            //darle de alta al pedido cuando se cargue
            Funciones fn = new Funciones();
            String fecha = fn.getDate(5);//Paso por parametro valor 1 al 5 ytraigo fecha necesaria
            //DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-LL-dd");
            //String fecha = formater.format(LocalDate.now());//Variable de fecha actual al sistema para los pedídos !
            DefaultTableModel tb = (DefaultTableModel) table.getModel();//Variable para maniobrar JTable
            int cantFilas = tb.getRowCount();//Variable para contar las filas
            int filaCantidad = 2;//Variable para instanciar la verificacion en la columna cant
            boolean verificar = false;//Variable para verificar que la columna cantidad no sea null
            int contador = 0, ct = 0, ct2 = 0;
            //El siguiente for verifica que la fila cantidad no este null
            //Si todas las filas estan null no ejecuta sentencias
            for (int i = 0; i < cantFilas; i++) {
                if (tb.getValueAt(i, filaCantidad) == null) {
                    verificar = true;
                    ct++;
                } else {
                    verificar = false;
                    ct2++;
                }
            }   if (ct2 != 0) {
                sql = "INSERT INTO pedido VALUES (null,'" + idProve + "','" + fecha + "','" + altBajPedidos + "')";
                st.executeUpdate(sql, RETURN_GENERATED_KEYS); //Recupero el ultimo valor autoincrement del insert anterior con st.RETURN_GENERATED_KEYS
                //Recupero el ultimo valor autoincrement del insert anterior con st.RETURN_GENERATED_KEYS
                ResultSet rs = st.getGeneratedKeys();//Asigno el id al resultset
                if (rs.next()) {
                    idAutoIncrement = rs.getLong(1);//Guardo el ultimo autoincrement en la variable
                }   out.println(idAutoIncrement);
            } else {
                showMessageDialog(null, "No posee filas con cantidades llenas.\nInserte cantidades aunque sea 1 fila.");
            }
            //Este if verifica si el usuario desea parar el programa o seguirlo ejecutando en caso de que quiera llenar
            //las filas vacias de cantidad
            if (ct2 != 0 && ct != 0) {
                int a = showConfirmDialog(null, "Tiene cantidades de producto vacías, ¿Desea llenarlas?", "INFORMACIÓN", YES_NO_OPTION);
                if (a == YES_OPTION) {
                    verificar = true;
                } else {
                    verificar = false;
                }
            }
            //Si la prueba pasa todos los verificar este quedara en false y podra exportar datos a la BD
            if (verificar == false) {
                for (int i = 0; i < cantFilas; i++) {
                    if ((tb.getValueAt(i, filaCantidad) != null) && (idAutoIncrement != 0)) {
                        id = (int) tb.getValueAt(i, 0);
                        cant = (int) tb.getValueAt(i, filaCantidad);
                        desc = (String) tb.getValueAt(i, 1);
                        //Agrego a la bd el pedido
                        sql2 = "INSERT INTO detallepedido VALUES (null,'" + idAutoIncrement + "','" + id + "','" + desc + "','" + cant + "', 1)";
                        st.executeUpdate(sql2);
                        out.println("Descripcion de productos: " + desc);
                        sql3 = "UPDATE productos SET altaBajaPedidos = 1 WHERE IDproducto = '" + id + "'";
                        st.executeUpdate(sql3);
                        out.println("Consulta " + sql3);
                    }
                }
                //las siguientes funciones es para llevar un conteo de exportacion de datos comparar y no cerrar
                //pestaña hasta que se exporte a exel todo.
                cont = parseInt(encargo.getText());
                cont++;
                encargo.setText(valueOf(cont));
                numP.setText(valueOf(idAutoIncrement));
                showMessageDialog(null, "Encargo de productos almacenados en la BD \nPor favor cree archivo excel antes de cerrar ventana!", "CONFIRMACION", INFORMATION_MESSAGE);
            }
            //Cierro conexion y statement
        }
        cc.desconectar();
    }

    //cerrar
    public int cerrar(JLabel encargo, JLabel exportar) {
        int b = 0;
        int a = parseInt(encargo.getText());
        int c = parseInt(exportar.getText());
        if ((a == 0) && (c == 0)) {
            b = 1;
        } else if (a == c) {
            b = 1;
        } else if (a > c) {
            if (showConfirmDialog(null, "¿Desea realmente salir de esta ventana, si lo hace y no a exportado los datos a excel\nNo podra hacerlo nunca más ?", "Salida", YES_NO_OPTION) == YES_OPTION) {
                b = 1;//paso a cerar el comando 1 si selecciono si lo que genera el cierre de la pestaña

            } else {
                b = 0;//Si marca no paso el comando 0 que no cierra la ventana
            }
        }
        return b;
    }
}
