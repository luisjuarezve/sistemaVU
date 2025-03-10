package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Categoria;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;

public interface CategoriaService {
    void registrarCategoria(Categoria categoria);
    Categoria mostrarCategoria(String categoriaID);
    Categoria buscarCategoria(int id);
    List<Categoria> mostrarLista();
    List<Categoria> buscarCategoriasPorPalabraClave(String palabraClave);
    void modificarCategoria(String categoriaID, Categoria categoria);
    void eliminarCategoria(String categoriaID);
    void cargarComboBoxCategorias(JComboBox cmb);
    void reporteCategoriasPDF(String destino) throws SQLException;
    void cargarTabla(JTable tabla) throws SQLException;
    void cargarTabla(JTable tabla, String palabraClave) throws SQLException;
    void cargarComboBox(JComboBox comboBox);
}
