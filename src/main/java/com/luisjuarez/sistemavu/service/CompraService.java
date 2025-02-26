package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Compra;
import java.sql.Timestamp;
import java.util.List;

public interface CompraService {
    void registrarCompra(Compra compra);
    Compra buscarCompraPorId(int id);
    List<Compra> buscarComprasPorProductoId(int productoId);
    List<Compra> buscarComprasPorProveedorId(int proveedorId);
    List<Compra> buscarComprasPorFecha(Timestamp fecha);
    void modificarCompra(Compra compra);
    void eliminarCompra(int id);
}
