package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Producto;
import com.luisjuarez.sistemavu.persistence.ProductoDAO;
import com.luisjuarez.sistemavu.service.ProductoService;

import java.util.List;

public class ProductoServiceImpl implements ProductoService {

    private ProductoDAO productoDAO;

    public ProductoServiceImpl(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    public void registrarProducto(Producto producto) {
        productoDAO.registrar(producto);
    }

    @Override
    public Producto buscarProductoPorCodigo(String codigo) {
        return productoDAO.buscarPorCodigo(codigo);
    }

    @Override
    public List<Producto> buscarProductosPorProveedorId(int proveedorId) {
        return productoDAO.buscarPorProveedorId(proveedorId);
    }

    @Override
    public List<Producto> buscarProductosPorCategoriaId(int categoriaId) {
        return productoDAO.buscarPorCategoriaId(categoriaId);
    }

    @Override
    public Producto buscarProductoPorId(int id) {
        return productoDAO.buscarPorId(id);
    }

    @Override
    public List<Producto> buscarProductosPorPalabraClave(String palabraClave) {
        return productoDAO.buscarPorPalabraClave(palabraClave);
    }

    @Override
    public List<Producto> mostrarListaProductos() {
        return productoDAO.mostrarLista();
    }

    @Override
    public void modificarProducto(Producto producto) {
        productoDAO.modificar(producto);
    }

    @Override
    public void eliminarProducto(int id) {
        productoDAO.eliminar(id);
    }
}
