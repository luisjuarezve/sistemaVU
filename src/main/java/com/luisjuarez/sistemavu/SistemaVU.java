package com.luisjuarez.sistemavu;

import com.luisjuarez.sistemavu.persistence.impl.ClienteDAOImplMariaDB;
import com.luisjuarez.sistemavu.service.impl.ClienteServiceImpl;
import java.sql.SQLException;


public class SistemaVU {

    public static void main(String[] args) throws SQLException {
        ClienteServiceImpl clienteService = new ClienteServiceImpl(new ClienteDAOImplMariaDB());
        clienteService.reporteClientesPDF("C:\\Users\\conta\\OneDrive\\Documentos\\prueba\\reportes.pdf");
    }
}
