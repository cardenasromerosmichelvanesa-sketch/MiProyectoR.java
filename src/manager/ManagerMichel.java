package src.manager;

import src.DAOs.ProductoDAO;
import src.DAOs.PagoDAO;
import src.entities.Producto;   

import java.util.List;

/**
 * Manager encargado de las 5 funcionalidades que se asignaron
 * 1. Registrar productos
 * 2. Modificar productos
 * 3. Consultar precios
 * 4. Controlar cantidades de productos
 * 5. Procesar pagos mediante la interfaz FormaPago
 * 
 * @author Michel Vanesa Romero
 */
public class ManagerMichel {

    
    /**
     * DAOs para gestionar la persistencia en memoria de productos y pagos
     */
    private ProductoDAO productoDAO = new ProductoDAO();
    private PagoDAO pagoDAO = new PagoDAO();
    
    /**
     * Scanner para leer las opciones del usuario por consola
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Funcionalidad 1: CRUD Producto
     * Permite registrar, listar, buscar y eliminar productos.
     */
    public void crudProducto() {
        System.out.println(" FUNCIONALIDAD 1: ");
        System.out.println("1. Registrar 2. Listar  3. Buscar  4. Eliminar");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch(op) {
            case 1:
                System.out.print("ID Producto: ");
                int id = sc.nextInt();
                sc.nextLine(); // limpiar buffer
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Precio: ");
                double precio = sc.nextDouble();
                System.out.print("Cantidad (Stock): ");
                int cantidad = sc.nextInt();
                
                productoDAO.crear(new Producto(id, nombre, precio, cantidad));
                System.out.println("¡Producto registrado con éxito!");
                break;
            case 2:
                System.out.println("Total de productos en inventario: " + productoDAO.obtenerTodos().size());
                for (Producto p : productoDAO.obtenerTodos()) {
                    System.out.println(p);
                }
                break;
            case 3:
                System.out.print("ID del producto a buscar: ");
                Producto p = productoDAO.obtenerPorId(sc.nextInt());
                System.out.println(p != null ? "Encontrado: " + p : "El producto no existe");
                break;
            case 4:
                System.out.print("ID del producto a eliminar: ");
                boolean eliminado = productoDAO.eliminar(sc.nextInt());
                System.out.println(eliminado ? "¡Producto eliminado!" : "No se encontró el ID");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    /**
     * Funcionalidad 2: Modificar producto
     * Actualiza los datos de un producto existente.
     */
    public void modificarProducto() {
        System.out.println(" FUNCIONALIDAD 2: MODIFICAR PRODUCTO");
        System.out.print("Ingrese el ID del producto a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nuevo Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Nuevo Precio: ");
        double precio = sc.nextDouble();
        System.out.print("Nueva Cantidad: ");
        int cantidad = sc.nextInt();
        
        Producto pMod = new Producto(id, nombre, precio, cantidad);
        boolean actualizado = productoDAO.actualizar(pMod);
        System.out.println(actualizado ? "¡Producto actualizado con éxito!" : "Error: Producto no encontrado.");
    }

    /**
     * Funcionalidad 3: Consultar precios
     * Muestra la lista detallada de precios y productos del menú.
     */
    public void consultarPrecios() {
        System.out.println(" FUNCIONALIDAD 3:");
        if(productoDAO.obtenerTodos().isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (Producto p : productoDAO.obtenerTodos()) {
                System.out.println(" - " + p.getNombre() + " | Precio: $" + p.getPrecio());
            }
        }
    }

    /**
     * Funcionalidad 4: Controlar cantidades 
     * Ajusta la cantidad disponible de un producto específico.
     */
    public void controlarCantidades() {
        System.out.println("FUNCIONALIDAD 4: CONTROLAR CANTIDADES ");
        System.out.print("Ingrese ID del producto: ");
        int id = sc.nextInt();
        Producto p = productoDAO.obtenerPorId(id);
        
        if (p != null) {
            System.out.print("Stock actual (" + p.getCantidad() + "). Ingrese la nueva cantidad: ");
            int nuevaCant = sc.nextInt();
            p.setCantidad(nuevaCant);
            productoDAO.actualizar(p);
            System.out.println("Stock actualizado correctamente");
        } else {
            System.out.println("Error: Producto no encontrado.");
        }
    }

    /**
     * Funcionalidad 5: Procesar pago
     * Permite elegir la forma de pago (Efectivo, Tarjeta o Transferencia) y registrar la transacción.
     */
    public void procesarPago() {
        System.out.println("FUNCIONALIDAD 5: PROCESAR PAGO");
        System.out.print("Ingrese ID del Pedido: ");
        int idPedido = sc.nextInt();
        System.out.print("Ingrese Monto a pagar: ");
        double monto = sc.nextDouble();
        
        System.out.println("Seleccione el método de pago:");
        System.out.println("1. Efectivo / 2. Tarjeta / 3. Transferencia");
        System.out.print("Opción: ");
        int tipo = sc.nextInt();
        
        FormaPago formaPago = null;
        String nombreMetodo = "";
        
        switch(tipo) {
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
                System.out.print("Ingrese número de cuenta / celular: ");
                String numCuenta = sc.nextLine();
                formaPago = new PagoTransferencia(numCuenta);
                nombreMetodo = "Transferencia";
                break;
            default:
                System.out.println("Método de pago inválido.");
                return;
        }
        
        if (formaPago != null) {
            boolean exito = formaPago.procesarPago(monto);
            if (exito) {
                pagoDAO.registrarPagoHistorico(idPedido, monto, nombreMetodo);
                System.out.println("¡Pago procesado y registrado con éxito en el sistema!");
            }
        }
    }

    /**
     * Menú principal del Manager de Michel
     * Muestra las 5 funcionalidades en bucle hasta que el usuario decida salir.
     */
    public void mostrarMenuPrincipal() {
        int op;
        do {
           
            System.out.println("    MÓDULO MICHEL  FUNCIONALIDADES     ");
          System.out.println("1. CRUD Productos");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Consultar Precios");
            System.out.println("4. Controlar Cantidades (Stock)");
            System.out.println("5. Procesar Pago");
            System.out.println("0. Volver al Menú Principal del Sistema");
            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();
            
            switch(op) {
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
                case 0: 
                    System.out.println("Regresando...");
                    break;
                default: 
                    System.out.println("Opción inválida.");
                    break;
            }
        } while(op != 0);
    }
}
