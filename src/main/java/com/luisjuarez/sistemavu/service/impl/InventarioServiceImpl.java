package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import com.luisjuarez.sistemavu.model.Inventario;
import com.luisjuarez.sistemavu.model.Producto;
import com.luisjuarez.sistemavu.model.Proveedor;
import com.luisjuarez.sistemavu.persistence.InventarioDAO;
import com.luisjuarez.sistemavu.service.InventarioService;
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

public class InventarioServiceImpl implements InventarioService {

    private InventarioDAO inventarioDAO;

    public InventarioServiceImpl(InventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public void registrarInventario(Inventario inventario) {
        inventarioDAO.registrar(inventario);
    }

    @Override
    public Inventario buscarInventarioPorId(int id) {
        return inventarioDAO.buscarPorId(id);
    }

    @Override
    public List<Inventario> buscarInventariosPorProductoId(int productoId) {
        return inventarioDAO.buscarPorProductoId(productoId);
    }

    @Override
    public List<Inventario> mostrarListaInventarios() {
        return inventarioDAO.mostrarLista();
    }

    @Override
    public void modificarInventario(Inventario inventario) {
        inventarioDAO.modificar(inventario);
    }

    @Override
    public void eliminarInventario(int id) {
        inventarioDAO.eliminar(id);
    }

    @Override
    public void aumentarInventario(int id, double cantidad) {
        inventarioDAO.aumentar(id, cantidad);
    }

    @Override
    public void disminuirInventario(int id, double cantidad) {
        inventarioDAO.disminuir(id, cantidad);
    }

    @Override
    public void reporteInventarioPDF(String destino) throws SQLException {
        ConfigProperties config = new ConfigProperties();
        config.recargarArchivo();
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
                File logoFile = new File("src/main/resources" + logoPath); // Ruta completa
                PDImageXObject logoImage = PDImageXObject.createFromFile(logoFile.getAbsolutePath(), document);
                contentStream.drawImage(logoImage, 50, yStart - 80, 80, 80);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar el logo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
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
                "REPORTE DE INVENTARIO", // Aquí puedes añadir manualmente el título del reporte
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
            Color orange = Color.decode("#" + config.getProperty("configuracion.colorEncabezado").toUpperCase());
            Color white = Color.decode("#" + config.getProperty("configuracion.colorTitulo").toUpperCase());
            Color black = Color.decode("#" + config.getProperty("configuracion.colorRegistros").toUpperCase());
            String[] headers = {"Cod", "Producto", "Existencias", "Cant. min", "Cant. Max", "P. Compra", "Inv. Total"};
            float tableWidth = pageWidth - 2 * margin;
            float[] columnWidths = {
                tableWidth * 0.05f, //codigo
                tableWidth * 0.2f, //Producto
                tableWidth * 0.05f, //cantidad
                tableWidth * 0.1f, //cant min
                tableWidth * 0.1f, //cant max
                tableWidth * 0.25f, //P. compra
                tableWidth * 0.25f, //inv. total
            };
            float yPosition = 670;
            int fontSize = 8;
            PDFboxUtils.drawTableHeaders(contentStream, segoeUIFontBold, fontSize, headers, columnWidths, margin, yPosition, orange, white, rowHeight);
            yPosition -= rowHeight;

            List<Inventario> inventarios = inventarioDAO.mostrarLista();
            for (Inventario inventario : inventarios) {
                // Formatear la fecha para mostrar solo la parte de la fecha sin la hora
                double tasa = Double.valueOf(config.getProperty("configuracion.tasa"));
                Producto producto = SistemaPrincipal.getProductoService().buscarProductoPorId(inventario.getProducto_idProducto());
                ///
                double precioCompra = producto.getPrecio_compra();
                String precioCompraString = String.format("%.2f Bs (%.2f USD)", precioCompra * tasa, precioCompra);
                double invTotal = precioCompra * inventario.getCantidad();
                String invTotalString = String.format("%.2f Bs (%.2f USD)", invTotal * tasa, invTotal);
                ///
                String[] rows = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    String.format("%.2f", inventario.getCantidad()),
                    String.format("%.2f", inventario.getInventario_min()),
                    String.format("%.2f", inventario.getInventario_max()),
                    precioCompraString,
                    invTotalString
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
                    "Id", "Cod", "Producto", "Cantidad", "Inv. minimo", "Inv. Maximo"
                }
        ) {

            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        tabla.setModel(model);
        model.setRowCount(0);

        List<Inventario> listaInventario = mostrarListaInventarios();
        for (Inventario inventario : listaInventario) {
            Producto producto = SistemaPrincipal.getProductoService().buscarProductoPorId(inventario.getProducto_idProducto());
            Object[] fila = new Object[11];
            fila[0] = inventario.getIdInventario();
            fila[1] = producto.getCodigo();
            fila[2] = producto.getNombre();
            fila[3] = String.format("%.2f", inventario.getCantidad()); // Formateo a 2 decimales
            fila[4] = String.format("%.2f", inventario.getInventario_min()); // Formateo a 2 decimales
            fila[5] = String.format("%.2f", inventario.getInventario_max()); // Formateo a 2 decimales
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
                    "Id", "Cod", "Producto", "Cantidad", "Inv. minimo", "Inv. Maximo"
                }
        ) {

            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        tabla.setModel(model);
        model.setRowCount(0);

        List<Inventario> listaInventario = buscarInventariosPorProductoId(Integer.parseInt(palabraClave));
        for (Inventario inventario : listaInventario) {
            Producto producto = SistemaPrincipal.getProductoService().buscarProductoPorId(inventario.getProducto_idProducto());
            Object[] fila = new Object[11];
            fila[0] = inventario.getIdInventario();
            fila[1] = producto.getCodigo();
            fila[2] = producto.getNombre();
            fila[3] = String.format("%.2f", inventario.getCantidad()); // Formateo a 2 decimales
            fila[4] = String.format("%.2f", inventario.getInventario_min()); // Formateo a 2 decimales
            fila[5] = String.format("%.2f", inventario.getInventario_max()); // Formateo a 2 decimales
            model.addRow(fila);
        }
        JTableUtils.centrarTitulosEncabezado(tabla);
        JTableUtils.ajustarAnchoCelda(tabla);
    }
}
