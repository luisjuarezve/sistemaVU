package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Permiso;
import java.util.List;

public interface PermisoDAO {
    void registrar(Permiso permiso);
    Permiso buscarPorId(int id);
    List<Permiso> mostrarLista();
    void modificar(Permiso permiso);
    void eliminar(int id);
}
