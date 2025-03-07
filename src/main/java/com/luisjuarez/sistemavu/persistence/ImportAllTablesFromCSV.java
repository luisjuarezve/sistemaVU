package com.luisjuarez.sistemavu.persistence;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportAllTablesFromCSV {

    public ImportAllTablesFromCSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Archivo CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("CSV Files", "csv"));

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String csvFilePath = fileChooser.getSelectedFile().getAbsolutePath();

            try (Connection connection = ConexionBDDMysql.getConnection();
                 CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {

                // Deshabilitar las verificaciones de claves foráneas
                disableForeignKeyChecks(connection);

                // Orden de eliminación de tablas basado en las dependencias de claves foráneas
                String[] tableOrder = {"DetalleFactura", "Factura", "DetalleCompra", "Compra", 
                                       "Inventario", "Producto", "ContactoProveedor", "Proveedor", 
                                       "Empleado_has_Permiso", "Permiso", "Categoria", "Cliente", "Empleado"};

                // Eliminar datos de todas las tablas en el orden correcto
                for (String table : tableOrder) {
                    clearTable(connection, table);
                }

                String[] nextLine;
                String currentTable = "";
                boolean skipHeader = true;

                while ((nextLine = csvReader.readNext()) != null) {
                    // Detectar cambio de tabla
                    if (nextLine.length > 0 && nextLine[0].startsWith("Tabla: ")) {
                        currentTable = nextLine[0].substring(7);
                        skipHeader = true;
                        continue;
                    }

                    // Saltar la fila de encabezado
                    if (skipHeader) {
                        skipHeader = false;
                        continue;
                    }

                    // Insertar datos si la tabla es válida y la línea no está vacía
                    if (!currentTable.isEmpty() && nextLine.length > 1) {
                        insertData(connection, currentTable, nextLine);
                    }
                }

                // Habilitar las verificaciones de claves foráneas
                enableForeignKeyChecks(connection);

                System.out.println("Datos importados exitosamente desde " + csvFilePath);
            } catch (SQLException | CsvValidationException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void disableForeignKeyChecks(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        }
    }

    private void enableForeignKeyChecks(Connection connection) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SET FOREIGN_KEY_CHECKS=1");
        }
    }

    private void clearTable(Connection connection, String tableName) throws SQLException {
        String deleteSQL = "DELETE FROM " + tableName;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(deleteSQL);
        }
    }

    private void insertData(Connection connection, String tableName, String[] values) throws SQLException {
        // Obtener el número de columnas de la tabla
        String selectSQL = "SELECT * FROM " + tableName + " LIMIT 1";
        try (PreparedStatement pstmt = connection.prepareStatement(selectSQL);
             ResultSet resultSet = pstmt.executeQuery()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            System.out.println("Tabla: " + tableName + " - Columnas esperadas: " + columnCount + ", Valores recibidos: " + values.length);
            
            if (values.length != columnCount) {
                throw new SQLException("El número de columnas no coincide con el número de valores.");
            }

            String insertSQL = generateInsertSQL(tableName, columnCount);
            try (PreparedStatement insertPstmt = connection.prepareStatement(insertSQL)) {
                for (int i = 0; i < values.length; i++) {
                    insertPstmt.setString(i + 1, values[i].equals("NULL") ? null : values[i]);
                }
                insertPstmt.executeUpdate();
            }
        }
    }

    private String generateInsertSQL(String tableName, int valueCount) {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(tableName).append(" VALUES (");
        for (int i = 0; i < valueCount; i++) {
            sql.append("?");
            if (i < valueCount - 1) {
                sql.append(", ");
            }
        }
        sql.append(");");
        return sql.toString();
    }

    public static void main(String[] args) {
        new ImportAllTablesFromCSV();
    }
}
