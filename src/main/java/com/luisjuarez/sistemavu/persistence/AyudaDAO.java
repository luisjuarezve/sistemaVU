package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Ayuda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AyudaDAO {

    // Método para obtener todas las entradas de la tabla como objetos Ayuda
    public List<Ayuda> obtenerTodasLasAyudas() {
        List<Ayuda> ayudas = new ArrayList<>();
        String query = "SELECT titulo, descripcion, categoria FROM PreguntasRespuestas";

        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ayuda ayuda = new Ayuda(
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("categoria")
                );
                ayudas.add(ayuda);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ayudas;
    }

    // Método para obtener ayudas según una búsqueda parcial por título o categoría
    public List<Ayuda> buscarAyudasPorTexto(String textoBusqueda) {
        List<Ayuda> ayudas = new ArrayList<>();
        String query = "SELECT titulo, descripcion, categoria FROM PreguntasRespuestas WHERE titulo LIKE ? OR categoria LIKE ?";

        try (Connection conn = ConexionBDDMysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + textoBusqueda + "%"); // Buscar en el título
            stmt.setString(2, "%" + textoBusqueda + "%"); // Buscar en la categoría
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Ayuda ayuda = new Ayuda(
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("categoria")
                    );
                    ayudas.add(ayuda);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ayudas;
    }
}
