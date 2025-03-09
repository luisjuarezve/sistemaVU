package com.luisjuarez.sistemavu.utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author conta
 */
public class ImagesUtils {
     public static ImageIcon redimensionarIcon (String url, int width,int height){
        ImageIcon icon = new ImageIcon(url);
        // Obtener la imagen original
        Image img = icon.getImage();
        // Redimensionar la imagen
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Crear un nuevo ImageIcon con la imagen redimensionada
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;
    }
     
    public static ImageIcon redimensionarIcon(URL resource, int width,int height) {
        ImageIcon icon = new ImageIcon(resource);
        // Obtener la imagen original
        Image img = icon.getImage();
        // Redimensionar la imagen
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Crear un nuevo ImageIcon con la imagen redimensionada
        ImageIcon newIcon = new ImageIcon(newImg);
        return newIcon;
    }
}
