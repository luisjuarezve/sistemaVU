    package com.luisjuarez.sistemavu.persistence;

    import com.luisjuarez.sistemavu.model.DetalleCompra;
    import java.util.List;

    public interface DetalleCompraDAO {
        void registrar(DetalleCompra detalleCompra);
        DetalleCompra buscarPorId(int id);
        List<DetalleCompra> buscarPorProveedorId(int proveedorId);
        List<DetalleCompra> buscarPorProductoId(int productoId);
        List<DetalleCompra> mostrarLista();
        void modificar(DetalleCompra detalleCompra);
        void eliminar(int id);
    }
