package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Proveedor;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public interface ProveedorService {
    void registrarProveedor(Proveedor proveedor);
    Proveedor buscarProveedorPorDocumento(String tipo_doc, String nro_doc);
    Proveedor buscarProveedorPorId(int id);
    List<Proveedor> buscarProveedoresPorPalabraClave(String palabraClave);
    List<Proveedor> mostrarListaProveedores();
    void modificarProveedor(Proveedor proveedor);
    void eliminarProveedor(int id);
    void reporteProveedorPDF(String destino) throws SQLException;
    void cargarTabla(JTable tabla) throws SQLException;
    void cargarTabla(JTable tabla, String palabraClave) throws SQLException;
}
