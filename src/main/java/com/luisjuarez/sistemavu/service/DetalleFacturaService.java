package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.DetalleFactura;
import java.util.List;

public interface DetalleFacturaService {
    void registrarDetalleFactura(DetalleFactura detalleFactura);
    DetalleFactura buscarDetalleFacturaPorId(int id);
    List<DetalleFactura> buscarDetallesPorFacturaId(int facturaId);
    List<DetalleFactura> mostrarListaDetalles();
    void modificarDetalleFactura(DetalleFactura detalleFactura);
    void eliminarDetalleFactura(int id);
}
