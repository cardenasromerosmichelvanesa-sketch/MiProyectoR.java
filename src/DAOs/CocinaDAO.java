package src.DAOs;
import java.util.List;

import src.entities.Cocina;
import src.entities.Pedido;
public class CocinaDAO {

    public void setCocinerosDisponibles(int cocinerosDisponibles) {
        if (cocinerosDisponibles >= 0) {
            Cocina.setCocinerosDisponibles(cocinerosDisponibles);
            System.out.println("Número de cocineros disponibles actualizado a: " + cocinerosDisponibles);
        } else {
            System.out.println("El número de cocineros disponibles no puede ser negativo");
        }
    }

    public static List<Pedido> getPedidosEntregados() {
        return Cocina.getPedidosEntregados();
    }
    public static List<Pedido> getPedidosEliminados() {
        return Cocina.getPedidosEliminados();
    }
    public static List<Pedido> getPedidosEnEspera() {
        return Cocina.getEnEspera();
    }
    public static List<Pedido> getPedidosEnPreparacion() {
        return Cocina.getEnPreparacion();
    }

    /**
     * Metodo que muestra los pedidos que estan en espera en la cocina
     */
    public void verEnEspera(){
        int cont = 1;
        for (Pedido p : Cocina.getEnEspera()) {
            System.out.println(cont+".\t"+"ID: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
            cont++;
        }
    }
    /**
     * Metodo que muestra los pedidos que estan preparandose en la cocina
     */
    public void verEnPreparacion(){
        int cont = 1;
        for (Pedido p : Cocina.getEnPreparacion()) {
            System.out.println(cont+".\t"+"ID: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
            System.out.println("\t" + p.getDetalles().toString());
            cont++;
        }
    }
    /**
     * Metodo que muestra los pedidos que ya fueron entregados
     */
    public void verPedidosEntregados(){
        int cont = 1;
        for (Pedido p : Cocina.getPedidosEntregados()) {
            System.out.println(cont+".\t"+"ID: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
            cont++;
        }
    }
    /**
     * Metodo que muestra los pedidos que fueron eliminados
     */
    public void verPedidosEliminados(){
        int cont = 1;
        for (Pedido p : Cocina.getPedidosEliminados()) {
            System.out.println(cont+".\t"+"ID: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
            cont++;
        }
    }

    /**
     * Metodo que busca un pedido en las diferentes listas de la cocina
     * @param newID Id del pedido que se busca
     */
    public static void buscarPedidoEnListas(String newID){
        if (buscarPedidoId(newID, Cocina.getEnEspera())) {
            System.out.println("El pedido se encuentra en la lista de pedidos en espera");
        } else if (buscarPedidoId(newID, Cocina.getEnPreparacion())) {
            System.out.println("El pedido se encuentra en la lista de pedidos en preparación");
        } else if (buscarPedidoId(newID, Cocina.getPedidosEntregados())) {
            System.out.println("El pedido se encuentra en la lista de pedidos entregados");
        } else if (buscarPedidoId(newID, Cocina.getPedidosEliminados())) {
            System.out.println("El pedido se encuentra en la lista de pedidos eliminados");
        } else {
            System.out.println("El pedido no se encuentra en ninguna lista");
        }
    }
    /**
     * Metodo que busca un pedido en una lista de pedidos
     * @param newID Id del pedido que se busca
     * @param listaPedidos Lista de pedidos donde se busca el pedido
     * @return true si el pedido se encuentra en la lista, false si no se encuentra
     */
    public static boolean buscarPedidoId(String newID, List<Pedido> listaPedidos) {
        for (Pedido p : listaPedidos) {
            if (p.getId().equals(newID)) {
                System.out.print("Pedido ID:"+newID+" en "+p.getEstado());
                return true;
            }
        }
        return false;
    }

    public static Pedido getPedidoId(String id) {
        for (Pedido p : Cocina.getEnEspera()) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo que confirma si la cocina esta dispobible
     * @return Cocineros disponibles (true) - Cocineros no dispobibles (false)
     */
    public static boolean disponibilidad(){
        if (Cocina.getCocinerosOcupados()<Cocina.getCocinerosDisponibles()) {
            return true;
        }
        return false;
    }

    public static void recibirPedido(Pedido pedido) {
        if (pedido != null && pedido.getEstado().equals("Pendiente")) {
            Cocina.añadirPedidoEnEspera(pedido);
            System.out.println("Pedido recibido en la cocina: " + pedido.getId() + ", Mesa: " + pedido.getMesa());
        } else {
            System.out.println("El pedido no puede ser nulo");
        }
    }

    /**
     * Metodo para comenzar a preparar el pedido
     * @param pedido Pedido que se va a cocinar
     */
    public static void comenzarPreparacion(Pedido pedido) {
        if (disponibilidad() && pedido.getEstado().equals("Pendiente")) {
            System.out.println("La cocina comenzo con la preparación del pedido");
            pedido.setEstado("En preparación");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()+1);
            Cocina.eliminarPedidoEnEspera(pedido);
            Cocina.añadirEnPreparacion(pedido);
        }
        else {
            System.out.println("No hay cocineros disponibles");
        }
    }

    /**
     * Metodo para cambiar el estadod de pedido a listo
     * @param pedido Pedido que va cambiar el estado a listo
     */
    public static void entregarPedido(Pedido pedido) {
        if (pedido.getEstado().equals("En preparación")) {
            System.out.println("La cocina termino el pedido: "+pedido.getId()+" mesa: "+pedido.getMesa());
            pedido.setEstado("Listo");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()-1);
            Cocina.eliminarEnPreparacion(pedido);
            Cocina.añadirPedidosEntregados(pedido);
            Reporte.annadirReporte(pedido);
        }
        else{
            System.out.println("El pedido no esta en prepariación");
        }
    }

    /**
     * Metodo para cambiar el estado de pedido a cancelado
     * @param pedido Pedido que va cambiar el estado a cancelado
     */
    public static void cancelarPedido(Pedido pedido) {
        if (pedido.getEstado().equals("En preparación") || pedido.getEstado().equals("Pendiente")) {
            System.out.println("La cocina cancelo el pedido: "+pedido.getId()+" mesa: "+pedido.getMesa());
            pedido.setEstado("Cancelado");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()-1);
            if (pedido.getEstado().equals("Pendiente")) {
                Cocina.eliminarPedidoEnEspera(pedido);
            }
            else {
                Cocina.eliminarEnPreparacion(pedido);
            }
            Cocina.añadirPedidosEliminados(pedido);
        }
        else{
            System.out.println("El pedido todavia no ha entrado en cocina");
        }
    }
}
