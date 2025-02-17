package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Empleado;
import com.luisjuarez.sistemavu.persistence.EmpleadoDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImplMariaDB implements EmpleadoDAO {

    @Override
    public void registrar(Empleado empleado) {
        String sql = "INSERT INTO empleado (nombre, apellido, usuario, contrasena, correo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getContrasena());
            stmt.setString(5, empleado.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Empleado buscarPorId(int id) {
        String sql = "SELECT * FROM empleado WHERE idEmpleado = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                    rs.getInt("idEmpleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Empleado> mostrarLista() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                empleados.add(new Empleado(
                    rs.getInt("idEmpleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("correo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public void modificar(Empleado empleado) {
        String sql = "UPDATE empleado SET nombre = ?, apellido = ?, usuario = ?, contrasena = ?, correo = ? WHERE idEmpleado = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setString(3, empleado.getUsuario());
            stmt.setString(4, empleado.getContrasena());
            stmt.setString(5, empleado.getCorreo());
            stmt.setInt(6, empleado.getIdEmpleado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM empleado WHERE idEmpleado = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Empleado inicioSesion(String usuario, String contrasena) {
        String sql = "SELECT * FROM empleado WHERE usuario = ? AND contrasena = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                    rs.getInt("idEmpleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Empleado buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM empleado WHERE correo = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Empleado(
                    rs.getInt("idEmpleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Empleado> buscarPorPalabraClave(String palabraClave) {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado WHERE nombre LIKE ? OR apellido LIKE ? OR correo LIKE ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + palabraClave + "%");
            stmt.setString(2, "%" + palabraClave + "%");
            stmt.setString(3, "%" + palabraClave + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                empleados.add(new Empleado(
                    rs.getInt("idEmpleado"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("usuario"),
                    rs.getString("contrasena"),
                    rs.getString("correo")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
