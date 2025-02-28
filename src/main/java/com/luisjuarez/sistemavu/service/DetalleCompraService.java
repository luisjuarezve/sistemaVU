package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.DetalleCompra;
import java.sql.SQLException;
import java.util.List;

public interface DetalleCompraService {
    void registrarDetalleCompra(DetalleCompra detalleCompra);
    List<DetalleCompra> buscarDetalleCompraPorId(int idCompra);
    List<DetalleCompra> buscarDetallesPorProveedorId(int proveedorId);
    List<DetalleCompra> buscarDetallesPorProductoId(int productoId);
    List<DetalleCompra> mostrarListaDetalles();
    void modificarDetalleCompra(DetalleCompra detalleCompra);
    void eliminarDetalleCompra(int id);
    void reporteDetallesCompraPDF(String destino, int idCompra) throws SQLException;
}
