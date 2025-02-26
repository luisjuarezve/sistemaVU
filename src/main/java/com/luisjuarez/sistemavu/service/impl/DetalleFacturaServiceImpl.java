package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.DetalleFactura;
import com.luisjuarez.sistemavu.persistence.DetalleFacturaDAO;
import com.luisjuarez.sistemavu.service.DetalleFacturaService;

import java.util.List;

public class DetalleFacturaServiceImpl implements DetalleFacturaService {

    private DetalleFacturaDAO detalleFacturaDAO;

    public DetalleFacturaServiceImpl(DetalleFacturaDAO detalleFacturaDAO) {
        this.detalleFacturaDAO = detalleFacturaDAO;
    }

    @Override
    public void registrarDetalleFactura(DetalleFactura detalleFactura) {
        detalleFacturaDAO.registrar(detalleFactura);
    }

    @Override
    public DetalleFactura buscarDetalleFacturaPorId(int id) {
        return detalleFacturaDAO.buscarPorId(id);
    }

    @Override
    public List<DetalleFactura> buscarDetallesPorFacturaId(int facturaId) {
        return detalleFacturaDAO.buscarPorFacturaId(facturaId);
    }

    @Override
    public List<DetalleFactura> mostrarListaDetalles() {
        return detalleFacturaDAO.mostrarLista();
    }

    @Override
    public void modificarDetalleFactura(DetalleFactura detalleFactura) {
        detalleFacturaDAO.modificar(detalleFactura);
    }

    @Override
    public void eliminarDetalleFactura(int id) {
        detalleFacturaDAO.eliminar(id);
    }
}
