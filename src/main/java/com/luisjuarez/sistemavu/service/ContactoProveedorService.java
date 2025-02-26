package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.ContactoProveedor;
import java.util.List;

public interface ContactoProveedorService {
    void registrarContactoProveedor(ContactoProveedor contactoProveedor);
    ContactoProveedor buscarContactoProveedorPorId(int id);
    List<ContactoProveedor> buscarContactosPorPalabraClave(String palabraClave);
    List<ContactoProveedor> buscarContactosPorProveedorId(int proveedorId);
    List<ContactoProveedor> mostrarListaContactos();
    void modificarContactoProveedor(ContactoProveedor contactoProveedor);
    void eliminarContactoProveedor(int id);
}
