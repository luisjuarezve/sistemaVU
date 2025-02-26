package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.EmpleadoHasPermiso;
import java.util.List;

public interface EmpleadoHasPermisoService {
    void registrarEmpleadoHasPermiso(EmpleadoHasPermiso empleadoHasPermiso);
    EmpleadoHasPermiso buscarEmpleadoHasPermisoPorId(int empleadoId, int permisoId);
    List<EmpleadoHasPermiso> buscarEmpleadoHasPermisosPorEmpleadoId(int empleadoId);
    List<EmpleadoHasPermiso> buscarEmpleadoHasPermisosPorPermisoId(int permisoId);
    List<EmpleadoHasPermiso> mostrarListaEmpleadoHasPermisos();
    void modificarEmpleadoHasPermiso(EmpleadoHasPermiso empleadoHasPermiso);
    void eliminarEmpleadoHasPermiso(int empleadoId, int permisoId);
}
