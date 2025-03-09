package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import com.luisjuarez.sistemavu.model.Categoria;
import com.luisjuarez.sistemavu.model.Proveedor;
import com.luisjuarez.sistemavu.persistence.CategoriaDAO;
import com.luisjuarez.sistemavu.service.CategoriaService;
import com.luisjuarez.sistemavu.utils.JTableUtils;
import com.luisjuarez.sistemavu.utils.PDFboxUtils;
import com.luisjuarez.sistemavu.utils.StringUtil;
import com.luisjuarez.sistemavu.view.SistemaPrincipal;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void registrarCategoria(Categoria categoria) {
        categoriaDAO.registrar(categoria);
    }

    @Override
    public Categoria mostrarCategoria(String categoriaID) {
        return categoriaDAO.buscarPorId(Integer.parseInt(categoriaID));
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return categoriaDAO.buscarPorId(id);
    }

    @Override
    public List<Categoria> mostrarLista() {
        return categoriaDAO.mostrarLista();
    }

    @Override
    public List<Categoria> buscarCategoriasPorPalabraClave(String palabraClave) {
        return categoriaDAO.buscarPorPalabraClave(palabraClave);
    }
    
    @Override
    public void modificarCategoria(String categoriaID, Categoria categoria) {
        categoria.setIdCategoria(Integer.parseInt(categoriaID));
        categoriaDAO.modificar(categoria);
    }

    @Override
    public void eliminarCategoria(String categoriaID) {
        categoriaDAO.eliminar(Integer.parseInt(categoriaID));
    }

    @Override
    public void cargarComboBoxCategorias(JComboBox cmb) {
        List<Categoria> lista = categoriaDAO.mostrarLista();
        lista.forEach(categoria -> cmb.addItem(categoria.getNombre()));
    }

    @Override
    public void reporteCategoriasPDF(String destino) throws SQLException {
        ConfigProperties config = new ConfigProperties();
        String logoPath = config.getProperty("empresa.logo");
        PDDocument document = new PDDocument();
        PDRectangle pdRectangle = PDRectangle.A4;
        float pageWidth = pdRectangle.getWidth();
        float pageHeight = pdRectangle.getHeight();
        float margin = 20;
        float yStart = pageHeight - margin;
        float rowHeight = 20f;
        int pageNum = 1;
        try {
            PDPage page = PDFboxUtils.createNewPage(document, pdRectangle);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true, true);
            // Añadir logo de la empresa en la primera página
            try {
                PDImageXObject logoImage = PDImageXObject.createFromFile(getClass().getResource(logoPath).getPath(), document);
                contentStream.drawImage(logoImage, 50, yStart - 80, 80, 80);
            } catch (IOException ex) {
                Logger.getLogger(ClienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Error al cargar el logo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Cargar fuentes
            File fontFile = new File("C:/Windows/Fonts/segoeui.ttf");
            File fontFileBold = new File("C:/Windows/Fonts/segoeuib.ttf");
            PDType0Font segoeUIFont = PDType0Font.load(document, fontFile);
            PDType0Font segoeUIFontBold = PDType0Font.load(document, fontFileBold);

            // Información de la empresa en la primera página
            String[] texts = {
                config.getProperty("empresa.razon_social").toUpperCase(),
                "RIF: " + config.getProperty("empresa.rif.tipo_doc").toUpperCase() + "-" + config.getProperty("empresa.rif.nro_doc"),
                "Calle: " + config.getProperty("empresa.calle").toUpperCase() + " Casa/Local N° " + config.getProperty("empresa.casa").toUpperCase() + " Sector: " + StringUtil.toCapitalize(config.getProperty("empresa.sector")),
                StringUtil.toCapitalize(config.getProperty("empresa.ciudad")) + ", Edo. " + StringUtil.toCapitalize(config.getProperty("empresa.estado")) + " Zona Postal " + StringUtil.toCapitalize(config.getProperty("empresa.codigo_postal")) + " Telf: " + config.getProperty("empresa.telefono"),
                "E-mail: " + config.getProperty("empresa.correo").toLowerCase(),
                "REPORTE DE CATEGORIAS", // Aquí puedes añadir manualmente el título del reporte
                "GENERADO POR: " + SistemaPrincipal.getEmpleado().getNombre() + " " + SistemaPrincipal.getEmpleado().getApellido(),
                "FECHA: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date())
            };

            int[] fontSizes = {12, 12, 12, 12, 12, 16, 10, 10};
            PDType0Font[] fonts = {segoeUIFontBold, segoeUIFont, segoeUIFont, segoeUIFont, segoeUIFont, segoeUIFontBold, segoeUIFont, segoeUIFont};
            float[] yPos = {800, 786, 772, 758, 744, 715, 700, 685};

            for (int i = 0; i < texts.length; i++) {
                if (i < 6) {
                    PDFboxUtils.addTextCenter(contentStream, fonts[i], texts[i], fontSizes[i], yPos[i], pageWidth);
                } else {
                    PDFboxUtils.addTextEnd(contentStream, fonts[i], texts[i], fontSizes[i], yPos[i], pageWidth);
                }
            }
            // Crear tabla de encabezados
            Color orange = Color.decode("#"+config.getProperty("configuracion.colorEncabezado").toUpperCase());
            Color white = Color.decode("#"+config.getProperty("configuracion.colorTitulo").toUpperCase());
            Color black = Color.decode("#"+config.getProperty("configuracion.colorRegistros").toUpperCase());
            String[] headers = {"id", "Nombre", "Descripcion"};
            float tableWidth = pageWidth - 2 * margin;
            float[] columnWidths = {
                tableWidth * 0.05f, //id
                tableWidth * 0.2f, //nombre
                tableWidth * 0.75f, //descripcion
            };
            float yPosition = 670;
            int fontSize = 8;
            PDFboxUtils.drawTableHeaders(contentStream, segoeUIFontBold, fontSize, headers, columnWidths, margin, yPosition, orange, white, rowHeight);
            yPosition -= rowHeight;

            List<Categoria> categorias = SistemaPrincipal.getCategoriaService().mostrarLista();
            for (Categoria categoria : categorias) {
               
                String[] rows = {
                    String.valueOf(categoria.getIdCategoria()),
                    categoria.getNombre(),
                    categoria.getDescripcion()
                };

                for (int j = 0; j < rows.length; j++) {
                    if (yPosition - rowHeight < margin) {
                        PDFboxUtils.addPageNumber(contentStream, segoeUIFont, 10, pageNum++, pageWidth, 20);
                        // Autor
                        contentStream.setNonStrokingColor(Color.BLACK);
                        String footer1 = StringUtil.toCapitalize(config.getProperty("configuracion.autor").toUpperCase());
                        PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer1, 8, 20, pageWidth);
                        yPosition -= rowHeight; // Ajuste posterior a la creación de encabezados
                        //Copyright
                        contentStream.setNonStrokingColor(Color.BLACK);
                        String footer2 = StringUtil.toCapitalize("Todos los derechos reservados © 2024");
                        PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer2, 8, 10, pageWidth);
                        contentStream.close();

                        //////
                        page = PDFboxUtils.createNewPage(document, pdRectangle);
                        contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true, true);
                        yPosition = yStart;
                        PDFboxUtils.drawTableHeaders(contentStream, segoeUIFont, fontSize, headers, columnWidths, margin, yPosition, orange, black, rowHeight);
                        yPosition -= rowHeight;
                    }
                    float xPosition = PDFboxUtils.getCumulativeColumnWidth(columnWidths, j, margin); // Calcular posición X para cada celda
                    PDFboxUtils.drawTableRows(contentStream, segoeUIFont, rows[j], 8, xPosition, yPosition, columnWidths[j], orange, black, rowHeight);
                }
                yPosition -= rowHeight;
            }
            // Autor
            contentStream.setNonStrokingColor(Color.BLACK);
            String footer1 = StringUtil.toCapitalize(config.getProperty("configuracion.autor").toUpperCase());
            PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer1, 8, 20, pageWidth);
            yPosition -= rowHeight + 20; // Ajuste posterior a la creación de encabezados
            //Copyright
            contentStream.setNonStrokingColor(Color.BLACK);
            String footer2 = StringUtil.toCapitalize("Todos los derechos reservados © 2024");
            PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer2, 8, 10, pageWidth);
            //page num
            PDFboxUtils.addPageNumber(contentStream, segoeUIFont, 10, pageNum++, pageWidth, 20);
            contentStream.close();
            document.save(destino);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarTabla(JTable tabla) throws SQLException {
       DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id","Nombre", "Descripción"
                }
        ) {

            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        tabla.setModel(model);
        model.setRowCount(0);

        List<Categoria> listaCategorias = mostrarLista();
        for (Categoria categoria : listaCategorias) {
            Object[] fila = new Object[11];
            fila[0] = categoria.getIdCategoria();
            fila[1] = categoria.getNombre();
            fila[2] = categoria.getDescripcion();
            model.addRow(fila);
        }
        JTableUtils.centrarTitulosEncabezado(tabla);
        JTableUtils.ajustarAnchoCelda(tabla);
    }

    @Override
    public void cargarTabla(JTable tabla, String palabraClave) throws SQLException {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id","Nombre", "Descripción"
                }
        ) {

            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        tabla.setModel(model);
        model.setRowCount(0);

        List<Categoria> listaCategorias = buscarCategoriasPorPalabraClave(palabraClave);
        for (Categoria categoria : listaCategorias) {
            Object[] fila = new Object[11];
            fila[0] = categoria.getIdCategoria();
            fila[1] = categoria.getNombre();
            fila[2] = categoria.getDescripcion();
            model.addRow(fila);
        }
        JTableUtils.centrarTitulosEncabezado(tabla);
        JTableUtils.ajustarAnchoCelda(tabla);
    }

    
}
