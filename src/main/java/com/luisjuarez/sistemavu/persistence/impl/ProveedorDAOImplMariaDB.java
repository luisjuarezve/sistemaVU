package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Proveedor;
import com.luisjuarez.sistemavu.persistence.ProveedorDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAOImplMariaDB implements ProveedorDAO {

    @Override
    public void registrar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (tipo_doc, nro_doc, nombre, apellido, telefono, correo_electronico, direccion, notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, proveedor.getTipo_doc());
            ps.setString(2, proveedor.getNro_doc());
            ps.setString(3, proveedor.getNombre());
            ps.setString(4, proveedor.getApellido());
            ps.setString(5, proveedor.getTelefono());
            ps.setString(6, proveedor.getCorreo_electronico());
            ps.setString(7, proveedor.getDireccion());
            ps.setString(8, proveedor.getNotas());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Proveedor buscarPorDocumento(String tipo_doc, String nro_doc) {
        String sql = "SELECT * FROM proveedor WHERE tipo_doc = ? AND nro_doc = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tipo_doc);
            ps.setString(2, nro_doc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return obtenerProveedor(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Proveedor buscarPorId(int id) {
        String sql = "SELECT * FROM proveedor WHERE idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return obtenerProveedor(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Proveedor> buscarPorPalabraClave(String palabraClave) {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE tipo_doc LIKE ? OR nro_doc LIKE ? OR nombre LIKE ? OR apellido LIKE ? OR correo_electronico LIKE ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + palabraClave + "%");
            ps.setString(2, "%" + palabraClave + "%");
            ps.setString(3, "%" + palabraClave + "%");
            ps.setString(4, "%" + palabraClave + "%");
            ps.setString(5, "%" + palabraClave + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    proveedores.add(obtenerProveedor(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    @Override
    public List<Proveedor> mostrarLista() {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try (Connection conn = ConexionBDDMysql.getConnection(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                proveedores.add(obtenerProveedor(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }

    @Override
    public void modificar(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET nombre = ?, apellido = ?, telefono = ?, correo_electronico = ?, direccion = ?, notas = ? WHERE idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getApellido());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getCorreo_electronico());
            ps.setString(5, proveedor.getDireccion());
            ps.setString(6, proveedor.getNotas());
            ps.setInt(7, proveedor.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM proveedor WHERE idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Proveedor obtenerProveedor(ResultSet rs) throws SQLException {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(rs.getInt("idProveedor"));
        proveedor.setTipo_doc(rs.getString("tipo_doc"));
        proveedor.setNro_doc(rs.getString("nro_doc"));
        proveedor.setNombre(rs.getString("nombre"));
        proveedor.setApellido(rs.getString("apellido"));
        proveedor.setTelefono(rs.getString("telefono"));
        proveedor.setCorreo_electronico(rs.getString("correo_electronico"));
        proveedor.setDireccion(rs.getString("direccion"));
        proveedor.setNotas(rs.getString("notas"));
        proveedor.setFecha_registro(rs.getTimestamp("fecha_registro"));
        return proveedor;
    }
}
