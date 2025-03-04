package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Factura;
import com.luisjuarez.sistemavu.persistence.FacturaDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOImplMariaDB implements FacturaDAO {

    @Override
    public void registrar(Factura factura) {
        String sql = "INSERT INTO factura (subtotal, impuesto, tasa, totalFactura, cantidad, fecha, Cliente_idCliente, Empleado_idEmpleado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, factura.getSubtotal());
            stmt.setDouble(2, factura.getImpuesto());
            stmt.setDouble(3, factura.getTasa());
            stmt.setDouble(4, factura.getTotalFactura());
            stmt.setDouble(5, factura.getCantidad()); // Nuevo campo
            stmt.setTimestamp(6, factura.getFecha());
            stmt.setInt(7, factura.getCliente_idCliente());
            stmt.setInt(8, factura.getEmpleado_idEmpleado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Factura buscarPorId(int id) {
        String sql = "SELECT * FROM factura WHERE idFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("tasa"), // Nuevo campo
                    rs.getDouble("totalFactura"),
                    rs.getDouble("cantidad"), // Nuevo campo
                    rs.getTimestamp("fecha"),
                    rs.getInt("Cliente_idCliente"),
                    rs.getInt("Empleado_idEmpleado")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Factura> buscarPorClienteId(int clienteId) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura WHERE Cliente_idCliente = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                facturas.add(new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("tasa"), // Nuevo campo
                    rs.getDouble("totalFactura"),
                    rs.getDouble("cantidad"), // Nuevo campo
                    rs.getTimestamp("fecha"),
                    rs.getInt("Cliente_idCliente"),
                    rs.getInt("Empleado_idEmpleado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public List<Factura> buscarPorEmpleadoId(int empleadoId) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura WHERE Empleado_idEmpleado = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                facturas.add(new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("tasa"), // Nuevo campo
                    rs.getDouble("totalFactura"),
                    rs.getDouble("cantidad"), // Nuevo campo
                    rs.getTimestamp("fecha"),
                    rs.getInt("Cliente_idCliente"),
                    rs.getInt("Empleado_idEmpleado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public List<Factura> buscarPorFecha(Timestamp desde, Timestamp hasta) {
        if (desde.after(hasta)) {
            throw new IllegalArgumentException("La fecha 'desde' debe ser menor que la fecha 'hasta'");
        }

        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura WHERE fecha BETWEEN ? AND ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, desde);
            stmt.setTimestamp(2, hasta);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                facturas.add(new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("tasa"), // Nuevo campo
                    rs.getDouble("totalFactura"),
                    rs.getDouble("cantidad"), // Nuevo campo
                    rs.getTimestamp("fecha"),
                    rs.getInt("Cliente_idCliente"),
                    rs.getInt("Empleado_idEmpleado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public List<Factura> mostrarLista() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                facturas.add(new Factura(
                    rs.getInt("idFactura"),
                    rs.getDouble("subtotal"),
                    rs.getDouble("impuesto"),
                    rs.getDouble("tasa"), // Nuevo campo
                    rs.getDouble("totalFactura"),
                    rs.getDouble("cantidad"), // Nuevo campo
                    rs.getTimestamp("fecha"),
                    rs.getInt("Cliente_idCliente"),
                    rs.getInt("Empleado_idEmpleado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facturas;
    }

    @Override
    public void modificar(Factura factura) {
        String sql = "UPDATE factura SET subtotal = ?, impuesto = ?, tasa = ?, totalFactura = ?, cantidad = ?, fecha = ?, Cliente_idCliente = ?, Empleado_idEmpleado = ? WHERE idFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, factura.getSubtotal());
            stmt.setDouble(2, factura.getImpuesto());
            stmt.setDouble(3, factura.getTasa());
            stmt.setDouble(4, factura.getTotalFactura());
            stmt.setDouble(5, factura.getCantidad()); // Nuevo campo
            stmt.setTimestamp(6, factura.getFecha());
            stmt.setInt(7, factura.getCliente_idCliente());
            stmt.setInt(8, factura.getEmpleado_idEmpleado());
            stmt.setInt(9, factura.getIdFactura());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM factura WHERE idFactura = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
