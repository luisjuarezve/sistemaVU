package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Factura;
import java.sql.Timestamp;
import java.util.List;

public interface FacturaDAO {
    void registrar(Factura factura);
    Factura buscarPorId(int id);
    List<Factura> buscarPorClienteId(int clienteId);
    List<Factura> buscarPorEmpleadoId(int empleadoId);
    List<Factura> buscarPorFecha(Timestamp desde, Timestamp hasta);
    List<Factura> mostrarLista();
    void modificar(Factura factura);
    void eliminar(int id);
    int contarFacturas();
}
