package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Producto;
import java.sql.SQLException;
import java.util.List;

public interface ProductoService {
    void registrarProducto(Producto producto);
    Producto buscarProductoPorCodigo(String codigo);
    List<Producto> buscarProductosPorProveedorId(int proveedorId);
    List<Producto> buscarProductosPorCategoriaId(int categoriaId);
    Producto buscarProductoPorId(int id);
    List<Producto> buscarProductosPorPalabraClave(String palabraClave);
    List<Producto> mostrarListaProductos();
    void modificarProducto(Producto producto);
    void eliminarProducto(int id);
    void reporteProductosPDF(String destino) throws SQLException;
}