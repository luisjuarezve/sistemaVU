package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.Categoria;
import com.luisjuarez.sistemavu.persistence.CategoriaDAO;
import com.luisjuarez.sistemavu.service.CategoriaService;

import javax.swing.JComboBox;
import java.util.List;

public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImpl(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void registrarCategoria(Categoria categoria) {
        categoriaDAO.registrar(categoria);
    }

    @Override
    public Categoria mostrarCategoria(String categoriaID) {
        return categoriaDAO.buscarPorId(Integer.parseInt(categoriaID));
    }

    @Override
    public Categoria buscarCategoria(int id) {
        return categoriaDAO.buscarPorId(id);
    }

    @Override
    public List<Categoria> mostrarListaCategorias() {
        return categoriaDAO.mostrarLista();
    }

    @Override
    public void modificarCategoria(String categoriaID, Categoria categoria) {
        categoria.setIdCategoria(Integer.parseInt(categoriaID));
        categoriaDAO.modificar(categoria);
    }

    @Override
    public void eliminarCategoria(String categoriaID) {
        categoriaDAO.eliminar(Integer.parseInt(categoriaID));
    }

    @Override
    public void cargarComboBoxCategorias(JComboBox cmb) {
        List<Categoria> lista = categoriaDAO.mostrarLista();
        lista.forEach(categoria -> cmb.addItem(categoria.getNombre()));
    }
}
