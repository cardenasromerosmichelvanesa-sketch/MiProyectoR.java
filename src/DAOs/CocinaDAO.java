package src.DAOs;
import src.entities.Cocina;
import src.entities.Pedido;
import java.util.List;
public class CocinaDAO {

    /**
     * Metodo que muestra los pedidos que estan en espera en la cocina
     */
    public void verEnEspera(){
        for (Pedido p : Cocina.getEnEspera()) {
            System.out.println("Pedido en espera: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
        }
    }
    /**
     * Metodo que muestra los pedidos que estan preparandose en la cocina
     */
    public void verEnPreparacion(){
        for (Pedido p : Cocina.getEnPreparacion()) {
            System.out.println("Pedido en preparación: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
        }
    }
    /**
     * Metodo que muestra los pedidos que ya fueron entregados
     */
    public void verPedidosEntregados(){
        for (Pedido p : Cocina.getPedidosEntregados()) {
            System.out.println("Pedido entregado: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
        }
    }
    /**
     * Metodo que muestra los pedidos que fueron eliminados
     */
    public void verPedidosEliminados(){
        for (Pedido p : Cocina.getPedidosEliminados()) {
            System.out.println("Pedido eliminado: " + p.getId() + ", Mesa: " + p.getMesa() + ", Estado: " + p.getEstado());
        }
    }

    /**
     * Metodo que busca un pedido en las diferentes listas de la cocina
     * @param pedido Id del pedido que se busca
     */
    public static void buscarPedidoEnListas(Cocina cocina, Pedido pedido){
        if (buscarPedidoId(pedido, Cocina.getEnEspera())) {
            System.out.println("El pedido se encuentra en la lista de pedidos en espera");
        } else if (buscarPedidoId(pedido, Cocina.getEnPreparacion())) {
            System.out.println("El pedido se encuentra en la lista de pedidos en preparación");
        } else if (buscarPedidoId(pedido, Cocina.getPedidosEntregados())) {
            System.out.println("El pedido se encuentra en la lista de pedidos entregados");
        } else if (buscarPedidoId(pedido, Cocina.getPedidosEliminados())) {
            System.out.println("El pedido se encuentra en la lista de pedidos eliminados");
        } else {
            System.out.println("El pedido no se encuentra en ninguna lista");
            
        }
    }
    /**
     * Metodo que busca un pedido en una lista de pedidos
     * @param pedido Id del pedido que se busca
     * @param listaPedidos Lista de pedidos donde se busca el pedido
     * @return true si el pedido se encuentra en la lista, false si no se encuentra
     */
    public static boolean buscarPedidoId(Pedido pedido, List<Pedido> listaPedidos) {
        for (Pedido p : listaPedidos) {
            if (p.getId().equals(pedido.getId())) {
                System.out.print("Pedido ID:"+pedido+" en "+p.getEstado());
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que confirma si la cocina esta dispobible
     * @return Cocineros disponibles (true) - Cocineros no dispobibles (false)
     */
    public boolean disponibilidad(){
        if (Cocina.getCocinerosOcupados()<Cocina.getCocinerosDisponibles()) {
            return true;
        }
        return false;
    }

    /**
     * Metodo para comenzar a preparar el pedido
     * @param pedido Pedido que se va a cocinar
     */
    public void cocinarPedido(Pedido pedido) {
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
    public void entregarPedido(Pedido pedido) {
        if (pedido.getEstado().equals("En preparación")) {
            System.out.println("La cocina termino el pedido: "+pedido.getId()+" mesa: "+pedido.getMesa());
            pedido.setEstado("Listo");
            Cocina.setCocinerosOcupados(Cocina.getCocinerosOcupados()-1);
            Cocina.eliminarEnPreparacion(pedido);
            Cocina.añadirPedidosEntregados(pedido);
        }
        else{
            System.out.println("El pedido todavia no ha entrado en prepariación");
        }
    }

    /**
     * Metodo para cambiar el estado de pedido a cancelado
     * @param pedido Pedido que va cambiar el estado a cancelado
     */
    public void cancelarPedido(Pedido pedido) {
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
