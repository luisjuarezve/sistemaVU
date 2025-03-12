/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.luisjuarez.sistemavu.view.formulario;

import com.luisjuarez.sistemavu.model.Empleado;
import com.luisjuarez.sistemavu.view.SistemaPrincipal;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Helen
 */
public class FormularioRecuperarContrasena extends javax.swing.JFrame {

    private Empleado empleado;

    /**
     * Creates new form FormularioTasaDolar
     */
    public FormularioRecuperarContrasena(int idEmpleado) {
        initComponents();
        empleado = SistemaPrincipal.getEmpleadoService().buscarEmpleadoPorId(idEmpleado);
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
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        roundedButton1 = new com.luisjuarez.sistemavu.view.components.RoundedButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 300));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 30, 30));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Recuperar Contraseña");
        jLabel1.setPreferredSize(new java.awt.Dimension(173, 100));
        jPanel1.add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(312, 100));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Contraseña: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel2.add(jLabel2, gridBagConstraints);

        jPasswordField1.setMinimumSize(new java.awt.Dimension(94, 26));
        jPasswordField1.setPreferredSize(new java.awt.Dimension(94, 26));
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel2.add(jPasswordField1, gridBagConstraints);

        jLabel3.setForeground(new java.awt.Color(30, 30, 30));
        jLabel3.setText("Repetir Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel3, gridBagConstraints);

        jPasswordField2.setMinimumSize(new java.awt.Dimension(94, 26));
        jPasswordField2.setPreferredSize(new java.awt.Dimension(94, 26));
        jPasswordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField2KeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        jPanel2.add(jPasswordField2, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 100));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 25));

        roundedButton1.setBackground(new java.awt.Color(0, 0, 255));
        roundedButton1.setForeground(new java.awt.Color(255, 255, 255));
        roundedButton1.setText("Guardar");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed

        boolean datosValidos = false; // Variable para controlar la validez de los datos

        while (!datosValidos) { // Mantener el bucle hasta que los datos sean válidos
            String password = new String(jPasswordField1.getPassword());
            String repassword = new String(jPasswordField2.getPassword());

            // Validar que las contraseñas no estén vacías
            if (password.isEmpty() || repassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Los campos de contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la ejecución
            }

            // Validar que las contraseñas sean iguales
            if (password.equals(repassword)) {
                empleado.setContrasena(password);
                SistemaPrincipal.getEmpleadoService().modificarEmpleado(empleado);
                JOptionPane.showMessageDialog(this, "La contraseña se actualizó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                datosValidos = true; // Salir del bucle
            } else {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                break; // Permite reintentar ingresando contraseñas
            }
            this.dispose();
        }
    }//GEN-LAST:event_roundedButton1ActionPerformed

    private void jPasswordField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean datosValidos = false; // Variable para controlar la validez de los datos

            while (!datosValidos) { // Mantener el bucle hasta que los datos sean válidos
                String password = new String(jPasswordField1.getPassword());
                String repassword = new String(jPasswordField2.getPassword());

                // Validar que las contraseñas no estén vacías
                if (password.isEmpty() || repassword.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Los campos de contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución
                }

                // Validar que las contraseñas sean iguales
                if (password.equals(repassword)) {
                    empleado.setContrasena(password);
                    SistemaPrincipal.getEmpleadoService().modificarEmpleado(empleado);
                    JOptionPane.showMessageDialog(this, "La contraseña se actualizó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    datosValidos = true; // Salir del bucle
                } else {
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                    break; // Permite reintentar ingresando contraseñas
                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_jPasswordField2KeyPressed

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean datosValidos = false; // Variable para controlar la validez de los datos

            while (!datosValidos) { // Mantener el bucle hasta que los datos sean válidos
                String password = new String(jPasswordField1.getPassword());
                String repassword = new String(jPasswordField2.getPassword());

                // Validar que las contraseñas no estén vacías
                if (password.isEmpty() || repassword.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Los campos de contraseña no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la ejecución
                }

                // Validar que las contraseñas sean iguales
                if (password.equals(repassword)) {
                    empleado.setContrasena(password);
                    SistemaPrincipal.getEmpleadoService().modificarEmpleado(empleado);
                    JOptionPane.showMessageDialog(this, "La contraseña se actualizó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    datosValidos = true; // Salir del bucle
                } else {
                    JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden. Por favor, inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                    break; // Permite reintentar ingresando contraseñas
                }
                this.dispose();
            }
        }
    }//GEN-LAST:event_jPasswordField1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private com.luisjuarez.sistemavu.view.components.RoundedButton roundedButton1;
    // End of variables declaration//GEN-END:variables
}
