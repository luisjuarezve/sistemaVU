/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.luisjuarez.sistemavu.view.formulario;

import com.luisjuarez.sistemavu.model.Cliente;
import com.luisjuarez.sistemavu.model.Empleado;
import com.luisjuarez.sistemavu.view.SistemaPrincipal;
import com.luisjuarez.sistemavu.view.paneles.Panel_Facturar;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Helen
 */
public class Formulario_Buscar_Cliente extends javax.swing.JFrame {

    private Panel_Facturar panel_facturar;

    /**
     * Creates new form FormularioTasaDolar
     */
    public Formulario_Buscar_Cliente(Panel_Facturar panel_facturar) {
        initComponents();
        this.panel_facturar = panel_facturar;
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmb_tipoDoc = new javax.swing.JComboBox<>();
        txt_nroDoc = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        roundedButton1 = new com.luisjuarez.sistemavu.view.components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 200));
        setPreferredSize(new java.awt.Dimension(300, 200));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setForeground(new java.awt.Color(30, 30, 30));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 30, 30));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Busqueda De Cliente");
        jLabel1.setPreferredSize(new java.awt.Dimension(173, 50));
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Tipo Doc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setForeground(new java.awt.Color(30, 30, 30));
        jLabel3.setText("N° Doc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel2.add(jLabel3, gridBagConstraints);

        cmb_tipoDoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- ", "V", "J", "G", "P", "E" }));
        cmb_tipoDoc.setMinimumSize(new java.awt.Dimension(50, 25));
        cmb_tipoDoc.setPreferredSize(new java.awt.Dimension(50, 25));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel2.add(cmb_tipoDoc, gridBagConstraints);

        txt_nroDoc.setPreferredSize(new java.awt.Dimension(100, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        jPanel2.add(txt_nroDoc, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        roundedButton1.setBackground(new java.awt.Color(0, 0, 255));
        roundedButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton1.setText("Buscar");
        roundedButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roundedButton1.setMinimumSize(new java.awt.Dimension(100, 30));
        roundedButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        roundedButton1.setRoundBottomLeft(10);
        roundedButton1.setRoundBottomRight(10);
        roundedButton1.setRoundTopLeft(10);
        roundedButton1.setRoundTopRight(10);
        roundedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(roundedButton1);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        String tipo_doc = cmb_tipoDoc.getSelectedItem().toString();
        String nro_doc = txt_nroDoc.getText();

        if (cmb_tipoDoc.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de documento válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!nro_doc.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "El número de documento debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = SistemaPrincipal.getClienteService().buscarClientePorDocumento(tipo_doc, nro_doc);
        if (cliente != null) {
            SistemaPrincipal.setCliente(cliente);
            panel_facturar.cargarCliente();
            panel_facturar.verificarEstadoBotonPagar();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Cédula no registrada en el Sistema", "Registrar cliente", JOptionPane.QUESTION_MESSAGE);
            Formulario_Cliente fr = new Formulario_Cliente(tipo_doc, nro_doc);
            fr.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_roundedButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_tipoDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.luisjuarez.sistemavu.view.components.RoundedButton roundedButton1;
    private javax.swing.JTextField txt_nroDoc;
    // End of variables declaration//GEN-END:variables
}
