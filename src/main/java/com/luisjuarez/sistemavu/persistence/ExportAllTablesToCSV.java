package com.luisjuarez.sistemavu.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ExportAllTablesToCSV {

    public ExportAllTablesToCSV() {
        String[] tables = {"Cliente", "Proveedor", "ContactoProveedor", "Empleado", "Categoria", "Producto", "Inventario",
                           "Permiso", "Empleado_has_Permiso", "Factura", "DetalleFactura", "Compra", "DetalleCompra"};

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Archivo CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String csvFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!csvFilePath.endsWith(".csv")) {
                csvFilePath += ".csv";
            }

            try (Connection connection = ConexionBDDMysql.getConnection();
                 FileWriter fileWriter = new FileWriter(csvFilePath)) {

                for (String table : tables) {
                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table)) {

                        int columnCount = resultSet.getMetaData().getColumnCount();
                        
                        // Escribir nombre de la tabla
                        fileWriter.append("Tabla: ").append(table).append("\n");

                        // Escribir encabezados de columna
                        for (int i = 1; i <= columnCount; i++) {
                            fileWriter.append("\"").append(resultSet.getMetaData().getColumnName(i)).append("\"");
                            if (i < columnCount) fileWriter.append(",");
                        }
                        fileWriter.append("\n");

                        // Escribir datos de filas
                        while (resultSet.next()) {
                            for (int i = 1; i <= columnCount; i++) {
                                String value = resultSet.getString(i);
                                fileWriter.append("\"").append(value != null ? value : "NULL").append("\"");
                                if (i < columnCount) fileWriter.append(",");
                            }
                            fileWriter.append("\n");
                        }

                        fileWriter.append("\n"); // Espacio entre tablas
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                JOptionPane.showMessageDialog(null, "Archivo guardado en: " + csvFilePath, "Guardado Exitoso!", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El usuario canceló la operación", "Guardado Fallido!", JOptionPane.WARNING_MESSAGE);
        }
    }
}
