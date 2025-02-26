package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Proveedor;
import com.luisjuarez.sistemavu.persistence.ProveedorDAO;
import com.luisjuarez.sistemavu.service.ProveedorService;

import java.util.List;

public class ProveedorServiceImpl implements ProveedorService {

    private ProveedorDAO proveedorDAO;

    public ProveedorServiceImpl(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    @Override
    public void registrarProveedor(Proveedor proveedor) {
        proveedorDAO.registrar(proveedor);
    }

    @Override
    public Proveedor buscarProveedorPorDocumento(String tipo_doc, String nro_doc) {
        return proveedorDAO.buscarPorDocumento(tipo_doc, nro_doc);
    }

    @Override
    public Proveedor buscarProveedorPorId(int id) {
        return proveedorDAO.buscarPorId(id);
    }

    @Override
    public List<Proveedor> buscarProveedoresPorPalabraClave(String palabraClave) {
        return proveedorDAO.buscarPorPalabraClave(palabraClave);
    }

    @Override
    public List<Proveedor> mostrarListaProveedores() {
        return proveedorDAO.mostrarLista();
    }

    @Override
    public void modificarProveedor(Proveedor proveedor) {
        proveedorDAO.modificar(proveedor);
    }

    @Override
    public void eliminarProveedor(int id) {
        proveedorDAO.eliminar(id);
    }
}
