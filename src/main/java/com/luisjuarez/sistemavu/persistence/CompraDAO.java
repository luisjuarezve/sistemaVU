package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Compra;
import java.sql.Timestamp;
import java.util.List;

public interface CompraDAO {
    void registrar(Compra compra);
    Compra buscarPorId(int id);
    List<Compra> buscarPorProductoId(int productoId);
    List<Compra> buscarPorProveedorId(int proveedorId);
    List<Compra> buscarPorFecha(Timestamp fecha);
    void modificar(Compra compra);
    void eliminar(int id);
}
