package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Producto;
import java.util.List;

public interface ProductoDAO {
    void registrar(Producto producto);
    Producto buscarPorCodigo(String codigo);
    List<Producto> buscarPorProveedorId(int proveedorId);
    List<Producto> buscarPorCategoriaId(int categoriaId);
    Producto buscarPorId(int id);
    List<Producto> buscarPorPalabraClave(String palabraClave);
    List<Producto> mostrarLista();
    void modificar(Producto producto);
    void eliminar(int id);
}
