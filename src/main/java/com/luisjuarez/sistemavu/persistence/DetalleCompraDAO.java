    package com.luisjuarez.sistemavu.persistence;

    import com.luisjuarez.sistemavu.model.DetalleCompra;
    import java.util.List;

    public interface DetalleCompraDAO {
        void registrar(DetalleCompra detalleCompra);
        List<DetalleCompra> buscarPorIdCompra(int idCompra);
        List<DetalleCompra> buscarPorProveedorId(int proveedorId);
        List<DetalleCompra> buscarPorProductoId(int productoId);
        List<DetalleCompra> mostrarLista();
        void modificar(DetalleCompra detalleCompra);
        void eliminar(int id);
    }
