package com.luisjuarez.sistemavu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private int idCliente;
    private String tipo_doc;
    private String nro_doc;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String correo_electronico;
    private Timestamp fecha_registro;
    private String notas;
}
