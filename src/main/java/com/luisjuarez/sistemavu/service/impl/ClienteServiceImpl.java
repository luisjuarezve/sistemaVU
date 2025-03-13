package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import com.luisjuarez.sistemavu.model.Cliente;
import com.luisjuarez.sistemavu.persistence.ClienteDAO;
import com.luisjuarez.sistemavu.service.ClienteService;
import com.luisjuarez.sistemavu.utils.JTableUtils;
import com.luisjuarez.sistemavu.utils.PDFboxUtils;
import com.luisjuarez.sistemavu.utils.StringUtil;
import com.luisjuarez.sistemavu.view.SistemaPrincipal;
import java.awt.Color;
import java.io.File;

import java.io.IOException;
import java.util.List;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class ClienteServiceImpl implements ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteServiceImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        clienteDAO.registrar(cliente);
    }

    @Override
    public Cliente buscarClientePorDocumento(String tipo_doc, String nro_doc) {
        return clienteDAO.buscarPorDocumento(tipo_doc, nro_doc);
    }

    @Override
    public Cliente buscarClientePorId(String clienteID) {
        return clienteDAO.buscarPorId(Integer.parseInt(clienteID));
    }

    @Override
    public List<Cliente> buscarClientesPorPalabraClave(String palabraClave) {
        return clienteDAO.buscarPorPalabraClave(palabraClave);
    }

    @Override
    public List<Cliente> mostrarListaClientes() {
        return clienteDAO.mostrarLista();
    }

    @Override
    public void modificarCliente(String clienteID, Cliente cliente) {
        cliente.setIdCliente(Integer.parseInt(clienteID));
        clienteDAO.modificar(cliente);
    }

    @Override
    public void eliminarCliente(String clienteID) {
        clienteDAO.eliminar(Integer.parseInt(clienteID));
    }

    @Override
    public void reporteClientesPDF(String destino) throws SQLException {
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
                "REPORTE DE CLIENTES", // Aquí puedes añadir manualmente el título del reporte
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
            String[] headers = {"T", "N° Doc", "Razon Social", "Telefono", "Direccion", "Correo", "F. Registro"};
            float tableWidth = pageWidth - 2 * margin;
            float[] columnWidths = {
                tableWidth * 0.05f, //ttipo
                tableWidth * 0.1f, //nro doc
                tableWidth * 0.2f, //razon
                tableWidth * 0.1f, //tel
                tableWidth * 0.25f, //dir
                tableWidth * 0.2f, //correo
                tableWidth * 0.1f //f registro
            };
            float yPosition = 670;
            int fontSize = 8;
            PDFboxUtils.drawTableHeaders(contentStream, segoeUIFontBold, fontSize, headers, columnWidths, margin, yPosition, orange, white, rowHeight);
            yPosition -= rowHeight;

            List<Cliente> clientes = clienteDAO.mostrarLista();
            for (Cliente cliente : clientes) {
                // Formatear la fecha para mostrar solo la parte de la fecha sin la hora
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = dateFormat.format(cliente.getFecha_registro());

                String[] rows = {
                    cliente.getTipo_doc(),
                    cliente.getNro_doc(),
                    cliente.getNombre() + (cliente.getApellido() != null ? " " + cliente.getApellido() : ""),
                    cliente.getTelefono(),
                    cliente.getDireccion(),
                    cliente.getCorreo_electronico(),
                    fecha
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
    public void cargarTabla(JTable tabla) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Tipo Doc", "Número Doc", "Razón Social", "Teléfono", "Correo Electrónico", "Dirección", "Comentarios", "Fecha Registro"
                }
        ) {

            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        tabla.setModel(model);
        model.setRowCount(0);

        List<Cliente> listaClientes = mostrarListaClientes();
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[11];
            fila[0] = cliente.getIdCliente();
            fila[1] = cliente.getTipo_doc();
            fila[2] = cliente.getNro_doc();
            fila[3] = cliente.getNombre()
                    + (cliente.getApellido() != null ? " " + cliente.getApellido() : "");
            fila[4] = cliente.getTelefono();
            fila[5] = cliente.getCorreo_electronico();
            fila[6] = cliente.getDireccion();
            fila[7] = cliente.getNotas();
            fila[8] = cliente.getFecha_registro();
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
                    "Id", "Tipo Doc", "Número Doc", "Razón Social", "Teléfono", "Correo Electrónico", "Dirección", "Comentarios", "Fecha Registro"
                }
        ) {

            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas no son editables
            }
        };

        tabla.setModel(model);
        model.setRowCount(0);

        List<Cliente> listaClientes = buscarClientesPorPalabraClave(palabraClave);
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[11];
            fila[0] = cliente.getIdCliente();
            fila[1] = cliente.getTipo_doc();
            fila[2] = cliente.getNro_doc();
            fila[3] = cliente.getNombre()
                    + (cliente.getApellido() != null ? " " + cliente.getApellido() : "");
            fila[4] = cliente.getTelefono();
            fila[5] = cliente.getCorreo_electronico();
            fila[6] = cliente.getDireccion();
            fila[7] = cliente.getNotas();
            fila[8] = cliente.getFecha_registro();
            model.addRow(fila);
        }
        JTableUtils.centrarTitulosEncabezado(tabla);
        JTableUtils.ajustarAnchoCelda(tabla);
    }

}
