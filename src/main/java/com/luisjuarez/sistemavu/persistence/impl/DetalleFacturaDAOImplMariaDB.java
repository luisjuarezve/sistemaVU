package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.DetalleFactura;
import com.luisjuarez.sistemavu.persistence.DetalleFacturaDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleFacturaDAOImplMariaDB implements DetalleFacturaDAO {

    @Override
    public void registrar(DetalleFactura detalleFactura) {
        String sql = "INSERT INTO DetalleFactura (cantidad, precioUnitario, subtotal, Factura_idFactura, Producto_idProducto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, detalleFactura.getCantidad());
            stmt.setDouble(2, detalleFactura.getPrecioUnitario());
            stmt.setDouble(3, detalleFactura.getSubtotal());
            stmt.setInt(4, detalleFactura.getFactura_idFactura());
            stmt.setInt(5, detalleFactura.getProducto_idProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DetalleFactura buscarPorId(int id) {
        String sql = "SELECT * FROM DetalleFactura WHERE idDetalleFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new DetalleFactura(
                    rs.getInt("idDetalleFactura"),
                    rs.getDouble("cantidad"),
                    rs.getDouble("precioUnitario"),
                    rs.getDouble("subtotal"),
                    rs.getInt("Factura_idFactura"),
                    rs.getInt("Producto_idProducto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DetalleFactura> buscarPorFacturaId(int facturaId) {
        List<DetalleFactura> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DetalleFactura WHERE Factura_idFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, facturaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                detalles.add(new DetalleFactura(
                    rs.getInt("idDetalleFactura"),
                    rs.getDouble("cantidad"),
                    rs.getDouble("precioUnitario"),
                    rs.getDouble("subtotal"),
                    rs.getInt("Factura_idFactura"),
                    rs.getInt("Producto_idProducto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public List<DetalleFactura> mostrarLista() {
        List<DetalleFactura> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DetalleFactura";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                detalles.add(new DetalleFactura(
                    rs.getInt("idDetalleFactura"),
                    rs.getDouble("cantidad"),
                    rs.getDouble("precioUnitario"),
                    rs.getDouble("subtotal"),
                    rs.getInt("Factura_idFactura"),
                    rs.getInt("Producto_idProducto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalles;
    }

    @Override
    public void modificar(DetalleFactura detalleFactura) {
        String sql = "UPDATE DetalleFactura SET cantidad = ?, precioUnitario = ?, subtotal = ?, Factura_idFactura = ?, Producto_idProducto = ? WHERE idDetalleFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, detalleFactura.getCantidad());
            stmt.setDouble(2, detalleFactura.getPrecioUnitario());
            stmt.setDouble(3, detalleFactura.getSubtotal());
            stmt.setInt(4, detalleFactura.getFactura_idFactura());
            stmt.setInt(5, detalleFactura.getProducto_idProducto());
            stmt.setInt(6, detalleFactura.getIdDetalleFactura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM DetalleFactura WHERE idDetalleFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
