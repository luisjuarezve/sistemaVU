package com.luisjuarez.sistemavu.model;

public class CarritoProducto {
    private Producto producto;
    private int cantidad;
    private Inventario inventario;

    public CarritoProducto(Producto producto, int cantidad, Inventario inventario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.inventario = inventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
