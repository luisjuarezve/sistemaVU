package com.luisjuarez.sistemavu;

import com.luisjuarez.sistemavu.model.DetalleCompra;
import com.luisjuarez.sistemavu.persistence.impl.CompraDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.DetalleCompraDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.ProveedorDAOImplMariaDB;
import com.luisjuarez.sistemavu.service.CompraService;
import com.luisjuarez.sistemavu.service.DetalleCompraService;
import com.luisjuarez.sistemavu.service.ProveedorService;
import com.luisjuarez.sistemavu.service.impl.CompraServiceImpl;
import com.luisjuarez.sistemavu.service.impl.DetalleCompraServiceImpl;
import com.luisjuarez.sistemavu.service.impl.ProveedorServiceImpl;
import java.sql.SQLException;


public class SistemaVU {

    public static void main(String[] args) throws SQLException {
        CompraService cs = new CompraServiceImpl(new CompraDAOImplMariaDB());
        cs.reporteComprasPDF("C:\\Users\\conta\\OneDrive\\Documentos\\prueba\\reportes.pdf");
        DetalleCompraService dc = new DetalleCompraServiceImpl(new DetalleCompraDAOImplMariaDB());
        dc.reporteDetallesCompraPDF("C:\\Users\\conta\\OneDrive\\Documentos\\prueba\\reportes_compra.pdf", 1);
        
    }
}
