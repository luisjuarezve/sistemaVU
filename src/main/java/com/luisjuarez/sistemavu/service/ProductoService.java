package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Producto;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public interface ProductoService {
    void registrarProducto(Producto producto);
    Producto buscarProductoPorId(int id);
    Producto buscarProductoPorCodigo(String codigo);
    List<Producto> buscarProductosPorPalabraClave(String palabraClave);
    List<Producto> buscarProductosPorProveedorId(int proveedorId);
    List<Producto> buscarProductosPorCategoriaId(int categoriaId);
    List<Producto> mostrarListaProductos();
    void modificarProducto(Producto producto);
    void eliminarProducto(int id);
    void reporteProductosPDF(String destino) throws SQLException;
    void cargarTabla(JTable tabla) throws SQLException;
    void cargarTabla(JTable tabla, String palabraClave) throws SQLException;
}