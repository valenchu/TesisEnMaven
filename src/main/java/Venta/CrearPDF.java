/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venta;

import static Venta.Vta.IVAS;
import static Venta.Vta.tdescuento;
import static Venta.Vta.txtSubTotal;
import static Venta.Vta.txtTotal;
import com.itextpdf.text.*;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_LEFT;
import static com.itextpdf.text.Element.ALIGN_RIGHT;
import static com.itextpdf.text.Font.BOLD;
import static com.itextpdf.text.Font.FontFamily.HELVETICA;
import static com.itextpdf.text.Font.NORMAL;
import static com.itextpdf.text.Font.UNDERLINE;
import static com.itextpdf.text.Image.getInstance;
import static com.itextpdf.text.PageSize.A4;
import com.itextpdf.text.pdf.*;
import static com.itextpdf.text.pdf.PdfWriter.getInstance;
import java.awt.print.PrinterException;
import java.io.*;
import static java.lang.System.out;
import javax.swing.JTable;

/**
 *
 * @author Valentin
 */
public class CrearPDF {

    //Creo variables de fondos
    private static final Font chapterFont = new Font(HELVETICA, 24, UNDERLINE);
    private static final Font paragraphFont = new Font(HELVETICA, 13, BOLD);
    private static final Font cyanColor = new Font(HELVETICA //Fuente
    ,
             10//Tamaño
            , NORMAL //Estilo
    );
    private static final Font smallBold = new Font(HELVETICA, 10, BOLD);
    private static final Font letFil = new Font(HELVETICA, 10, NORMAL);
    //Variable que inserta una imagen en el pdf
    private static final String ImagenTest = "D:\\Cosas - Valen\\Datos Valen facu\\Final Carrera\\Tesis\\tesis\\src\\main\\resources\\Logo58x125.png";

    /*
    * Creamos un documento PDF con iText usando diferentes elementos para aprender 
    * a usar esta librería.
     */
    //constructor vacío
    public CrearPDF() {

    }

    public void createPDF(File nuevoArchivoPDF, JTable tbve, String idVentas) throws PrinterException {
        out.println(tdescuento + "   " + IVAS);
        String descuento = tdescuento + "%";//Varable donde guardare el decuento para mostrarlo en el comprobante
        String subtotal = txtSubTotal.getText() + "$";
        String iva = IVAS;
        String total = txtTotal.getText() + "$";
//Creamos documento e indicamos nombre del fichero
        try {
            Document document = new Document(A4, 36, 36, 54, 36);

            try {
                getInstance(document, new FileOutputStream(nuevoArchivoPDF));
            } catch (FileNotFoundException ex) {
                out.println("No se encontró el fichero para generar el pdf " + ex);
            }
            document.open();

            //Rellenamos con codigo para generar el pdf
            // Añadimos los metadatos del PDF
            document.addTitle("Exportamos la tabla a PDF");
            document.addSubject("usando iText");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("Valentín");
            document.addCreator("Valentín");

            // Primera página 
            //Paragraph son los parrafos 
            Paragraph idVenta = new Paragraph("ID Venta N°: " + idVentas, paragraphFont);
            FncFecha fn = new FncFecha();
            Paragraph fecha = new Paragraph("Fecha venta: " + fn.fechaTodo(), paragraphFont);
            fecha.setAlignment(ALIGN_LEFT);
            fecha.setSpacingAfter(10);
            fecha.setIndentationLeft(51);
            idVenta.setAlignment(ALIGN_LEFT);
            idVenta.setSpacingAfter(10);//aplica el espacio de abajo y arriba
            idVenta.setIndentationLeft(51);//Dejamos espacio desde la izq tamb se puede de la derecha
            Paragraph titulo = new Paragraph("COMPROBANTE", chapterFont);
            titulo.setAlignment(ALIGN_CENTER);
            // Creeamos el primer capítulo
            Chapter chapter = new ChapterAutoNumber(titulo);//Chapter son las hojas del pdf
            // Añadimos una imagen
            Image image = null;
            try {
                /*
                LOS SIGUIENTES TEXTOS DETERMINAN EL ESCALADO DE LA IMAGEN EN EL PDF
              --scaleAbsoluteWidth(): Recibe un float donde el mismo es el tamaño de ancho
                horizontal asignado a la imagen.   (Hablando de porcentaje)
              --scaleAbsoluteHeight(): Recibe un float donde el mismo es el tamaño de alto 
                vertical asignado a la imagen.  (Hablando de porcentaje)
              --scalePercent(): Recibe un float donde el mismo es el porcentaje en el cual
                aplicara el ancho y alto de la imagen.
              --scaleToFit(): Recibe dos float, definir el máximo de las dimensiones 
                de la imagen. Si la relación de anchura / altura difiere de la relación de aspecto de la 
                imagen, ya sea el ancho o la altura, será menor que el
                correspondiente parámetro de este método.
              --scaleAbsolute: Tenemos 2 firmas una que recibe un solo float y otra que
                recibe dos float, escalar la imagen a un ancho absoluto y una altura absoluta.
                
                 */
                image = getInstance(ImagenTest);
                image.scalePercent(50f);
                image.setAlignment(ALIGN_CENTER);
                image.setSpacingBefore(10);
            } catch (BadElementException ex) {
                out.println("Image BadElementException" + ex);
            } catch (IOException ex) {
                out.println("Image IOException " + ex);
            }
            //Creamos tabla
            PdfPTable tabla = new PdfPTable(4);//Creamos tabla y le pasamos numero de columnas
            float[] medidasColumnas = {30, 12, 8, 10};//Creo float con las medidas de cada columna

            tabla.setWidths(medidasColumnas);//Agrego las medidas de cada columna a la tabla
            tabla.setHorizontalAlignment(ALIGN_CENTER);//alineo la tabla a la izquierda
            //Creamos para llenar nombres columnas
            PdfPCell cabecera;

            int cantCabeceras = 0;
            int cantFilas = 0;
            cantCabeceras = tbve.getColumnCount();
            out.println("Total de cabeceras = " + cantCabeceras);
            out.println("Total de filas = " + cantFilas);
            int[] cabeceras = {1, 2, 4, 5};
            cantFilas = tbve.getRowCount();
            //Rellenamos las cabeceras de las columnas de la tabla
            for (int colum = 0; colum < cabeceras.length; colum++) {
                cabecera = new PdfPCell(new Phrase(tbve.getColumnName(cabeceras[colum]).toString(), paragraphFont));//Obtengo las cabeceras
                cabecera.setHorizontalAlignment(ALIGN_LEFT);//posiciono cabeceras horizontalmente y centradas

                tabla.addCell(cabecera);//agrego cabeceras
            }

            //Creamos para llenar las filas
            tabla.setHeaderRows(1);
            for (int row = 0; row < cantFilas; row++) {
                for (int colum = 0; colum < cabeceras.length; colum++) {
                    //Hago if para tomar la columna de oferta y insertarle el simbolo %
                    if (cabeceras[colum] == cabeceras[2]) {
                        tabla.addCell(new Phrase(tbve.getValueAt(row, cabeceras[colum]).toString() + "%", letFil));
                    } else {
                        tabla.addCell(new Phrase(tbve.getValueAt(row, cabeceras[colum]).toString(), letFil));
                    }

                }
            }
            //Agrego las celdas finales a la tabla
            Phrase subT = new Phrase(subtotal, smallBold);
            Phrase subT1 = new Phrase("SUBTOTAL = ", cyanColor);
            Phrase subD = new Phrase(descuento, smallBold);
            Phrase subD1 = new Phrase("DESCUENTO = ", cyanColor);
            Phrase subC = new Phrase(total, smallBold);
            Phrase subC1 = new Phrase("TOTAL " + iva + " = ", cyanColor);
            PdfPCell interlineado = new PdfPCell(new Paragraph());
            interlineado.setColspan(4);
            tabla.addCell(interlineado);
            //creo array multiple para gregar datos a la tabla
            PdfPCell[][] finales = new PdfPCell[2][3];

            Phrase[] celda = {subT1, subD1, subC1};//Titulos
            Phrase[] celda2 = {subT, subD, subC};//Resultado cuenta
            //For para agregar informacion celdas
            for (int i = 0; i < celda.length; i++) {
                finales[0][i] = new PdfPCell(celda[i]);
                finales[1][i] = new PdfPCell(celda2[i]);
                finales[1][i].setHorizontalAlignment(ALIGN_RIGHT);//Alinea los resultado a la derecha
            }
            out.println(finales.toString());
            //for para acomodar las celdas en la tabla
            for (int i = 0; i < 3; i++) {
                finales[0][i].setColspan(2);
                tabla.addCell(finales[0][i]);

                finales[1][i].setColspan(2);
                tabla.addCell(finales[1][i]);

            }
            chapter.setNumberDepth(0); //.setNumberDepth(0); hace que no se le coloque numeracion
            chapter.add(image);
            chapter.add(idVenta);
            chapter.add(fecha);
            chapter.add(tabla);
            document.add(chapter);

            // Segunda página - Algunos elementos
            document.close();
            out.println("Se ha generado correctamente su hoja pdf!");
        } catch (DocumentException exx) {
            out.println("Se ha producido un error al generar un documento " + exx);
        }
    }
}
