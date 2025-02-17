package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.EmpleadoHasPermiso;
import com.luisjuarez.sistemavu.persistence.EmpleadoHasPermisoDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoHasPermisoDAOImplMariaDB implements EmpleadoHasPermisoDAO {

    @Override
    public void registrar(EmpleadoHasPermiso empleadoHasPermiso) {
        String sql = "INSERT INTO empleado_has_permiso (Empleado_idEmpleado, Permiso_idPermiso) VALUES (?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleadoHasPermiso.getEmpleado_idEmpleado());
            stmt.setInt(2, empleadoHasPermiso.getPermiso_idPermiso());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EmpleadoHasPermiso buscarPorId(int empleadoId, int permisoId) {
        String sql = "SELECT * FROM empleado_has_permiso WHERE Empleado_idEmpleado = ? AND Permiso_idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            stmt.setInt(2, permisoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EmpleadoHasPermiso(
                    rs.getInt("Empleado_idEmpleado"),
                    rs.getInt("Permiso_idPermiso")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EmpleadoHasPermiso> buscarPorEmpleadoId(int empleadoId) {
        List<EmpleadoHasPermiso> permisos = new ArrayList<>();
        String sql = "SELECT * FROM empleado_has_permiso WHERE Empleado_idEmpleado = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                permisos.add(new EmpleadoHasPermiso(
                    rs.getInt("Empleado_idEmpleado"),
                    rs.getInt("Permiso_idPermiso")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permisos;
    }

    @Override
    public List<EmpleadoHasPermiso> buscarPorPermisoId(int permisoId) {
        List<EmpleadoHasPermiso> permisos = new ArrayList<>();
        String sql = "SELECT * FROM empleado_has_permiso WHERE Permiso_idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, permisoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                permisos.add(new EmpleadoHasPermiso(
                    rs.getInt("Empleado_idEmpleado"),
                    rs.getInt("Permiso_idPermiso")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permisos;
    }

    @Override
    public List<EmpleadoHasPermiso> mostrarLista() {
        List<EmpleadoHasPermiso> permisos = new ArrayList<>();
        String sql = "SELECT * FROM empleado_has_permiso";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                permisos.add(new EmpleadoHasPermiso(
                    rs.getInt("Empleado_idEmpleado"),
                    rs.getInt("Permiso_idPermiso")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permisos;
    }

    @Override
    public void modificar(EmpleadoHasPermiso empleadoHasPermiso) {
        String sql = "UPDATE empleado_has_permiso SET Empleado_idEmpleado = ?, Permiso_idPermiso = ? WHERE Empleado_idEmpleado = ? AND Permiso_idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleadoHasPermiso.getEmpleado_idEmpleado());
            stmt.setInt(2, empleadoHasPermiso.getPermiso_idPermiso());
            stmt.setInt(3, empleadoHasPermiso.getEmpleado_idEmpleado());
            stmt.setInt(4, empleadoHasPermiso.getPermiso_idPermiso());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int empleadoId, int permisoId) {
        String sql = "DELETE FROM empleado_has_permiso WHERE Empleado_idEmpleado = ? AND Permiso_idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empleadoId);
            stmt.setInt(2, permisoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
