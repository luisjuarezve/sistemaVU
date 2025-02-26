package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.EmpleadoHasPermiso;
import com.luisjuarez.sistemavu.persistence.EmpleadoHasPermisoDAO;
import com.luisjuarez.sistemavu.service.EmpleadoHasPermisoService;

import java.util.List;

public class EmpleadoHasPermisoServiceImpl implements EmpleadoHasPermisoService {

    private EmpleadoHasPermisoDAO empleadoHasPermisoDAO;

    public EmpleadoHasPermisoServiceImpl(EmpleadoHasPermisoDAO empleadoHasPermisoDAO) {
        this.empleadoHasPermisoDAO = empleadoHasPermisoDAO;
    }

    @Override
    public void registrarEmpleadoHasPermiso(EmpleadoHasPermiso empleadoHasPermiso) {
        empleadoHasPermisoDAO.registrar(empleadoHasPermiso);
    }

    @Override
    public EmpleadoHasPermiso buscarEmpleadoHasPermisoPorId(int empleadoId, int permisoId) {
        return empleadoHasPermisoDAO.buscarPorId(empleadoId, permisoId);
    }

    @Override
    public List<EmpleadoHasPermiso> buscarEmpleadoHasPermisosPorEmpleadoId(int empleadoId) {
        return empleadoHasPermisoDAO.buscarPorEmpleadoId(empleadoId);
    }

    @Override
    public List<EmpleadoHasPermiso> buscarEmpleadoHasPermisosPorPermisoId(int permisoId) {
        return empleadoHasPermisoDAO.buscarPorPermisoId(permisoId);
    }

    @Override
    public List<EmpleadoHasPermiso> mostrarListaEmpleadoHasPermisos() {
        return empleadoHasPermisoDAO.mostrarLista();
    }

    @Override
    public void modificarEmpleadoHasPermiso(EmpleadoHasPermiso empleadoHasPermiso) {
        empleadoHasPermisoDAO.modificar(empleadoHasPermiso);
    }

    @Override
    public void eliminarEmpleadoHasPermiso(int empleadoId, int permisoId) {
        empleadoHasPermisoDAO.eliminar(empleadoId, permisoId);
    }
}
