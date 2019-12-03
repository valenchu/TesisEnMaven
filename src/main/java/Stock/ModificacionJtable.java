/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.*;

public class ModificacionJtable extends JFrame {

    public void modificartbl(JTable tbl) {

        String[] columnas = {"Descripción", "Cantidad", "PrecioS/IVA", "PrecioC/IVA", "Oferta"};

        //Recibo modelo de jtable y le paso los titulos indicados arriba
        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        dtm.setColumnIdentifiers(columnas);

        tbl.setModel(dtm);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(250);
    }
//Agrega fila a tabla

    public void agregarfila(JTable tblAgregarProd, int cmb) {
        int i = 0;
        int k = 0;
        DefaultTableModel dtm = (DefaultTableModel) tblAgregarProd.getModel();
        //getSelectedRow devuelve el numero de filas seleccionadas
        int filaSeleccionada = tblAgregarProd.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Selecciona
            while (i <= cmb) {
                //insertRow sirve para insertar fila en donde nosotros querramos
                dtm.insertRow(filaSeleccionada + 1, new Object[]{null, null, null, null, null});
                tblAgregarProd.clearSelection();
                tblAgregarProd.setModel(dtm);
                i++;

            }
        } else {
            while (k <= cmb) {
                dtm.addRow(new Object[]{null, null, null, null, null});
                tblAgregarProd.setModel(dtm);
                k++;
            }

        }
    }

    //Elimina fila tabla
    public void eliminarFilas(JTable tbl) {

        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        //getSelectedRow devuelve el numero de filas seleccionadas
        int filaSeleccionada = tbl.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Selecciona

            //insertRow sirve para insertar fila en donde nosotros querramos
            dtm.removeRow(filaSeleccionada);
            tbl.clearSelection();
            tbl.setModel(dtm);

        } else {
            dtm.removeRow(1);
        }
        tbl.setModel(dtm);
    }

    //Limpia todas las filas de la tabla
    public void limpiarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
            tabla.setModel(modelo);
        } catch (Exception e) {
            showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    //Elimina fila tabla

    public void eliminarFilasTabla(JTable tbl) {

        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
        //getSelectedRow devuelve el numero de filas seleccionadas
        int filaSeleccionada = tbl.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Selecciona

            //insertRow sirve para insertar fila en donde nosotros querramos
            dtm.removeRow(filaSeleccionada);
            tbl.clearSelection();
            tbl.setModel(dtm);

        } else {
            showMessageDialog(null, "Debe seleccionar una fila a eliminar", "ERROR", ERROR_MESSAGE);
        }

    }
}
