package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Compra;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface CompraService {
    void registrarCompra(Compra compra);
    Compra buscarCompraPorId(int id);
    List<Compra> buscarComprasPorProveedorId(int proveedorId);
    List<Compra> buscarComprasPorFecha(Timestamp fecha);
    void modificarCompra(Compra compra);
    void eliminarCompra(int id);
    void reporteComprasPDF(String destino) throws SQLException;
}
