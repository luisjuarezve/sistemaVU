package com.luisjuarez.sistemavu.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class SearchBar extends JTextField{
    
    public SearchBar() {
        super();
        initialize();
    }
    
    private void initialize() {
        setFont(new Font("Segoe UI", Font.PLAIN, 16));
        setForeground(new Color(153,153,153));
        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, this.getForeground());
        Border emptyBorder = BorderFactory.createEmptyBorder(2, 10, 2, 6);
        setBorder(new CompoundBorder(matteBorder, emptyBorder));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
    }
    
}
