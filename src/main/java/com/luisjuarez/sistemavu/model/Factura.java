package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    private int idFactura;
    private double subtotal;
    private double impuesto;
    private double tasa;
    private double totalFactura;
    private double cantidad; // Nuevo campo
    private Timestamp fecha;
    private int Cliente_idCliente;
    private int Empleado_idEmpleado;
}

