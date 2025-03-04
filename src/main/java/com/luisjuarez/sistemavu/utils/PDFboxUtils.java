package com.luisjuarez.sistemavu.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

/**
 *
 * @author conta
 */
public class PDFboxUtils {

    public static float textCenter(float stringWidth, float pageWidth, int fontSize) {
        float stringWidthInPoints = stringWidth / 1000 * fontSize;
        return (pageWidth - stringWidthInPoints) / 2;
    }

    public static float textEnd(float stringWidth, float pageWidth, int fontSize) {
        float stringWidthInPoints = stringWidth / 1000 * fontSize;
        return pageWidth - stringWidthInPoints - 40;
    }

    public static PDPage createNewPage(PDDocument document, PDRectangle pdRectangle) {
        PDPage newPage = new PDPage(pdRectangle);
        document.addPage(newPage);
        return newPage;
    }

    public static void drawTableHeaders(PDPageContentStream contentStream, PDType0Font font, int fontSize, String[] headers, float[] columnWidths, float margin, float yPosition, Color headerColor, Color textColor, float rowHeight) throws IOException {
        float headerXPosition = margin; // Inicializar headerXPosition con margin

        for (int i = 0; i < headers.length; i++) {
            contentStream.setNonStrokingColor(headerColor); // Color del encabezado
            contentStream.addRect(headerXPosition, yPosition - rowHeight, columnWidths[i], rowHeight); // Dibujar el rectángulo de fondo
            contentStream.fill();
            contentStream.setNonStrokingColor(textColor); // Color del texto
            addTextCenter(contentStream, font, headers[i], fontSize, headerXPosition, yPosition - rowHeight + (rowHeight - 8) / 2, columnWidths[i]); // Agregar texto centrado y ajustar posición vertical
            headerXPosition += columnWidths[i]; // Actualizar headerXPosition para la siguiente columna
        }
    }

    public static void drawTableFactura(PDPageContentStream contentStream, PDType0Font font, int fontSize, String[] headers, float[] columnWidths, float margin, float yPosition, Color headerColor, Color textColor, float rowHeight) throws IOException {
        float headerXPosition = margin; // Inicializar headerXPosition con margin
        Color random;
        for (int i = 0; i < headers.length; i++) {
            if (i>=1) {
                contentStream.setNonStrokingColor(headerColor); // Color del encabezado
                contentStream.addRect(headerXPosition, yPosition - rowHeight, columnWidths[i], rowHeight); // Dibujar el rectángulo de fondo
                contentStream.fill();
                contentStream.setNonStrokingColor(textColor); // Color del texto
                if (i>=3) {
                    addTextStart(contentStream, font, headers[i], fontSize, headerXPosition, yPosition - rowHeight + (rowHeight - fontSize) / 2, columnWidths[i]); // Agregar texto centrado y ajustar posición vertical
                }else{
                    addTextCenter(contentStream, font, headers[i], fontSize, headerXPosition, yPosition - rowHeight + (rowHeight - fontSize) / 2, columnWidths[i]);
                }
            }
            headerXPosition += columnWidths[i]; // Actualizar headerXPosition para la siguiente columna
        }
    }

    public static void drawTableRows(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float xPosition, float yPosition, float columnWidth, Color cellColor, Color textColor, float rowHeight) throws IOException {
        contentStream.setNonStrokingColor(textColor); // Color del texto
        addTextCenter(contentStream, font, text, fontSize, xPosition, yPosition - rowHeight + (rowHeight - fontSize) / 2, columnWidth); // Agregar texto centrado y ajustar posición vertical
    }

    public static void drawTableRows(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float xPosition, float yPosition, float columnWidth, Color cellColor, Color textColor, float rowHeight, int iteracion) throws IOException {
        contentStream.setNonStrokingColor(textColor); // Color del texto
        if (iteracion > 2) {
            addTextStart(contentStream, font, text, fontSize, xPosition, yPosition - rowHeight + (rowHeight - fontSize) / 2, columnWidth);
        } else {
            addTextCenter(contentStream, font, text, fontSize, xPosition, yPosition - rowHeight + (rowHeight - fontSize) / 2, columnWidth); // Agregar texto centrado y ajustar posición vertical
        }
    }

    public static void drawTableRowsStart(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float xPosition, float yPosition, float columnWidth, Color cellColor, Color textColor, float rowHeight) throws IOException {
        contentStream.setNonStrokingColor(textColor); // Color del texto
        addTextStart(contentStream, font, text, fontSize, xPosition, yPosition - rowHeight + (rowHeight - fontSize) / 2, columnWidth); // Agregar texto centrado y ajustar posición vertical
    }

    public static void addTextStart(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float xPosition, float yPosition, float containerWidth) throws IOException {
        float startX = xPosition + 20;
        contentStream.setFont(font, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yPosition);
        contentStream.showText(text);
        contentStream.endText();
    }

    public static void addTextCenter(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float yPosition, float containerWidth) throws IOException {
        float textWidth = font.getStringWidth(text) / 1000 * fontSize;
        float startX = (containerWidth - textWidth) / 2;
        contentStream.setFont(font, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yPosition);
        contentStream.showText(text);
        contentStream.endText();
    }

    public static void addTextCenter(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float xPosition, float yPosition, float containerWidth) throws IOException {
        float textWidth = font.getStringWidth(text) / 1000 * fontSize;
        float startX = xPosition + (containerWidth - textWidth) / 2;
        contentStream.setFont(font, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yPosition);
        contentStream.showText(text);
        contentStream.endText();
    }

    public static void addTextEnd(PDPageContentStream contentStream, PDType0Font font, String text, int fontSize, float yPosition, float pageWidth) throws IOException {
        float textWidth = font.getStringWidth(text);
        float startX = PDFboxUtils.textEnd(textWidth, pageWidth, fontSize);
        contentStream.setFont(font, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yPosition);
        contentStream.showText(text);
        contentStream.endText();
    }

    public static void addPageNumber(PDPageContentStream contentStream, PDType0Font font, int fontSize, int pageNum, float pageWidth, float yPosition) throws IOException {
        String pageNumberText = pageNum + "";
        float textWidth = font.getStringWidth(pageNumberText) / 1000 * fontSize;
        float startX = PDFboxUtils.textEnd(textWidth, pageWidth, fontSize);
        contentStream.setFont(font, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yPosition);
        contentStream.showText(pageNumberText);
        contentStream.endText();
    }

    public static float getCumulativeColumnWidth(float[] columnWidths, int upToIndex, float margin) {
        float cumulativeWidth = margin;
        for (int i = 0; i < upToIndex; i++) {
            cumulativeWidth += columnWidths[i];
        }
        return cumulativeWidth;
    }
    
   

}
