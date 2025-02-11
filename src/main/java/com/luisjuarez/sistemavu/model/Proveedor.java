package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {
    private int idProveedor;
    private String tipo_doc;
    private String nro_doc;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo_electronico;
    private String direccion;
    private String notas;
    private Timestamp fecha_registro;
}
