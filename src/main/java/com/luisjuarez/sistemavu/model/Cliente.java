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

    public void limpiar() {
        this.idCliente = 0;
        this.tipo_doc = "";
        this.nro_doc = "";
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.direccion = "";
        this.correo_electronico = "";
        this.fecha_registro = null; // Restablecer a null
        this.notas = "";
    }

}
