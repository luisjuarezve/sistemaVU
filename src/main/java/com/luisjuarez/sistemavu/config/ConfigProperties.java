package com.luisjuarez.sistemavu.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ConfigProperties {

    private Properties properties = new Properties();
    private String propertiesFilePath = "src/main/resources/config.properties"; // Define la ruta completa del archivo de propiedades

    public ConfigProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                JOptionPane.showMessageDialog(null, "No se encontró el archivo config.properties", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo config.properties: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void saveProperties() {
        try (OutputStream output = new FileOutputStream(propertiesFilePath)) {
            properties.store(output, null);
            JOptionPane.showMessageDialog(null, "Archivo config.properties actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo config.properties: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void recargarArchivo() {
        try (InputStream input = new FileInputStream(propertiesFilePath)) { // Usa la ruta absoluta
            properties.load(input);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al recargar el archivo config.properties: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
