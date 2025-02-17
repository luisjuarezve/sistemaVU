package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.EmpleadoHasPermiso;
import java.util.List;

public interface EmpleadoHasPermisoDAO {
    void registrar(EmpleadoHasPermiso empleadoHasPermiso);
    EmpleadoHasPermiso buscarPorId(int empleadoId, int permisoId);
    List<EmpleadoHasPermiso> buscarPorEmpleadoId(int empleadoId);
    List<EmpleadoHasPermiso> buscarPorPermisoId(int permisoId);
    List<EmpleadoHasPermiso> mostrarLista();
    void modificar(EmpleadoHasPermiso empleadoHasPermiso);
    void eliminar(int empleadoId, int permisoId);
}
