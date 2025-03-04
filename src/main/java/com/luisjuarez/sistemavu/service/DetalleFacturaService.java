package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.DetalleFactura;
import java.sql.SQLException;
import java.util.List;

public interface DetalleFacturaService {
    void registrarDetalleFactura(DetalleFactura detalleFactura);
    DetalleFactura buscarDetalleFacturaPorId(int id);
    List<DetalleFactura> buscarDetallesPorFacturaId(int facturaId);
    List<DetalleFactura> mostrarListaDetalles();
    void modificarDetalleFactura(DetalleFactura detalleFactura);
    void eliminarDetalleFactura(int id);
    void reporteDetalleFacturasPDF(String destino, int idFactura) throws SQLException;
    void generarFacturaPDF(String destino, int idFactura) throws SQLException;
}
