package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import com.luisjuarez.sistemavu.model.Cliente;
import com.luisjuarez.sistemavu.model.DetalleCompra;
import com.luisjuarez.sistemavu.model.Producto;
import com.luisjuarez.sistemavu.persistence.DetalleCompraDAO;
import com.luisjuarez.sistemavu.service.DetalleCompraService;
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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class DetalleCompraServiceImpl implements DetalleCompraService {

    private DetalleCompraDAO detalleCompraDAO;

    public DetalleCompraServiceImpl(DetalleCompraDAO detalleCompraDAO) {
        this.detalleCompraDAO = detalleCompraDAO;
    }

    @Override
    public void registrarDetalleCompra(DetalleCompra detalleCompra) {
        detalleCompraDAO.registrar(detalleCompra);
    }

    @Override
    public List<DetalleCompra> buscarDetalleCompraPorId(int idCompra) {
        return detalleCompraDAO.buscarPorIdCompra(idCompra);
    }

    @Override
    public List<DetalleCompra> buscarDetallesPorProveedorId(int proveedorId) {
        return detalleCompraDAO.buscarPorProveedorId(proveedorId);
    }

    @Override
    public List<DetalleCompra> buscarDetallesPorProductoId(int productoId) {
        return detalleCompraDAO.buscarPorProductoId(productoId);
    }

    @Override
    public List<DetalleCompra> mostrarListaDetalles() {
        return detalleCompraDAO.mostrarLista();
    }

    @Override
    public void modificarDetalleCompra(DetalleCompra detalleCompra) {
        detalleCompraDAO.modificar(detalleCompra);
    }

    @Override
    public void eliminarDetalleCompra(int id) {
        detalleCompraDAO.eliminar(id);
    }

    @Override
    public void reporteDetallesCompraPDF(String destino,int idCompra) throws SQLException {
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
                "REPORTE DE DETALLES - COMPRA N° "+idCompra, // Aquí puedes añadir manualmente el título del reporte
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
            Color orange = new Color(0xFF8900);
            Color black = new Color(0x000000);
            String[] headers = {"Cod", "Producto", "Und", "Precio unitario", "Monto"};
            float tableWidth = pageWidth - 2 * margin;
            float[] columnWidths = {
                                        tableWidth * 0.1f, //codigo
                                        tableWidth * 0.3f, //Producto
                                        tableWidth * 0.1f, //und
                                        tableWidth * 0.2f, //precio unitario
                                        tableWidth * 0.3f, //monto
            };
            float yPosition = 670;
            int fontSize = 8;
            PDFboxUtils.drawTableHeaders(contentStream, segoeUIFont, fontSize, headers, columnWidths, margin, yPosition, orange, black, rowHeight);
            yPosition -= rowHeight;
           

            List<DetalleCompra> detallesCompras = detalleCompraDAO.buscarPorIdCompra(idCompra);
            for (DetalleCompra detalleCompra : detallesCompras) {
                Producto producto = SistemaPrincipal.getProductoService().buscarProductoPorId(detalleCompra.getCompra_Producto_idProducto());
                String[] rows = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    String.valueOf(detalleCompra.getCantidad()),
                    String.valueOf(detalleCompra.getPrecio_unitario()),
                    String.valueOf(detalleCompra.getSubtotal())
                };

                for (int j = 0; j < rows.length; j++) {
                    if (yPosition - rowHeight < margin) {
                        PDFboxUtils.addPageNumber(contentStream, segoeUIFont, 10, pageNum++, pageWidth, 20);
                        // Autor
                        contentStream.setNonStrokingColor(Color.BLACK);
                        String footer1 = StringUtil.toCapitalize("Luis Juarez - Software Developer");
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
            String footer1 = StringUtil.toCapitalize("Luis Juarez - Software Developer");
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
}
