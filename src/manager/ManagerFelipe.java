package src.manager;

import src.DAOs.ClienteDAO;
import src.DAOs.DetallePedidoDAO;
import src.DAOs.MesaDAO;
import src.DAOs.MeseroDAO;
import src.DAOs.PedidoDAO;
import src.entities.Cliente;
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
    private ClienteDAO clienteDAO = new ClienteDAO();
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
                    System.out.println("  ID " + me.getIdo() + " - " + me.getNombre()
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
                    System.out.println("  ID " + me.getIdo() + " - " + me.getNombre());
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
     * Menú de pedidos de un mesero: crear pedido (mesa, cliente y detalle) o ver sus pedidos
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
                    System.out.println("  " + pe);
                    for (DetallePedido d : pe.getDetalles()) {
                        System.out.println("     - " + d);
                    }
                }
                break;
        }
    }

    /**
     * Crea un pedido: mesero (ya elegido), número de mesa, cliente y luego el detalle
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
        System.out.print("Cliente (contacto): ");
        String contacto = sc.nextLine();
        System.out.print("Cliente (ubicación): ");
        String ubicacion = sc.nextLine();
        Cliente cliente = new Cliente(contacto, ubicacion);

        Pedido pedido = mesero.crearPedido(mesa, cliente);
        if (pedido == null) {
            return;
        }
        clienteDAO.registra(cliente);
        pedidoDAO.registrarPedido(pedido);

        // Detalle del pedido: producto y cantidad
        String mas;
        do {
            System.out.print("ID Producto: ");
            int idProducto = sc.nextInt();
            System.out.print("Cantidad: ");
            int cantidad = sc.nextInt();
            sc.nextLine();
            try {
                DetallePedido detalle = new DetallePedido(idProducto, cantidad);
                pedido.agregarDetalle(detalle);
                detalleDAO.registrar(detalle);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.print("¿Agregar otro producto? (s/n): ");
            mas = sc.nextLine().trim();
        } while (mas.equalsIgnoreCase("s"));
        System.out.println("Pedido guardado: " + pedido);
    }

    public void crudPedido() {
        /**
         * fucionalida 3: crud pedido
         * Crea pedidos ligados a una mesa
         */
        System.out.println("FUNCIONALIDAD 3: CRUD PEDIDO");
        System.out.println("1.Crear Pedido | 2.Lista Pendientes | 3.Total Ventas");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch(op) {
            case 1:
                /**
                 * Pedido necesita (Mesa, estado, monto) según entities.Pedido
                 */
                System.out.print("Num mesa para pedido: ");
                Mesa mesa = mesaDAO.buscarMesa(sc.nextInt());
                if(mesa == null){ System.out.println("Mesa no existe"); return; }
              Pedido p = new Pedido("ped" + System.currentTimeMillis(), mesa.getNumMesa(), "Pendiente", 0.0);
                pedidoDAO.registrarPedido(p);
                System.out.println("Pedido creado en mesa " + mesa.getNumMesa());
                break;
            case 2:
                /**
                 * Filtra por estado usando buscarPorEstado("Pendiente")
                 */
                System.out.println("Pendientes: " + pedidoDAO.buscarPorEstado("Pendiente").size());
                break;
            case 3:
                /**
                 * Suma todos los montos con calcularTotalVentas()
                 */
                System.out.println("Total ventas: " + pedidoDAO.calcularTotalVentas());
                break;
        }
    }

    public void crudDetallePedido() {
        /**
         * Ffuncionalidad 4: crud detallepedido
         * Guarda qué producto y cuántos se pidieron
         */
        System.out.println(" FUNCIONALIDAD 4: CRUD DETALLE PEDIDO");
        System.out.print("ID Producto: ");
        int id = sc.nextInt();
        System.out.print("Cantidad: ");
        int cant = sc.nextInt();
       /**
        * El DAO espera getIdProducto() y getCantidad() que ya existen en la Entity
        */
        DetallePedido detalle = new DetallePedido(id, cant);
        detalleDAO.registrar(detalle);
        System.out.println("Detalle guardado");
    }
       /**
        *  Funcionalidad 5: crud cliente
        * Simulación simple de registro de cliente
        */ 
    public void crudCliente() {
        System.out.println("FUNCIONALIDAD 5: CRUD CLIENTE ");
        sc.nextLine();
        System.out.print("Nombre cliente: ");
        String nombre = sc.nextLine();
        System.out.println("Cliente " + nombre + " registrado");
    }

    public void mostrarMenuPrincipal() {
        /**
         * Menu primcipal
         * Muestra las 5 funcionalidades en bucle hasta que el usuario ponga 0
         */
        int op;
        do {
            System.out.println("  MI MANAGER - 5 FUNCIONALIDADES");
            System.out.println("1. MESA | 2. MESERO | 3. PEDIDO | 4. DETALLE | 5. CLIENTE | 0.Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();
            switch(op){
                case 1: crudMesa(); break;
                case 2: crudMesero(); break;
                case 3: crudPedido(); break;
                case 4: crudDetallePedido(); break;
                case 5: crudCliente(); break;
            }
        } while(op != 0);
    }
}
