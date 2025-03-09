package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Inventario;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public interface InventarioService {
    void registrarInventario(Inventario inventario);
    Inventario buscarInventarioPorId(int id);
    List<Inventario> buscarInventariosPorProductoId(int productoId);
    List<Inventario> mostrarListaInventarios();
    void modificarInventario(Inventario inventario);
    void eliminarInventario(int id);
    void aumentarInventario(int id, double cantidad);
    void disminuirInventario(int id, double cantidad);
    void reporteInventarioPDF(String destino) throws SQLException;
    void cargarTabla(JTable tabla) throws SQLException;
    void cargarTabla(JTable tabla, String palabraClave) throws SQLException;
}
