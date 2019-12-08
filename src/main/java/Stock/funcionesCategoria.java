/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stock;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class funcionesCategoria {
    
    //Metodo que pasa de la tabla a los textfield y jbuton los datos
    public void obtenerDatos(JTable tabla, JRadioButton A, JRadioButton B, JTextField txtCate, JTextField idCate){
        //Creo modelo tabla
        DefaultTableModel defaultModel = (DefaultTableModel) tabla.getModel();
        int idCat = 0, altaBaja = 0;
        String categoria;
        
        //Creo variable para verificar seleccion de fila
        int filaSeleccionada = tabla.getSelectedRow();
        //Verifico que selecciono fila y paso datos
        if(filaSeleccionada != -1){
       idCat = Integer.parseInt(defaultModel.getValueAt(filaSeleccionada, 0).toString());
       categoria = defaultModel.getValueAt(filaSeleccionada, 1).toString();
       altaBaja = Integer.parseInt(defaultModel.getValueAt(filaSeleccionada, 2).toString());
       txtCate.setText(categoria);
       idCate.setText(""+idCat);
       if(altaBaja == 0){
           B.doClick();
       }else{
           A.doClick();
       }
       tabla.setModel(defaultModel);
                }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
