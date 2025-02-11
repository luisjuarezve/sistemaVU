package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private int idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String imagen_producto;
    private double precio_venta;
    private double precio_mayoreo;
    private double utilidad;
    private Timestamp fecha_registro;
    private int Proveedor_idProveedor;
    private int Categoria_idCategoria;
}
