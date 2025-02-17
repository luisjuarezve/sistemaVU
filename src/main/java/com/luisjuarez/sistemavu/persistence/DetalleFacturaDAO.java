package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.DetalleFactura;
import java.util.List;

public interface DetalleFacturaDAO {
    void registrar(DetalleFactura detalleFactura);
    DetalleFactura buscarPorId(int id);
    List<DetalleFactura> buscarPorFacturaId(int facturaId);
    List<DetalleFactura> mostrarLista();
    void modificar(DetalleFactura detalleFactura);
    void eliminar(int id);
}
