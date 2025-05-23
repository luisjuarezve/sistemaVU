/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.luisjuarez.sistemavu.view.formulario;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Helen
 */
public class FormularioAjusteIva extends javax.swing.JFrame {

    private ConfigProperties config = new ConfigProperties();

    /**
     * Creates new form FormularioTasaDolar
     */
    public FormularioAjusteIva() {
        initComponents();
        config.recargarArchivo();
        jTextField1.setText(config.getProperty("configuracion.iva"));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        roundedButton1 = new com.luisjuarez.sistemavu.view.components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 300));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setForeground(new java.awt.Color(30, 30, 30));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 30, 30));
        jLabel1.setText("Ajustar Iva");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jTextField1.setPreferredSize(new java.awt.Dimension(100, 25));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel1.add(jTextField1, gridBagConstraints);

        roundedButton1.setBackground(new java.awt.Color(0, 0, 255));
        roundedButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton1.setText("Guardar");
        roundedButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roundedButton1.setPreferredSize(new java.awt.Dimension(100, 30));
        roundedButton1.setRoundBottomLeft(10);
        roundedButton1.setRoundBottomRight(10);
        roundedButton1.setRoundTopLeft(10);
        roundedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel1.add(roundedButton1, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        String iva = jTextField1.getText();

        if (iva.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo de Iva no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Detener la ejecución
        }

        try {
            double tasaNumerica = Double.parseDouble(iva);
            if (tasaNumerica <= 0) {
                JOptionPane.showMessageDialog(null, "El Iva debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución
            }
            // Si es válido, guardarlo en config.properties
            config.setProperty("configuracion.iva", iva);
            config.saveProperties();
            JOptionPane.showMessageDialog(null, "Iva guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El Iva debe ser un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_roundedButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String iva = jTextField1.getText();

            if (iva.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El campo de Iva no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución
            }

            try {
                double tasaNumerica = Double.parseDouble(iva);
                if (tasaNumerica <= 0) {
                    JOptionPane.showMessageDialog(null, "El Iva debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución
                }
                // Si es válido, guardarlo en config.properties
                config.setProperty("configuracion.iva", iva);
                config.saveProperties();
                JOptionPane.showMessageDialog(null, "Iva guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El Iva debe ser un valor numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private com.luisjuarez.sistemavu.view.components.RoundedButton roundedButton1;
    // End of variables declaration//GEN-END:variables
}
