package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoHasPermiso {
    private int Empleado_idEmpleado;
    private int Permiso_idPermiso;
}
