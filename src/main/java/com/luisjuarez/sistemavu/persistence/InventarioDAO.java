package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Inventario;
import java.util.List;

public interface InventarioDAO {
    void registrar(Inventario inventario);
    Inventario buscarPorId(int id);
    List<Inventario> buscarPorProductoId(int productoId);
    List<Inventario> mostrarLista();
    void modificar(Inventario inventario);
    void eliminar(int id);
    void aumentar(int id, double cantidad);
    void disminuir(int id, double cantidad);
}
