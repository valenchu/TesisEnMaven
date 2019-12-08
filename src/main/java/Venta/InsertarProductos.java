/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venta;

import FNC.Paginado;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JTable;

/**
 *
 * @author Valentin
 */
public class InsertarProductos extends javax.swing.JFrame {

    /**
     * Creates new form InsertarProductos
     */
    //totalreset variable static que guarda el valor total para poder implementar descuentos
    //o movimientos extras en el total.
    public static double totalreset = 0;
    public static int llamadaV2 = 0;

    public JTable getTblInsertarProducto() {
        return tblInsertarProducto;
    }

    public void setTblInsertarProducto(JTable tblInsertarProducto) {
        this.tblInsertarProducto = tblInsertarProducto;
    }

    public void llamada() {
        ConsultasVentas cn = new ConsultasVentas();
        tblInsertarProducto.setVisible(false);
        Paginado p = new Paginado();
        int[] result = {0, 0};
        result = p.cmbDePaginas(cmbPag);
        if (llamadaV2 == 0) {
            ConsultasVentas con = new ConsultasVentas();
            con.rellenarStock(tblInsertarProducto, result);
        }
        tblInsertarProducto.setVisible(true);

    }

    public InsertarProductos() {
        initComponents();

    }
    private Set<Integer> pressed = new HashSet<>();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInsertarProducto = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();
        txtDato = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnEnviar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        cmbPag = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numeroDePag = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/Logo.png")).getImage());
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblInsertarProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID producto", "Descripción", "Cantidad", "Precio S/IVA", "Precio C/IVA", "Oferta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInsertarProducto.getTableHeader().setReorderingAllowed(false);
        tblInsertarProducto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblInsertarProductoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tblInsertarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblInsertarProductoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInsertarProducto);
        if (tblInsertarProducto.getColumnModel().getColumnCount() > 0) {
            tblInsertarProducto.getColumnModel().getColumn(0).setResizable(false);
            tblInsertarProducto.getColumnModel().getColumn(1).setResizable(false);
            tblInsertarProducto.getColumnModel().getColumn(1).setPreferredWidth(300);
            tblInsertarProducto.getColumnModel().getColumn(2).setResizable(false);
            tblInsertarProducto.getColumnModel().getColumn(3).setResizable(false);
            tblInsertarProducto.getColumnModel().getColumn(4).setResizable(false);
            tblInsertarProducto.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 108, 800, 466));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(408, 74, -1, -1));

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Descripción", "ID" }));
        cmbTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTipoKeyPressed(evt);
            }
        });
        jPanel1.add(cmbTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 75, -1, -1));

        txtDato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDatoKeyPressed(evt);
            }
        });
        jPanel1.add(txtDato, new org.netbeans.lib.awtextra.AbsoluteConstraints(208, 75, 190, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Filtrado por");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 53, 80, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Dato");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(209, 53, 190, -1));

        btnEnviar.setText("Enviar productos");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 74, -1, -1));

        btnRefrescar.setText("Refrescar stock");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });
        btnRefrescar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRefrescarKeyPressed(evt);
            }
        });
        jPanel1.add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 74, -1, -1));

        cmbPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPagActionPerformed(evt);
            }
        });
        jPanel1.add(cmbPag, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 580, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Pagina:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 582, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("de");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 582, -1, -1));

        numeroDePag.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        numeroDePag.setText("jLabel5");
        numeroDePag.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                numeroDePagAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.add(numeroDePag, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 582, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        Paginado p = new Paginado();
        String fil = cmbTipo.getSelectedItem().toString();
        String dato = txtDato.getText();
        System.out.println("Filtro = " + fil);
        String sqll = "";
        //Si utilizo switch puedo comparar si filtro es iguala null si no los traio por descripcion o id
        if (null == fil) {//Si el filtro es nulo estraigo toda la bd hasta 30 lineas
            sqll = "SELECT DISTINCT count(*) AS total,IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                    + " FROM productos WHERE altabajaproductos = 1 ORDER BY IDProducto ASC";
        } else {
            switch (fil) {
                case "ID":
                    //Filtro por id la busqueda
                    sqll = "SELECT DISTINCT count(*) AS total,IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                            + " FROM productos WHERE (IDProducto LIKE  '" + dato + "%') AND (altabajaproductos = 1)ORDER BY IDProducto ASC";
                    break;
                case "Descripción":
                    //Filtro por descripcion la busqueda
                    //count(*) AS total sirve para contar los registros de la busqueda
                    sqll = "SELECT DISTINCT count(*) AS total,IDProducto, Descripcio, PrecioSinIVA, PrecioConIVA, Cantidad, Oferta "
                            + " FROM productos WHERE (Descripcio LIKE  '" + dato + "%') AND (altabajaproductos = 1)ORDER BY IDProducto ASC";
                    break;
                default:
                    break;
            }
        }
        p.contarReg(cmbPag, sqll);
        this.llamadaV2 = 1;
        this.cmbPagActionPerformed(evt);
        //Hago tic de tiempo para tomar los parametros
        for (int i = 0; i < 90000; i++) {
        }
        //instancio en n el numero de paginas totales que posee el cmb
        Integer n = cmbPag.getItemCount();
        //mando al jlabel el numero de paginas del cmb
        numeroDePag.setText(n.toString());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1AncestorAdded

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        tblInsertarProducto.clearSelection();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tblInsertarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblInsertarProductoKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ESCAPE) {
            tblInsertarProducto.clearSelection();
        }
        char carr = (char) evt.getKeyCode();
        if (carr == KeyEvent.VK_ENTER) {

            btnEnviar.doClick();
        }
        char carrr = (char) evt.getKeyCode();
        boolean crt = evt.isControlDown();
        if (crt && (carrr == KeyEvent.VK_R)) {

            btnRefrescar.doClick();
        }
    }//GEN-LAST:event_tblInsertarProductoKeyPressed

    private void tblInsertarProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblInsertarProductoAncestorAdded
        // TODO add your handling code here:
        Paginado p = new Paginado();
        //Paso la consulta y el cmb por string para realizar el proceso de paginado del table
        //El resto funciona internamente en el metodo que esta en paginado en el paquete FNC
        String sqlNumReg = "SELECT count(*) AS total FROM productos";
        p.contarReg(cmbPag, sqlNumReg);

    }//GEN-LAST:event_tblInsertarProductoAncestorAdded

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
        ConsultasVentas cons = new ConsultasVentas();
        FncTablas fn = new FncTablas();
        fn.pasarDatos(tblInsertarProducto);
        //cons.buscarProductos(null, null, tblInsertarProducto);
        Vta.jButton4.doClick();
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void txtDatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDatoKeyPressed
        // TODO add your handling code here:
        char carr = (char) evt.getKeyCode();
        if (carr == KeyEvent.VK_ENTER) {

            btnBuscar.doClick();
        }
        char carrr = (char) evt.getKeyCode();
        boolean crt = evt.isControlDown();
        if (crt && (carrr == KeyEvent.VK_R)) {

            btnRefrescar.doClick();
        }
    }//GEN-LAST:event_txtDatoKeyPressed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO add your handling code here:
        //sirve para recargar jtable de vuelta en caso de cambios
        Paginado p = new Paginado();
        //Paso la consulta y el cmb por string para realizar el proceso de paginado del table
        //El resto funciona internamente en el metodo que esta en paginado en el paquete FNC
        String sqlNumReg = "SELECT count(*) AS total FROM productos";
        p.contarReg(cmbPag, sqlNumReg);
        this.llamadaV2 = 0;
        this.cmbPagActionPerformed(evt);
        //Hago tic de tiempo para tomar los parametros
        for (int i = 0; i < 90000; i++) {
        }
        //instancio en n el numero de paginas totales que posee el cmb
        Integer n = cmbPag.getItemCount();
        //mando al jlabel el numero de paginas del cmb
        numeroDePag.setText(n.toString());
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnRefrescarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRefrescarKeyPressed
        // TODO add your handling code here:

        char carr = (char) evt.getKeyCode();
        boolean crt = evt.isControlDown();
        if (crt && (carr == KeyEvent.VK_R)) {

            btnRefrescar.doClick();
        }
    }//GEN-LAST:event_btnRefrescarKeyPressed

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:
        char carr = (char) evt.getKeyCode();
        boolean crt = evt.isControlDown();
        if (crt && (carr == KeyEvent.VK_R)) {

            btnRefrescar.doClick();
        }
    }//GEN-LAST:event_jPanel1KeyPressed

    private void cmbTipoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoKeyPressed
        // TODO add your handling code here:
        char carr = (char) evt.getKeyCode();
        boolean crt = evt.isControlDown();
        if (crt && (carr == KeyEvent.VK_R)) {

            btnRefrescar.doClick();
        }
    }//GEN-LAST:event_cmbTipoKeyPressed

    private void cmbPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPagActionPerformed
        // TODO add your handling code here:
        Paginado p = new Paginado();
        //instancio result para determinar los limitadores de busqueda por pagina
        int[] result = {0, 0};
        //obtengo el numero de paginas y de donde hasta donde la limitara y la instancio en result
        result = p.cmbDePaginas(cmbPag);
        //dependiendo de la llamada ejecuta una u otra consulta !, en dicha consulta
        //paso el result para luego dividirlo en el vector 0 y el 1 para delimitar la busqueda !
        if (this.llamadaV2 == 0) {
            ConsultasVentas con = new ConsultasVentas();
            con.rellenarStock(tblInsertarProducto, result);
        } else if (this.llamadaV2 == 1) {
            ConsultasVentas con = new ConsultasVentas();
            String filtro = cmbTipo.getSelectedItem().toString();
            String dato = txtDato.getText();
            con.buscarProductos(filtro, dato, tblInsertarProducto, result);
        }
    }//GEN-LAST:event_cmbPagActionPerformed

    private void numeroDePagAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_numeroDePagAncestorAdded
        // TODO add your handling code here:
        //Hago tic de tiempo para tomar los parametros
        for (int i = 0; i < 90000; i++) {
        }
        //instancio en n el numero de paginas totales que posee el cmb
        Integer n = cmbPag.getItemCount();
        //mando al jlabel el numero de paginas del cmb
        numeroDePag.setText(n.toString());

    }//GEN-LAST:event_numeroDePagAncestorAdded

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox<String> cmbPag;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel numeroDePag;
    private javax.swing.JTable tblInsertarProducto;
    private javax.swing.JTextField txtDato;
    // End of variables declaration//GEN-END:variables
}
