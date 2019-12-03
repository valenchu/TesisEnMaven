/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venta;

import Bienvenido.Menu;
import FNC.ConsultasDePaginado;
import FNC.Funciones;
import FNC.Paginado;
import Stock.ModificacionJtable;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class Vta extends javax.swing.JFrame {

    //Instancio modelo         
    public static DefaultTableModel model2;
    //llamada variable cuya funcion es asignarle numero para el llamado de sentencia sql en el 
    //combobox y jtable
    public static int llamada = 0;
    //cmbDescTotal uso esta variable para manejar con el descuento del cmb y el descuento total
    public static Paginado p = new Paginado();
    private static double cmbDescTotal;
    public static double tdescuento = 0.0;
    public static String IVAS = "Sin IVA";
    public static int contadorTicket = 0;

    public Vta() {
        initComponents();

        //Creo un metodo escucha para cerrar ventana 
        //Le paso el modelo creado al modelo instanciado
        try {
            tblVenta.setModel(model2);
        } catch (java.lang.IllegalArgumentException exception) {
            model2 = (DefaultTableModel) tblVenta.getModel();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIDventa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnVuelto = new javax.swing.JButton();
        btnRehacer = new javax.swing.JButton();
        btnEliminarFila = new javax.swing.JButton();
        btnFinVenta = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        impPDF = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnBuscarProd = new javax.swing.JButton();
        txtDia = new javax.swing.JTextField();
        txtMes = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPagoUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtVuelto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        cmbIVA = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        txtBuscarUser = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dateFechaHasta = new com.toedter.calendar.JDateChooser();
        btnBuscar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnPagSig = new javax.swing.JButton();
        cmbPaginas = new javax.swing.JComboBox<>();
        pagAnt = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        dateFechaDesde = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        btnResetTbl = new javax.swing.JButton();
        borraTodoMovimiento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Venta");
        setIconImage(new ImageIcon(getClass().getResource("/Logo.png")).getImage());
        setMinimumSize(new java.awt.Dimension(800, 600));
        setSize(new java.awt.Dimension(800, 600));

        jTabbedPane1.setMinimumSize(new java.awt.Dimension(700, 500));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel2.setBackground(new java.awt.Color(100, 183, 100));
        jPanel2.setMinimumSize(new java.awt.Dimension(700, 500));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 800));
        this.pack();

        jPanel4.setBackground(new java.awt.Color(100, 183, 100));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(120, 419));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ID venta:");
        jLabel1.setAlignmentY(0.0F);

        txtIDventa.setEditable(false);
        txtIDventa.setBackground(new java.awt.Color(153, 153, 153));
        txtIDventa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtIDventa.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtIDventaAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("N°");

        btnVuelto.setText("Calcula vuelto");
        btnVuelto.setVisible(true);
        btnVuelto.setMaximumSize(new java.awt.Dimension(101, 20));
        btnVuelto.setMinimumSize(new java.awt.Dimension(101, 20));
        btnVuelto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVueltoActionPerformed(evt);
            }
        });

        btnRehacer.setVisible(true);
        btnRehacer.setText("Limpiar venta");
        btnRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerActionPerformed(evt);
            }
        });

        btnEliminarFila.setText("Eliminar fila");
        btnEliminarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFilaActionPerformed(evt);
            }
        });
        btnEliminarFila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEliminarFilaKeyPressed(evt);
            }
        });

        btnFinVenta.setText("Finalizar venta");
        btnFinVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinVentaActionPerformed(evt);
            }
        });

        jButton2.setText("Volver menu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        impPDF.setText("Comprobante vta");
        impPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impPDFActionPerformed(evt);
            }
        });

        jButton3.setText("Descuento al total");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(txtIDventa)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRehacer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarFila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFinVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(impPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(7, 14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(19, 19, 19)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarFila)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(btnRehacer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(impPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinVenta)
                .addGap(29, 29, 29))
        );

        jPanel5.setBackground(new java.awt.Color(100, 183, 100));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel5AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnBuscarProd.setText("Buscar producto");
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });

        txtDia.setEditable(false);
        txtDia.setBackground(new java.awt.Color(153, 153, 153));
        txtDia.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMes.setEditable(false);
        txtMes.setBackground(new java.awt.Color(153, 153, 153));
        txtMes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAno.setEditable(false);
        txtAno.setBackground(new java.awt.Color(153, 153, 153));
        txtAno.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fecha:");
        jLabel3.setToolTipText("");
        jLabel3.setAlignmentY(0.0F);

        txtPagoUser.setVisible(true);
        txtPagoUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPagoUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagoUserKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setVisible(true);
        jLabel6.setText("Paga con:");
        jLabel6.setToolTipText("");
        jLabel6.setAlignmentY(0.0F);

        txtVuelto.setEditable(false);
        txtVuelto.setBackground(new java.awt.Color(153, 153, 153));
        txtVuelto.setToolTipText("");
        txtVuelto.setVisible(true);
        txtVuelto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setVisible(true);
        jLabel7.setText("Vuelto:");
        jLabel7.setToolTipText("");
        jLabel7.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscarProd)
                .addGap(72, 72, 72)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtPagoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel6, jLabel7, txtPagoUser, txtVuelto});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPagoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarProd)
                .addContainerGap())
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel7, txtPagoUser, txtVuelto});

        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDProducto", "Detalle", "Cantidad", "PrecioS/IVA", "Oferta", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVenta.getTableHeader().setReorderingAllowed(false);
        tblVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblVentaMousePressed(evt);
            }
        });
        tblVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblVentaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblVenta);
        if (tblVenta.getColumnModel().getColumnCount() > 0) {
            tblVenta.getColumnModel().getColumn(0).setResizable(false);
            tblVenta.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblVenta.getColumnModel().getColumn(1).setResizable(false);
            tblVenta.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblVenta.getColumnModel().getColumn(2).setResizable(false);
            tblVenta.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblVenta.getColumnModel().getColumn(3).setResizable(false);
            tblVenta.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblVenta.getColumnModel().getColumn(4).setResizable(false);
            tblVenta.getColumnModel().getColumn(4).setPreferredWidth(12);
            tblVenta.getColumnModel().getColumn(5).setResizable(false);
            tblVenta.getColumnModel().getColumn(5).setPreferredWidth(30);
        }

        cmbIVA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0%", "21%", "10.5%" }));
        cmbIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIVAActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seleccione IVA:");
        jLabel4.setToolTipText("");
        jLabel4.setAlignmentY(0.0F);

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(153, 153, 153));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TOTAL:");
        jLabel5.setToolTipText("");
        jLabel5.setAlignmentY(0.0F);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Subtotal sin IVA");

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(153, 153, 153));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo58x125.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtSubTotal)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTotal)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                                        .addGap(41, 41, 41))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(10, 10, 10)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(cmbIVA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                .addContainerGap())
        );

        txtTotal.setText(Double.toString(0.0));
        txtSubTotal.setText(Double.toString(0.0));
        jButton4.setVisible(false);

        jTabbedPane1.addTab("Venta", jPanel2);

        jPanel3.setBackground(new java.awt.Color(100, 183, 100));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Fecha desde:");
        jLabel8.setToolTipText("");
        jLabel8.setAlignmentY(0.0F);

        btnBuscar.setText("Buscar por usuario");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDventa", "Usuario", "IDproducto", "Detalle", "Fecha", "Cant", "PreecioS/IVA", "Oferta", "Importe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovimientos.getTableHeader().setReorderingAllowed(false);
        tblMovimientos.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tblMovimientosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(tblMovimientos);
        if (tblMovimientos.getColumnModel().getColumnCount() > 0) {
            tblMovimientos.getColumnModel().getColumn(3).setPreferredWidth(260);
            tblMovimientos.getColumnModel().getColumn(7).setPreferredWidth(50);
        }

        txtBuscarUser.setToolTipText("Ejemplo Usuario = Admin");
        txtBuscarUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtBuscarUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarUserKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Usuario:");
        jLabel9.setToolTipText("");
        jLabel9.setAlignmentY(0.0F);

        btnBuscar1.setText("Buscar por fecha");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        jButton1.setText("Volver menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        btnPagSig.setText("Pag sig");
        btnPagSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagSigActionPerformed(evt);
            }
        });

        cmbPaginas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPaginas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPaginasActionPerformed(evt);
            }
        });

        pagAnt.setText("Pag ant");
        pagAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagAntActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Pagina :");

        dateFechaDesde.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                dateFechaDesdeAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Fecha hasta:");
        jLabel11.setToolTipText("");
        jLabel11.setAlignmentY(0.0F);

        btnResetTbl.setText("Reset componentes");
        btnResetTbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTblActionPerformed(evt);
            }
        });

        borraTodoMovimiento.setText("Borrar todos los movimiento");
        borraTodoMovimiento.setToolTipText("Borra todos los movimietos hasta la fecha");
        borraTodoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borraTodoMovimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(borraTodoMovimiento)
                        .addGap(258, 258, 258)
                        .addComponent(pagAnt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPagSig)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnBuscar1)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(dateFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnResetTbl)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addGap(8, 8, 8)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(dateFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnResetTbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagAnt)
                    .addComponent(jLabel10)
                    .addComponent(cmbPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPagSig)
                    .addComponent(borraTodoMovimiento))
                .addGap(48, 48, 48))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar1)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(txtBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(526, 526, 526))
        );

        jTabbedPane1.addTab("Movimientos", jPanel3);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel5AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel5AncestorAdded
        // TODO add your handling code here:
        FncFecha trae = new FncFecha();

        trae.fecha(txtDia, txtMes, txtAno);
    }//GEN-LAST:event_jPanel5AncestorAdded

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
        // TODO add your handling code here:
        InsertarProductos ins = new InsertarProductos();
        ins.setVisible(true);

    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void tblVentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentaMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblVentaMousePressed

    private void btnVueltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVueltoActionPerformed
        // TODO add your handling code here:
        if (!txtPagoUser.getText().isEmpty()) {

            // var paga recive el pago
            double paga = Double.parseDouble(txtPagoUser.getText());
            if(paga < 0){
                JOptionPane.showConfirmDialog(null, "Paga es menor que cero","ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
            //var total recive total
            double total = Double.parseDouble(txtTotal.getText());
            if (total != 0.0 || total != 0) {
                //var vuelto calcula el vuelto a dar
                double vuelto;
                vuelto = (paga - total);
                //se le assigan vuelto a variable string para hacerlo mas preciso y sin errores 
                // ya que asignando el vuelto directamente generaba algunos errores en el cual no sacaba
                // el calculo correctamente !
                String v = Double.toString(vuelto);
                // seteo/envio el vuelto al textfield en el frame
                txtVuelto.setText(v);
            } else {
                JOptionPane.showConfirmDialog(null,"Total es igual a 0.0, no hay operación para calcular !", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            }
        } else {
            JOptionPane.showMessageDialog(null,"No inserto paga o es = 0","ERROR", JOptionPane.ERROR_MESSAGE);
                
        }
        
    }//GEN-LAST:event_btnVueltoActionPerformed

    private void cmbIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIVAActionPerformed
        // TODO add your handling code here:
        //Calculo el porcentaje de IVA
        InsertarProductos in = new InsertarProductos();
        int indice = cmbIVA.getSelectedIndex();
        Double porcentaje;
        Double cmbtotal = in.totalreset;
        if (indice == 0) {
            txtTotal.setText(Double.toString(in.totalreset));
            this.IVAS = "Sin IVA";
        } else if (indice == 1) {
            porcentaje = 21.0;

            cmbtotal = cmbtotal + ((cmbtotal * porcentaje) / 100);
            cmbtotal = (double) Math.round(cmbtotal * 100) / 100;
            txtTotal.setText(cmbtotal.toString());
            cmbDescTotal = cmbtotal;
            this.IVAS = "Con IVA 21%";
        } else if (indice == 2) {
            porcentaje = 10.5;
            cmbtotal = cmbtotal + ((cmbtotal * porcentaje) / 100);
            cmbtotal = (double) Math.round(cmbtotal * 100) / 100;
            txtTotal.setText(cmbtotal.toString());
            cmbDescTotal = cmbtotal;
            this.IVAS = "Con IVA 10.5%";
        }

    }//GEN-LAST:event_cmbIVAActionPerformed

    private void txtIDventaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtIDventaAncestorAdded
        // TODO add your handling code here:

        ConsultasVentas con = new ConsultasVentas();
        con.idVentas(txtIDventa);
        String ventas = txtIDventa.getText();
        if (ventas.isEmpty()) {
            txtIDventa.setText("1");
        }
    }//GEN-LAST:event_txtIDventaAncestorAdded

    private void btnEliminarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFilaActionPerformed
        FncTablas fn = new FncTablas();
        fn.eliminarfilas(tblVenta, txtTotal);
    }//GEN-LAST:event_btnEliminarFilaActionPerformed

    private void btnEliminarFilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarFilaKeyPressed
        // TODO add your handling code here:
        char carr = (char) evt.getKeyCode();
        if (carr == KeyEvent.VK_DELETE) {
            FncTablas fn = new FncTablas();
            fn.eliminarfilas(tblVenta, txtTotal);
        }
    }//GEN-LAST:event_btnEliminarFilaKeyPressed

    private void tblVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblVentaKeyPressed
        // TODO add your handling code here:
        char carr = (char) evt.getKeyCode();
        if (carr == KeyEvent.VK_DELETE) {
            FncTablas fn = new FncTablas();
            fn.eliminarfilas(tblVenta, txtTotal);
        }
    }//GEN-LAST:event_tblVentaKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        //ejecuto sql antes de la llamada para contar numero de registro 
        if ((txtBuscarUser.getText() != null) && (!txtBuscarUser.getText().equals(""))) {
            String user = txtBuscarUser.getText();
            System.out.println("El user es = " + user);
            String sqll = "";
            sqll = "SELECT DISTINCT count(*) AS total,venta.idVenta, venta.Usuario,detallevta.IDproducto, "
                    + "detallevta.Descripcion, detallevta.Fecha, detallevta.Cant, "
                    + "detallevta.PrecioSIVA, detallevta.oferta,detallevta.Importe "
                    + "FROM venta INNER JOIN  detallevta ON venta.idVenta = detallevta.idVenta "
                    + "WHERE (venta.Usuario LIKE '" + user + "%')";
            //Envio sql para paginar jtble
            p.contarReg(cmbPaginas, sqll);
            //Instancio llamada 2 para trabajar con ese sql de datos
            this.llamada = 2;
            //Lllamo al evento de cmbox para ejecutar consultas 
            this.cmbPaginasActionPerformed(evt);
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar el campo user para ejecutar la busqueda", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUserKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c
                < '0' || c > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarUserKeyTyped

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        ConsultasDePaginado cp = new ConsultasDePaginado();
        //Me aseguro que las fechas no sean nulas 
        if ((dateFechaDesde.getDate() != null) && (dateFechaHasta.getDate() != null)) {
            //Verifico que desde no sea mayor que hasta
            int val = dateFechaDesde.getDate().compareTo(dateFechaHasta.getDate());
            if ((val < 0) || (val == 0)) {

                //Genero consulta para contar los registros
                String sqll = "";
                sqll = cp.getMovimientoConsulta(dateFechaDesde.getDate(), dateFechaHasta.getDate());
                //Paso el combo y el sql para que cuente los registros y pagine el JTable
                p.contarReg(cmbPaginas, sqll);
                for (int i = 0; i < 20000; i++) {

                }
                //Instancio llamada para ejecutar sql despues de contar los registro 
                //Debe de hacerse doble consulta para paginar un JTable
                this.llamada = 1;
                this.cmbPaginasActionPerformed(evt);
            } else {
                JOptionPane.showMessageDialog(null, "Las fechas deben de ser o "
                        + "iguales, o desde, debe ser menor que, hasta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos de fecha para la busqueda", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void tblMovimientosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tblMovimientosAncestorAdded
        // TODO add your handling code here:
        Paginado p = new Paginado();
        ConsultasDePaginado cp = new ConsultasDePaginado();
        //Paso la consulta y el cmb por string para realizar el proceso de paginado del table
        //El resto funciona internamente en el metodo que esta en paginado en el paquete FNC
        String sqlNumReg = cp.movimientoTbl();
        p.contarReg(cmbPaginas, sqlNumReg);

    }//GEN-LAST:event_tblMovimientosAncestorAdded

    private void btnFinVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinVentaActionPerformed
        // TODO add your handling code here:

        Funciones fc = new Funciones();
        //fc.imprimirJTable(tblVenta, "Valentin", "1000");
        ConsultasVentas con = new ConsultasVentas();

        con.agregarVenta(tblVenta, txtDia, txtMes, txtAno, txtTotal, txtIDventa);

        System.out.println(tblVenta);
        //Seteo la cantidad a stock
        con.movimientoStock(tblVenta);
        
        //Reseteo ventas para hacer otra nueva.
        InsertarProductos in = new InsertarProductos();
        ConsultasVentas cn = new ConsultasVentas();
        cn.idVentas(txtIDventa);
        in.llamada();
        FncTablas fnc = new FncTablas();
        fnc.limpiarTabla2(tblVenta, txtTotal, txtSubTotal, txtPagoUser, txtVuelto, cmbIVA);

    }//GEN-LAST:event_btnFinVentaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu men = new Menu();
        men.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Menu men = new Menu();
        men.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:
        char carr = (char) evt.getKeyCode();
        if (carr == KeyEvent.VK_F1) {
            Menu men = new Menu();
            men.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jButton2KeyPressed

    private void cmbPaginasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPaginasActionPerformed
        // TODO add your handling code here
        Paginado p = new Paginado();
        int[] resultado = {0, 0};/*
        //Verifico que el combo no este null para poder asignar paginas
        if (cmbPaginas.getSelectedItem() != null) {

            //Variable res guarda la pagina seleccionada en el combobox
            int res = cmbPaginas.getSelectedIndex();

            resultado[1] = res + 1;

            //Envio un array con el numero de la pagina y otro lugar vacio para dar base de registro desde donde se parte la consulta
            // y un top de cantidad de registros a traer q es 30 siempre
            resultado = p.dividorPag(resultado);*/
        resultado = p.cmbDePaginas(cmbPaginas);
        //Dependiendo del numero de llamadas la cosnsulta sera distinta.
        if (this.llamada == 0) {//Llamada 0 carga la consulta de entrada carga automatíca table
            ConsultasVentas cn = new ConsultasVentas();
            cn.cargaAutomatica(tblMovimientos, resultado);
        } else if (this.llamada == 1) {//llamada 1 es para consulta por fechas
            ConsultasVentas cn = new ConsultasVentas();
            Date fechDesde = dateFechaDesde.getDate();
            Date fechHasta = dateFechaHasta.getDate();
            cn.movimientoFecha(tblMovimientos, fechDesde, fechHasta, resultado);
        } else if (this.llamada == 2) {
            ConsultasVentas con = new ConsultasVentas();
            String user = txtBuscarUser.getText();
            con.movimientoUsuario(resultado, tblMovimientos, user);
        }

    }//GEN-LAST:event_cmbPaginasActionPerformed

    private void btnPagSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagSigActionPerformed
        // TODO add your handling code here:
        Paginado p = new Paginado();
        int[] resultado = {0, 0};

        resultado = p.btnPagSiguiente(cmbPaginas);
        if (this.llamada == 0) {//Llamada 0 carga la consulta de entrada carga automatíca table
            ConsultasVentas cn = new ConsultasVentas();
            cn.cargaAutomatica(tblMovimientos, resultado);
        } else if (this.llamada == 1) {//llamada 1 es para consulta por fechas
            ConsultasVentas cn = new ConsultasVentas();
            Date fechDesde = dateFechaDesde.getDate();
            Date fechHasta = dateFechaHasta.getDate();
            cn.movimientoFecha(tblMovimientos, fechDesde, fechHasta, resultado);
        } else if (this.llamada == 2) {
            ConsultasVentas con = new ConsultasVentas();
            String user = txtBuscarUser.getText();
            con.movimientoUsuario(resultado, tblMovimientos, user);
        }

    }//GEN-LAST:event_btnPagSigActionPerformed

    private void pagAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagAntActionPerformed
        // TODO add your handling code here:
        Paginado p = new Paginado();
        int[] resultado = {0, 0};
        resultado = p.btnPaginaAnterior(cmbPaginas);
        if (this.llamada == 0) {//Llamada 0 carga la consulta de entrada carga automatíca table
            ConsultasVentas cn = new ConsultasVentas();
            cn.cargaAutomatica(tblMovimientos, resultado);
        } else if (this.llamada == 1) {//llamada 1 es para consulta por fechas
            ConsultasVentas cn = new ConsultasVentas();
            Date fechDesde = dateFechaDesde.getDate();
            Date fechHasta = dateFechaHasta.getDate();
            cn.movimientoFecha(tblMovimientos, fechDesde, fechHasta, resultado);
        } else if (this.llamada == 2) {
            ConsultasVentas con = new ConsultasVentas();
            String user = txtBuscarUser.getText();
            con.movimientoUsuario(resultado, tblMovimientos, user);
        }

    }//GEN-LAST:event_pagAntActionPerformed

    private void dateFechaDesdeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateFechaDesdeAncestorAdded
        // TODO add your handling code here:
        //Borro los campos de jdatachooser
        dateFechaDesde.setCalendar(null);
        dateFechaHasta.setCalendar(null);
        //asigno llamada a cero para que llame a determinada sentncia sql
        this.llamada = 0;
        //llamo al metodo de acceso de jtable para ejecutar el evento
        this.tblMovimientosAncestorAdded(evt);
    }//GEN-LAST:event_dateFechaDesdeAncestorAdded

    private void btnResetTblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTblActionPerformed
        // TODO add your handling code here:
        //Borro los campos de jdatachooser y txtfield
        dateFechaDesde.setCalendar(null);
        dateFechaHasta.setCalendar(null);
        txtBuscarUser.setText("");
        //Reseteo tabla como al inicio muestro todo los registros
        Paginado p = new Paginado();
        ConsultasDePaginado cp = new ConsultasDePaginado();
        this.llamada = 0;
        //Paso la consulta y el cmb por string para realizar el proceso de paginado del table
        //El resto funciona internamente en el metodo que esta en paginado en el paquete FNC
        String sqlNumReg = cp.movimientoTbl();
        p.contarReg(cmbPaginas, sqlNumReg);
    }//GEN-LAST:event_btnResetTblActionPerformed

    private void btnRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerActionPerformed
        // TODO add your handling code here:
        FncTablas fn = new FncTablas();
        fn.limpiarTabla(tblVenta, txtTotal, txtSubTotal, txtPagoUser, txtVuelto, cmbIVA);
    }//GEN-LAST:event_btnRehacerActionPerformed

    private void txtPagoUserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoUserKeyPressed
        // TODO add your handling code here:
        //asigno evento a una variable
        char c = (char) evt.getKeyCode();
        if (c == KeyEvent.VK_ENTER) {
            btnVuelto.doClick();
        }
    }//GEN-LAST:event_txtPagoUserKeyPressed

    private void impPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impPDFActionPerformed
        // TODO add your handling code here:
        // System.out.println(this.pd.file + " " + this.pd.getDesc());

        Funciones fn = new Funciones();
        fn.ejecutarPDF(tblVenta, txtIDventa.getText());

    }//GEN-LAST:event_impPDFActionPerformed

    private void borraTodoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borraTodoMovimientoActionPerformed
        ModificacionJtable mf = new ModificacionJtable();
        ConsultasVentas cv = new ConsultasVentas();
        //Si el usuario marca si se reestablecen las tablas si marca no quedan normales
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea reestablecer completamente las tablas venta y detalle venta desde 0?", "PREGUNTA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            mf.limpiarTabla(tblMovimientos);
            cv.resetearTablaMov();
        } else {
            System.out.println("No se reestablecieron las tablas");
        }
    }//GEN-LAST:event_borraTodoMovimientoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        InsertarProductos in = new InsertarProductos();
        //Tomo el totalreset que es el que se mantendra sin ser modificado
        Double t = in.totalreset;
        int descuento = 0;
        String des = "";
        double resta = 0;//Variable que le voy a asignar el descuento mas abajo
        //Tomo el total de la tabla
        Double total = Double.parseDouble(txtTotal.getText());
        //redondeo el total con math.round
        total = (double) Math.round(total * 100) / 100;
        //Redondeo el totalreset para que se quede con dos decimales despues de la coma
        t = (double) Math.round(t * 100) / 100;
        int indi = cmbIVA.getSelectedIndex();
        //genero una validacion para que verifique si desea aplicar descuento sin iva incluido al total
        if (indi == 0) {
            int res = JOptionPane.showConfirmDialog(null, "No ha aplicado IVA ¿Desea aplicar descuento al total final igual?", "PREGUNTA", JOptionPane.YES_NO_OPTION);
            //Si responde si paso a generar el descuento
            if (res == JOptionPane.YES_OPTION) {
                //Se le solicita al usuario que ingrese un descuento para aplicar sobre el total de la cuenta final
                des = JOptionPane.showInputDialog(null, "Ingrese un descuento de 1 a 100 para aplicar sobre el total final", "INGRESO DE DATOS", JOptionPane.INFORMATION_MESSAGE);
                //paso descuento de cadena a entero;
                try {
                    descuento = Integer.parseInt(des);
                    this.tdescuento = descuento;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Descuento no aplicado ingrese solo numeros");
                }
                //Iff para limitar de 1 a 100 el descuento y no mandar 1200 por ejemplo
                if ((descuento > 0) && (descuento < 101)) {
                    //Saco descuento lo asigno a resta
                    resta = (total * descuento) / 100;
                    //le resto el descuento al total
                    total = (total - resta);
                    //redondeo el total para que quede con dos decimales con math round
                    total = (double) Math.round(total * 100) / 100;
                    //Aplico el descuento y lo muestro
                    txtTotal.setText(Double.toString(total));
                } else {
                    JOptionPane.showMessageDialog(null, "El descuento no se ha aplicado, vuelva a intentarlo", "ERROR", JOptionPane.ERROR);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el IVA entonces ¡Por favor!");
            }
            //If que indica que marco iva 21
        } else if (indi == 1) {
            //Pido el descuento al total con iva 21
            des = JOptionPane.showInputDialog(null, "Ingrese un descuento de 1 a 100 para aplicar sobre el total con IVA 21% final", "INGRESO DE DATOS", JOptionPane.INFORMATION_MESSAGE);
            //Este try es para que ingrese solo numero en el descuento y no cualquier otra cosa 
            try {
                descuento = Integer.parseInt(des);
                this.tdescuento = descuento;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Descuento no aplicado, ingrese solo numeros");
            }
            if ((descuento > 0) && (descuento < 101)) {
                resta = (total * descuento) / 100;
                total = (total - resta);
                total = (double) Math.round(total * 100) / 100;
                txtTotal.setText(Double.toString(total));
            } else {
                JOptionPane.showMessageDialog(null, "Descuento no aplicado. Ingrese numeros de 1 a 100", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (indi == 2) {
            //Pido el descuento al total con iva 10.5
            des = JOptionPane.showInputDialog(null, "Ingrese un descuento de 1 a 100 para aplicar sobre el total con IVA 21% final", "INGRESO DE DATOS", JOptionPane.INFORMATION_MESSAGE);
            //Este try es para que ingrese solo numero en el descuento y no cualquier otra cosa 
            try {
                descuento = Integer.parseInt(des);
                this.tdescuento = descuento;//Variable que envia el descuento al pdf
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Descuento no aplicado, ingrese solo numeros");
            }
            if ((descuento > 0) && (descuento < 101)) {
                resta = (total * descuento) / 100;
                total = (total - resta);
                total = (double) Math.round(total * 100) / 100;
                txtTotal.setText(Double.toString(total));
            } else {
                JOptionPane.showMessageDialog(null, "Descuento no aplicado. Ingrese numeros de 1 a 100", "ERROR", JOptionPane.ERROR);
            }

        } else {
            txtTotal.setText(Double.toString(t));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        cmbIVA.setSelectedIndex(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borraTodoMovimiento;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnEliminarFila;
    private javax.swing.JButton btnFinVenta;
    private javax.swing.JButton btnPagSig;
    private javax.swing.JButton btnRehacer;
    private javax.swing.JButton btnResetTbl;
    private javax.swing.JButton btnVuelto;
    protected static javax.swing.JComboBox<String> cmbIVA;
    public static javax.swing.JComboBox<String> cmbPaginas;
    private com.toedter.calendar.JDateChooser dateFechaDesde;
    private com.toedter.calendar.JDateChooser dateFechaHasta;
    private javax.swing.JButton impPDF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    protected static javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton pagAnt;
    protected javax.swing.JTable tblMovimientos;
    public static javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtBuscarUser;
    private javax.swing.JTextField txtDia;
    private javax.swing.JTextField txtIDventa;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtPagoUser;
    public static javax.swing.JTextField txtSubTotal;
    public static javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables

}
