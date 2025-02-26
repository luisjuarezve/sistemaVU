package com.luisjuarez.sistemavu.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigProperties {
    private Properties properties = new Properties();
    private String propertiesFilePath = "src/main/resources/config.properties"; // Define la ruta completa del archivo de propiedades

    public ConfigProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
