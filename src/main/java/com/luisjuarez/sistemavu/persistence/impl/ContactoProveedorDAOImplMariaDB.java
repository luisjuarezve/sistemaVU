package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.ContactoProveedor;
import com.luisjuarez.sistemavu.persistence.ContactoProveedorDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoProveedorDAOImplMariaDB implements ContactoProveedorDAO {

    @Override
    public void registrar(ContactoProveedor contactoProveedor) {
        String sql = "INSERT INTO contactoproveedor (nombre, apellido, telefono, correo, estado, fecha_registro, Proveedor_idProveedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contactoProveedor.getNombre());
            stmt.setString(2, contactoProveedor.getApellido());
            stmt.setString(3, contactoProveedor.getTelefono());
            stmt.setString(4, contactoProveedor.getCorreo());
            stmt.setBoolean(5, contactoProveedor.isEstado());
            stmt.setTimestamp(6, contactoProveedor.getFecha_registro());
            stmt.setInt(7, contactoProveedor.getProveedor_idProveedor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactoProveedor buscarPorId(int id) {
        String sql = "SELECT * FROM contactoproveedor WHERE idContactoProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new ContactoProveedor(
                    rs.getInt("idContactoProveedor"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getBoolean("estado"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ContactoProveedor> buscarPorPalabraClave(String palabraClave) {
        List<ContactoProveedor> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactoproveedor WHERE nombre LIKE ? OR apellido LIKE ? OR correo LIKE ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + palabraClave + "%");
            stmt.setString(2, "%" + palabraClave + "%");
            stmt.setString(3, "%" + palabraClave + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contactos.add(new ContactoProveedor(
                    rs.getInt("idContactoProveedor"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getBoolean("estado"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    @Override
    public List<ContactoProveedor> buscarPorProveedorId(int proveedorId) {
        List<ContactoProveedor> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactoproveedor WHERE Proveedor_idProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, proveedorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                contactos.add(new ContactoProveedor(
                    rs.getInt("idContactoProveedor"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getBoolean("estado"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    @Override
    public List<ContactoProveedor> mostrarLista() {
        List<ContactoProveedor> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactoproveedor";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contactos.add(new ContactoProveedor(
                    rs.getInt("idContactoProveedor"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getBoolean("estado"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getInt("Proveedor_idProveedor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    @Override
    public void modificar(ContactoProveedor contactoProveedor) {
        String sql = "UPDATE contactoproveedor SET nombre = ?, apellido = ?, telefono = ?, correo = ?, estado = ?, fecha_registro = ?, Proveedor_idProveedor = ? WHERE idContactoProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contactoProveedor.getNombre());
            stmt.setString(2, contactoProveedor.getApellido());
            stmt.setString(3, contactoProveedor.getTelefono());
            stmt.setString(4, contactoProveedor.getCorreo());
            stmt.setBoolean(5, contactoProveedor.isEstado());
            stmt.setTimestamp(6, contactoProveedor.getFecha_registro());
            stmt.setInt(7, contactoProveedor.getProveedor_idProveedor());
            stmt.setInt(8, contactoProveedor.getIdContactoProveedor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM contactoproveedor WHERE idContactoProveedor = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
