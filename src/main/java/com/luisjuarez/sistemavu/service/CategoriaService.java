package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Categoria;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;

public interface CategoriaService {
    void registrarCategoria(Categoria categoria);
    Categoria mostrarCategoria(String categoriaID);
    Categoria buscarCategoria(int id);
    List<Categoria> mostrarLista();
    void modificarCategoria(String categoriaID, Categoria categoria);
    void eliminarCategoria(String categoriaID);
    void cargarComboBoxCategorias(JComboBox cmb);
    void reporteCategoriasPDF(String destino) throws SQLException;
}
