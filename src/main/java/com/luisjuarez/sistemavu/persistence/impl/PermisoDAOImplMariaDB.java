package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Permiso;
import com.luisjuarez.sistemavu.persistence.PermisoDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermisoDAOImplMariaDB implements PermisoDAO {

    @Override
    public void registrar(Permiso permiso) {
        String sql = "INSERT INTO permiso (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, permiso.getNombre());
            stmt.setString(2, permiso.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Permiso buscarPorId(int id) {
        String sql = "SELECT * FROM permiso WHERE idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Permiso(
                    rs.getInt("idPermiso"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Permiso> mostrarLista() {
        List<Permiso> permisos = new ArrayList<>();
        String sql = "SELECT * FROM permiso";
        try (Connection conn = ConexionBDDMysql.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                permisos.add(new Permiso(
                    rs.getInt("idPermiso"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permisos;
    }

    @Override
    public void modificar(Permiso permiso) {
        String sql = "UPDATE permiso SET nombre = ?, descripcion = ? WHERE idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, permiso.getNombre());
            stmt.setString(2, permiso.getDescripcion());
            stmt.setInt(3, permiso.getIdPermiso());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM permiso WHERE idPermiso = ?";
        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
