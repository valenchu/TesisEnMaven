/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import FNC.Funciones;
import static java.lang.Long.valueOf;
import static java.lang.System.out;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class ModiTblProveedor extends JFrame {

    public void modificartblprove(JTable tbl) {
        //Creo los titulos de las columnas
        String[] columnas = {"DNI", "Nombre", "Apellido", "Telefono", "Email", "Dirección"};

        //Recibo modelo de jtable y le paso los titulos indicados arriba
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setColumnIdentifiers(columnas);
        //paso del DefaultTableModel creado a la tabla original con setModel
        tbl.setModel(dtm);
      

        tbl.getColumnModel().getColumn(0).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(60);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(60);

    }

    public void tablaProveedoresModificadora(JTable tbl) {
        //Creo los titulos de las columnas
        String[] columnas = {"ID", "DNI", "Nombre", "Apellido", "Telefono", "Email", "Dirección"};

        //Recibo modelo de jtable y le paso los titulos indicados arriba
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setColumnIdentifiers(columnas);
        //paso del DefaultTableModel creado a la tabla original con setModel
        tbl.setModel(dtm);
        tbl.getTableHeader().setReorderingAllowed(false);

    }

    //Limpia todas las filas de la tabla
    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    //Elimina los proveedores
    public void eliminarProveedor(JTable tbl) {

        //Preguntamos si esta seguro de eliminar
        int res = showConfirmDialog(null, "¿Esta seguro de querer eliminar al proveedor seleccionado?", "Confirmación", INFORMATION_MESSAGE);

        //Obtenemos la tabla
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        int id;

        //Verificamos respuesta
        if (res == YES_OPTION) {
            //Verificamos que se seleccione una fila para eliminar el proveedor en ella

            int selecFila = tbl.getSelectedRow();

            if (selecFila == -1) {
                showMessageDialog(null, "Debe seleccionar una fila para eliminar", "Error", ERROR_MESSAGE);
            } else {
                id = (Integer) model.getValueAt(selecFila, 0);

                ConsultaProveedor con = new ConsultaProveedor();
                con.eliminarProvee(tbl, id);
            }

        }

    }
    //Agrega fila a tabla

    public void agregarfila(JTable tblprov) {
        int i = 0;
        int k = 0;
        DefaultTableModel dtm = (DefaultTableModel) tblprov.getModel();
        //getSelectedRow devuelve el numero de fila seleccionada
        int filaSeleccionada = tblprov.getSelectedRow();
        if (filaSeleccionada != -1) {

            //insertRow sirve para insertar fila en donde nosotros querramos
            dtm.insertRow(filaSeleccionada + 1, new Object[]{null, null, null, null, null, null});
            tblprov.clearSelection();
            tblprov.setModel(dtm);
            i++;

        } else {
            //addrow agrega una fila al jtable
            dtm.addRow(new Object[]{null, null, null, null, null, null});
            tblprov.setModel(dtm);
            k++;

        }
    }
//Moifica proveedor

    public void modProve(JTable tbl) {
        int res = showConfirmDialog(null, "¿Esta seguro que desea modificar la tabla?", "Confirmación", WARNING_MESSAGE);
        if (res == YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) tbl.getModel();
            int ro = model.getRowCount();
            int j = 0;
            int id;
            int dni;
            String nom;
            String ape;
            long tel;
            String email;
            String dire;

            while (j < ro) {
                id = (int) model.getValueAt(j, 0);//Id de provee
                dni = (int) model.getValueAt(j, 1);//dni provee
                nom = (String) model.getValueAt(j, 2);//nombre provee               
                ape = (String) model.getValueAt(j, 3);// apellido provee
                tel = valueOf(model.getValueAt(j, 4).toString());// telefono provee
                email = (String) model.getValueAt(j, 5);// email provee
                dire = (String) model.getValueAt(j, 6);//Dirección provee
                ConsultaProveedor con = new ConsultaProveedor();
                con.modProvee(id, dni, nom, ape, tel, email, dire);
                out.println(tel);
                j++;
            }
            Funciones fn = new Funciones();
            fn.refrescarTbl(tbl);
            showMessageDialog(null, "Los registros se modificaron correctamente", "Mensaje", INFORMATION_MESSAGE);

        } else {
            showMessageDialog(null, "No se modifico ningun dato", "Mensaje", INFORMATION_MESSAGE);
        }
    }
}
