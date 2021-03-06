/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FNC;


import Stock.Stok;
import coneccion.ConexionDB;
import static java.lang.Double.parseDouble;
import static java.lang.Math.ceil;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Valentin
 */
public class Paginado {

    //Calcular numero de paginas
    public double calcularNumPag(double num) {//num es el numero que recibo para dividirlo en la 
        //cantidad de paginas

        //Creo variable double
        double numDividido = 0.0;
        //Divido el numero recibido de registros por 30 que es la cantidad a mostrar por pag
        numDividido = (num / 30);
       System.out.println("numero dividido 30 = " + numDividido);
        //redondeo numero de paginas hacia arriba
        num = ceil(numDividido);
        return num;
    }

    //Dividir paginas para instanciar cmb
    public void cmbPaginas(JComboBox pag, int numPag) {//pag es el cmb que recibo para instanciarle
        //las paginas junto con el numero de paginas que es numPag
        int result;
        int cantidad = pag.getItemCount();
        if(cantidad > 0){
            pag.removeAllItems();
        }
        
        //Asigno numero de paginas al cmb con un for
        for (int i = 0; i < numPag; i++) {
            pag.addItem(i + 1);
            result = (i + 1);
        }
        
    }
    //Cuento los registros y le asigno numero de pagina al cmb
//Proceso principal
    public void contarReg(JComboBox cmb, String sqlNumReg) {
      
        Double count = 0.0;//Variable que guarda numero de registro consulta
        Double cantReg = 0.0;//Variable que se le instancia la cantidad de registros para calcularlo
        //y dar la cantidad de paginas
        Integer cantPag = 0;//Variable donde se almacena el numero final de pagina

        count = contarRegSql(sqlNumReg);// Metodo donde se le pasa el SQL para contar reg.
        if (count != 0) {
            //Asigno cant de reg
            cantReg = count;
            //Calculo pagina y asigno numero de pag.
            cantPag = (int) calcularNumPag(cantReg);
            System.out.println("La cantidad de registro de la consulta movimientos son = " + cantReg);
            System.out.println("La cantidad de paginas a poner en el cmb es de = " + cantPag);
            //Paso cmb y numero de pagina para borrar los datos del cmb y intanciarlo con el
            //numero de pag.
          
            cmb.setVisible(true);
            cmbPaginas(cmb, cantPag);

        }else{
           // JOptionPane.showMessageDialog(null, "No se encontraron registros en la BD", "ERROR", JOptionPane.ERROR_MESSAGE);
            
            cmb.removeAllItems();
            
            cmb.addItem(1);
            
            
        }
    }
    //Contar registro con lista
    public void contarRegLista(JList el, String sql){
        double regTotales = 0;
        int cantDePag = 0;
        double contarReg = 0;
        
        contarReg = contarRegSql(sql);
        if(contarReg > 0){
            //Asigno cant de reg
            regTotales = contarReg;       
            //Calculo pagina y asigno numero de pag.
            cantDePag = (int) calcularNumPag(regTotales);
            System.out.println("La cantidad de registro de la consulta movimientos son = " + regTotales);
            System.out.println("La cantidad de paginas a poner en el cmb es de = " + cantDePag);
            listar(el, cantDePag);
            
        }
    }
    public void listar(JList l , int numPag){
        l.removeAll();
        
        DefaultListModel m = new DefaultListModel();
        for(int i = 0; i < numPag; i++){
            m.addElement(i+1);
        }
        
        l.setModel(m);
        
    }
    
        //Cuenta registros tabla

    public double contarRegSql(String countSql) {
         double numReg = 0;
        if(!countSql.isEmpty()){
        //Creo objeto bd
        ConexionDB con = new ConexionDB();
       
        try {
            //Conecto bd
            Connection cn = con.getConnection();
            //Prearo el sql
            PreparedStatement st = cn.prepareStatement(countSql);
            System.out.println("SQL= " + countSql);
            //Asigno el sql a resulset para trabajar con el 
            ResultSet rs = st.executeQuery();
            //repaso los registros con next()
            if (rs.next()) {
                //Asigno numero de reg
                numReg = parseDouble(rs.getString("total"));//sql ejemplo SELECT count(*) AS total FROM tabla
            }                                                    //Tengo que poner AS total si o si
            

            System.out.println("Numero de registros " + numReg);
        } catch (SQLException ex) {
            getLogger(ConexionDB.class.getName()).log(SEVERE, null, ex);
        }
        //Retorno la cantidad de reg
        
        }
        return numReg;
    }
    public boolean btnS(JList l){
        DefaultListModel lis = (DefaultListModel)l.getModel();
        int tamanoLista = lis.getSize();
        boolean set= false;
        int primero = l.getFirstVisibleIndex();
        int indice = 0;
        if(tamanoLista != -1){
            indice = l.getSelectedIndex();
            if(indice == -1){
                indice = 0;
                
            }else if(indice < tamanoLista){
                    indice++;
                }else if (indice+1 == tamanoLista){
                    return set;
                }
        }
        l.setSelectedIndex(indice);
        return true;
    }

    //Contar los registros pero pasando el numero de registro no sql
    public void contarRegSinSql(JComboBox cmb, double numreg) {
        Double count = 0.0;//Variable que guarda numero de registro consulta
        Double cantReg = 0.0;//Variable que se le instancia la cantidad de registros para calcularlo
        //y dar la cantidad de paginas
        Integer cantPag = 0;//Variable donde se almacena el numero final de pagina

        count = numreg;// Metodo donde se le pasa el numero de reg
        if (count != 0) {
            //Asigno cant de reg
            cantReg = count;
            //Calculo pagina y asigno numero de pag.
            cantPag = (int) calcularNumPag(cantReg);
            System.out.println("La cantidad de registro de la consulta movimientos son = " + cantReg);
            System.out.println("La cantidad de paginas a poner en el cmb es de = " + cantPag);
            //Paso cmb y numero de pagina para borrar los datos del cmb y intanciarlo con el
            //numero de pag.
            cmbPaginas(cmb, cantPag);

        }else{
            System.out.println("No se encontraron registros de la consulta");
        }
            
    }

    //Metodo que toma el numero de cmb y lo multiplica por 30 para lograr consulta con limites
    //y así paginar un jtable
    public int[] dividorPag(int[] resultado) {//La variable resultado determina el rango de paginacion
        int cantDeRegistros = 30;
        int C = 0;
        int[] vuelto = new int[2];

        if (resultado[1] == 1) {
            /*
        Si resultado es =  a 1 lo multiplico por 30 y instancio que tome desde el registro 0 hasta
        un maximo de 30 registros de la BD
             */
            vuelto[1] = resultado[1] * cantDeRegistros;
            vuelto[0] = C;

        } else if (resultado[1] > 1) {
            /*Si el resultado es distinto de 1 instancio en vuelto[0] la multiplicacion de la pagina
            menos 30 para dar como base , para que tome los registro y a vuelto[1] le instancio la 
            cantidad maxima de registros a mostrar en el jtable que sera siempre de 30. Osea que si
            vuelto[0] que es 30 lo multiplico por 2 da 60 a esos 60 le resto 30 y queda 30 tonces tomo desde
            el registro 30 hasta el registro 60 ya que va de 30 en 30*/
            vuelto[0] = (resultado[1] * cantDeRegistros) - cantDeRegistros;
            vuelto[1] = cantDeRegistros;
        }else if(resultado[0] == 0){
            vuelto[1] = 0;
                    vuelto[0]= 0;
        }
        return vuelto;
    }

    //calcula las paginas seleccionadas para establecer un limitador usa cmb
    public int[] cmbDePaginas(JComboBox cmbPaginas) {
      
        int[] resultado = {0, 0};
        //Verifico que el combo no este null para poder asignar paginas
        if (cmbPaginas.getSelectedItem() != null && !cmbPaginas.getSize().equals(0)) {

            //Variable res guarda la pagina seleccionada en el combobox
            int res = cmbPaginas.getSelectedIndex();

            resultado[1] = res + 1;

            //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
            // y un top de cantidad de registros a traer q es 30 siempre
            resultado = dividorPag(resultado);
        }
        return resultado;
    }
    
        //calcula las paginas seleccionadas para establecer un limitador usa cmb
    public int[] jlistDePaginas(JList Paginas) {
      DefaultListModel l = (DefaultListModel)Paginas.getModel();
        int[] resultado = {0, 0};
        //Verifico que el combo no este null para poder asignar paginas
        if (Paginas.getSelectedIndex()+1 != -1 && l.getSize() != 0) {
            
            
            //Variable res guarda la pagina seleccionada en el combobox
            int res = Paginas.getSelectedIndex();
            System.out.println("Pag selec  "+res);

            resultado[1] = res + 1;
            System.out.println("Valore res "+resultado[1]);
            //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
            // y un top de cantidad de registros a traer q es 30 siempre
            resultado = dividorPag(resultado);
        }
        return resultado;
    }
    //Metodo boton current
     public int[] btnCurrent(JList Paginas) {
      DefaultListModel l = (DefaultListModel)Paginas.getModel();
        int[] resultado = {0, 0};
        //Verifico que el combo no este null para poder asignar paginas
        if (Paginas.getSelectedIndex()+1 != -1 && l.getSize() != 0) {
            
            
            //Variable res guarda la pagina seleccionada en el combobox
            int res = Paginas.getSelectedIndex()+1;
            System.out.println("Pag selec  "+res);

            resultado[1] = res + 1;
            System.out.println("Valore res "+resultado[1]);
            //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
            // y un top de cantidad de registros a traer q es 30 siempre
            resultado = dividorPag(resultado);
        }
        return resultado;
    }
    //Metodo boton sig lista
    public int[] btnSigLista(JList l){
        int[]resultado = {0,0};
        DefaultListModel mo = (DefaultListModel) l.getModel();
        int indice =  l.getSelectedIndex()+1;
        System.out.println("INDICE "+indice);
        if(l.getSelectedIndex()+1 != -1){
            int pagSelected = l.getSelectedIndex()+1;
            int ress =pagSelected+1;
            System.out.println("INDICE "+indice);
            int tamanoLista = mo.getSize();
            if (tamanoLista > ress){
                resultado[1] = ress+1;
                l.setSelectedIndex(resultado[1]);
                resultado = dividorPag(resultado);
                
            }else if(tamanoLista == ress){
           // resultado[1]=ress;
            //l.setSelectedIndex(resultado[1]);
            //resultado= dividorPag(resultado);
                System.out.println("NO HAY MAS REGISTROS");
        }
        }
        return resultado;
    }


    //calcula las paginas seleccionadas para establecer un limitador usa pagina siguiente
    public int[] btnPagSiguiente(JComboBox cmbPaginas) {
        int[] resultado = {0, 0};
        //Verifico que el combo no este null para poder asignar paginas
        if (cmbPaginas.getSelectedItem().equals(null)) {
        } else {
            //Variable res guarda la pagina seleccionada en el combobox
            int res = cmbPaginas.getSelectedIndex();
            int ress = res + 1;
            //Instancio el numero de items que posee el cmb en numItem
            int numItem = cmbPaginas.getItemCount();
            //Creo un if para verificar que aya una pagina siguiente y que no ejecute comando de mas
            if (numItem > ress) {
                //Guardo en resultado el numero + 1 ya que es la pagina siguiente
                resultado[1] = ress + 1;
                //Le instancio al cmb el numero siguiente para que se vea en este 
                cmbPaginas.setSelectedItem(resultado[1]);
                //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
                // y un top de cantidad de registros a traer q es 30 siempre
                resultado = dividorPag(resultado);
            } else if (numItem == ress) {
                //Guardo en resultado el numero  ya que es la pagina siguiente
                resultado[1] = ress;
                //Le instancio al cmb el numero siguiente para que se vea en este 
                cmbPaginas.setSelectedItem(resultado[1]);
                //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
                // y un top de cantidad de registros a traer q es 30 siempre
                resultado = dividorPag(resultado);
            }
        }
        return resultado;
    }
    //calcula las paginas seleccionadas para establecer un limitador usa pagina anterior

    public int[] btnPaginaAnterior(JComboBox cmbPaginas) {
        int[] resultado = {0, 0};
        //Verifico que el combo no este null para poder asignar paginas
        if (cmbPaginas.getSelectedItem().equals(null)) {
        } else {
            //Variable res guarda la pagina seleccionada en el combobox
            int res = cmbPaginas.getSelectedIndex();
            res = res + 1;
            // Creo if para verificar que hay una pagina para restar
            if (res > 1) {
                //Guardo en resultado el numero - 1 ya que es la pagina anterior
                resultado[1] = res - 1;
                //Le instancio al cmb el numero anterior para que se vea en este 
                cmbPaginas.setSelectedItem(resultado[1]);
                //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
                // y un top de cantidad de registros a traer q es 30 siempre
                resultado = dividorPag(resultado);
            } else if (res == 1) {
                //Guardo en resultado el numero - 1 ya que es la pagina anterior
                resultado[1] = res;
                //Le instancio al cmb el numero anterior para que se vea en este 
                cmbPaginas.setSelectedItem(resultado[1]);
                //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
                // y un top de cantidad de registros a traer q es 30 siempre
                resultado = dividorPag(resultado);
            }
        }
        return resultado;
    }

    //instanciar jlabel con las paginas
    public void setJlabelPag(JLabel pag, JComboBox cmbPag) {
       
        
        //instancio en n el numero de paginas totales que posee el cmb
        Integer n = cmbPag.getItemCount();
    
        //mando al jlabel el numero de paginas del cmb
        
        pag.setText(n.toString());
         
    }

}
