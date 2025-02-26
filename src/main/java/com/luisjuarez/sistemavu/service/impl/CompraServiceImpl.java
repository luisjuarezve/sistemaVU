package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Compra;
import com.luisjuarez.sistemavu.persistence.CompraDAO;
import com.luisjuarez.sistemavu.service.CompraService;

import java.sql.Timestamp;
import java.util.List;

public class CompraServiceImpl implements CompraService {

    private CompraDAO compraDAO;

    public CompraServiceImpl(CompraDAO compraDAO) {
        this.compraDAO = compraDAO;
    }

    @Override
    public void registrarCompra(Compra compra) {
        compraDAO.registrar(compra);
    }

    @Override
    public Compra buscarCompraPorId(int id) {
        return compraDAO.buscarPorId(id);
    }

    @Override
    public List<Compra> buscarComprasPorProductoId(int productoId) {
        return compraDAO.buscarPorProductoId(productoId);
    }

    @Override
    public List<Compra> buscarComprasPorProveedorId(int proveedorId) {
        return compraDAO.buscarPorProveedorId(proveedorId);
    }

    @Override
    public List<Compra> buscarComprasPorFecha(Timestamp fecha) {
        return compraDAO.buscarPorFecha(fecha);
    }

    @Override
    public void modificarCompra(Compra compra) {
        compraDAO.modificar(compra);
    }

    @Override
    public void eliminarCompra(int id) {
        compraDAO.eliminar(id);
    }
    
}
