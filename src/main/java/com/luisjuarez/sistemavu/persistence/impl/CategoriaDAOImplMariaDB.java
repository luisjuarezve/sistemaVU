package com.luisjuarez.sistemavu.persistence.impl;

import com.luisjuarez.sistemavu.model.Categoria;
import com.luisjuarez.sistemavu.persistence.CategoriaDAO;
import com.luisjuarez.sistemavu.persistence.ConexionBDDMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImplMariaDB implements CategoriaDAO {

    @Override
    public void registrar(Categoria categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Categoria buscarPorId(int id) {
        String sql = "SELECT * FROM categoria WHERE idCategoria = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Categoria(
                        rs.getInt("idCategoria"),
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
    public List<Categoria> mostrarLista() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        try (Connection conn = ConexionBDDMysql.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categorias.add(new Categoria(
                        rs.getInt("idCategoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    @Override
    public void modificar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE idCategoria = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM categoria WHERE idCategoria = ?";
        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Categoria> buscarPorPalabraClave(String palabraClave) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE idCategoria LIKE ? OR nombre LIKE ?";

        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + palabraClave + "%"); // Decorador LIKE para idCategoria
            ps.setString(2, "%" + palabraClave + "%"); // Decorador LIKE para nombre

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    categorias.add(new Categoria(
                            rs.getInt("idCategoria"),
                            rs.getString("nombre"),
                            rs.getString("descripcion")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

}
