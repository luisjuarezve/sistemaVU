package com.luisjuarez.sistemavu.service;

import com.luisjuarez.sistemavu.model.Cliente;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;

public interface ClienteService {
    void registrarCliente(Cliente cliente);
    Cliente buscarClientePorDocumento(String tipo_doc, String nro_doc);
    Cliente buscarClientePorId(String clienteID);
    List<Cliente> buscarClientesPorPalabraClave(String palabraClave);
    List<Cliente> mostrarListaClientes();
    void modificarCliente(String clienteID, Cliente cliente);
    void eliminarCliente(String clienteID);
    void reporteClientesPDF(String destino) throws SQLException;
    void cargarTabla(JTable tabla) throws SQLException;
    void cargarTabla(JTable tabla, String palabraClave) throws SQLException;
}
