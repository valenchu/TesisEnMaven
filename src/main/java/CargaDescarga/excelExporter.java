/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDescarga;

import java.io.*;
import static java.lang.String.valueOf;
import java.util.List;
import javax.swing.JTable;
import jxl.*;
import static jxl.Workbook.createWorkbook;
import static jxl.format.Alignment.CENTRE;
import jxl.write.*;
import static jxl.write.WritableFont.ARIAL;
import static jxl.write.WritableFont.BOLD;

/**
 *
 * @author Valentin
 */
public class excelExporter {

    private File file;
    private List tabla;
    private List nomTabla;
    private JTable tb;
    private Proveedores p;
    private JTable j;
    private WritableSheet s;
    private String numPedido;

    public excelExporter(File file, List tabla, List nomTabla, Proveedores p, JTable tb, String numPedido) throws Exception {
        this.file = file;
        this.tabla = tabla;
        this.nomTabla = nomTabla;
        this.numPedido = numPedido;
        this.p = p;
        this.tb = tb;
        if (nomTabla.size() != tabla.size()) {
            throw new Exception("Error");
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
//Eporto el jtable y los datos del proeedor a excel !

    public boolean export() {
        try {
            //a,b son variables de control de modificacion de celdas !
            int a = 0;
            int b = 0;
            JTable table;
            String[] v = new String[]{"Descripción", "Cantidad"};
            /*La clase DataOutputStream es útil para escribir datos del tipo primitivo de una forma portable. 
            Esta clase tiene un sólo constructor que toma un objeto de la clase OutputStream o 
            sus derivadas como parámetro.*/
            DataOutputStream salida;
            salida = new DataOutputStream(new FileOutputStream(file));
            //Representa nuestro archivo en excel y necesita un
            //OutputStream para saber donde va a colocar los datos 
            WritableWorkbook w = createWorkbook(salida);
            WorkbookSettings op = new WorkbookSettings();//creo modificador de opciones para modificar opciones
            //de agregado de Ñ y acentos al excel si problemas !
            op.setEncoding("iso-8859-1");
            //Creo formato letra ARIAL y negrita
            WritableFont cellFont = new WritableFont(ARIAL, 12);
            cellFont.setBoldStyle(BOLD);

            //Instancio el formato a la celda
            WritableCellFormat formateado1 = new WritableCellFormat(cellFont);
            //Alinio texto al centro
            formateado1.setAlignment(CENTRE);

            for (int in = 0; in < tabla.size(); in++) {

                table = (JTable) tabla.get(in);
                //Creo la hoja en el exel con w.createsheet
                this.s = w.createSheet((String) nomTabla.get(in), 0);
                //Creamos for para colocar cada dato en cada celda y columna del excel
                for (int i = 0; i < table.getColumnCount(); i++) {
                    //saco el nombre de las columnas
                    String nomColu = table.getColumnName(i);
                    s.addCell(new Label(i, 0, nomColu, formateado1));
                    for (int j = 0; j < table.getRowCount(); j++) {
                        //Creo objeto con el nombre de columnas y filas
                        Object ob = table.getValueAt(j, i);
                        //Instancio en el exel creado los datos de columnas y filas
                        Label label = new Label(i, j + 1, valueOf(ob));
                        s.addCell(label);
                        b = 1;
                    }
                }
            }
            //Instancio que tan ancho seran las columnas !
            s.setColumnView(0, 15);
            s.setColumnView(1, 50);
            s.setColumnView(2, 15);
            //Creo hoja para agregar productos nuevos
            this.s = w.createSheet("Agregar productos nuevos", 1);
            for (int j = 0; j < v.length; j++) {
                s.addCell(new Label(j, 0, v[j], formateado1));//Fila - Columna - Datos
            }
            s.setColumnView(0, 50);
            s.setColumnView(1, 15);

            //Creo formato para los datos de los proveedores
            cellFont = new WritableFont(ARIAL, 10, BOLD);
            WritableCellFormat formato = new WritableCellFormat(cellFont);
            String[] titulosDatos = {"Nombre: ", "Apellido: ", "DNI: ", "Teléfono: ", "Email: ", "Dirección: ", "Numero de pedido: "};
            String[] datosProveedor = {p.getNomb(), p.getApe(), valueOf(p.getDNI()), valueOf(p.getTel()), p.getEmail(), p.getDire(), numPedido};
            //Creo hoja donde se agregan los datos del proveedor 
            this.s = w.createSheet("Datos proveedor", 2);
            for (int i = 0; i < titulosDatos.length; i++) {

                this.s.addCell(new Label(0, i, titulosDatos[i], formato));
                this.s.addCell(new Label(1, i, datosProveedor[i]));
            }
            //Instancio que tan ancho sera la columna de datos proveedores
            s.setColumnView(0, 30);
            s.setColumnView(1, 50);
            //Como excel tiene muchas hojas esta crea o toma la hoja 
            //Coloca el nombre del "tab" y el indice del tab
            w.write();//Escribimos los datos en excel finalmente
            //Cerramos el WritableWorkbook y DataOutputStream 
            w.close();
            salida.close();
            //si todo sale bien salimos de aqui con un true            
            return true;

        } catch (IOException | WriteException ex) {
            ex.printStackTrace();
        }
        //Si llegamos hasta aqui algo salio mal amigo valentin.
        return false;
    }
    /*
    public boolean exporExecel2() {
        //Creo el libro de trabajo de excel con el que trabajare para exportar los datos
        XSSFWorkbook libro = new XSSFWorkbook();
        //Creo la hoja del libro exel y le paso por la variable nombreHoja su nombre
        XSSFSheet hoja = libro.createSheet("Pedido");
       try { for(int in = 0; in < tabla.size(); in ++){
           this.j = (JTable) tabla.get(in);
        
        
            XSSFRow fila = hoja.createRow(0);
            XSSFRow filas;

            for (int i = 0; i < this.j.getColumnCount(); i++) {

                String nombColumna = this.j.getColumnName(i);
                fila.createCell(i).setCellValue(nombColumna);
                for (int j = 0; j < this.j.getRowCount(); j++) {
                    filas = hoja.createRow(j + 1);
                    Object ob = this.j.getValueAt(j, i);
                    System.out.println(ob.toString());
                    filas.createCell(i).setCellValue(ob.toString());
                }
            }
        }
            libro.write(new FileOutputStream(file));
            System.out.println("Creado libro exelcon exito");
            return true;
        } catch (Exception e) {
            System.out.println("No se cre libro excel error " + e);
            return false;
        }
    }
     */
}
