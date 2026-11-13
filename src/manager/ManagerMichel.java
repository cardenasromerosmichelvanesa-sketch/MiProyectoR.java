package src.manager;

import src.DAOs.ProductoDAO;
import src.DAOs.PagoDAO;
import src.entities.Producto;

import src.vista.FormaPago;
import src.vista.PagoEfectivo;
import src.vista.PagoTarjeta;
import src.vista.PagoTransferencia;


import src.entities.Pagos;

import java.util.List;
import java.util.Scanner;

public class ManagerMichel {

    // DAO de productos y pagos
    private ProductoDAO productoDAO = new ProductoDAO();
    private PagoDAO pagoDAO = new PagoDAO();

    // Scanner compartido con el programa principal
    private Scanner sc;

    // Constructor
    public ManagerMichel(Scanner sc) {
        this.sc = sc;
    }

    // FUNCIONALIDAD 1: CRUD PRODUCTOS
    public void crudProducto() {

        System.out.println("\n===== GESTIÓN DE PRODUCTOS =====");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto");
        System.out.println("4. Eliminar producto");
        System.out.print("Seleccione una opción: ");

        int op = sc.nextInt();

        switch (op) {

            case 1:

                System.out.print("ID Producto: ");
                int id = sc.nextInt();

                if (productoDAO.obtenerPorId(id) != null) {
                    System.out.println("Ya existe un producto con ese ID.");
                    break;
                }

                sc.nextLine();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Precio: ");
                double precio = sc.nextDouble();

                System.out.print("Cantidad: ");
                int cantidad = sc.nextInt();

                if (precio < 0 || cantidad < 0) {
                    System.out.println("El precio y la cantidad no pueden ser negativos.");
                    break;
                }

                productoDAO.crear(
                    new Producto(id, nombre, precio, cantidad)
                );

                System.out.println("¡Producto registrado con éxito!");
                break;

            case 2:

                List<Producto> productos = productoDAO.obtenerTodos();

                if (productos.isEmpty()) {
                    System.out.println("No hay productos registrados.");
                } else {

                    System.out.println("\nPRODUCTOS REGISTRADOS");

                    for (Producto p : productos) {
                        System.out.println(p);
                    }
                }

                break;

            case 3:

                System.out.print("Ingrese ID del producto: ");
                int idBuscar = sc.nextInt();

                Producto p = productoDAO.obtenerPorId(idBuscar);

                if (p != null) {
                    System.out.println("Producto encontrado: " + p);
                } else {
                    System.out.println("El producto no existe.");
                }

                break;

            case 4:

                System.out.print("Ingrese ID del producto a eliminar: ");
                int idEliminar = sc.nextInt();

                boolean eliminado = productoDAO.eliminar(idEliminar);

                if (eliminado) {
                    System.out.println("Producto eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el producto.");
                }

                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    // FUNCIONALIDAD 2: MODIFICAR PRODUCTO
    public void modificarProducto() {

        System.out.println("\n===== MODIFICAR PRODUCTO =====");

        System.out.print("Ingrese el ID del producto: ");
        int id = sc.nextInt();

        Producto producto = productoDAO.obtenerPorId(id);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        sc.nextLine();

        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Nuevo precio: ");
        double precio = sc.nextDouble();

        System.out.print("Nueva cantidad: ");
        int cantidad = sc.nextInt();

        if (precio < 0 || cantidad < 0) {
            System.out.println("No se permiten valores negativos.");
            return;
        }

        Producto pModificado =
            new Producto(id, nombre, precio, cantidad);

        boolean actualizado = productoDAO.actualizar(pModificado);

        if (actualizado) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el producto.");
        }
    }

    // FUNCIONALIDAD 3: CONSULTAR PRECIOS
    public void consultarPrecios() {

        System.out.println("\n===== CONSULTAR PRECIOS =====");

        List<Producto> productos = productoDAO.obtenerTodos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto p : productos) {

            System.out.println(
                "Producto: " + p.getNombre()
                + " | Precio: $" + p.getPrecio()
            );
        }
    }

    // FUNCIONALIDAD 4: CONTROLAR CANTIDADES
    public void controlarCantidades() {

        System.out.println("\n===== CONTROLAR CANTIDADES =====");

        System.out.print("Ingrese ID del producto: ");
        int id = sc.nextInt();

        Producto p = productoDAO.obtenerPorId(id);

        if (p != null) {

            System.out.println("Producto: " + p.getNombre());

            System.out.println(
                "Stock actual: " + p.getCantidad()
            );

            System.out.print("Ingrese nueva cantidad: ");
            int nuevaCantidad = sc.nextInt();

            if (nuevaCantidad < 0) {
                System.out.println("La cantidad no puede ser negativa.");
                return;
            }

            p.setCantidad(nuevaCantidad);

            productoDAO.actualizar(p);

            System.out.println("Cantidad actualizada correctamente.");

        } else {

            System.out.println("Producto no encontrado.");
        }
    }

    // FUNCIONALIDAD 5: PROCESAR PAGO
    public void procesarPago() {

        System.out.println("\n===== PROCESAR PAGO =====");

        System.out.print("Ingrese ID del pedido: ");
        int idPedido = sc.nextInt();

        System.out.print("Ingrese monto a pagar: ");
        double monto = sc.nextDouble();

        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero.");
            return;
        }

        System.out.println("\nSeleccione el método de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta");
        System.out.println("3. Transferencia");

        System.out.print("Opción: ");
        int tipo = sc.nextInt();

        FormaPago formaPago = null;
        String nombreMetodo = "";

        switch (tipo) {

            case 1:

                formaPago = new PagoEfectivo();
                nombreMetodo = "Efectivo";
                break;

            case 2:

                System.out.print("Ingrese número de tarjeta: ");
                long numTarjeta = sc.nextLong();

                formaPago = new PagoTarjeta(numTarjeta);
                nombreMetodo = "Tarjeta";
                break;

            case 3:

                sc.nextLine();

                System.out.print("Ingrese número de cuenta o celular: ");
                String numCuenta = sc.nextLine();

                formaPago = new PagoTransferencia(numCuenta);
                nombreMetodo = "Transferencia";
                break;

            default:

                System.out.println("Método de pago inválido.");
                return;
        }

        // Procesar pago
        boolean exito = formaPago.procesarPago(monto);

        if (exito) {

            pagoDAO.registrarPagoHistorico(
                idPedido,
                monto,
                nombreMetodo
            );

            System.out.println("¡Pago procesado correctamente!");
            System.out.println("Pedido: " + idPedido);
            System.out.println("Monto: $" + monto);
            System.out.println("Método: " + nombreMetodo);

        } else {

            System.out.println("No fue posible procesar el pago.");
        }
    }

    // CONSULTAR HISTORIAL DE PAGOS
    public void consultarHistorialPagos() {

        System.out.println("\n===== HISTORIAL DE PAGOS =====");

        List<Pagos> pagos = pagoDAO.obtenerTodos();

        if (pagos.isEmpty()) {
            System.out.println("No hay pagos registrados.");
        } else {

            for (Pagos p : pagos) {
                System.out.println(p);
            }
        }
    }

    // MENÚ PRINCIPAL DE MICHEL
    public void mostrarMenuPrincipal() {

        int op;

        do {

            System.out.println("\n==================================");
            System.out.println("      MÓDULO DE MICHEL");
            System.out.println("==================================");

            System.out.println("1. Registrar y gestionar productos");
            System.out.println("2. Modificar producto");
            System.out.println("3. Consultar precios");
            System.out.println("4. Controlar cantidades");
            System.out.println("5. Procesar pago");
            System.out.println("6. Consultar historial de pagos");
            System.out.println("0. Volver al menú principal");

            System.out.print("Seleccione una opción: ");

            op = sc.nextInt();

            switch (op) {

                case 1:
                    crudProducto();
                    break;

                case 2:
                    modificarProducto();
                    break;

                case 3:
                    consultarPrecios();
                    break;

                case 4:
                    controlarCantidades();
                    break;

                case 5:
                    procesarPago();
                    break;

                case 6:
                    consultarHistorialPagos();
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (op != 0);
    }
}
