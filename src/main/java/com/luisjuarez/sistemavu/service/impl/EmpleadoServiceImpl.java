package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Empleado;
import com.luisjuarez.sistemavu.persistence.EmpleadoDAO;
import com.luisjuarez.sistemavu.service.EmpleadoService;

import java.util.List;

public class EmpleadoServiceImpl implements EmpleadoService {

    private EmpleadoDAO empleadoDAO;

    public EmpleadoServiceImpl(EmpleadoDAO empleadoDAO) {
        this.empleadoDAO = empleadoDAO;
    }

    @Override
    public void registrarEmpleado(Empleado empleado) {
        empleadoDAO.registrar(empleado);
    }

    @Override
    public Empleado buscarEmpleadoPorId(int id) {
        return empleadoDAO.buscarPorId(id);
    }

    @Override
    public List<Empleado> mostrarListaEmpleados() {
        return empleadoDAO.mostrarLista();
    }

    @Override
    public void modificarEmpleado(Empleado empleado) {
        empleadoDAO.modificar(empleado);
    }

    @Override
    public void eliminarEmpleado(int id) {
        empleadoDAO.eliminar(id);
    }

    @Override
    public Empleado inicioSesion(String usuario, String contrasena) {
        return empleadoDAO.inicioSesion(usuario, contrasena);
    }

    @Override
    public Empleado buscarEmpleadoPorCorreo(String correo) {
        return empleadoDAO.buscarPorCorreo(correo);
    }

    @Override
    public List<Empleado> buscarEmpleadosPorPalabraClave(String palabraClave) {
        return empleadoDAO.buscarPorPalabraClave(palabraClave);
    }
}
