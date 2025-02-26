package com.luisjuarez.sistemavu.service.impl;

import com.luisjuarez.sistemavu.model.ContactoProveedor;
import com.luisjuarez.sistemavu.persistence.ContactoProveedorDAO;
import com.luisjuarez.sistemavu.service.ContactoProveedorService;

import java.util.List;

public class ContactoProveedorServiceImpl implements ContactoProveedorService {

    private ContactoProveedorDAO contactoProveedorDAO;

    public ContactoProveedorServiceImpl(ContactoProveedorDAO contactoProveedorDAO) {
        this.contactoProveedorDAO = contactoProveedorDAO;
    }

    @Override
    public void registrarContactoProveedor(ContactoProveedor contactoProveedor) {
        contactoProveedorDAO.registrar(contactoProveedor);
    }

    @Override
    public ContactoProveedor buscarContactoProveedorPorId(int id) {
        return contactoProveedorDAO.buscarPorId(id);
    }

    @Override
    public List<ContactoProveedor> buscarContactosPorPalabraClave(String palabraClave) {
        return contactoProveedorDAO.buscarPorPalabraClave(palabraClave);
    }

    @Override
    public List<ContactoProveedor> buscarContactosPorProveedorId(int proveedorId) {
        return contactoProveedorDAO.buscarPorProveedorId(proveedorId);
    }

    @Override
    public List<ContactoProveedor> mostrarListaContactos() {
        return contactoProveedorDAO.mostrarLista();
    }

    @Override
    public void modificarContactoProveedor(ContactoProveedor contactoProveedor) {
        contactoProveedorDAO.modificar(contactoProveedor);
    }

    @Override
    public void eliminarContactoProveedor(int id) {
        contactoProveedorDAO.eliminar(id);
    }
}
