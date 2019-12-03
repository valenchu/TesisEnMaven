/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FNC;

import CargaDescarga.Proveedores;
import CargaDescarga.excelExporter;
import Venta.CrearPDF;
import static java.awt.Desktop.getDesktop;
import java.awt.Dimension;
import java.awt.Image;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.print.PrinterException;
import java.io.File;
import static java.lang.ClassLoader.getSystemResource;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.regex.Pattern.compile;
import javax.swing.*;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Valentin
 */
public class Funciones {

    //Limpia los campos de jtextfield
    public void limpiar_texto(JPanel panel) {
        for (int i = 0; panel.getComponents().length > i; i++) {
            if (panel.getComponents()[i] instanceof JTextField) {
                ((JTextComponent) panel.getComponents()[i]).setText(null);
            } else if (panel.getComponents()[i] instanceof JPasswordField) {
                ((JTextComponent) panel.getComponents()[i]).setText(null);
            }
        }
    }

    public void cmbCambiarseleccion(JComboBox cmb) {
        cmb.setSelectedIndex(0);
        cmb.setSelectedItem(0);
    }

    //Ajustar pantalla automaticamente al 50%
    public void ajustarPantalla(Dimension pan) {

        pan.setSize(getDefaultToolkit().getScreenSize());

        float escalar = 1.0F; // una ventana al 50% del tamaño de la pantalla
        int ancho = (int) (getDefaultToolkit().getScreenSize().width * escalar);
        int alto = (int) (getDefaultToolkit().getScreenSize().height * escalar);
        pan.setSize(ancho, alto);

    }

    //A = Al textfield de imprimir pedidos
    //B = Al JTable de imprimir pedidos
    public void ingresarCantidad(int a, JTable pedidos) {
        int j = 0;//Contador para recorrer while
        int i = 0, l = 0;
        int columCant = 2;//Cambiar el valor para especificar la columna a la que se quiere modificar la cant
        DefaultTableModel tb = (DefaultTableModel) pedidos.getModel();

        i = tb.getRowCount();//Cant filas del JTable.
        //Verifico si la columna esta vacia inserto numero si no no inserto numero !
        while (j < i) {
            Integer cantidad = (Integer) tb.getValueAt(j, columCant);
            if ((cantidad == null) || (cantidad == 0)) {
                tb.setValueAt(a, j, columCant);
                j++;
                l++;
            } else {
                tb.setValueAt(cantidad, j, columCant);
                j += 1;
                out.println(j);
            }
        }
        if (l != 0) {
            showMessageDialog(null, "Ya cargo todas las filas con cantidades !", "IFORMACIÓN", INFORMATION_MESSAGE);
        }
        pedidos.setModel(tb);
    }

    //Refresco automaticamente jtable
    public void refrescarTbl(JTable bl1) {
        DefaultTableModel dtm = (DefaultTableModel) bl1.getModel();

        bl1.setVisible(false);
        bl1.setModel(dtm);
        dtm.fireTableDataChanged();
        bl1.setVisible(true);
    }

    public Image icono() {
        Image retValue = getDefaultToolkit().
                getImage(getSystemResource("Imagenes/LogoLibreria.png"));
        return retValue;
    }

    //Verifica si un string es numero o no
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

    /*
    //Imprimir JTABLE
    public void imprimirJTable(JTable jTable, String cabecera, String piePagina) {

        boolean fitWidth = true;
        boolean interactive = true;
        // Definimos el modo de impresión

        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH : JTable.PrintMode.NORMAL;
        try {
            // Imprimo la tabla           
            boolean complete = jTable.print(mode,
                    new MessageFormat(cabecera),
                    new MessageFormat(piePagina));
            if (complete == true) {
                // Mostramos el mensaje de impresión existosa
                JOptionPane.showMessageDialog(jTable,
                        "Impresión completa",
                        "Resultado de la impresión",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mostramos un mensaje indicando que la impresión fue cancelada                 
                JOptionPane.showMessageDialog(jTable,
                        "Impresión cancelada",
                        "Resultado de la impresión",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(jTable,
                    "Fallo de impresión: " + pe.getMessage(),
                    "Resultado de la impresión",
                    JOptionPane.ERROR_MESSAGE);
        }

    }*/
    //Metodo que coloco en el boton exportar para exportar a excel
    public void excelExp(JLabel exportar, JLabel numP, DefaultTableModel ta, Proveedores pv) {
        int contador = 0;
        JTable tab = new JTable();
        tab.setModel(ta);
        String numPedido = numP.getText();
        if (tab.getRowCount() > 0) {
            //JFileChooser es una clase java que nos permite mostrar fácilmente,
            //una ventana para la selección de un fichero.
            JFileChooser opcion = new JFileChooser();
            //El objeto FileNameExtensionFilter sirve para mostrar la extensiones de archivos
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo excel", "xls");
            //Luego le indicamos al JFileChooser el filtro que vamos a usar, 
            //esto con la instruccion opcion.setFileFilter(filtro);
            opcion.setFileFilter(filtro);
            //opcion.setDialogTitle Establece la cadena que va en la barra de título de la ventana JFileChooser.
            opcion.setDialogTitle("Guardar archivo");
            /*Determina si AccepAll FileFilter se utiliza como una opción disponible en la lista de filtros seleccionables. 
            Si es falso, el filtro de archivos AcceptAll se elimina de la lista de filtros de archivos disponibles. 
            Si es verdadero, 
            el filtro de archivos AcceptAll se convertirá en el filtro de archivos utilizado activamente.*/
            opcion.setAcceptAllFileFilterUsed(false);
            //si el dialogo a mostrar es nulo que ejecute la siguiente accion !
            if (opcion.showSaveDialog(null) == APPROVE_OPTION) {
                //Creo los arraylist que contendran la tabla y el nombre de esta
                List tb = new ArrayList();
                List nomb = new ArrayList();

                //Agrego la tabla y el nombre
                tb.add(tab);
                out.println(tb);

                out.println("");
                nomb.add("Pedido");
                //Guardo en un string la opcion seleccionada !
                String file = opcion.getSelectedFile().toString().concat(".xls");

                try {
                    excelExporter e = new excelExporter(new File(file), tb, nomb, pv, tab, numPedido);

                    if (e.export()) {
                        showMessageDialog(null, "Los datos fueron exportados a excel en el directorio seleccionado", "Mensaje de Informacion", INFORMATION_MESSAGE);
                        contador = parseInt(exportar.getText());
                        contador++;
                        exportar.setText(valueOf(contador));

                    }

                    getDesktop().open(e.getFile());

                } catch (Exception e) {
                    showMessageDialog(null, "Hubo un error " + e.getMessage(), " Error", ERROR_MESSAGE);
                }

            }
        }

    }
//Metodo que ejecutara el creado y guardado del pdf

    public void ejecutarPDF(JTable tblVta, String idVenta) {
        String nombFiltro = "";

        CrearPDF tes = new CrearPDF();

        //Creo el jfilechooser para guardar archivo en determinado lugar
        JFileChooser opcion = new JFileChooser();
        opcion.setSelectedFile(new File("Comprobante " + idVenta));// con setSelectedFile agrego un nombre predeterminado al archivo
        FileNameExtensionFilter filt = new FileNameExtensionFilter("Archivo pdf", ".pdf");//agrego qe extension poseera el archivo
        opcion.setFileFilter(filt);
        //Muestro la ventana con showsave dialog
        opcion.showSaveDialog(opcion);
        //Guardo la direccion en guar
        File guar = opcion.getSelectedFile();

        //Siguar es distinto de null ejecuto el siguiente codigo
        if (guar != null) {
            try {
                tes.createPDF(new File(guar + ".pdf"), tblVta, idVenta);
            } catch (PrinterException ex) {
                getLogger(Funciones.class.getName()).log(SEVERE, null, ex);
            }

        }
    }

    //Metodo para seleccionar entre los distintos tipos de creaciones de fechas 
    public String getDate(int tipoDeFecha) {//Debo devolver un valor numerico entre 1 y 5 para que
        //me devuelva el tipo de fecha que requiero.
        Date date = new Date();
        String devolucion = "";
        switch (tipoDeFecha) {
            case 1:

                DateFormat hora = new SimpleDateFormat("HH:mm:ss");
                devolucion = hora.format(date);
                break;
            case 2:

                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                devolucion = fecha.format(date);
                break;
            case 3:
                DateFormat fecha2 = new SimpleDateFormat("yyyy-LL-dd");
                devolucion = fecha2.format(date);
                break;
            case 4:
                DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                devolucion = fechaHora.format(date);

            case 5:
                DateFormat fechaHora2 = new SimpleDateFormat("yyyy-LL-dd HH:mm:ss");
                devolucion = fechaHora2.format(date);
                break;

        }
        return devolucion;

    }

    //paso caso y fecha para formatearla
    public String getDateformat(int tipoDeFecha, Date date) {//Debo devolver un valor numerico entre 1 y 5 para que
        //me devuelva el tipo de fecha que requiero.
        String devolucion = "";
        switch (tipoDeFecha) {
            case 1:

                DateFormat hora = new SimpleDateFormat("HH:mm:ss");
                devolucion = hora.format(date);
                break;
            case 2:

                DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                devolucion = fecha.format(date);
                break;
            case 3:
                DateFormat fecha2 = new SimpleDateFormat("yyyy-LL-dd");
                devolucion = fecha2.format(date);
                break;
            case 4:
                DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                devolucion = fechaHora.format(date);

            case 5:
                DateFormat fechaHora2 = new SimpleDateFormat("yyyy-LL-dd HH:mm:ss");
                devolucion = fechaHora2.format(date);
                break;

        }
        return devolucion;

    }

    //Validar un correo electronico
    public boolean validarCorreo(String correo) {
        // Patrón para validar el email
        Pattern pattern = compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        //Matcher: Un motor que realiza operaciones de coincidencia en 
        //una secuencia de caracteres interpretando a Pattern.
        Matcher mather = pattern.matcher(correo);
        boolean validar = false;
        if (mather.find() == true) {
            out.println("El email ingresado es válido.");
            validar=true;
        } else {
            validar=false;
            out.println("El email ingresado es inválido.");
        }

        return validar;
    }
   //Metodo para buscar direcccion de archivo
    public String buscarDir(String url){
        JFileChooser archivo = new JFileChooser();
        //Le indico que quiero ver tanto archivos como directorio en la ventana
        archivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //Le digo cual va a ser el filtro de archivo. Aunque el todos los archivos sigue activo por las dudas
        FileNameExtensionFilter filter = new FileNameExtensionFilter("mysqldump.exe & mysql", "exe");
        archivo.setFileFilter(filter);
        
        int resultado = archivo.showOpenDialog(archivo);
        if(resultado != JFileChooser.CANCEL_OPTION){
           File archivoURL1 = archivo.getSelectedFile();//me trae todo el directorio con el nombre del archivo
           File archivoURL2 =  archivo.getCurrentDirectory();//evita traerme el nombre del archivo incluido
            if((archivoURL1.equals(null))|| (archivoURL1.getName().equals("")) || (archivoURL2.equals(null)) || (archivoURL2.equals(""))){
                url = "...";
                return url;
            }else{
                url  = archivoURL1.getAbsolutePath();
                String url2 = archivoURL2.getAbsolutePath();
                System.out.println("mostrame url 2"+url2);
                System.out.println("mostrame fub url1 "+ url);
                return url;
            }
                
        }
        return "No encontre nada";
    }

    //Guardo los numeros recibidos a la hora de hacer un pedido en productos
    public Object guardarNumerosProductos(Object num) {

        return num;
    }
    


}
