package Bienvenido;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Inicio_Sesion extends javax.swing.JFrame {

    public static String U = "";
    public static String P = "";

    // Cambia imagen de icono 
    /**
     * ***********
     *
     * new ImageIcon(getClass().getResource("/Logo.png")).getImage()
     *
     *
     */
    public Inicio_Sesion() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        pass = new javax.swing.JPasswordField();
        bott1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bott2 = new javax.swing.JButton();
        jocLabel1 = new com.xzq.osc.JocLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
        setFocusable(false);
        setIconImage(new ImageIcon(getClass().getResource("/Logo.png")).getImage());
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 500));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usuario.png"))); // NOI18N
        jLabel1.setText(" Usuario : ");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/clave.png"))); // NOI18N
        jLabel2.setText(" Contraseña : ");
        jLabel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 110, 30));

        user.setToolTipText("Indistinto MAYUSCULAS de minusculas");
        user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        user.setSelectionColor(java.awt.SystemColor.activeCaption);
        user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userKeyTyped(evt);
            }
        });
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 200, 30));

        pass.setToolTipText("Indistinto MAYUSCULAS de minusculas");
        pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pass.setSelectionColor(java.awt.SystemColor.activeCaption);
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passKeyTyped(evt);
            }
        });
        jPanel1.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 200, 30));

        bott1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/comprobar (1).png"))); // NOI18N
        bott1.setText("Aceptar");
        bott1.setDoubleBuffered(true);
        bott1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bott1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bott1FocusGained(evt);
            }
        });
        bott1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bott1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bott1MousePressed(evt);
            }
        });
        bott1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bott1ActionPerformed(evt);
            }
        });
        bott1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bott1KeyPressed(evt);
            }
        });
        jPanel1.add(bott1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 100, 30));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("ATENCIÓN: SE ADMITEN SOLAMENTE LETRAS Y NUMEROS EN LOS CAMPOS USER Y CONTRASEÑA!!");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 580, 30));

        bott2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cruzar (1).png"))); // NOI18N
        bott2.setText("Cerrar");
        bott2.setDoubleBuffered(true);
        bott2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bott2.setPreferredSize(new java.awt.Dimension(99, 33));
        bott2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bott2FocusGained(evt);
            }
        });
        bott2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bott2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bott2MousePressed(evt);
            }
        });
        bott2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bott2ActionPerformed(evt);
            }
        });
        bott2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bott2KeyPressed(evt);
            }
        });
        jPanel1.add(bott2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 100, 30));

        jocLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo58x125.png"))); // NOI18N
        jPanel1.add(jocLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 100, 150));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKeyTyped
        //Este comando sirve para cuando el usuario termine de escribir
        // el usuario y contraseña al darle enter haga focus en aceptar para
        //pasar a la pagina siguiente !
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            bott1.doClick();
        }
        // Valida solo letras y numero el contenido de abajo en los campos
        //de textos.
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c
                < '0' || c > '9')) {
            evt.consume();
        }
        //Este comando sirve para pasar el focus entre los jtextfield apretando la tecla tab o cualquier
        //tecla especifica !
        user.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, java.util.Collections.EMPTY_SET);
        if (evt.getKeyChar() == KeyEvent.VK_TAB) {
            pass.requestFocus();
        }
    }//GEN-LAST:event_userKeyTyped

    private void passKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyTyped
        //Este comando sirve para cuando el usuario termine de escribir
        // el usuario y contraseña al darle enter haga focus en aceptar para
        //pasar a la pagina siguiente !
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_ENTER) {
            bott1.doClick();
        }
        // Valida solo letras y numero el contenido de abajo en los campos
        //de textos.
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c
                < '0' || c > '9')) {
            evt.consume();
        }
        //Este comando sirve para pasar el focus entre los jtextfield apretando la tecla tab o cualquier
        //tecla especifica !
        pass.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, java.util.Collections.EMPTY_SET);
        if (evt.getKeyChar() == KeyEvent.VK_TAB) {
            user.requestFocus();
        }
    }//GEN-LAST:event_passKeyTyped

    private void bott1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bott1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_bott1FocusGained

    private void bott1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bott1MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 3) {
            System.out.println("hola");
        }
    }//GEN-LAST:event_bott1MouseClicked

    private void bott1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bott1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bott1MousePressed

    private void bott1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bott1ActionPerformed
        // TODO add your handling code here:

        int cont = 3;

        // declaro la variable para tirarla true o false de el
        //registro de inicio de sesion.
        boolean V = false;
        // Creo objeto consultas bd donde tengo el metodo de verificacion
        // user pass
        ConsultaInicioSesion con = new ConsultaInicioSesion();
        // le paso user and pass de textfields
        U = user.getText();
        P = new String(pass.getPassword());

        //verifico que campos no esten vacios
        if ((U.length() == 0) || (P.length() == 0)) {
            JOptionPane.showMessageDialog(null, "Llene todos los campos por favor", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            //valido usuario con el metodo validar sesion de consultasBD
            V = con.validarsesion(U, P);

            //si es true camia de ventana si no larga error mostrado por
            //JOptionPane
            if (V == true) {
                System.out.println(P + " " + " " + U);

                Menu men = new Menu();
                men.setVisible(true);

                dispose();
            } else {

                JOptionPane.showMessageDialog(null, "Clave y/o Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }
    }//GEN-LAST:event_bott1ActionPerformed

    private void bott1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bott1KeyPressed
        // TODO add your handling code here:
        char car = (char) evt.getKeyCode();
        if (car == evt.VK_ENTER) {
            bott1.doClick();
        }
    }//GEN-LAST:event_bott1KeyPressed

    private void bott2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bott2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_bott2FocusGained

    private void bott2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bott2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bott2MouseClicked

    private void bott2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bott2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bott2MousePressed

    private void bott2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bott2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bott2ActionPerformed

    private void bott2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bott2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bott2KeyPressed
// Comando grega icono a la pantalla
// Activar icoimagen en propiedades y elegiir seunda opcin    
/*
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("/Imagenes/Logo.png"));
        
        return retValue;
        
    }
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bott1;
    private javax.swing.JButton bott2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private com.xzq.osc.JocLabel jocLabel1;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
