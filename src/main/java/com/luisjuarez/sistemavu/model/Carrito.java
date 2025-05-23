package com.luisjuarez.sistemavu.model;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<CarritoProducto> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, Inventario inventario, int cantidad) {
        if (inventario.getCantidad() >= cantidad) {
            for (CarritoProducto item : items) {
                if (item.getProducto().getCodigo().equals(producto.getCodigo())) {
                    item.setCantidad(item.getCantidad() + cantidad);
                    item.getInventario().setCantidad(item.getInventario().getCantidad() - cantidad); // Reduce la cantidad en inventario específico
                    return;
                }
            }
            items.add(new CarritoProducto(producto, cantidad, inventario));
            inventario.setCantidad(inventario.getCantidad() - cantidad); // Reduce la cantidad en inventario
        }
    }

    public void disminuirProducto(Producto producto, Inventario inventario, int cantidad) {
        for (CarritoProducto item : items) {
            if (item.getProducto().getCodigo().equals(producto.getCodigo())) {
                if (item.getCantidad() > cantidad) {
                    item.setCantidad(item.getCantidad() - cantidad);
                    item.getInventario().setCantidad(item.getInventario().getCantidad() + cantidad); // Restaurar al inventario específico
                } else {
                    cantidad = item.getCantidad();
                    items.remove(item);
                    inventario.setCantidad(inventario.getCantidad() + cantidad); // Restaurar al inventario
                }
                return;
            }
        }
    }

    public double calcularBIG() {
        double total = 0.0;
        for (CarritoProducto item : items) {
            if (item.getProducto().getImpuesto() > 0) {
                total += item.getCantidad() * item.getProducto().getPrecio_venta();
            }
        }
        return total;
    }

    public double calcularExcento() {
        double total = 0.0;
        for (CarritoProducto item : items) {
            if (item.getProducto().getImpuesto() == 0) {
                total += item.getCantidad() * item.getProducto().getPrecio_venta();
            }
        }
        return total;
    }

    public double calcularIVA() {
        double total = 0.0;
        for (CarritoProducto item : items) {
            total += item.getCantidad() * item.getProducto().getPrecio_venta() * (item.getProducto().getImpuesto() / 100);
        }
        return total;
    }

    public ArrayList<CarritoProducto> getItems() {
        return items;
    }

    public void limpiarCarrito() {
        items.clear();
    }

    public int getCantidadTotal() {
        int total = 0;
        for (CarritoProducto item : items) {
            total += item.getCantidad(); // Suma la cantidad de cada producto
        }
        return total;
    }

}
