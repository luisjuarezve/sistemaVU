package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Inventario;
import com.luisjuarez.sistemavu.persistence.InventarioDAO;
import com.luisjuarez.sistemavu.service.InventarioService;

import java.util.List;

public class InventarioServiceImpl implements InventarioService {

    private InventarioDAO inventarioDAO;

    public InventarioServiceImpl(InventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
    }

    @Override
    public void registrarInventario(Inventario inventario) {
        inventarioDAO.registrar(inventario);
    }

    @Override
    public Inventario buscarInventarioPorId(int id) {
        return inventarioDAO.buscarPorId(id);
    }

    @Override
    public List<Inventario> buscarInventariosPorProductoId(int productoId) {
        return inventarioDAO.buscarPorProductoId(productoId);
    }

    @Override
    public List<Inventario> mostrarListaInventarios() {
        return inventarioDAO.mostrarLista();
    }

    @Override
    public void modificarInventario(Inventario inventario) {
        inventarioDAO.modificar(inventario);
    }

    @Override
    public void eliminarInventario(int id) {
        inventarioDAO.eliminar(id);
    }

    @Override
    public void aumentarInventario(int id, double cantidad) {
        inventarioDAO.aumentar(id, cantidad);
    }

    @Override
    public void disminuirInventario(int id, double cantidad) {
        inventarioDAO.disminuir(id, cantidad);
    }
}
