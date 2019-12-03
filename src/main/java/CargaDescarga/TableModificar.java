/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDescarga;

import FNC.Paginado;
import static java.lang.Integer.parseInt;
import javax.swing.JComboBox;
import javax.swing.JList;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;

/**
 *
 * @author Valentin
 */
public class TableModificar {

    //Recibo los datos de los jtable y la jlist para modificarlos y enviarlos a la consulta con el id del pedido
    public void modificar(JTable tb, JList datosProveedor) {
        int a = tb.getSelectedRow();//Tomo que ha seleccionado una fila
        int b = tb.getRowCount();//Cuento la cantidad de filas que posee el jtable
        boolean selected = false;//Para verificar fila
        ConsultasCargaPedido cp = new ConsultasCargaPedido();//Creo objeto consultas carga pedidos       
        int idPedido = 0;
        boolean altaBajaPedido = false;
        //Verifico que selecciono una fila
        if (a == -1) {
            showMessageDialog(null, "Debe seleccionar una fila!", "ERROR", ERROR_MESSAGE);
        } else {
            //Recorro con el for fila por fila para revisar cual se selecciono
            for (int i = 0; i < b; i++) {
                //paso a true selected si se toma una fila
                selected = tb.isRowSelected(i);
                if (selected == true) {
                    //instancio el id del pedido que se selecciono para poder pasarlo a una consulta y rellenar la lista de proveedor
                    idPedido = parseInt(tb.getValueAt(i, 0).toString());
                    altaBajaPedido = (boolean) tb.getValueAt(i, 2);
                    cp.cargaDatosProveedor(altaBajaPedido, idPedido, datosProveedor);//paso parametros al metodo del objeto

                }
            }

        }
    }

    //Modificar jtable detalle pedido con combobox
    public void modificarJTableDetallePedido(JTable pedido, JTable detallePedido, JComboBox cmbDetallePedido) {
        int a = pedido.getSelectedRow();//Tomo que ha seleccionado una fila
        int b = pedido.getRowCount();//Cuento la cantidad de filas que posee el jtable
        boolean selected = false;//Para verificar fila
        int idPedido = 0;//Instanciare en esta variable el id pedido
        boolean altaBajaPedido = false;//Instanciare si el pedido fue cargado o no en boolean
        String sqll = null; //String que contendra el sql para instanciar el cmb
        int[] result = {0, 0};

        ConsultasCargaPedido cp = new ConsultasCargaPedido();//Creo objeto consultas carga pedidos
        Paginado p = new Paginado();

        //Verifico que se haya seleccionado una fila
        if (a == -1) {
            showMessageDialog(null, "Debe seleccionar una fila!", "ERROR", ERROR_MESSAGE);
        } else {
            //Recorro las filas para guardar la que se selecciono
            for (int i = 0; i < b; i++) {
                //isRowSelected(i); asigna true la fila que se ha seleccionado y pasamos al if
                selected = pedido.isRowSelected(i);
                if (selected == true) {
                    //instancio el id del pedido que se selecciono para poder pasarlo a una consulta y rellenar la lista de proveedor
                    idPedido = parseInt(pedido.getValueAt(i, 0).toString());
                    altaBajaPedido = (boolean) pedido.getValueAt(i, 2);//Paso si se dio de baja o no el pedido

                    //Creo sql para contar registros y asignarlos al cmb
                    sqll = "SELECT count(*) AS total FROM detallepedido WHERE IDPedido = '" + idPedido + "'";
                    //Paso el cmb y el sqll
                    p.contarReg(cmbDetallePedido, sqll);
                    //Paso el cmb para instanciar pagina a result
                    result = p.cmbDePaginas(cmbDetallePedido);
                    //paso por parametros los valores necesarios para la consulta y la genero en cargar detalle
                    cp.cargarDetallePedido(idPedido, detallePedido, result, altaBajaPedido);

                }
            }
        }
    }
}
