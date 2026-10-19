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

public class MiManagerMesero { 
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
        System.out.println("1.Registrar | 2.Lista | 3.Buscar | 4.Liberar");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch(op) {
            case 1:
                /**
                 * Registra una nueva mesa, por defecto queda disponible = true
                 */
                System.out.print("Num mesa: ");
                int num = sc.nextInt();
                mesaDAO.registrarMesa(new Mesa(num));
                System.out.println("Mesa " + num + " registrada");
                break;
            case 2:
                /**
                 * muestra disponibilidad
                 */
                System.out.println("Total mesas: " + mesaDAO.ListaMesas().size());
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
        }
    }

    public void crudMesero() {
        /**
         * funcionalidad 2: crud mesero
         * Registra meseros y asigna mesas
         */
        System.out.println(" FUNCIONALIDAD 2: CRUD MESERO ");
        System.out.println("1.Registrar | 2.Lista Disponibles | 3.Asignar Mesa");
        System.out.print("Opcion: ");
        int op = sc.nextInt();
        switch(op) {
            case 1:
                /**
                 * Crea un mesero con disponibilidad true
                 */
                System.out.print("ID mesero: ");
                int idMesero = sc.nextInt();
                sc.nextLine();
                System.out.print("Nombre: ");
                String nomMesero = sc.nextLine();
                System.out.print("Apellido: ");
                String apeMesero = sc.nextLine();
                meseroDAO.registrarMesero(new Mesero(idMesero, nomMesero, apeMesero, true));
                System.out.println("Mesero registrado");
                break;
            case 2:
                /**
                 * Filtra solo los que tienen meseroDisponible == true
                 */
                System.out.println("Disponibles: " + meseroDAO.listaDisponibles().size());
                break;
            case 3:
                /**
                 * Busca una mesa y se la asigna al primer mesero libre
                 */
                System.out.print("Num mesa: ");
                Mesa mesa = mesaDAO.buscarMesa(sc.nextInt());
                if(mesa != null) meseroDAO.asignarMesa(mesa);
                else System.out.println("Mesa no existe");
                break;
        }
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
                Pedido p = new Pedido("ped" + System.nanoTime(), mesa.getNumMesa(), "Pendiente", 0.0);
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