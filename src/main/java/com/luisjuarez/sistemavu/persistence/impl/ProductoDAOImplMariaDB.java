package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Producto;
import com.luisjuarez.sistemavu.persistence.ProductoDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImplMariaDB implements ProductoDAO {

    @Override
    public void registrar(Producto producto) {
        String sql = "INSERT INTO producto (codigo, nombre, descripcion, imagen_producto, precio_venta, precio_mayoreo, utilidad, impuesto, fecha_registro, Proveedor_idProveedor, Categoria_idCategoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setString(4, producto.getImagen_producto());
            stmt.setDouble(5, producto.getPrecio_venta());
            stmt.setDouble(6, producto.getPrecio_mayoreo());
            stmt.setDouble(7, producto.getUtilidad());
            stmt.setDouble(8, producto.getImpuesto());
            stmt.setTimestamp(9, producto.getFecha_registro());
            stmt.setInt(10, producto.getProveedor_idProveedor());
            stmt.setInt(11, producto.getCategoria_idCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM producto WHERE codigo = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("imagen_producto"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_mayoreo"),
                    rs.getDouble("utilidad"),
                    rs.getDouble("impuesto"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor"),
                    rs.getInt("Categoria_idCategoria")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorProveedorId(int proveedorId) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE Proveedor_idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, proveedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("imagen_producto"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_mayoreo"),
                    rs.getDouble("utilidad"),
                    rs.getDouble("impuesto"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor"),
                    rs.getInt("Categoria_idCategoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public List<Producto> buscarPorCategoriaId(int categoriaId) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE Categoria_idCategoria = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("imagen_producto"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_mayoreo"),
                    rs.getDouble("utilidad"),
                    rs.getDouble("impuesto"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor"),
                    rs.getInt("Categoria_idCategoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Producto buscarPorId(int id) {
        String sql = "SELECT * FROM producto WHERE idProducto = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("imagen_producto"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_mayoreo"),
                    rs.getDouble("utilidad"),
                    rs.getDouble("impuesto"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor"),
                    rs.getInt("Categoria_idCategoria")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorPalabraClave(String palabraClave) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE nombre LIKE ? OR codigo LIKE ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + palabraClave + "%");
            stmt.setString(2, "%" + palabraClave + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("imagen_producto"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_mayoreo"),
                    rs.getDouble("utilidad"),
                    rs.getDouble("impuesto"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor"),
                    rs.getInt("Categoria_idCategoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public List<Producto> mostrarLista() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("imagen_producto"),
                    rs.getDouble("precio_venta"),
                    rs.getDouble("precio_mayoreo"),
                    rs.getDouble("utilidad"),
                    rs.getDouble("impuesto"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor"),
                    rs.getInt("Categoria_idCategoria")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public void modificar(Producto producto) {
        String sql = "UPDATE producto SET codigo = ?, nombre = ?, descripcion = ?, imagen_producto = ?, precio_venta = ?, precio_mayoreo = ?, utilidad = ?, impuesto = ?, fecha_registro = ?, Proveedor_idProveedor = ?, Categoria_idCategoria = ? WHERE idProducto = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getCodigo());
            stmt.setString(2, producto.getNombre());
            stmt.setString(3, producto.getDescripcion());
            stmt.setString(4, producto.getImagen_producto());
            stmt.setDouble(5, producto.getPrecio_venta());
            stmt.setDouble(6, producto.getPrecio_mayoreo());
            stmt.setDouble(7, producto.getUtilidad());
            stmt.setDouble(8, producto.getImpuesto());
            stmt.setTimestamp(9, producto.getFecha_registro());
            stmt.setInt(10, producto.getProveedor_idProveedor());
            stmt.setInt(11, producto.getCategoria_idCategoria());
            stmt.setInt(12, producto.getIdProducto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM producto WHERE idProducto = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
