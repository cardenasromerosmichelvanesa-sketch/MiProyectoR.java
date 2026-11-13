package src.manager;

import src.DAOs.DetallePedidoDAO;
import src.DAOs.MesaDAO;
import src.DAOs.MeseroDAO;
import src.DAOs.PedidoDAO;
import src.entities.DetallePedido;
import src.entities.Mesa;
import src.entities.Mesero;
import src.entities.Pedido;

import java.util.Scanner;

public class ManagerFelipe { 
    /**
     * DAOs que simulan la base de datos en memoria
     */
    private MeseroDAO meseroDAO = new MeseroDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private DetallePedidoDAO detalleDAO = new DetallePedidoDAO();
    private MesaDAO mesaDAO = new MesaDAO();
    /**
     *  Scanner para leer lo que escribe el usuario
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * funcionalidad 1: crud mesa
     * Permite registrar, anotar la disponibilidad, buscar y liberar mesas
     */
    public void crudMesa() {
        System.out.println("FUNCIONALIDAD 1: CRUD MESA ");
        System.out.println("1.Registrar | 2.Lista (todas) | 3.Buscar | 4.Liberar | 5.Mesas disponibles");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch(op) {
            case 1:
                /**
                 * Registra una nueva mesa, por defecto queda disponible = true
                 */
                System.out.print("Num mesa: ");
                int num = sc.nextInt();
                System.out.print("Capacidad (cuántas personas caben): ");
                int capacidad = sc.nextInt();
        try {
               mesaDAO.registrarMesa(new Mesa(num, capacidad));
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
}
                break;
            case 2:
                /**
                 * muestra disponibilidad
                 */
                System.out.println("Total mesas: " + mesaDAO.ListaMesas().size());
                for (Mesa mm : mesaDAO.ListaMesas()) {
                    System.out.println("  " + mm);
                }
                break;
            case 3:
                /**
                 * Busca una mesa por número para saber si existe
                 */
                System.out.print("Num mesa a buscar: ");
                Mesa m = mesaDAO.buscarMesa(sc.nextInt());
                System.out.println(m != null ? "Mesa encontrada: " + m.getNumMesa() : "No existe");
                break;
            case 4:
                /**
                 * Libera una mesa: disponible=true y mesero=null
                 */
                System.out.print("Num mesa a liberar: ");
                Mesa ml = mesaDAO.buscarMesa(sc.nextInt());
                if(ml != null) ml.liberarMesa();
                else System.out.println("No existe");
                break;
            case 5:
                mostrarMesasDisponibles();
                break;
        }
    }

    /**
     * Muestra solo las mesas disponibles (las asignadas a un mesero no aparecen)
     * @return true si hay al menos una mesa disponible
     */
    private boolean mostrarMesasDisponibles() {
        java.util.List<Mesa> libres = mesaDAO.MesasDisponibles();
        if (libres.isEmpty()) {
            System.out.println("No hay mesas disponibles");
            return false;
        }
        System.out.println("Mesas disponibles:");
        for (Mesa mm : libres) {
            System.out.println("  " + mm);
        }
        return true;
    }

    public void crudMesero() {
        /**
         * funcionalidad 2: crud mesero
         * Registra meseros y asigna mesas
         */
        System.out.println(" FUNCIONALIDAD 2: CRUD MESERO ");
        System.out.println("1.Registrar | 2.Lista Disponibles | 3.Asignar Mesa | 4.Pedidos del mesero");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch(op) {
            case 1:           
               System.out.print("ID: ");
               int idMesero = sc.nextInt();
               sc.nextLine();
               System.out.print("Nombre: ");
               String nombreM = sc.nextLine();
               System.out.print("Apellido: ");
               String apellidoM = sc.nextLine();
               meseroDAO.registrarMesero(new Mesero(idMesero, nombreM, apellidoM, true));
               break;
            case 2:
                /**
                 * Filtra solo los que tienen meseroDisponible == true
                 */
                System.out.println("Disponibles: " + meseroDAO.listaDisponibles().size());
                break;
            case 3:
                /**
                 * Muestra las mesas disponibles, deja elegir mesa y mesero,
                 * y al asignarla la mesa deja de estar disponible.
                 */
                if (meseroDAO.listaMeseros().isEmpty()) {
                    System.out.println("Primero registra un mesero");
                    break;
                }
                if (!mostrarMesasDisponibles()) {
                    break;
                }
                System.out.print("Num mesa a asignar: ");
                Mesa mesa = mesaDAO.buscarMesa(sc.nextInt());
                if (mesa == null) {
                    System.out.println("Mesa no existe");
                    break;
                }
                System.out.println("Meseros disponibles:");
                for (Mesero me : meseroDAO.listaDisponibles()) {
                    System.out.println("  ID " + me.getId() + " - " + me.getNombre()
                            + " (mesas: " + me.getMesasAsignadas().size() + ")");
                }
                System.out.print("ID del mesero: ");
                Mesero elegido = meseroDAO.buscarMesero(sc.nextInt());
                if (elegido == null) {
                    System.out.println("Mesero no existe");
                    break;
                }
                meseroDAO.asignarMesa(elegido, mesa);
                break;
            case 4:
                /**
                 * Pide el ID del mesero y abre su menú de pedidos
                 */
                if (meseroDAO.listaMeseros().isEmpty()) {
                    System.out.println("Primero registra un mesero");
                    break;
                }
                System.out.println("Meseros:");
                for (Mesero me : meseroDAO.listaMeseros()) {
                    System.out.println("  ID " + me.getId() + " - " + me.getNombre());
                }
                System.out.print("ID del mesero: ");
                Mesero atiende = meseroDAO.buscarMesero(sc.nextInt());
                if (atiende == null) {
                    System.out.println("Mesero no existe");
                    break;
                }
                menuPedidosMesero(atiende);
                break;
        }
    }

    /**
     * Menú de pedidos de un mesero: crear pedido (mesa y detalle) o ver sus pedidos
     * @param mesero mesero que atiende
     */
    private void menuPedidosMesero(Mesero mesero) {
        System.out.println("PEDIDOS DE " + mesero.getNombre());
        System.out.println("1.Crear pedido | 2.Ver mis pedidos");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                crearPedidoDeMesero(mesero);
                break;
            case 2:
                java.util.List<Pedido> mios = pedidoDAO.buscarPorMesero(mesero);
                if (mios.isEmpty()) {
                    System.out.println("Este mesero no tiene pedidos");
                }
                for (Pedido pe : mios) {
                    mostrarDetallePedido(pe);
                    System.out.println();
                }
                break;
        }
    }

    /**
     * Crea un pedido: mesero (ya elegido), número de mesa y luego el detalle
     * (producto y cantidad, tantas veces como se quiera).
     * @param mesero mesero que hace el pedido
     */
    private void crearPedidoDeMesero(Mesero mesero) {
        java.util.List<Mesa> suyas = mesero.getMesasAsignadas();
        if (suyas.isEmpty()) {
            System.out.println("Este mesero no tiene mesas asignadas. Asígnale una primero.");
            return;
        }
        System.out.println("Mesas del mesero:");
        for (Mesa mm : suyas) {
            System.out.println("  " + mm);
        }
        System.out.print("Num mesa: ");
        Mesa mesa = mesaDAO.buscarMesa(sc.nextInt());
        sc.nextLine();
        if (mesa == null) {
            System.out.println("Mesa no existe");
            return;
        }
        Pedido pedido = mesero.crearPedido(mesa);
        if (pedido == null) {
            return;
        }
        pedidoDAO.registrarPedido(pedido);

        // Detalle del pedido: producto, precio y cantidad
        agregarProductosAPedido(pedido);
        System.out.println("Pedido guardado:");
        mostrarDetallePedido(pedido);
    }

    /**
     * Pide producto, precio y cantidad, y los agrega al pedido (las veces que se quiera).
     * @param pedido pedido al que se agregan los productos
     */
    private void agregarProductosAPedido(Pedido pedido) {
        String mas;
        do {
            System.out.print("ID Producto: ");
            int idProducto = sc.nextInt();
            sc.nextLine();
            System.out.print("Nombre del producto: ");
            String nombre = sc.nextLine();
            System.out.print("Precio: ");
            double precio = sc.nextDouble();
            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();
            try {
                DetallePedido detalle = new DetallePedido(idProducto, nombre, precio, cantidad);
                pedido.agregarDetalle(detalle);
                detalleDAO.registrar(detalle);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("¿Agregar otro producto? (s/n): ");
            mas = sc.nextLine().trim();
        } while (mas.equalsIgnoreCase("s"));
    }

    public void crudPedido() {
        /**
         * funcionalidad 3: pedidos
         * Muestra solo los pedidos pendientes
         */
        System.out.println("FUNCIONALIDAD 3: PEDIDOS PENDIENTES");
        java.util.List<Pedido> pendientes = pedidoDAO.buscarPorEstado("Pendiente");
        if (pendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes");
            return;
        }
        for (Pedido pe : pendientes) {
            System.out.println("  " + pe);
        }
    }

    public void crudDetallePedido() {
        /**
         * funcionalidad 4: detalle de pedido
         * Pide el id del pedido y muestra mesero, productos, precios y estado
         */
        System.out.println(" FUNCIONALIDAD 4: DETALLE DE PEDIDO");
        System.out.println("1.Ver detalle de un pedido | 2.Agregar productos a un pedido");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        sc.nextLine();
        if (op != 1 && op != 2) {
            return;
        }
        if (pedidoDAO.listaPedidos().isEmpty()) {
            System.out.println("No hay pedidos registrados");
            return;
        }
        System.out.print("ID del pedido: ");
        Pedido pedido = pedidoDAO.buscarPorId(sc.nextLine().trim());
        if (pedido == null) {
            System.out.println("El pedido no existe");
            return;
        }
        if (op == 2) {
            if (!"Pendiente".equalsIgnoreCase(pedido.getEstado())) {
                System.out.println("Solo se pueden agregar productos a pedidos pendientes");
                return;
            }
            agregarProductosAPedido(pedido);
        }
        mostrarDetallePedido(pedido);
    }

    /**
     * Muestra el pedido con su mesero, estado, productos, precios y total
     * @param pedido pedido a mostrar
     */
    private void mostrarDetallePedido(Pedido pedido) {
        System.out.println("Pedido " + pedido.getId() + " | Mesa " + pedido.getMesa());
        System.out.println("Mesero: " + (pedido.getMesero() != null
                ? pedido.getMesero().getNombre() + " " + pedido.getMesero().getApellido() : "sin mesero"));
        System.out.println("Estado: " + pedido.getEstado());
        if (pedido.getDetalles().isEmpty()) {
            System.out.println("Sin productos");
        }
        for (DetallePedido d : pedido.getDetalles()) {
            System.out.println("  - " + d.getNombreProducto() + " (ID " + d.getIdProducto() + ")"
                    + " | precio: $" + d.getPrecio() + " | cantidad: " + d.getCantidad()
                    + " | subtotal: $" + d.getSubtotal());
        }
        System.out.println("Total: $" + pedido.getMonto());
    }

    public void mostrarMenuPrincipal() {
        /**
         * Menu primcipal
         * Muestra las 4 funcionalidades en bucle hasta que el usuario ponga 0
         */
        int op;
        do {
            System.out.println("  MI MANAGER - 4 FUNCIONALIDADES");
            System.out.println("1. MESA | 2. MESERO | 3. PEDIDO | 4. DETALLE | 0.Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();
            switch(op){
                case 1: crudMesa(); break;
                case 2: crudMesero(); break;
                case 3: crudPedido(); break;
                case 4: crudDetallePedido(); break;
            }
        } while(op != 0);
    }
}
