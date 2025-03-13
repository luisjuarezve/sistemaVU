package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import com.luisjuarez.sistemavu.model.DetalleFactura;
import com.luisjuarez.sistemavu.model.Factura;
import com.luisjuarez.sistemavu.model.Producto;
import com.luisjuarez.sistemavu.persistence.DetalleFacturaDAO;
import com.luisjuarez.sistemavu.service.DetalleFacturaService;
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

public class DetalleFacturaServiceImpl implements DetalleFacturaService {

    private DetalleFacturaDAO detalleFacturaDAO;

    public DetalleFacturaServiceImpl(DetalleFacturaDAO detalleFacturaDAO) {
        this.detalleFacturaDAO = detalleFacturaDAO;
    }

    @Override
    public void registrarDetalleFactura(DetalleFactura detalleFactura) {
        detalleFacturaDAO.registrar(detalleFactura);
    }

    @Override
    public DetalleFactura buscarDetalleFacturaPorId(int id) {
        return detalleFacturaDAO.buscarPorId(id);
    }

    @Override
    public List<DetalleFactura> buscarDetallesPorFacturaId(int facturaId) {
        return detalleFacturaDAO.buscarPorFacturaId(facturaId);
    }

    @Override
    public List<DetalleFactura> mostrarListaDetalles() {
        return detalleFacturaDAO.mostrarLista();
    }

    @Override
    public void modificarDetalleFactura(DetalleFactura detalleFactura) {
        detalleFacturaDAO.modificar(detalleFactura);
    }

    @Override
    public void eliminarDetalleFactura(int id) {
        detalleFacturaDAO.eliminar(id);
    }

    @Override
    public void reporteDetalleFacturasPDF(String destino, int idFactura) throws SQLException {
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
                "REPORTE DE DETALLES - VENTA N° " + idFactura, // Aquí puedes añadir manualmente el título del reporte
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
            String[] headers = {"Cod", "Producto", "Und", "Precio unitario", "IVA 16%", "Monto"};
            float tableWidth = pageWidth - 2 * margin;
            float[] columnWidths = {
                tableWidth * 0.05f, //codigo
                tableWidth * 0.18f, //Producto
                tableWidth * 0.07f, //und
                tableWidth * 0.25f, //precio unitario
                tableWidth * 0.23f, //monto
                tableWidth * 0.22f, //monto
            };
            float yPosition = 670;
            int fontSize = 8;
            PDFboxUtils.drawTableHeaders(contentStream, segoeUIFont, fontSize, headers, columnWidths, margin, yPosition, orange, white, rowHeight);
            yPosition -= rowHeight;

            List<DetalleFactura> detallesFacturas = detalleFacturaDAO.buscarPorFacturaId(idFactura);
            Factura factura = SistemaPrincipal.getFacturaService().buscarFacturaPorId(idFactura);
            double tasa = factura.getTasa();
            for (DetalleFactura detalleFactura : detallesFacturas) {
                Producto producto = SistemaPrincipal.getProductoService().buscarProductoPorId(detalleFactura.getProducto_idProducto());
                double precioUnitarioBS = detalleFactura.getPrecioUnitario() * tasa;
                String precioUnitario = String.format("%.2f Bs (%.2f USD)", precioUnitarioBS, detalleFactura.getPrecioUnitario());
                double iva = detalleFactura.getPrecioUnitario() * (producto.getImpuesto() / 100);
                String impuesto = String.format("%.2f Bs (%.2f USD)", iva * tasa, iva);
                String monto = String.format("%.2f Bs (%.2f USD)", detalleFactura.getSubtotal() * tasa, detalleFactura.getSubtotal());

                String[] rows = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    String.format("%.2f", detalleFactura.getCantidad()),
                    precioUnitario,
                    impuesto,
                    monto
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
                        String footer2 = StringUtil.toCapitalize("Todos los derechos reservados © 2025");
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
                    PDFboxUtils.drawTableRows(contentStream, segoeUIFont, rows[j], 8, xPosition, yPosition, columnWidths[j], orange, black, rowHeight, j);
                }
                yPosition -= rowHeight;
            }

            double cantidad = factura.getCantidad();
            String cantidadString = String.format("%.2f", cantidad);
            double subtotalf = factura.getSubtotal();
            String subtotalString = String.format("%.2f Bs (%.2f USD)", subtotalf, subtotalf * tasa);
            double ivaf = factura.getImpuesto();
            String ivaString = String.format("%.2f Bs (%.2f USD)", ivaf, ivaf * tasa);
            double totalf = factura.getTotalFactura();
            String totalString = String.format("%.2f Bs (%.2f USD)", totalf, totalf * tasa);
            String[] total = {"", "TOTAL", cantidadString, subtotalString, ivaString, totalString};
            PDFboxUtils.drawTableFactura(contentStream, segoeUIFontBold, fontSize, total, columnWidths, margin, yPosition, orange, black, rowHeight);
            yPosition -= rowHeight;
            // Autor
            contentStream.setNonStrokingColor(Color.BLACK);
            String footer1 = StringUtil.toCapitalize(config.getProperty("configuracion.autor").toUpperCase());
            PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer1, 8, 20, pageWidth);
            yPosition -= rowHeight + 20; // Ajuste posterior a la creación de encabezados
            //Copyright
            contentStream.setNonStrokingColor(Color.BLACK);
            String footer2 = StringUtil.toCapitalize("Todos los derechos reservados © 2025");
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
    public void generarFacturaPDF(String destino, int idFactura) throws SQLException {
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
                "NOTA DE ENTREGA N° " + idFactura, // Aquí puedes añadir manualmente el título del reporte
                "CLIENTE: " + SistemaPrincipal.getCliente().getNombre() + (SistemaPrincipal.getCliente()
                .getApellido() != null ? " " + SistemaPrincipal.getCliente().getApellido() : "") + " " + SistemaPrincipal.getCliente().getTipo_doc() + "-" + SistemaPrincipal.getCliente().getNro_doc(),
                "GENERADO POR: " + SistemaPrincipal.getEmpleado().getNombre() + " " + SistemaPrincipal.getEmpleado().getApellido(),
                "FECHA: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date())
            };

            int[] fontSizes = {12, 12, 12, 12, 12, 16, 10, 10, 10, 10};
            PDType0Font[] fonts = {segoeUIFontBold, segoeUIFont, segoeUIFont, segoeUIFont, segoeUIFont, segoeUIFontBold, segoeUIFont, segoeUIFont, segoeUIFont};
            float[] yPos = {800, 786, 772, 758, 744, 715, 700, 685, 670};

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
            String[] headers = {"Cod", "Producto", "Und", "Precio unitario", "IVA 16%", "Monto"};
            float tableWidth = pageWidth - 2 * margin;
            float[] columnWidths = {
                tableWidth * 0.05f, //codigo
                tableWidth * 0.18f, //Producto
                tableWidth * 0.07f, //und
                tableWidth * 0.25f, //precio unitario
                tableWidth * 0.23f, //monto
                tableWidth * 0.22f, //monto
            };
            float yPosition = 655;
            int fontSize = 8;
            PDFboxUtils.drawTableHeaders(contentStream, segoeUIFontBold, fontSize, headers, columnWidths, margin, yPosition, orange, white, rowHeight);
            yPosition -= rowHeight;

            List<DetalleFactura> detallesFacturas = detalleFacturaDAO.buscarPorFacturaId(idFactura);
            Factura factura = SistemaPrincipal.getFacturaService().buscarFacturaPorId(idFactura);
            double tasa = factura.getTasa();
            for (DetalleFactura detalleFactura : detallesFacturas) {
                Producto producto = SistemaPrincipal.getProductoService().buscarProductoPorId(detalleFactura.getProducto_idProducto());
                double precioUnitarioBS = detalleFactura.getPrecioUnitario() * tasa;
                String precioUnitario = String.format("%.2f Bs (%.2f USD)", precioUnitarioBS, detalleFactura.getPrecioUnitario());
                double iva = detalleFactura.getPrecioUnitario() * (producto.getImpuesto() / 100);
                String impuesto = String.format("%.2f Bs (%.2f USD)", iva * tasa, iva);
                String monto = String.format("%.2f Bs (%.2f USD)", detalleFactura.getSubtotal() * tasa, detalleFactura.getSubtotal());

                String[] rows = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    String.format("%.2f", detalleFactura.getCantidad()),
                    precioUnitario,
                    impuesto,
                    monto
                };

                for (int j = 0; j < rows.length; j++) {
                    if (yPosition - rowHeight < 50) {
                        //Page 
                        PDFboxUtils.addPageNumber(contentStream, segoeUIFontBold, 10, pageNum++, pageWidth, 20);
                        // Autor
                        contentStream.setNonStrokingColor(Color.BLACK);
                        String footer1 = StringUtil.toCapitalize(config.getProperty("configuracion.autor").toUpperCase());
                        PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer1, 8, 20, pageWidth);
                        yPosition -= rowHeight; // Ajuste posterior a la creación de encabezados
                        //Copyright
                        contentStream.setNonStrokingColor(Color.BLACK);
                        String footer2 = StringUtil.toCapitalize("Todos los derechos reservados © 2025");
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
                    PDFboxUtils.drawTableRows(contentStream, segoeUIFont, rows[j], 8, xPosition, yPosition, columnWidths[j], orange, black, rowHeight, j);
                }
                yPosition -= rowHeight;
            }

            double cantidad = factura.getCantidad();
            String cantidadString = String.format("%.2f", cantidad);
            double subtotalf = factura.getSubtotal();
            String subtotalString = String.format("%.2f Bs (%.2f USD)", subtotalf, subtotalf * tasa);
            double ivaf = factura.getImpuesto();
            String ivaString = String.format("%.2f Bs (%.2f USD)", ivaf, ivaf * tasa);
            double totalf = factura.getTotalFactura();
            String totalString = String.format("%.2f Bs (%.2f USD)", totalf, totalf * tasa);
            String[] total = {"", "TOTAL", cantidadString, subtotalString, ivaString, totalString};
            PDFboxUtils.drawTableFactura(contentStream, segoeUIFontBold, fontSize, total, columnWidths, margin, yPosition, orange, white, rowHeight);
            yPosition -= rowHeight;

            // Autor
            contentStream.setNonStrokingColor(Color.BLACK);
            String footer1 = StringUtil.toCapitalize(config.getProperty("configuracion.autor").toUpperCase());
            PDFboxUtils.addTextCenter(contentStream, segoeUIFontBold, footer1, 8, 20, pageWidth);
            yPosition -= rowHeight + 20; // Ajuste posterior a la creación de encabezados
            //Copyright
            contentStream.setNonStrokingColor(Color.BLACK);
            String footer2 = StringUtil.toCapitalize("Todos los derechos reservados © 2025");
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
