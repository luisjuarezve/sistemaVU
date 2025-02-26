package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.DetalleCompra;
import com.luisjuarez.sistemavu.persistence.DetalleCompraDAO;
import com.luisjuarez.sistemavu.service.DetalleCompraService;

import java.util.List;

public class DetalleCompraServiceImpl implements DetalleCompraService {

    private DetalleCompraDAO detalleCompraDAO;

    public DetalleCompraServiceImpl(DetalleCompraDAO detalleCompraDAO) {
        this.detalleCompraDAO = detalleCompraDAO;
    }

    @Override
    public void registrarDetalleCompra(DetalleCompra detalleCompra) {
        detalleCompraDAO.registrar(detalleCompra);
    }

    @Override
    public DetalleCompra buscarDetalleCompraPorId(int id) {
        return detalleCompraDAO.buscarPorId(id);
    }

    @Override
    public List<DetalleCompra> buscarDetallesPorProveedorId(int proveedorId) {
        return detalleCompraDAO.buscarPorProveedorId(proveedorId);
    }

    @Override
    public List<DetalleCompra> buscarDetallesPorProductoId(int productoId) {
        return detalleCompraDAO.buscarPorProductoId(productoId);
    }

    @Override
    public List<DetalleCompra> mostrarListaDetalles() {
        return detalleCompraDAO.mostrarLista();
    }

    @Override
    public void modificarDetalleCompra(DetalleCompra detalleCompra) {
        detalleCompraDAO.modificar(detalleCompra);
    }

    @Override
    public void eliminarDetalleCompra(int id) {
        detalleCompraDAO.eliminar(id);
    }
}
