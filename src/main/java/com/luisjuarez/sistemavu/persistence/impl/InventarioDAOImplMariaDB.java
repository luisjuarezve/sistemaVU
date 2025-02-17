package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Inventario;
import com.luisjuarez.sistemavu.persistence.InventarioDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAOImplMariaDB implements InventarioDAO {

    @Override
    public void registrar(Inventario inventario) {
        String sql = "INSERT INTO inventario (cantidad, inventario_min, inventario_max, Producto_idProducto) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, inventario.getCantidad());
            stmt.setDouble(2, inventario.getInventario_min());
            stmt.setDouble(3, inventario.getInventario_max());
            stmt.setInt(4, inventario.getProducto_idProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inventario buscarPorId(int id) {
        String sql = "SELECT * FROM inventario WHERE idInventario = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Inventario(
                    rs.getInt("idInventario"),
                    rs.getDouble("cantidad"),
                    rs.getDouble("inventario_min"),
                    rs.getDouble("inventario_max"),
                    rs.getInt("Producto_idProducto")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inventario> buscarPorProductoId(int productoId) {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario WHERE Producto_idProducto = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                inventarios.add(new Inventario(
                    rs.getInt("idInventario"),
                    rs.getDouble("cantidad"),
                    rs.getDouble("inventario_min"),
                    rs.getDouble("inventario_max"),
                    rs.getInt("Producto_idProducto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    @Override
    public List<Inventario> mostrarLista() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                inventarios.add(new Inventario(
                    rs.getInt("idInventario"),
                    rs.getDouble("cantidad"),
                    rs.getDouble("inventario_min"),
                    rs.getDouble("inventario_max"),
                    rs.getInt("Producto_idProducto")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    @Override
    public void modificar(Inventario inventario) {
        String sql = "UPDATE inventario SET cantidad = ?, inventario_min = ?, inventario_max = ?, Producto_idProducto = ? WHERE idInventario = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, inventario.getCantidad());
            stmt.setDouble(2, inventario.getInventario_min());
            stmt.setDouble(3, inventario.getInventario_max());
            stmt.setInt(4, inventario.getProducto_idProducto());
            stmt.setInt(5, inventario.getIdInventario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM inventario WHERE idInventario = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void aumentar(int id, double cantidad) {
        String sql = "UPDATE inventario SET cantidad = cantidad + ? WHERE idInventario = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, cantidad);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disminuir(int id, double cantidad) {
        Inventario inventario = buscarPorId(id);
        if (inventario != null && inventario.getCantidad() >= cantidad) {
            String sql = "UPDATE inventario SET cantidad = cantidad - ? WHERE idInventario = ?";
            try (Connection conn = ConexionBDDMysql.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, cantidad);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("La cantidad a disminuir debe ser menor o igual a la cantidad actual en inventario para evitar inventario negativo");
        }
    }
}
