package com.luisjuarez.sistemavu;

import com.luisjuarez.sistemavu.persistence.impl.ProveedorDAOImplMariaDB;
import com.luisjuarez.sistemavu.service.ProveedorService;
import com.luisjuarez.sistemavu.service.impl.ProveedorServiceImpl;
import java.sql.SQLException;


public class SistemaVU {

    public static void main(String[] args) throws SQLException {
        ProveedorService cs = new ProveedorServiceImpl(new ProveedorDAOImplMariaDB());
        cs.reporteProveedorPDF("C:\\Users\\conta\\OneDrive\\Documentos\\prueba\\reportes.pdf");
    }
}
