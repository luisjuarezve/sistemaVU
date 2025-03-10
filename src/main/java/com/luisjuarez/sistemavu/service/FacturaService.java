package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JTable;

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
    void cargarTabla(JTable tabla) throws SQLException;
    void cargarTabla(JTable tabla, String palabraClave) throws SQLException;
    int contarFacturas();  
}
