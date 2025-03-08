package com.luisjuarez.sistemavu.utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class JTableUtils {
    
    public static void ajustarAnchoCelda(JTable tabla){
        for (int col = 0; col < tabla.getColumnCount(); col++) {
            int maxWidth = 0;
            for (int row = 0; row < tabla.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tabla.getCellRenderer(row, col);
                Component c = tabla.prepareRenderer(cellRenderer, row, col);
                maxWidth = Math.max(c.getPreferredSize().width + tabla.getIntercellSpacing().width, maxWidth);
            }
            tabla.getColumnModel().getColumn(col).setPreferredWidth(maxWidth);
        }
    }
    
    public static void centrarTitulosEncabezado(JTable tabla){
        DefaultTableCellRenderer centroRenderer = new DefaultTableCellRenderer();
        centroRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centroRenderer);
        }
        ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    
}
