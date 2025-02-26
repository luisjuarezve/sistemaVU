package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Permiso;
import com.luisjuarez.sistemavu.persistence.PermisoDAO;
import com.luisjuarez.sistemavu.service.PermisoService;

import java.util.List;

public class PermisoServiceImpl implements PermisoService {

    private PermisoDAO permisoDAO;

    public PermisoServiceImpl(PermisoDAO permisoDAO) {
        this.permisoDAO = permisoDAO;
    }

    @Override
    public void registrarPermiso(Permiso permiso) {
        permisoDAO.registrar(permiso);
    }

    @Override
    public Permiso buscarPermisoPorId(int id) {
        return permisoDAO.buscarPorId(id);
    }

    @Override
    public List<Permiso> mostrarListaPermisos() {
        return permisoDAO.mostrarLista();
    }

    @Override
    public void modificarPermiso(Permiso permiso) {
        permisoDAO.modificar(permiso);
    }

    @Override
    public void eliminarPermiso(int id) {
        permisoDAO.eliminar(id);
    }
}
