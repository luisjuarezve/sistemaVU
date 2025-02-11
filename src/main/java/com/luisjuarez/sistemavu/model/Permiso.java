package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {
    private int idPermiso;
    private String nombre;
    private String descripcion;
}
