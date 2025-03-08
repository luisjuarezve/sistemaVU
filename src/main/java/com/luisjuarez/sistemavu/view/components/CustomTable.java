/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.luisjuarez.sistemavu.view.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class CustomTable extends JTable {

    public CustomTable() {
        this(new Object[][]{}, new Object[]{});
    }

    public CustomTable(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        setCellRenderer();
        setHeaderRenderer();
    }

    private void setCellRenderer() {
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    setBackground(new Color(153,204,255)); // Color para filas pares
                } else {
                    setBackground(new Color(255,255,255)); // Color para filas impares
                }
                return this;
            }
        });
    }

    private void setHeaderRenderer() {
        JTableHeader header = getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(new Color(0,0,102)); // Color de fondo del encabezado
                setForeground(Color.WHITE); // Color del texto del encabezado
                return this;
            }
        });
    }
}
