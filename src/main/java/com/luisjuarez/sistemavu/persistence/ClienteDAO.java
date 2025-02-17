package com.luisjuarez.sistemavu.persistence;

import com.luisjuarez.sistemavu.model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void registrar(Cliente cliente);
    Cliente buscarPorDocumento(String tipo_doc, String nro_doc);
    Cliente buscarPorId(int id);
    List<Cliente> buscarPorPalabraClave(String palabraClave);
    List<Cliente> mostrarLista();
    void modificar(Cliente cliente);
    void eliminar(int id);
}
