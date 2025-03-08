package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Categoria;
import java.util.List;

public interface CategoriaDAO {
    void registrar(Categoria categoria);
    Categoria buscarPorId(int id);
    List<Categoria> mostrarLista();
    List<Categoria> buscarPorPalabraClave(String palabraClave);
    void modificar(Categoria categoria);
    void eliminar(int id);
}
