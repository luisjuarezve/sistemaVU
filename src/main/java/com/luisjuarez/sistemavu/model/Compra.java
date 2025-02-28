package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {
    private int idCompra;
    private Timestamp fechaCompra;
    private double totalCompra;
    private int idProveedor;
}
