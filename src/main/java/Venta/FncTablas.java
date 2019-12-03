/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venta;

import Stock.ModificacionJtable;
import static Venta.InsertarProductos.totalreset;
import static Venta.Vta.model2;
import static Venta.Vta.txtSubTotal;
import static Venta.Vta.txtTotal;
import java.awt.Frame;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Math.round;
import static java.lang.System.out;
import javax.swing.*;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;

public class FncTablas extends Frame {

    public void calcularTotal(double tbl, JTextField total) {
        //  int filasTabla = tbl.getRowCount();
        double suma = 0;
        out.println("suma es = " + suma);
        double datos = 0;
        out.println("datos es =  " + datos);
        datos = tbl + tbl;
        suma = datos;
        out.println("suma despues de suma con datos es = " + suma);
        //for (int i = 0; i < filasTabla; i++) {
        // datos = (int) tbl.getValueAt(i, 5);
        //  datos = ++datos;
        //}
        total.setText(Double.toString(suma));
    }
//Elimina filas tabla ventas

    public void eliminarfilas(JTable tblVenta, JTextField txtTotal) {
        DefaultTableModel mo = (DefaultTableModel) tblVenta.getModel();
        Double restarImporte = 0.0;
        Double totalTabla = parseDouble(txtTotal.getText());
        totalTabla = (double) round(totalTabla * 100) / 100;
        InsertarProductos in = new InsertarProductos();
        double tores = totalreset;
        int filaSeleccionada = tblVenta.getSelectedRow();
        if (filaSeleccionada >= 0) {
            //asigno el import que se le resta al eliminar una fila a restar importe
            restarImporte = (Double) tblVenta.getValueAt(filaSeleccionada, 5);
            //removo la fila
            mo.removeRow(filaSeleccionada);
            //borro la seleccion
            tblVenta.clearSelection();
            //instancio la tabla de nuevo
            tblVenta.setModel(mo);
            //le asigno el valor de resta a total en la tabla y a total reset para los descuentos
            totalTabla = (totalTabla - restarImporte);
            tores = (tores - restarImporte);
            //Uso (double) Math.round(variable aca * 100)/100; Para redondear y dejar 2 decimales
            totalreset = (double) round(tores * 100) / 100;
            totalTabla = (double) round(totalTabla * 100) / 100;
            txtTotal.setText(Double.toString(totalTabla));
        } else {
            showMessageDialog(null, "Seleccione una fila a eliminar", "Error", ERROR_MESSAGE);
        }
        int filastabla = tblVenta.getRowCount();
        if (filastabla == 0) {
            txtTotal.setText(Double.toString(0.0));
            totalreset = 0.0;
        }

    }

    //Limpio completamente la venta incluido pago y vuelto calculado
    public void limpiarTabla(JTable tabla, JTextField txtTotal, JTextField subTotal, JTextField pago, JTextField vuelto, JComboBox cmb) {

        int resp = showConfirmDialog(null, "¿Está seguro de querer eliminar el proceso de venta?", "PREGUNTA", YES_NO_OPTION);
        if (resp == YES_OPTION) {
            //Limpio la tabla
            ModificacionJtable md = new ModificacionJtable();
            md.limpiarTabla(tabla);
            //Reseteo todo de la tabla venta
            String to = txtTotal.getText();
            String subTo = subTotal.getText();
            subTo = "0.0";
            to = "0.0";
            txtTotal.setText(to);
            subTotal.setText(to);
            totalreset = 0.0;
            cmb.setSelectedIndex(0);
            pago.setText(null);
            vuelto.setText(null);

        } else {
            showMessageDialog(null, "Limpieza de tabla cancelada", "INFORMACIÓN", INFORMATION_MESSAGE);
        }

    }
        public void limpiarTabla2(JTable tabla, JTextField txtTotal, JTextField subTotal, JTextField pago, JTextField vuelto, JComboBox cmb) {
       
            //Limpio la tabla
            ModificacionJtable md = new ModificacionJtable();
            md.limpiarTabla(tabla);
            //Reseteo todo de la tabla venta
            String to = txtTotal.getText();
            String subTo = subTotal.getText();
            subTo = "0.0";
            to = "0.0";
            txtTotal.setText(to);
            subTotal.setText(to);
            totalreset = 0.0;
            cmb.setSelectedIndex(0);
            pago.setText(null);
            vuelto.setText(null);

    }


    //Paso datos a tablaventas
    public void pasarDatos(JTable tbl) {
        //Funcion que pasa datos a la tabla ventas
        int filaseleccionada = tbl.getSelectedRow();
        int contar = 0;
        //verifico que aya una fila marcada
        if (filaseleccionada >= 0) {
            Double impor = 0.0;
            Integer ofe = 0;
            String oferton = "0";
            Integer cant = 0;
            boolean band = false;
            //Paso los datos a variables
            Integer id = (Integer) tbl.getValueAt(filaseleccionada, 0);
            String desc = (String) tbl.getValueAt(filaseleccionada, 1);
            Integer cantTabla = (Integer) tbl.getValueAt(filaseleccionada, 2);
            try {
                String cantidad = showInputDialog(null, "Ingrese la cantidad de productos a vender", "Ingresar cantidad", INFORMATION_MESSAGE);

                cant = parseInt(cantidad);

                band = true;

                if (cant > cantTabla) {
                    showMessageDialog(null, "NO HAY SUFICIENTE STOK", "ERROR", ERROR_MESSAGE);
                    band = false;
                    contar = 1;
                }
//java.lang.NumberFormatException -> exepxion de que se a ingresado letras en lugar de numeros
// en la caja de texto del JOptionPane.
            } catch (java.lang.NumberFormatException exception) {

                band = false;
            }
            if (contar == 1) {

            } else {
                if (band == false) {
                    showMessageDialog(null, "Usted no ingreso un dato numérico para pasar la cantidad de productos.\nIntentelo de nuevo", "Error", ERROR_MESSAGE);
                }
            }
            Double pre = (Double) tbl.getValueAt(filaseleccionada, 3);
            if (band == true) {
                oferton = showInputDialog(null, "Ingrese oferta del producto.", "Ingresar cantidad", INFORMATION_MESSAGE);
                try {
                    if (oferton.isEmpty()) {

                        showMessageDialog(null, "El campo de oferta se dejo vacio lo que quedara igual a 0% de oferta", "Mensaje informativo", INFORMATION_MESSAGE);
                        oferton = "0";
                    }
                } catch (NullPointerException e) {
                    showMessageDialog(null, "El campo de oferta se dejo vacio lo que quedara igual a 0% de oferta", "Mensaje informativo", INFORMATION_MESSAGE);
                    oferton = "0";
                }
                out.println(oferton);
                ofe = parseInt(oferton);
            }

            if (band == true) {
                //Le resto la cantidad en la tabla
                //int resto = (cantTabla - cant);
                // DefaultTableModel m = (DefaultTableModel) tbl.getModel();
                //m.setValueAt(resto, filaseleccionada, 2);

                //Multiplico precio por cantidad y se lo asigno a impor
                impor = pre * cant;
                Double importefinal = impor - ((impor * ofe) / 100);
                Object[] data = {id, desc, cant, pre, ofe, importefinal};//Cargo vector con cada fila
                out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4] + " " + data[5]);
                //Inserto fila a model 2 que esta en vnta con los datos

                model2.addRow(data);
                //Uso Math.round(total * 100)/100; Para instanciar y redondear 2 decimales luego de la coma y no mas !
                importefinal = (double) round(importefinal * 100) / 100;
                //Tomo datos del txt total
                Double total = parseDouble(txtTotal.getText());
                //Le sumo importe de la tabla al total
                total += importefinal;
                totalreset += importefinal;
                total = (double) round(total * 100) / 100;
                totalreset = (double) round(totalreset * 100) / 100;
                //Envio el total definitivo al txtTotal que esta en la tabla ventas
                txtTotal.setText(Double.toString(totalreset));
                txtSubTotal.setText(Double.toString(totalreset));
            }
        } else {
            showMessageDialog(null, "Seleccione una fila del producto a cargar", "Error", ERROR_MESSAGE);

        }

    }

}
