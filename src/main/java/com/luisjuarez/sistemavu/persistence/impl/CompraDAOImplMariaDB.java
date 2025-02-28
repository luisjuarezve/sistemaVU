package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Compra;
import com.luisjuarez.sistemavu.persistence.CompraDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImplMariaDB implements CompraDAO {

    @Override
    public void registrar(Compra compra) {
        String sql = "INSERT INTO compra (fechaCompra, totalCompra, Producto_idProducto, Producto_Proveedor_idProveedor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, compra.getFechaCompra());
            stmt.setDouble(2, compra.getTotalCompra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Compra> mostrarLista() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("idCompra"),
                        rs.getTimestamp("fechaCompra"),
                        rs.getDouble("totalCompra"),
                        rs.getInt("Proveedor_idProveedor")
                );
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }

    @Override
    public Compra buscarPorId(int id) {
        String sql = "SELECT * FROM compra WHERE idCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Compra(
                        rs.getInt("idCompra"),
                        rs.getTimestamp("fechaCompra"),
                        rs.getDouble("totalCompra"),
                        rs.getInt("Proveedor_idProveedor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Compra> buscarPorProveedorId(int proveedorId) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE Producto_Proveedor_idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, proveedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                compras.add(new Compra(
                        rs.getInt("idCompra"),
                        rs.getTimestamp("fechaCompra"),
                        rs.getDouble("totalCompra"),
                        rs.getInt("Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }

    @Override
    public List<Compra> buscarPorFecha(Timestamp fecha) {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE fechaCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, fecha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                compras.add(new Compra(
                        rs.getInt("idCompra"),
                        rs.getTimestamp("fechaCompra"),
                        rs.getDouble("totalCompra"),
                        rs.getInt("Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }

    @Override
    public void modificar(Compra compra) {
        String sql = "UPDATE compra SET fechaCompra = ?, totalCompra = ?, Producto_idProducto = ?, Producto_Proveedor_idProveedor = ? WHERE idCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, compra.getFechaCompra());
            stmt.setDouble(2, compra.getTotalCompra());
            stmt.setInt(3, compra.getIdCompra());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM compra WHERE idCompra = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
