/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.luisjuarez.sistemavu.view.paneles;

import com.luisjuarez.sistemavu.view.Formulario_Modificar.Formulario_Aumentar_inventario;
import com.luisjuarez.sistemavu.view.Formulario_Modificar.Formulario_Disminuir_inventario;
import com.luisjuarez.sistemavu.view.Formulario_Modificar.Formulario_inventario_Modificar;
import com.luisjuarez.sistemavu.view.SistemaPrincipal;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Usuario
 */
public class Panel_Inventario extends javax.swing.JPanel {

    private Timer timer = new Timer();
    
    /**
     * Creates new form Panel_Inventario
     */
    public Panel_Inventario(Dimension Size) {
        try {
            initComponents();
            SistemaPrincipal.getInventarioService().cargarTabla(TableInventario);
        } catch (SQLException ex) {
            Logger.getLogger(Panel_Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        ContenedorBarraBusqueda = new javax.swing.JPanel();
        roundedPanel1 = new com.luisjuarez.sistemavu.view.components.RoundedPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_buscador = new javax.swing.JTextField();
        separador = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ContenedorTable = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableInventario = new com.luisjuarez.sistemavu.view.components.CustomTable();
        ContenedorBotones = new javax.swing.JPanel();
        roundedPanel2 = new com.luisjuarez.sistemavu.view.components.RoundedPanel();
        btn_Modificar = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_Regresar = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_Regresar1 = new com.luisjuarez.sistemavu.view.components.RoundedButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 550));
        setLayout(new java.awt.BorderLayout());

        ContenedorBarraBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        ContenedorBarraBusqueda.setPreferredSize(new java.awt.Dimension(100, 90));
        ContenedorBarraBusqueda.setLayout(new java.awt.GridBagLayout());

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setPreferredSize(new java.awt.Dimension(800, 60));
        roundedPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        roundedPanel1.add(jLabel1);

        txt_buscador.setBackground(new java.awt.Color(153, 204, 255));
        txt_buscador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_buscador.setForeground(new java.awt.Color(30, 30, 30));
        txt_buscador.setText("Introduce el código o nombre del producto");
        txt_buscador.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        txt_buscador.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_buscador.setPreferredSize(new java.awt.Dimension(400, 30));
        txt_buscador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscadorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_buscadorFocusLost(evt);
            }
        });
        txt_buscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_buscadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscadorKeyReleased(evt);
            }
        });
        roundedPanel1.add(txt_buscador);

        separador.setOpaque(false);
        separador.setPreferredSize(new java.awt.Dimension(100, 50));
        roundedPanel1.add(separador);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Inventario");
        roundedPanel1.add(jLabel2);

        ContenedorBarraBusqueda.add(roundedPanel1, new java.awt.GridBagConstraints());

        add(ContenedorBarraBusqueda, java.awt.BorderLayout.PAGE_START);

        ContenedorTable.setBackground(new java.awt.Color(255, 255, 255));
        ContenedorTable.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(700, 450));

        TableInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableInventario.setPreferredSize(new java.awt.Dimension(600, 600));
        jScrollPane1.setViewportView(TableInventario);

        ContenedorTable.add(jScrollPane1, new java.awt.GridBagConstraints());

        add(ContenedorTable, java.awt.BorderLayout.CENTER);

        ContenedorBotones.setBackground(new java.awt.Color(255, 255, 255));
        ContenedorBotones.setPreferredSize(new java.awt.Dimension(200, 0));
        ContenedorBotones.setLayout(new java.awt.GridBagLayout());

        roundedPanel2.setBackground(new java.awt.Color(153, 204, 255));
        roundedPanel2.setPreferredSize(new java.awt.Dimension(200, 400));
        roundedPanel2.setRoundBottomLeft(15);
        roundedPanel2.setRoundBottomRight(15);
        roundedPanel2.setRoundTopLeft(15);
        roundedPanel2.setRoundTopRight(15);
        roundedPanel2.setLayout(new java.awt.GridBagLayout());

        btn_Modificar.setBackground(new java.awt.Color(0, 0, 102));
        btn_Modificar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Modificar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Modificar.setText("Modificar");
        btn_Modificar.setFocusable(false);
        btn_Modificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Modificar.setMaximumSize(new java.awt.Dimension(120, 120));
        btn_Modificar.setMinimumSize(new java.awt.Dimension(120, 120));
        btn_Modificar.setPreferredSize(new java.awt.Dimension(120, 140));
        btn_Modificar.setRoundBottomLeft(10);
        btn_Modificar.setRoundBottomRight(10);
        btn_Modificar.setRoundTopLeft(10);
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        roundedPanel2.add(btn_Modificar, gridBagConstraints);

        btn_Regresar.setBackground(new java.awt.Color(0, 0, 102));
        btn_Regresar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Regresar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Regresar.setText("Aumentar");
        btn_Regresar.setFocusable(false);
        btn_Regresar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Regresar.setMaximumSize(new java.awt.Dimension(120, 120));
        btn_Regresar.setMinimumSize(new java.awt.Dimension(120, 120));
        btn_Regresar.setPreferredSize(new java.awt.Dimension(120, 140));
        btn_Regresar.setRoundBottomLeft(10);
        btn_Regresar.setRoundBottomRight(10);
        btn_Regresar.setRoundTopLeft(10);
        btn_Regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegresarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 0);
        roundedPanel2.add(btn_Regresar, gridBagConstraints);

        btn_Regresar1.setBackground(new java.awt.Color(0, 0, 102));
        btn_Regresar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_Regresar1.setForeground(new java.awt.Color(255, 255, 255));
        btn_Regresar1.setText("Disminuir");
        btn_Regresar1.setFocusable(false);
        btn_Regresar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Regresar1.setMaximumSize(new java.awt.Dimension(120, 120));
        btn_Regresar1.setMinimumSize(new java.awt.Dimension(120, 120));
        btn_Regresar1.setPreferredSize(new java.awt.Dimension(120, 140));
        btn_Regresar1.setRoundBottomLeft(10);
        btn_Regresar1.setRoundBottomRight(10);
        btn_Regresar1.setRoundTopLeft(10);
        btn_Regresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Regresar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        roundedPanel2.add(btn_Regresar1, gridBagConstraints);

        ContenedorBotones.add(roundedPanel2, new java.awt.GridBagConstraints());

        add(ContenedorBotones, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscadorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscadorFocusGained
        if (txt_buscador.getText().equalsIgnoreCase("Introduce el código o nombre del producto")) {
            txt_buscador.setText("");
        }
    }//GEN-LAST:event_txt_buscadorFocusGained

    private void txt_buscadorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscadorFocusLost
        if (txt_buscador.getText().isEmpty()) {
            txt_buscador.setText("Introduce el código o nombre del producto");
        }
    }//GEN-LAST:event_txt_buscadorFocusLost

    private void txt_buscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscadorKeyReleased
        timer.cancel(); // Cancelar el temporizador anterior
        timer = new Timer(); // Crear un nuevo temporizador
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    try {
                        SistemaPrincipal.getInventarioService().cargarTabla(TableInventario, txt_buscador.getText());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al cargar la tabla de Clientes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        }, 300);
    }//GEN-LAST:event_txt_buscadorKeyReleased

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed
          if (TableInventario.getRowCount() > 0) {
            if (TableInventario.getSelectedRow() != -1) {
                String Id_inventario = String.valueOf(TableInventario.getValueAt(TableInventario.getSelectedRow(), 0));
                String codigo = String.valueOf(TableInventario.getValueAt(TableInventario.getSelectedRow(), 1));
                Formulario_inventario_Modificar Fi= new Formulario_inventario_Modificar(TableInventario, SistemaPrincipal.getInventarioService().buscarInventarioPorId(Integer.parseInt(Id_inventario)), SistemaPrincipal.getProductoService().buscarProductoPorCodigo(codigo));
                Fi.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un inventario", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla de inventario está vacía", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
         
    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void btn_RegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegresarActionPerformed
        if (TableInventario.getRowCount() > 0) {
            if (TableInventario.getSelectedRow() != -1) {
                String Id_inventario = String.valueOf(TableInventario.getValueAt(TableInventario.getSelectedRow(), 0));
                Formulario_Aumentar_inventario fa = new Formulario_Aumentar_inventario(TableInventario, SistemaPrincipal.getInventarioService().buscarInventarioPorId(Integer.parseInt(Id_inventario)));
                fa.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un inventario", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla de inventario está vacía", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_RegresarActionPerformed

    private void btn_Regresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Regresar1ActionPerformed
        if (TableInventario.getRowCount() > 0) {
            if (TableInventario.getSelectedRow() != -1) {
                String Id_inventario = String.valueOf(TableInventario.getValueAt(TableInventario.getSelectedRow(), 0));
                Formulario_Disminuir_inventario fd = new Formulario_Disminuir_inventario(TableInventario, SistemaPrincipal.getInventarioService().buscarInventarioPorId(Integer.parseInt(Id_inventario)));
                fd.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un inventario", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La tabla de inventario está vacía", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_Regresar1ActionPerformed

    private void txt_buscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscadorKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (SistemaPrincipal.getProductoService().buscarProductosPorPalabraClave(txt_buscador.getText()).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Inventario no registrado en la base de datos", "Busqueda Fallida", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_txt_buscadorKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContenedorBarraBusqueda;
    private javax.swing.JPanel ContenedorBotones;
    private javax.swing.JPanel ContenedorTable;
    private com.luisjuarez.sistemavu.view.components.CustomTable TableInventario;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_Modificar;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_Regresar;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_Regresar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.luisjuarez.sistemavu.view.components.RoundedPanel roundedPanel1;
    private com.luisjuarez.sistemavu.view.components.RoundedPanel roundedPanel2;
    private javax.swing.JPanel separador;
    private javax.swing.JTextField txt_buscador;
    // End of variables declaration//GEN-END:variables
}
