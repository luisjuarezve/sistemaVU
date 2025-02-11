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
    private double totalFactura;
    private Timestamp fecha;
    private int Cliente_idCliente;
    private int Empleado_idEmpleado;
}
