package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.DetalleCompra;
import java.util.List;

public interface DetalleCompraService {
    void registrarDetalleCompra(DetalleCompra detalleCompra);
    DetalleCompra buscarDetalleCompraPorId(int id);
    List<DetalleCompra> buscarDetallesPorProveedorId(int proveedorId);
    List<DetalleCompra> buscarDetallesPorProductoId(int productoId);
    List<DetalleCompra> mostrarListaDetalles();
    void modificarDetalleCompra(DetalleCompra detalleCompra);
    void eliminarDetalleCompra(int id);
}
