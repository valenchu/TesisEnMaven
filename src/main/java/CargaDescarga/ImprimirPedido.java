/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDescarga;

import FNC.Funciones;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentin
 */
public class ImprimirPedido extends javax.swing.JFrame {

    public String tex = null;
    public String[] llenado = new String[1];
    DefaultListModel list = new DefaultListModel();
    public static int idPedi;//Variable que guarda el id del proveedor para comparar encargo con exportar
    public static int proveedorDNI;//Variable que guarda el DNI de proveedor para mostrar en exportar cual es el proveedor
    //que se debe elegir!

    public ImprimirPedido() {
        initComponents();
        listaDeProveedores.setModel(list);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPedidos = new javax.swing.JTable();
        btnImprimir = new javax.swing.JButton();
        txtLlenar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btInsertar = new javax.swing.JButton();
        eliminarFila = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaDeProveedores = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        lblEmail = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDNI = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        btnCargarPedido = new javax.swing.JButton();
        numPedido = new javax.swing.JLabel();
        contadorExportar = new javax.swing.JLabel();
        contadorEncargo = new javax.swing.JLabel();
        resetTabalaEncargo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pedido");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(new ImageIcon(getClass().getResource("/Logo.png")).getImage());
        setMinimumSize(new java.awt.Dimension(700, 650));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(650, 350));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 350));
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

        tablaPedidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDproducto", "Descripción pedido", "Cantidad", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPedidos.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tablaPedidosAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tablaPedidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaPedidosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPedidos);
        if (tablaPedidos.getColumnModel().getColumnCount() > 0) {
            tablaPedidos.getColumnModel().getColumn(0).setResizable(false);
            tablaPedidos.getColumnModel().getColumn(0).setPreferredWidth(10);
            tablaPedidos.getColumnModel().getColumn(1).setPreferredWidth(130);
            tablaPedidos.getColumnModel().getColumn(2).setPreferredWidth(10);
            tablaPedidos.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        btnImprimir.setText("Exportar a excel");
        btnImprimir.setToolTipText("Boton que exporta la tabla a exel para dar el pedido al proveedor");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        txtLlenar.setToolTipText("Llena todas las columnas de cantidad con el numero ingresado.");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Insertar cantidades");

        btInsertar.setText("Insertar");
        btInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInsertarActionPerformed(evt);
            }
        });

        eliminarFila.setText("Eliminar fila");
        eliminarFila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarFilaActionPerformed(evt);
            }
        });

        listaDeProveedores.setBackground(new java.awt.Color(204, 255, 204));
        listaDeProveedores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaDeProveedores.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                listaDeProveedoresAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        listaDeProveedores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listaDeProveedoresKeyPressed(evt);
            }
        });
        listaDeProveedores.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaDeProveedoresValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaDeProveedores);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel2KeyPressed(evt);
            }
        });

        lblEmail.setText("jLabel2");
        lblEmail.setVisible(false);

        lblTelefono.setText("jLabel2");
        lblTelefono.setVisible(false);

        lblDNI.setText("jLabel2");
        lblDNI.setVisible(false);

        lblApellido.setText("jLabel2");
        lblApellido.setVisible(false);

        lblNombre.setText("jLabel2");
        lblNombre.setVisible(false);

        lblNombre1.setBackground(new java.awt.Color(255, 51, 0));
        lblNombre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombre.setVisible(false);
        lblNombre1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                lblNombre1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        lblDireccion.setText("jLabel2");
        lblDireccion.setVisible(false);

        lblID.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblID)
                    .addComponent(lblDireccion)
                    .addComponent(lblEmail)
                    .addComponent(lblTelefono)
                    .addComponent(lblDNI)
                    .addComponent(lblApellido)
                    .addComponent(lblNombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(lblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(lblID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblApellido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDNI)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDireccion)
                .addContainerGap())
        );

        lblID.setVisible(false);

        btnCargarPedido.setText("Encargo de pedido");
        btnCargarPedido.setToolTipText("Boton que crea el encargo para el pedido");
        btnCargarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPedidoActionPerformed(evt);
            }
        });

        numPedido.setText("0");
        numPedido.setVisible(true);
        numPedido.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                numPedidoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        contadorExportar.setText("0");
        contadorExportar.setVisible(true);
        contadorExportar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                contadorExportarAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        contadorEncargo.setText("0");
        contadorEncargo.setVisible(true);
        contadorEncargo.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                contadorEncargoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        resetTabalaEncargo.setText("Reset tabla");
        resetTabalaEncargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetTabalaEncargoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Proveedores");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(txtLlenar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btInsertar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(resetTabalaEncargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(eliminarFila, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(btnCargarPedido)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(numPedido)
                                        .addGap(17, 17, 17)
                                        .addComponent(contadorEncargo)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(contadorExportar)
                                    .addComponent(btnImprimir))))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btInsertar)
                                    .addComponent(txtLlenar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarFila)
                        .addGap(11, 11, 11)
                        .addComponent(resetTabalaEncargo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(223, 223, 223)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(numPedido)
                            .addComponent(contadorExportar)
                            .addComponent(contadorEncargo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE)))
                .addContainerGap())
        );

        numPedido.setVisible(false);
        contadorExportar.setVisible(false);
        contadorEncargo.setVisible(false);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        Integer[] at = null;
        String ar = contadorExportar.getText();
        String b = contadorEncargo.getText();

        if (ar.equals(b)) {
            JOptionPane.showMessageDialog(null, "Debe primero realizar un pedido antes de exportar a exel", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (ar != b) {
            String[] c = new String[]{"IDproducto", "Descripción pedido", "Cantidad"};
            final Class[] indices = new Class[]{Integer.class, String.class, Integer.class};//Creo objeto class que pasara cada clase que poseera las columnas
            DefaultTableModel mo = new DefaultTableModel(null, c);//Creo modelo de tabla para instanciar
            DefaultTableModel mo2 = new DefaultTableModel(null, c) {
                //Paso los indices de clase que tomara cada columna
                @Override
                public Class getColumnClass(int indic) {
                    return indices[indic];
                }
            };//Creo segundo modelo de tabla para borrar las que exporte y dejar las que no exporte
            int filas = tablaPedidos.getRowCount();

            boolean verificar = false;
            int count = 0, count2 = 0;
            int cant = 2;//columna de cantidad a reemplazar
            //Verifico que la fila cantidad no este vacia
            for (int i = 0; i < filas; i++) {
                if (tablaPedidos.getValueAt(i, cant) == null) {
                    verificar = true;
                    count++;
                } else {
                    verificar = false;

                }
            }
            int verificar_2 = listaDeProveedores.getSelectedIndex();
            //Si ha algunas filas de cantidad muestra mensaje, si pone SI Pasa de largo y toma solamente
            //las filas que posean cantidad las que no no las agrega
            if (count != 0) {
                int a = JOptionPane.showConfirmDialog(null, "Faltan llenar columnas de cantidad, ¿Desea dejarlas vacías?", "PREGUNTA", JOptionPane.YES_NO_OPTION);
                if (a == JOptionPane.YES_OPTION) {
                    verificar = false;
                } else {
                    verificar = true;
                }
            }  //Si el verificar es correcto tonces exporta. Si no no exporta.
            if ((verificar_2 != -1) && (verificar != true)) {
                Funciones f = new Funciones();
                int indice = listaDeProveedores.getSelectedIndex();
                Proveedores pv = (Proveedores) list.getElementAt(indice);
                //Tomo únicamente las filas llenas con cantidad en el siguiente for
                for (int j = 0; j < filas; j++) {

                    //Si la fila cantidad es distinta de null la toma
                    //Si la fila cantidad es == a null la toma en el segundo modelo para pasarlo a la tabla original
                    if (tablaPedidos.getValueAt(j, cant) == null) {
                        int id = (int) tablaPedidos.getValueAt(j, 0);
                        String des = (String) tablaPedidos.getValueAt(j, 1);

                        mo2.addRow(new Object[]{id, des});
                    } else {
                        //Tomo con get las filas que poseen cantidad distinta de null
                        int id = (int) tablaPedidos.getValueAt(j, 0);
                        String des = (String) tablaPedidos.getValueAt(j, 1);
                        int ca = (int) tablaPedidos.getValueAt(j, cant);
                        //Agrego al modelo las filas completas
                        mo.addRow(new Object[]{id, des, ca});

                    }
                }
                //Si selecciona el proveedor lo exporta si no no lo exporta a el exel
                if (this.idPedi != pv.getID()) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar el mismo proveedor que el encargo el cual es = DNI: " + this.proveedorDNI, "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    f.excelExp(contadorExportar, numPedido, mo, pv);
                    tablaPedidos.setModel(mo2);
                    for (int e = 0; e < tablaPedidos.getColumnCount(); e++) {
                        if (e == 0 || e == 2) {
                            tablaPedidos.getColumnModel().getColumn(e).setPreferredWidth(10);
                        } else {
                            tablaPedidos.getColumnModel().getColumn(e).setPreferredWidth(150);
                        }
                    }
                }
            } else if (verificar_2 == -1) {
                JOptionPane.showMessageDialog(null, "Le falta seleccionar un proveedor \nPara poder exportar a excel ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInsertarActionPerformed
        // TODO add your handling code here:
        Funciones fun = new Funciones();
        int llenar = 0;
        llenar = Integer.parseInt(txtLlenar.getText());
        fun.ingresarCantidad(llenar, tablaPedidos);
    }//GEN-LAST:event_btInsertarActionPerformed

    private void tablaPedidosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tablaPedidosAncestorAdded
        // TODO add your handling code here:
        ConsultaImprimir conec = new ConsultaImprimir();
        conec.cargarPedido(tablaPedidos);
    }//GEN-LAST:event_tablaPedidosAncestorAdded

    private void eliminarFilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarFilaActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tablaPedidos.getModel();
        //Confirma que se a seleccionado una fila por lo menos
        int fila = tablaPedidos.getSelectedRow();
        int cuentaFila = tablaPedidos.getRowCount();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "No selecciono ninguna fila a eliminar");
        } else {
            for (int i = 0; i < cuentaFila; i++) {
                Boolean tipo = tablaPedidos.isRowSelected(i);
                if (tipo == true) {
                    int cant = 1;
                    //Remuevo la dila
                    modelo.removeRow(fila);
                    tablaPedidos.setModel(modelo);
                    //borro seleccion
                    tablaPedidos.clearSelection();
                }
            }
    }//GEN-LAST:event_eliminarFilaActionPerformed
    }
    private void listaDeProveedoresAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_listaDeProveedoresAncestorAdded
        ConsultaImprimir cs = new ConsultaImprimir();
        //Paso las listas para ser llenadas por BD
        cs.llenarJlistProveedores(listaDeProveedores, list);
    }//GEN-LAST:event_listaDeProveedoresAncestorAdded

    private void listaDeProveedoresValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaDeProveedoresValueChanged
        // TODO add your handling code here:
        //Creo el indice que tomo del elemente
        int index = listaDeProveedores.getSelectedIndex();
        //Si se selecciona algo en el jlist activa el siguiente codigo si no, no
        if (index != -1) {
            //Paso el indice al objeto creado de proveedores
            Proveedores pv = (Proveedores) list.getElementAt(index);
            //activo los labels para que se vean
            lblID.setVisible(true);
            lblNombre.setVisible(true);
            lblApellido.setVisible(true);
            lblDNI.setVisible(true);
            lblTelefono.setVisible(true);
            lblEmail.setVisible(true);
            lblDireccion.setVisible(true);
            //Muestro la informacion de la lista en los Jlabel
            //Creo el estilo de letras de los jlabel con las etiquetas html y css de style
            String id = "<html><body>IDproveedor : <strong style='font-weight: normal'>" + pv.getID() + "</strong></body></html>";
            lblID.setText(id);
            String nom = "<html><body>Nombre : <strong style='font-weight: normal'>" + pv.getNomb() + "</strong></body></html>";
            lblNombre.setText(nom);
            String ap = "<html><body>Apellido : <strong style='font-weight: normal'>" + pv.getApe() + "</strong></body></html>";
            lblApellido.setText(ap);
            String dni = "<html><body>DNI : <strong style='font-weight: normal'>" + pv.getDNI() + "</strong></body></html>";
            lblDNI.setText(dni);
            String tel = "<html><body>Teléfono : <strong style='font-weight: normal'>" + pv.getTel() + "</strong></body></html>";
            lblTelefono.setText(tel);
            String email = "<html><body>Email : <strong style='font-weight: normal'>" + pv.getEmail() + "</strong></body></html>";
            lblEmail.setText(email);
            String dire = "<html><body>Dirección : <strong style='font-weight: normal'>" + pv.getDire() + "</strong></body></html>";
            lblDireccion.setText(dire);
            //lblDireccion.setText("Dirección : "+pv.getDire());
        }
    }//GEN-LAST:event_listaDeProveedoresValueChanged

    private void lblNombre1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_lblNombre1AncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_lblNombre1AncestorAdded

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        //Alinio el texto al centro y el jlabel tambien !
        lblNombre1.setText("<html><u style='text-align: center;'>Información de proveedores</u><html>");
        lblNombre1.setHorizontalAlignment(lblNombre1.CENTER);    }//GEN-LAST:event_jPanel1AncestorAdded

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        //Borro la seleccion de tabla al hacer click en el panel
        listaDeProveedores.clearSelection();

        tablaPedidos.clearSelection();
        lblID.setVisible(false);
        lblNombre.setVisible(false);
        lblApellido.setVisible(false);
        lblDNI.setVisible(false);
        lblTelefono.setVisible(false);
        lblEmail.setVisible(false);
        lblDireccion.setVisible(false);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void tablaPedidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaPedidosKeyPressed
        // TODO add your handling code here:
        //Borro la seleccion del jtable al presionar escape
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_ESCAPE) {
            tablaPedidos.clearSelection();
        }
    }//GEN-LAST:event_tablaPedidosKeyPressed

    private void listaDeProveedoresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaDeProveedoresKeyPressed
        // TODO add your handling code here:
        //Borro la seleccion del jlist al presionar escape 
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_ESCAPE) {
            listaDeProveedores.clearSelection();

            lblID.setVisible(false);
            lblNombre.setVisible(false);
            lblApellido.setVisible(false);
            lblDNI.setVisible(false);
            lblTelefono.setVisible(false);
            lblEmail.setVisible(false);
            lblDireccion.setVisible(false);
        }
    }//GEN-LAST:event_listaDeProveedoresKeyPressed

    private void jPanel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyPressed
        // TODO add your handling code here:
        //Borro la seleccion del jlist al presionar escape 
        int c = evt.getKeyCode();
        if (c == KeyEvent.VK_ESCAPE) {
            listaDeProveedores.clearSelection();

            lblID.setVisible(false);
            lblNombre.setVisible(false);
            lblApellido.setVisible(false);
            lblDNI.setVisible(false);
            lblTelefono.setVisible(false);
            lblEmail.setVisible(false);
            lblDireccion.setVisible(false);
        }
    }//GEN-LAST:event_jPanel2KeyPressed

    private void btnCargarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPedidoActionPerformed
        // TODO add your handling code here:
        int en = Integer.parseInt(contadorEncargo.getText());
        int exp = Integer.parseInt(contadorExportar.getText());

        int idex = listaDeProveedores.getSelectedIndex();
        //Verifico que se haya marcado un proveedor antes de mandarlo como parametro para la consulta
        if (en > exp) {
            JOptionPane.showMessageDialog(null, "Debe exportar a exel el pedido ya realizado.\nUna vez que exporte a exel se le permitira hacer mas encargos", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (en == exp) {
            if (idex != -1) {
                try {
                    ConsultaImprimir cs = new ConsultaImprimir();
                    Proveedores pv = (Proveedores) list.getElementAt(idex);
                    int idpv = pv.getID();//obtengo id proveedor
                    this.idPedi = pv.getID();//Obtengo id proveedor
                    this.proveedorDNI = pv.getDNI();//Obtengo dni
                    /*Contador encargo Este label guarda la cantidad que se ha encargado algo
                numPedido Este label guarda el numero de pedido para exportarlo a exel*/
                    cs.cargarPedidoBD(contadorEncargo, numPedido, idpv, tablaPedidos);

                } catch (SQLException ex) {
                    Logger.getLogger(ImprimirPedido.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor para guardar el pedido", "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_btnCargarPedidoActionPerformed

    private void numPedidoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_numPedidoAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_numPedidoAncestorAdded

    private void contadorEncargoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_contadorEncargoAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_contadorEncargoAncestorAdded

    private void contadorExportarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_contadorExportarAncestorAdded
        // TODO add your handling code here:

    }//GEN-LAST:event_contadorExportarAncestorAdded

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ConsultaImprimir ci = new ConsultaImprimir();
        int a = ci.cerrar(contadorEncargo, contadorExportar);
        System.out.println(a);
        if (a != 0) {
            dispose();//al recibir 1 que es distinto de cero cierra la pestaña, si recibe 0 no cierra la pestaña
            //tener en cuenta que 1 es la opcion si de la ventana que aparece y 0 es la opcion no de la ventana
        } else {

        }
    }//GEN-LAST:event_formWindowClosing

    private void resetTabalaEncargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetTabalaEncargoActionPerformed
        ConsultaImprimir conec = new ConsultaImprimir();
        conec.cargarPedido(tablaPedidos);
    }//GEN-LAST:event_resetTabalaEncargoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btInsertar;
    private javax.swing.JButton btnCargarPedido;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel contadorEncargo;
    private javax.swing.JLabel contadorExportar;
    private javax.swing.JButton eliminarFila;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JList<String> listaDeProveedores;
    private javax.swing.JLabel numPedido;
    private javax.swing.JButton resetTabalaEncargo;
    private javax.swing.JTable tablaPedidos;
    private javax.swing.JTextField txtLlenar;
    // End of variables declaration//GEN-END:variables
}
