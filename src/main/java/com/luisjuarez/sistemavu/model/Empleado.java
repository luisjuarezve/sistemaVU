package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private int idEmpleado;
    private String nombre;
    private String apellido;
    private String usuario;
    private String contrasena;
    private String correo;
}
