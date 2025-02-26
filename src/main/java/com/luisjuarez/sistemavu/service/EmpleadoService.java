package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Empleado;
import java.util.List;

public interface EmpleadoService {
    void registrarEmpleado(Empleado empleado);
    Empleado buscarEmpleadoPorId(int id);
    List<Empleado> mostrarListaEmpleados();
    void modificarEmpleado(Empleado empleado);
    void eliminarEmpleado(int id);
    Empleado inicioSesion(String usuario, String contrasena);
    Empleado buscarEmpleadoPorCorreo(String correo);
    List<Empleado> buscarEmpleadosPorPalabraClave(String palabraClave);
}
