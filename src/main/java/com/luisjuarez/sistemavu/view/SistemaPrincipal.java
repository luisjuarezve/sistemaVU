package com.luisjuarez.sistemavu.view;

import com.luisjuarez.sistemavu.config.ConfigProperties;
import com.luisjuarez.sistemavu.model.Carrito;
import com.luisjuarez.sistemavu.model.Cliente;
import com.luisjuarez.sistemavu.model.Empleado;
import com.luisjuarez.sistemavu.persistence.impl.CategoriaDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.ClienteDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.CompraDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.DetalleCompraDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.DetalleFacturaDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.EmpleadoDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.FacturaDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.InventarioDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.ProductoDAOImplMariaDB;
import com.luisjuarez.sistemavu.persistence.impl.ProveedorDAOImplMariaDB;
import com.luisjuarez.sistemavu.service.CategoriaService;
import com.luisjuarez.sistemavu.service.ClienteService;
import com.luisjuarez.sistemavu.service.CompraService;
import com.luisjuarez.sistemavu.service.DetalleFacturaService;
import com.luisjuarez.sistemavu.service.EmpleadoService;
import com.luisjuarez.sistemavu.service.FacturaService;
import com.luisjuarez.sistemavu.service.InventarioService;
import com.luisjuarez.sistemavu.service.ProductoService;
import com.luisjuarez.sistemavu.service.ProveedorService;
import com.luisjuarez.sistemavu.service.impl.CategoriaServiceImpl;
import com.luisjuarez.sistemavu.service.impl.ClienteServiceImpl;
import com.luisjuarez.sistemavu.service.impl.CompraServiceImpl;
import com.luisjuarez.sistemavu.service.impl.DetalleFacturaServiceImpl;
import com.luisjuarez.sistemavu.service.impl.EmpleadoServiceImpl;
import com.luisjuarez.sistemavu.service.impl.FacturaServiceImpl;
import com.luisjuarez.sistemavu.service.impl.InventarioServiceImpl;
import com.luisjuarez.sistemavu.service.impl.ProductoServiceImpl;
import com.luisjuarez.sistemavu.service.impl.ProveedorServiceImpl;
import com.luisjuarez.sistemavu.view.paneles.Panel_Ayuda;
import com.luisjuarez.sistemavu.view.paneles.Panel_Categoria;
import com.luisjuarez.sistemavu.view.paneles.Panel_Cliente;
import com.luisjuarez.sistemavu.view.paneles.Panel_Configuracion;
import com.luisjuarez.sistemavu.view.paneles.Panel_Facturar;
import com.luisjuarez.sistemavu.view.paneles.Panel_Inventario;
import com.luisjuarez.sistemavu.view.paneles.Panel_Mantenimiento;
import com.luisjuarez.sistemavu.view.paneles.Panel_NotaEntrega;
import com.luisjuarez.sistemavu.view.paneles.Panel_Productos;
import com.luisjuarez.sistemavu.view.paneles.Panel_Proveedor;
import com.luisjuarez.sistemavu.view.paneles.Panel_Reportes;
import java.sql.Timestamp;

/**
 *
 * @author conta
 */
public class SistemaPrincipal extends javax.swing.JFrame {
    private ConfigProperties config = new ConfigProperties();
    private static Empleado empleado;
    private static Double tasa;
    private static Carrito carrito = new Carrito();
    
    private static Cliente cliente = new Cliente(
        1, // idCliente
        "V", // tipo_doc: 'V' para venezolano, 'E' para extranjero
        "12345678", // nro_doc
        "Luis", // nombre
        "Gómez", // apellido
        "04141234567", // telefono
        "Calle Principal #123, Ciudad", // direccion
        "lgomez@example.com", // correo_electronico
        new Timestamp(System.currentTimeMillis()), // fecha_registro
        "Cliente preferencial" // notas
    );

    public static InventarioService inventarioService = new InventarioServiceImpl(new InventarioDAOImplMariaDB());
    public static ClienteService clienteService = new ClienteServiceImpl(new ClienteDAOImplMariaDB());
    public static ProductoService productoService = new ProductoServiceImpl(new ProductoDAOImplMariaDB());
    public static ProveedorService proveedorService = new ProveedorServiceImpl(new ProveedorDAOImplMariaDB());
    public static CategoriaService categoriaService = new CategoriaServiceImpl(new CategoriaDAOImplMariaDB());
    public static EmpleadoService empleadoService = new EmpleadoServiceImpl(new EmpleadoDAOImplMariaDB());
    public static CompraService compraService = new CompraServiceImpl(new CompraDAOImplMariaDB());
    public static FacturaService facturaService = new FacturaServiceImpl(new FacturaDAOImplMariaDB());
    public static DetalleFacturaService detalleFacturaService = new DetalleFacturaServiceImpl(new DetalleFacturaDAOImplMariaDB());
    
    public static Empleado getEmpleado() {
        return empleado;
    }

    public static void setEmpleado(Empleado empleado) {
        SistemaPrincipal.empleado = empleado;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        SistemaPrincipal.cliente = cliente;
    }

    public static FacturaService getFacturaService() {
        return facturaService;
    }

    public static void setFacturaService(FacturaService facturaService) {
        SistemaPrincipal.facturaService = facturaService;
    }
    
    public static ClienteService getClienteService() {
        return clienteService;
    }

    public static void setClienteService(ClienteService clienteService) {
        SistemaPrincipal.clienteService = clienteService;
    }

    public static ProductoService getProductoService() {
        return productoService;
    }

    public static void setProductoService(ProductoService productoService) {
        SistemaPrincipal.productoService = productoService;
    }

    public static ProveedorService getProveedorService() {
        return proveedorService;
    }

    public static void setProveedorService(ProveedorService proveedorService) {
        SistemaPrincipal.proveedorService = proveedorService;
    }

    public static CategoriaService getCategoriaService() {
        return categoriaService;
    }

    public static void setCategoriaService(CategoriaService categoriaService) {
        SistemaPrincipal.categoriaService = categoriaService;
    }

    public static InventarioService getInventarioService() {
        return inventarioService;
    }

    public static void setInventarioService(InventarioService inventarioService) {
        SistemaPrincipal.inventarioService = inventarioService;
    }

    public static EmpleadoService getEmpleadoService() {
        return empleadoService;
    }

    public static void setEmpleadoService(EmpleadoService empleadoService) {
        SistemaPrincipal.empleadoService = empleadoService;
    }

    public static CompraService getCompraService() {
        return compraService;
    }

    public static void setCompraService(CompraService compraService) {
        SistemaPrincipal.compraService = compraService;
    }

    public static DetalleFacturaService getDetalleFacturaService() {
        return detalleFacturaService;
    }

    public static void setDetalleFacturaService(DetalleFacturaService detalleFacturaService) {
        SistemaPrincipal.detalleFacturaService = detalleFacturaService;
    }

    
    
    public static Double getTasa() {
        return tasa;
    }

    public static void setTasa(Double tasa) {
        SistemaPrincipal.tasa = tasa;
    }

    public static Carrito getCarrito() {
        return carrito;
    }

    public static void setCarrito(Carrito carrito) {
        SistemaPrincipal.carrito = carrito;
    }
    
    /**
     * Creates new form SistemaVU
     */
    public SistemaPrincipal(Empleado empleado) {
        initComponents();
        this.empleado = empleado;
        jLabel2.setText(empleado.getNombre() + (empleado.getApellido() != null ? " " + empleado.getApellido() : ""));
        tasa = Double.valueOf(config.getProperty("configuracion.tasa"));    
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        header = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        body = new javax.swing.JPanel();
        Menu = new javax.swing.JPanel();
        roundedPanel1 = new com.luisjuarez.sistemavu.view.components.RoundedPanel();
        btn_facturar = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_clientes = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_proveedor = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_categoria = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_producto = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_inventario = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_notaentrega = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_mantenimiento = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_reportes = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_configuracion = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_ayuda = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        btn_salir = new com.luisjuarez.sistemavu.view.components.RoundedButton();
        Seccion = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 650));

        header.setBackground(new java.awt.Color(0, 0, 102));
        header.setPreferredSize(new java.awt.Dimension(0, 50));
        header.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 7));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apps.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/apps_hover.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SistemaVU - ");
        jPanel1.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ADMIN");
        jPanel1.add(jLabel2);

        header.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 7));
        header.add(jPanel2, java.awt.BorderLayout.LINE_END);

        getContentPane().add(header, java.awt.BorderLayout.NORTH);

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setPreferredSize(new java.awt.Dimension(1024, 550));
        body.setLayout(new java.awt.BorderLayout());

        Menu.setBackground(new java.awt.Color(255, 255, 255));
        Menu.setPreferredSize(new java.awt.Dimension(180, 100));
        Menu.setLayout(new java.awt.GridBagLayout());

        roundedPanel1.setBackground(new java.awt.Color(255, 255, 255));
        roundedPanel1.setPreferredSize(new java.awt.Dimension(160, 550));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);
        roundedPanel1.setLayout(new java.awt.GridBagLayout());

        btn_facturar.setBackground(new java.awt.Color(153, 204, 255));
        btn_facturar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_facturar.setForeground(new java.awt.Color(255, 255, 255));
        btn_facturar.setText("Facturar");
        btn_facturar.setFocusable(false);
        btn_facturar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_facturar.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_facturar.setRoundBottomLeft(10);
        btn_facturar.setRoundBottomRight(10);
        btn_facturar.setRoundTopLeft(10);
        btn_facturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_facturarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_facturar, gridBagConstraints);

        btn_clientes.setBackground(new java.awt.Color(153, 204, 255));
        btn_clientes.setBorder(null);
        btn_clientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_clientes.setText("Clientes");
        btn_clientes.setFocusable(false);
        btn_clientes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_clientes.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_clientes.setRoundTopLeft(10);
        btn_clientes.setRoundTopRight(10);
        btn_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clientesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_clientes, gridBagConstraints);

        btn_proveedor.setBackground(new java.awt.Color(153, 204, 255));
        btn_proveedor.setBorder(null);
        btn_proveedor.setForeground(new java.awt.Color(255, 255, 255));
        btn_proveedor.setText("Proveedor");
        btn_proveedor.setFocusable(false);
        btn_proveedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_proveedor.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_proveedor.setRoundTopLeft(10);
        btn_proveedor.setRoundTopRight(10);
        btn_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_proveedorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_proveedor, gridBagConstraints);

        btn_categoria.setBackground(new java.awt.Color(153, 204, 255));
        btn_categoria.setBorder(null);
        btn_categoria.setForeground(new java.awt.Color(255, 255, 255));
        btn_categoria.setText("Categoría");
        btn_categoria.setFocusable(false);
        btn_categoria.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_categoria.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_categoria.setRoundTopLeft(10);
        btn_categoria.setRoundTopRight(10);
        btn_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_categoriaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_categoria, gridBagConstraints);

        btn_producto.setBackground(new java.awt.Color(153, 204, 255));
        btn_producto.setBorder(null);
        btn_producto.setForeground(new java.awt.Color(255, 255, 255));
        btn_producto.setText("Producto");
        btn_producto.setFocusable(false);
        btn_producto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_producto.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_producto.setRoundTopLeft(10);
        btn_producto.setRoundTopRight(10);
        btn_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_productoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_producto, gridBagConstraints);

        btn_inventario.setBackground(new java.awt.Color(153, 204, 255));
        btn_inventario.setBorder(null);
        btn_inventario.setForeground(new java.awt.Color(255, 255, 255));
        btn_inventario.setText("Inventario");
        btn_inventario.setFocusable(false);
        btn_inventario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_inventario.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_inventario.setRoundTopLeft(10);
        btn_inventario.setRoundTopRight(10);
        btn_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inventarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_inventario, gridBagConstraints);

        btn_notaentrega.setBackground(new java.awt.Color(153, 204, 255));
        btn_notaentrega.setBorder(null);
        btn_notaentrega.setForeground(new java.awt.Color(255, 255, 255));
        btn_notaentrega.setText("Nota de Entrega");
        btn_notaentrega.setFocusable(false);
        btn_notaentrega.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_notaentrega.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_notaentrega.setRoundTopLeft(10);
        btn_notaentrega.setRoundTopRight(10);
        btn_notaentrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_notaentregaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_notaentrega, gridBagConstraints);

        btn_mantenimiento.setBackground(new java.awt.Color(153, 204, 255));
        btn_mantenimiento.setBorder(null);
        btn_mantenimiento.setForeground(new java.awt.Color(255, 255, 255));
        btn_mantenimiento.setText("Mantenimiento");
        btn_mantenimiento.setFocusable(false);
        btn_mantenimiento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_mantenimiento.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_mantenimiento.setRoundTopLeft(10);
        btn_mantenimiento.setRoundTopRight(10);
        btn_mantenimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mantenimientoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_mantenimiento, gridBagConstraints);

        btn_reportes.setBackground(new java.awt.Color(153, 204, 255));
        btn_reportes.setBorder(null);
        btn_reportes.setForeground(new java.awt.Color(255, 255, 255));
        btn_reportes.setText("Reportes");
        btn_reportes.setFocusable(false);
        btn_reportes.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_reportes.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_reportes.setRoundTopLeft(10);
        btn_reportes.setRoundTopRight(10);
        btn_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_reportesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_reportes, gridBagConstraints);

        btn_configuracion.setBackground(new java.awt.Color(153, 204, 255));
        btn_configuracion.setBorder(null);
        btn_configuracion.setForeground(new java.awt.Color(255, 255, 255));
        btn_configuracion.setText("Configuración");
        btn_configuracion.setFocusable(false);
        btn_configuracion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_configuracion.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_configuracion.setRoundTopLeft(10);
        btn_configuracion.setRoundTopRight(10);
        btn_configuracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configuracionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_configuracion, gridBagConstraints);

        btn_ayuda.setBackground(new java.awt.Color(153, 204, 255));
        btn_ayuda.setBorder(null);
        btn_ayuda.setForeground(new java.awt.Color(255, 255, 255));
        btn_ayuda.setText("Ayuda");
        btn_ayuda.setFocusable(false);
        btn_ayuda.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_ayuda.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_ayuda.setRoundTopLeft(10);
        btn_ayuda.setRoundTopRight(10);
        btn_ayuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ayudaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_ayuda, gridBagConstraints);

        btn_salir.setBackground(new java.awt.Color(153, 204, 255));
        btn_salir.setBorder(null);
        btn_salir.setForeground(new java.awt.Color(255, 255, 255));
        btn_salir.setText("Salir");
        btn_salir.setFocusable(false);
        btn_salir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_salir.setPreferredSize(new java.awt.Dimension(150, 40));
        btn_salir.setRoundTopLeft(10);
        btn_salir.setRoundTopRight(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        roundedPanel1.add(btn_salir, gridBagConstraints);

        Menu.add(roundedPanel1, new java.awt.GridBagConstraints());

        body.add(Menu, java.awt.BorderLayout.LINE_START);

        Seccion.setBackground(new java.awt.Color(255, 255, 255));
        Seccion.setLayout(new java.awt.BorderLayout());
        body.add(Seccion, java.awt.BorderLayout.CENTER);

        getContentPane().add(body, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (Menu.isVisible()){
            Menu.setVisible(false);
        }else{
            Menu.setVisible(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clientesActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Cliente(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clientesActionPerformed

    private void btn_configuracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_configuracionActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Configuracion(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_configuracionActionPerformed

    private void btn_facturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_facturarActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Facturar(),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_facturarActionPerformed

    private void btn_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_proveedorActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Proveedor(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_proveedorActionPerformed

    private void btn_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_categoriaActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Categoria(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_categoriaActionPerformed

    private void btn_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_productoActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Productos(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_productoActionPerformed

    private void btn_inventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inventarioActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Inventario(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_inventarioActionPerformed

    private void btn_notaentregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_notaentregaActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_NotaEntrega(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint(); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_notaentregaActionPerformed

    private void btn_mantenimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mantenimientoActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Mantenimiento(Seccion.getSize(),Seccion),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint(); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_mantenimientoActionPerformed

    private void btn_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_reportesActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Reportes(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint(); 
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_reportesActionPerformed

    private void btn_ayudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ayudaActionPerformed
        Seccion.removeAll();
        Seccion.add(new Panel_Ayuda(Seccion.getSize()),new java.awt.BorderLayout().CENTER);
        Seccion.revalidate();
        Seccion.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ayudaActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Seccion;
    private javax.swing.JPanel body;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_ayuda;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_categoria;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_clientes;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_configuracion;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_facturar;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_inventario;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_mantenimiento;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_notaentrega;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_producto;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_proveedor;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_reportes;
    private com.luisjuarez.sistemavu.view.components.RoundedButton btn_salir;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.luisjuarez.sistemavu.view.components.RoundedPanel roundedPanel1;
    // End of variables declaration//GEN-END:variables
}
