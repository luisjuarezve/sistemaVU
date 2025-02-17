package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Cliente;
import com.luisjuarez.sistemavu.persistence.ClienteDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImplMariaDB implements ClienteDAO {

    @Override
    public void registrar(Cliente cliente) {
        String sql = "INSERT INTO cliente (tipo_doc, nro_doc, nombre, apellido, telefono, direccion, correo_electronico, fecha_registro, notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getTipo_doc());
            stmt.setString(2, cliente.getNro_doc());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getApellido());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getDireccion());
            stmt.setString(7, cliente.getCorreo_electronico());
            stmt.setTimestamp(8, cliente.getFecha_registro());
            stmt.setString(9, cliente.getNotas());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente buscarPorDocumento(String tipo_doc, String nro_doc) {
        String sql = "SELECT * FROM cliente WHERE tipo_doc = ? AND nro_doc = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipo_doc);
            stmt.setString(2, nro_doc);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("tipo_doc"),
                    rs.getString("nro_doc"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo_electronico"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getString("notas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE idCliente = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("tipo_doc"),
                    rs.getString("nro_doc"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo_electronico"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getString("notas")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> buscarPorPalabraClave(String palabraClave) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nombre LIKE ? OR apellido LIKE ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + palabraClave + "%");
            stmt.setString(2, "%" + palabraClave + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("tipo_doc"),
                    rs.getString("nro_doc"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo_electronico"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getString("notas")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public List<Cliente> mostrarLista() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("tipo_doc"),
                    rs.getString("nro_doc"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("correo_electronico"),
                    rs.getTimestamp("fecha_registro"),
                    rs.getString("notas")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void modificar(Cliente cliente) {
        String sql = "UPDATE cliente SET tipo_doc = ?, nro_doc = ?, nombre = ?, apellido = ?, telefono = ?, direccion = ?, correo_electronico = ?, fecha_registro = ?, notas = ? WHERE idCliente = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getTipo_doc());
            stmt.setString(2, cliente.getNro_doc());
            stmt.setString(3, cliente.getNombre());
            stmt.setString(4, cliente.getApellido());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getDireccion());
            stmt.setString(7, cliente.getCorreo_electronico());
            stmt.setTimestamp(8, cliente.getFecha_registro());
            stmt.setString(9, cliente.getNotas());
            stmt.setInt(10, cliente.getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE idCliente = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
