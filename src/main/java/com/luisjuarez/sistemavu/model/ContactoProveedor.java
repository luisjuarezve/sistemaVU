package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoProveedor {
    private int idContactoProveedor;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private boolean estado;
    private Timestamp fecha_registro;
    private int Proveedor_idProveedor;
}
