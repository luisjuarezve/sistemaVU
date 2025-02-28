package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.DetalleCompra;
import com.luisjuarez.sistemavu.persistence.DetalleCompraDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleCompraDAOImplMariaDB implements DetalleCompraDAO {

    @Override
    public void registrar(DetalleCompra detalleCompra) {
        String sql = "INSERT INTO detallecompra (cantidad, precio_unitario, subtotal, Compra_idCompra, producto_idProducto, Producto_Proveedor_idProveedor) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, detalleCompra.getCantidad());
            stmt.setDouble(2, detalleCompra.getPrecio_unitario());
            stmt.setDouble(3, detalleCompra.getSubtotal());
            stmt.setInt(4, detalleCompra.getCompra_idCompra());
            stmt.setInt(5, detalleCompra.getCompra_Producto_idProducto());
            stmt.setInt(6, detalleCompra.getCompra_Producto_Proveedor_idProveedor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetalleCompra> buscarPorIdCompra(int idCompra) {
        List<DetalleCompra> listaDetalles = new ArrayList<>();
        String sql = "SELECT * FROM detallecompra WHERE Compra_idCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCompra);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                DetalleCompra detalle = new DetalleCompra(
                        rs.getInt("idDetalleCompra"),
                        rs.getDouble("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal"),
                        rs.getInt("Compra_idCompra"),
                        rs.getInt("producto_idProducto"),
                        rs.getInt("Producto_Proveedor_idProveedor")
                );
                listaDetalles.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDetalles;
    }

    @Override
    public List<DetalleCompra> buscarPorProveedorId(int proveedorId) {
        List<DetalleCompra> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detallecompra WHERE Producto_Proveedor_idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, proveedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                detalles.add(new DetalleCompra(
                        rs.getInt("idDetalleCompra"),
                        rs.getDouble("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal"),
                        rs.getInt("Compra_idCompra"),
                        rs.getInt("producto_idProducto"),
                        rs.getInt("Producto_Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public List<DetalleCompra> buscarPorProductoId(int productoId) {
        List<DetalleCompra> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detallecompra WHERE producto_idProducto = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                detalles.add(new DetalleCompra(
                        rs.getInt("idDetalleCompra"),
                        rs.getDouble("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal"),
                        rs.getInt("Compra_idCompra"),
                        rs.getInt("producto_idProducto"),
                        rs.getInt("Producto_Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public List<DetalleCompra> mostrarLista() {
        List<DetalleCompra> detalles = new ArrayList<>();
        String sql = "SELECT * FROM detallecompra";
        try (Connection conn = ConexionBDDMysql.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                detalles.add(new DetalleCompra(
                        rs.getInt("idDetalleCompra"),
                        rs.getDouble("cantidad"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("subtotal"),
                        rs.getInt("Compra_idCompra"),
                        rs.getInt("producto_idProducto"),
                        rs.getInt("Producto_Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public void modificar(DetalleCompra detalleCompra) {
        String sql = "UPDATE detallecompra SET cantidad = ?, precio_unitario = ?, subtotal = ?, Compra_idCompra = ?, producto_idProducto = ?, Producto_Proveedor_idProveedor = ? WHERE idDetalleCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, detalleCompra.getCantidad());
            stmt.setDouble(2, detalleCompra.getPrecio_unitario());
            stmt.setDouble(3, detalleCompra.getSubtotal());
            stmt.setInt(4, detalleCompra.getCompra_idCompra());
            stmt.setInt(5, detalleCompra.getCompra_Producto_idProducto());
            stmt.setInt(6, detalleCompra.getCompra_Producto_Proveedor_idProveedor());
            stmt.setInt(7, detalleCompra.getIdDetalleCompra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM detallecompra WHERE idDetalleCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
