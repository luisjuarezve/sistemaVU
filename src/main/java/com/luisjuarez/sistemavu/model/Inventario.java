package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    private int idInventario;
    private double cantidad;
    private double inventario_min;
    private double inventario_max;
    private int Producto_idProducto;
}
