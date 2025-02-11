package com.luisjuarez.sistemavu.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFactura {
    private int idDetalleFactura;
    private double cantidad;
    private double precioUnitario;
    private double subtotal;
    private int Factura_idFactura;
    private int Producto_idProducto;
}
