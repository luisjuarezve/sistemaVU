package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Empleado;
import java.util.List;

public interface EmpleadoDAO {
    void registrar(Empleado empleado);
    Empleado buscarPorId(int id);
    List<Empleado> mostrarLista();
    void modificar(Empleado empleado);
    void eliminar(int id);
    Empleado inicioSesion(String usuario, String contrasena);
    Empleado buscarPorCorreo(String correo);
    List<Empleado> buscarPorPalabraClave(String palabraClave);
}
