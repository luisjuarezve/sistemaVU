package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.ContactoProveedor;
import java.util.List;

public interface ContactoProveedorDAO {
    void registrar(ContactoProveedor contactoProveedor);
    ContactoProveedor buscarPorId(int id);
    List<ContactoProveedor> buscarPorPalabraClave(String palabraClave);
    List<ContactoProveedor> buscarPorProveedorId(int proveedorId);
    List<ContactoProveedor> mostrarLista();
    void modificar(ContactoProveedor contactoProveedor);
    void eliminar(int id);
}
