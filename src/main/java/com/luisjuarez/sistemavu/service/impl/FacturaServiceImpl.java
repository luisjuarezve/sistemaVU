package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Factura;
import com.luisjuarez.sistemavu.persistence.FacturaDAO;
import com.luisjuarez.sistemavu.service.FacturaService;

import java.sql.Timestamp;
import java.util.List;

public class FacturaServiceImpl implements FacturaService {

    private FacturaDAO facturaDAO;

    public FacturaServiceImpl(FacturaDAO facturaDAO) {
        this.facturaDAO = facturaDAO;
    }

    @Override
    public void registrarFactura(Factura factura) {
        facturaDAO.registrar(factura);
    }

    @Override
    public Factura buscarFacturaPorId(int id) {
        return facturaDAO.buscarPorId(id);
    }

    @Override
    public List<Factura> buscarFacturasPorClienteId(int clienteId) {
        return facturaDAO.buscarPorClienteId(clienteId);
    }

    @Override
    public List<Factura> buscarFacturasPorEmpleadoId(int empleadoId) {
        return facturaDAO.buscarPorEmpleadoId(empleadoId);
    }

    @Override
    public List<Factura> buscarFacturasPorFecha(Timestamp desde, Timestamp hasta) {
        return facturaDAO.buscarPorFecha(desde, hasta);
    }

    @Override
    public List<Factura> mostrarListaFacturas() {
        return facturaDAO.mostrarLista();
    }

    @Override
    public void modificarFactura(Factura factura) {
        facturaDAO.modificar(factura);
    }

    @Override
    public void eliminarFactura(int id) {
        facturaDAO.eliminar(id);
    }
}
