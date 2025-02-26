package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Permiso;
import java.util.List;

public interface PermisoService {
    void registrarPermiso(Permiso permiso);
    Permiso buscarPermisoPorId(int id);
    List<Permiso> mostrarListaPermisos();
    void modificarPermiso(Permiso permiso);
    void eliminarPermiso(int id);
}
