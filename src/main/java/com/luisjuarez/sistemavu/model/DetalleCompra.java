package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompra {
    private int idDetalleCompra;
    private double cantidad;
    private double precio_unitario;
    private double subtotal;
    private int Compra_idCompra;
    private int Compra_Producto_idProducto;
    private int Compra_Producto_Proveedor_idProveedor;
}
