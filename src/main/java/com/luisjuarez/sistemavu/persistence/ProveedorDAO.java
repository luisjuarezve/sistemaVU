package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Proveedor;
import java.util.List;

public interface ProveedorDAO {
    void registrar(Proveedor proveedor);
    Proveedor buscarPorDocumento(String tipo_doc, String nro_doc);
    Proveedor buscarPorId(int id);
    List<Proveedor> buscarPorPalabraClave(String palabraClave);
    List<Proveedor> mostrarLista();
    void modificar(Proveedor proveedor);
    void eliminar(int id);
}
