package com.luisjuarez.sistemavu.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ayuda {

    // Método para obtener todas las preguntas de la tabla
    public List<String> obtenerPreguntas() {
        List<String> preguntas = new ArrayList<>();
        String query = "SELECT titulo FROM PreguntasRespuestas";

        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                preguntas.add(rs.getString("titulo")); // Agregar las preguntas a la lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return preguntas;
    }

    // Método para obtener respuestas asociadas a una pregunta específica (búsqueda parcial)
    public List<String> obtenerRespuestasPorPregunta(String textoBusqueda) {
        List<String> respuestas = new ArrayList<>();
        String query = "SELECT descripcion FROM PreguntasRespuestas WHERE titulo LIKE ? OR CATEGORIA LIKE ?";

        try (Connection conn = ConexionBDDMysql.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + textoBusqueda + "%"); // Agregar comodines para búsqueda parcial
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    respuestas.add(rs.getString("descripcion")); // Agregar la respuesta a la lista
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respuestas;
    }
}
