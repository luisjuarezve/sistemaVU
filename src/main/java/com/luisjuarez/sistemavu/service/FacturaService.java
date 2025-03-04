package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Factura;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface FacturaService {
    void registrarFactura(Factura factura);
    Factura buscarFacturaPorId(int id);
    List<Factura> buscarFacturasPorClienteId(int clienteId);
    List<Factura> buscarFacturasPorEmpleadoId(int empleadoId);
    List<Factura> buscarFacturasPorFecha(Timestamp desde, Timestamp hasta);
    List<Factura> mostrarListaFacturas();
    void modificarFactura(Factura factura);
    void eliminarFactura(int id);
    void reporteFacturasPDF(String destino) throws SQLException;
}
